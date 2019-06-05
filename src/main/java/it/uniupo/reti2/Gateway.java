package it.uniupo.reti2;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Gateway {

    public static void main(String[] args) {

        String bartURL = "http://api.bart.gov/api/stn.aspx?cmd=stninfo&orig=colm&json=y&key=QVM6-525T-955T-DWE9";
        RestTemplate bartRest = new RestTemplate();
        //RootStation station = bartRest.getForObject(bartURL, RootStation.class);

        Map<String, Root> station = bartRest.getForObject(bartURL, Map.class);

        System.out.println(station.get("root"));
    }
}
