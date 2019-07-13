package it.uniupo.reti2.restAPI;

import com.google.gson.Gson;
import it.uniupo.reti2.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import spark.Filter;
import spark.Spark;

import java.util.*;

import static spark.Spark.*;

public class GatewayAPI {

    public static void main(String[] args) {
        // init

        Gson gson = new Gson();
        String baseURL = "gateway/api/";
        Gateway gatewayDao = new Gateway();
        BartDao bartDao = new BartDao();
        ArrayList<TrainSeats> trains = new ArrayList<>();

        // get station info
        get(baseURL + "/stninfo", "application/json", (request, response) -> {

            // set a proper response code and type
            response.type("application/json");
            response.status(200);

            String nameStn = gatewayDao.getStnName();
            String addressStn = gatewayDao.getStnAddress();
            String cityStn = gatewayDao.getStnCity();
            String stateStn = gatewayDao.getStnState();
            int zipcodeStn = gatewayDao.getStnZipcode();

            Integer zipcode = (Integer) zipcodeStn;

            // prepare the JSON-related structure to return
            Map<String, String> finalJson = new HashMap<>();
            finalJson.put("name", nameStn);
            finalJson.put("address", addressStn);
            finalJson.put("state", stateStn);
            finalJson.put("city", cityStn);
            finalJson.put("zipcode", zipcode.toString());

            return finalJson;
        }, gson::toJson);

        get(baseURL + "/stnschedule", "application/json", (request, response) -> {
            // set a proper response code and type
            response.type("application/json");
            response.status(200);

            Map<String, Object> finalJson = new HashMap<>();

            gatewayDao.getStationSchedule().setNewDate();

            Iterator<ItemSchedule> iterator = gatewayDao.getStationSchedule().getStationSchedule().getItemSchedule().iterator();

            while (iterator.hasNext()) {
                iterator.next().setStationArr();
            }

            Iterator<ItemSchedule> iter = gatewayDao.getStationSchedule().getStationSchedule().getItemSchedule().iterator();


            while (iter.hasNext()) {
                iter.next().setStationDep();
            }

            Iterator<ItemSchedule> iteratorPrice = gatewayDao.getStationSchedule().getStationSchedule().getItemSchedule().iterator();

            while (iteratorPrice.hasNext()) {
                iteratorPrice.next().setPrice();
            }

            Iterator<ItemSchedule> iteratorPriceDouble = gatewayDao.getStationSchedule().getStationSchedule().getItemSchedule().iterator();

            while (iteratorPriceDouble.hasNext()) {
                iteratorPriceDouble.next().setPriceDouble();
            }

            // prepare the JSON-related structure to return
            finalJson.put("newdate", gatewayDao.getStationSchedule().getNewDate());
            finalJson.put("date", gatewayDao.getStationSchedule().getDate());
            finalJson.put("name", gatewayDao.getStationSchedule().getStationSchedule().getName());
            finalJson.put("station", gatewayDao.getStationSchedule().getStationSchedule().getItemSchedule());
            //finalJson.put("nameDep",  name);
           // finalJson.put("nameArr", )

            return finalJson;
        }, gson::toJson);

        get(baseURL + "/stnrealtime", "application/json", (request, response) -> {
            // set a proper response code and type
            response.type("application/json");
            response.status(200);

            Map<String, Object> finalJson = new HashMap<>();

            Gateway gatewayReal = new Gateway();

            try {
                Iterator<Etd> iter1 = gatewayReal.getRealTimeInfo().getRoot().getStation().get(0).getEtd().iterator();
                while (iter1.hasNext()) {
                    iter1.next().setDest();
                }
            } catch (Exception e) {
                System.out.println("There are no trains currently");
            }

            try {
                Iterator<ItemSchedule> iter0 = gatewayReal.getStationSchedule().getStationSchedule().getItemSchedule().iterator();
                while (iter0.hasNext()) {
                    iter0.next().setTrainId();
                }
            } catch (Exception e) {
                System.out.println("There are no trains currently");
                finalJson.put("error", "There are no trains currently");
            }

            String[] timeArray = gatewayReal.getRealTimeInfo().getRoot().getTime().split(":");

            int timeMin = Integer.parseInt(timeArray[1]);
            int timeHour = Integer.parseInt(timeArray[0]);

            String[] timeTemp = timeArray[2].split(" ");

            String timeAMPM=timeTemp[1];

            try {
                //sortTrains(gatewayTemp.getRealTimeInfo().getRoot().getStation());
                Iterator<Etd> iter = gatewayReal.getRealTimeInfo().getRoot().getStation().get(0).getEtd().iterator();
                while (iter.hasNext()) {
                    Iterator<Estimate> iterator = iter.next().getEstimate().iterator();
                    while (iterator.hasNext()) {
                        iterator.next().setTrainIdTime(timeMin, timeHour, timeAMPM);
                    }
                }
                finalJson.put("departures", gatewayReal.getRealTimeInfo().getRoot().getStation().get(0).getEtd());
            } catch (Exception e) {
                System.out.println("There are no trains currently");
                finalJson.put("error", "There are no trains currently");
            }


            //sortTrains(gatewayTemp.getRealTimeInfo().getRoot().getStation().get(0).getEtd());

            gatewayDao.getStationSchedule().setNewDate();

            finalJson.put("date", gatewayDao.getStationSchedule().getDate());
            finalJson.put("newdate", gatewayDao.getStationSchedule().getNewDate());
            finalJson.put("time", gatewayReal.getRealTimeInfo().getRoot().getTime());
            finalJson.put("station", gatewayReal.getStationSchedule().getStationSchedule().getItemSchedule());
            finalJson.put("timeAMPM", timeAMPM);

            return finalJson;
        }, gson::toJson);

        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        post(baseURL+"/buy", (req, res) -> {
            Map addRequest = gson.fromJson(req.body(), Map.class);

            TrainCapacity trainCapacity = new TrainCapacity(0, 0);

            if (addRequest != null && addRequest.containsKey("CF") && addRequest.containsKey("bike")) {
                String cf = String.valueOf(addRequest.get("CF"));
                int bike = Integer.parseInt((String) addRequest.get("bike"));
                String trainId = String.valueOf(addRequest.get("trainId"));
                String date = String.valueOf(addRequest.get("date"));
                int i=55;
                Passenger seats = new Passenger(cf, bike, trainId, date);

                Iterator<TrainSeats> iterator = trains.iterator();
                if(trains.isEmpty()) {
                    TrainSeats trainTemp = new TrainSeats(trainId, date);
                    trainCapacity=trainTemp.bookingSeat(bike);
                    trains.add(trainTemp);

                }
                else {
                    while (iterator.hasNext()) {
                        TrainSeats trainTemp=iterator.next();
                        if(trainTemp.getTrainId().equals(trainId) && trainTemp.getDate().equals(date)) {
                            trainCapacity=trainTemp.bookingSeat(bike);
                        }
                        else {
                            trainTemp = new TrainSeats(trainId, date);
                            trainCapacity=trainTemp.bookingSeat(bike);
                            trains.add(trainTemp);
                            break;
                        }
                    }
                }
                lightInit(trainCapacity);



                bartDao.addBooking(seats);
                System.out.println(i);

                res.status(201);
            } else {
                halt(403);
            }

            return "";
        });

        get(baseURL + "/change/:date/:station", "application/json", (request, response) -> {

            // get the id from the URL
            String date = request.params(":date");
            String station = request.params(":station");

            String newDate[] = date.split("-");

            Gateway gatewayBooking = new Gateway(newDate[1] + "/" + newDate[2] + "/" + newDate[0]);

            Map<String, Object> finalJson = new HashMap<>();


            Iterator<ItemSchedule> iterator = gatewayBooking.getStationSchedule().getStationSchedule().getItemSchedule().iterator();

            while (iterator.hasNext()) {
                iterator.next().setStationArr();
            }

            Iterator<ItemSchedule> iter = gatewayBooking.getStationSchedule().getStationSchedule().getItemSchedule().iterator();


            while (iter.hasNext()) {
                iter.next().setStationDep();
            }

            if (station.equals("none")) {

            } else {
                Iterator<ItemSchedule> iterator2 = gatewayBooking.getStationSchedule().getStationSchedule().getItemSchedule().iterator();

                while (iterator2.hasNext()) {
                    if (iterator2.next().getAbbr().equals(station)) {
                    } else {
                        iterator2.remove();
                    }
                }
            }

            Iterator<ItemSchedule> iterator3 = gatewayBooking.getStationSchedule().getStationSchedule().getItemSchedule().iterator();

            while (iterator3.hasNext()) {
                iterator3.next().setBikeFlag();
            }

            Iterator<ItemSchedule> iteratorPrice = gatewayBooking.getStationSchedule().getStationSchedule().getItemSchedule().iterator();

            while (iteratorPrice.hasNext()) {
                iteratorPrice.next().setPrice();
            }

            Iterator<ItemSchedule> iteratorPriceDouble = gatewayBooking.getStationSchedule().getStationSchedule().getItemSchedule().iterator();

            while (iteratorPriceDouble.hasNext()) {
                iteratorPriceDouble.next().setPriceDouble();
            }

            gatewayDao.getStationSchedule().setNewDate();

            // prepare the JSON-related structure to return
            finalJson.put("date", gatewayBooking.getStationSchedule().getDate());
            finalJson.put("name", gatewayBooking.getStationSchedule().getStationSchedule().getName());
            finalJson.put("station", gatewayBooking.getStationSchedule().getStationSchedule().getItemSchedule());
            finalJson.put("newdate", gatewayDao.getStationSchedule().getNewDate());

            //if(task==null)
            //   halt(404);

            // prepare the JSON-related structure to return
            // and the suitable HTTP response code and type
            response.type("application/json");

            return finalJson;
        }, gson::toJson);
    }

    public static void lightInit(TrainCapacity i) {

        String lightsURL;
        RestTemplate rest=new RestTemplate();;

        String baseURL = "http://localhost:8000";
        String username = "newdeveloper";

        lightsURL = baseURL + "/api/" + username + "/lights/";

        String getLucebri = "http://localhost:8000/lights/1";

        int brightness =i.getCapacity();

        String onLightsNew = "{ \"on\" : true,\"hue\" : 25500, \"bri\" : " + brightness + "}";
        String fullByke = "{ \"on\" : true, \"hue\" : 65535, \"bri\" : 200}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // create the HTTP request
        HttpEntity<String> onRequestNew = new HttpEntity<>(onLightsNew, headers);
        HttpEntity<String> fullBykeReq = new HttpEntity<>(fullByke, headers);
        switch (i.getReturnValue()) {
            case 1:
                changeStatus("/api/newdeveloper/lights/1", onRequestNew, lightsURL, rest);
                break;
                //changeStatus("/api/newdeveloper/lights/2", onRequestNew, lightsURL, rest);
                //changeStatus("/api/newdeveloper/lights/3", onRequestNew, lightsURL, rest);
            case 2:
                changeStatus("/api/newdeveloper/lights/1", fullBykeReq, lightsURL, rest);
                break;
                //changeStatus("/api/newdeveloper/lights/2", fullBykeReq, lightsURL, rest);
               // changeStatus("/api/newdeveloper/lights/3", fullBykeReq, lightsURL, rest);

            default:
                break;
        }




    }
    private static void changeStatus(String lightId, HttpEntity request, String lightsURL, RestTemplate rest) {
        String callURL = lightsURL + lightId + "/state";
        rest.put(callURL, request);
    }
}