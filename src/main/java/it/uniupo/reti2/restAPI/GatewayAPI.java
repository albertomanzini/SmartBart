package it.uniupo.reti2.restAPI;

import com.google.gson.Gson;
import it.uniupo.reti2.*;
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
            finalJson.put("date", gatewayDao.getStationSchedule().getDate());
            finalJson.put("newdate", gatewayDao.getStationSchedule().getNewDate());
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

            try {
                //sortTrains(gatewayTemp.getRealTimeInfo().getRoot().getStation());
                Iterator<Etd> iter = gatewayReal.getRealTimeInfo().getRoot().getStation().get(0).getEtd().iterator();
                while (iter.hasNext()) {
                    Iterator<Estimate> iterator = iter.next().getEstimate().iterator();
                    while (iterator.hasNext()) {
                        iterator.next().setTrainIdTime(timeMin, timeHour);
                    }
                }
                finalJson.put("departures", gatewayReal.getRealTimeInfo().getRoot().getStation().get(0).getEtd());
            } catch (Exception e) {
                System.out.println("There are no trains currently");
                finalJson.put("error", "There are no trains currently");
            }


            //sortTrains(gatewayTemp.getRealTimeInfo().getRoot().getStation().get(0).getEtd());

            gatewayDao.getStationSchedule().setNewDate();

            finalJson.put("newdate", gatewayDao.getStationSchedule().getNewDate());
            finalJson.put("time", gatewayReal.getRealTimeInfo().getRoot().getTime());
            finalJson.put("station", gatewayReal.getStationSchedule().getStationSchedule().getItemSchedule());

            return finalJson;
        }, gson::toJson);

        post(baseURL+"/buy", (req, res) -> {
            Map addRequest = gson.fromJson(req.body(), Map.class);

            if (addRequest != null && addRequest.containsKey("CF") && addRequest.containsKey("bike")) {
                String cf = String.valueOf(addRequest.get("CF"));
                int bike = Integer.parseInt((String) addRequest.get("bike"));
                String trainId = String.valueOf(addRequest.get("trainId"));

                Passenger seats = new Passenger(cf, bike, trainId);

                System.out.println(trainId);
                bartDao.addBooking(seats);


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

            // prepare the JSON-related structure to return
            finalJson.put("date", gatewayBooking.getStationSchedule().getDate());
            finalJson.put("name", gatewayBooking.getStationSchedule().getStationSchedule().getName());
            finalJson.put("station", gatewayBooking.getStationSchedule().getStationSchedule().getItemSchedule());

            //if(task==null)
            //   halt(404);

            // prepare the JSON-related structure to return
            // and the suitable HTTP response code and type
            response.type("application/json");

            return finalJson;
        }, gson::toJson);
    }
}
    /*
    private static void sortTrains(ArrayList<Etd> station) {

        ArrayList<Estimate> trains=null;

        String firstHour=null;

        Iterator<Etd> iterator = station.iterator();
        while (iterator.hasNext()) {
            Iterator<Estimate> iter = iterator.next().getEstimate().iterator();
            while (iter.hasNext()) {
                if(firstHour.isEmpty()) {
                    firstHour = iter.next().getTimeDep();
                }
                else {
                    trains.add(iter.next());
                }

            }
        }
    }*
}

        // add a new task
        post(baseURL + "/changedate", (request, response) -> {
            // get the body of the HTTP request
            Map addRequest = gson.fromJson(request.body(), Map.class);

            // check whether everything is in place
            if(addRequest!=null && addRequest.containsKey("date") && addRequest.containsKey("station")) {
                String date = String.valueOf(addRequest.get("date"));
                // gson convert JSON num in double, but we need an int
                //int urgent = ((Double) addRequest.get("urgent")).intValue();

                // add the task into the DB
                gatewayDao.addTask(new Task(description, urgent));

                // if success, prepare a suitable HTTP response code
                response.status(201);
            }
            else {
                halt(403);
            }

            return "";
        });


    }

}
/*
package it.uniupo.reti2;

        import static spark.Spark.*;

        import com.google.gson.Gson;

        import java.util.List;
        import java.util.Map;

        import static spark.Spark.*;

public class TaskService {

    public static void main(String[] args) {

        gatewayDao gatewayDao = new gatewayDao();
        Gson gson = new gGson();

        //get all tasks
        get(path:"/api/v1/tasks", acceptType: /application/json(req, res) -> {

            //set proper response code and type
            res.type(contentType:"application/json");
            res.status(statusCode: 200);

            //get all tasks from DB
            List<gatewayDao> allTasks = gatewayDao.getAllTasks();

            //prepare json
            Map<String, List<Task>> finalJson = new HashMap<>();
            finalJson.put("tasks", allTasks);

            return finalJson;
        }, gson::toJson);

        //get a single task
        get(path:"/api/v1/tasks/:id", acceptType: /application/json(req, res) -> {
            Task task = gatewayDao.getTask(Integer.valueOf(req.params(":id")));

            if (task==null)
                halt(404);

            res.type(contentType:"application/json");
            res.status(statusCode: 200);
            Map<String, Task> finalJson = new HashMap<>();
            finalJson.put("tasks", tasks);

            return finalJson;
        }, gson::toJson);

        post("api/v1/tasks", (req, res) -> {
            Map addRequest = gson.fromJson(req.body(), Map.class);

            if(addRequest!=null && addRequest.containsKey("description") && addRequest.containsKey("urgent")) {
                String description = String.valueOf(addRequest.get("description"));
                int urgent = ((Double)addRequest.get("urgent")).intValue();

                gatewayDao.addTask(new Task(description, urgent));

                res.status(201);
            }
            else {
                halt(403);
            }

            return "";
        })

    }

}


 */
