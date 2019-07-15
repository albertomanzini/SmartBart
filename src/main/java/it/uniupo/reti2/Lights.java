package it.uniupo.reti2;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class Lights {

    private String lightsURL;
    private RestTemplate rest;

    public void Lights() {

    }

    public void lightsOn(String[] args) throws InterruptedException {

        String baseURL = "http://localhost:8000";
        String username = "newdeveloper";

        lightsURL = baseURL + "/api/" + username + "/lights/";
        rest = new RestTemplate();

        String getLucebri = "http://localhost:8000/lights/1";

        int brightness = 25;

        String onLightsNew = "{ \"on\" : true,\"hue\" : 25500, \"bri\" : " + brightness + "}";
        String offLights = "{ \"on\" : false}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // create the HTTP request
        HttpEntity<String> onRequestNew = new HttpEntity<>(onLightsNew, headers);
        HttpEntity<String> offRequest = new HttpEntity<>(offLights, headers);

        changeStatus("/api/newdeveloper/lights/1", onRequestNew);
        changeStatus("/api/newdeveloper/lights/2", onRequestNew);
        changeStatus("/api/newdeveloper/lights/3", onRequestNew);

    }

    private void changeStatus(String lightId, HttpEntity request) {
        String callURL = lightsURL + lightId + "/state";
        rest.put(callURL, request);
    }
}
