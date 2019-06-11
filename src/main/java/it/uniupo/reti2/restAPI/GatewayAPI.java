package it.uniupo.reti2.restAPI;

import com.google.gson.Gson;
import it.uniupo.reti2.*;

import java.lang.reflect.Array;
import java.util.*;

import static spark.Spark.get;
import static spark.Spark.halt;

public class GatewayAPI {


    public static void main(String[] args) {
        // init
        Gson gson = new Gson();
        String baseURL = "gateway/api/";
        Gateway gatewayDao = new Gateway();

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

            ArrayList<Train> nameStop = gatewayDao.getRoute().getRoot().getRoute().getTrains();

            String name = nameStop.get(0).getStop().get(0).getStation();

            Iterator<ItemSchedule> iterator = gatewayDao.getStationSchedule().getStationSchedule().getItemSchedule().iterator();
            int i=0;

            while (iterator.hasNext()) {
                iterator.next().setStationDep();
            }


            // prepare the JSON-related structure to return
            Map<String, Object> finalJson = new HashMap<>();
            finalJson.put("date", gatewayDao.getStationSchedule().getDate());
            finalJson.put("name", gatewayDao.getStationSchedule().getStationSchedule().getName());
            finalJson.put("station", gatewayDao.getStationSchedule().getStationSchedule().getItemSchedule());
            finalJson.put("nameDep", name);

            return finalJson;
        }, gson::toJson);
    }
}


        /*get a single task
        get(baseURL + "/tasks/:id", "application/json", (request, response) -> {
            // get the id from the URL
            ShutdownHooks.Task task = gatewayDao.getTask(Integer.valueOf(request.params(":id")));

            // no task? 404!
            if(task==null)
                halt(404);

            // prepare the JSON-related structure to return
            // and the suitable HTTP response code and type
            Map<String,?> finalJson = new HashMap<>();
            finalJson.put("task", task);
            response.status(200);
            response.type("application/json");

            return finalJson;
        }, gson::toJson);

        // add a new task
        post(baseURL + "/tasks", (request, response) -> {
            // get the body of the HTTP request
            Map addRequest = gson.fromJson(request.body(), Map.class);

            // check whether everything is in place
            if(addRequest!=null && addRequest.containsKey("description") && addRequest.containsKey("urgent")) {
                String description = String.valueOf(addRequest.get("description"));
                // gson convert JSON num in double, but we need an int
                int urgent = ((Double) addRequest.get("urgent")).intValue();

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
