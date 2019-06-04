package it.uniupo.reti2;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Gateway {

    public static void main(String[] args) {

        String bartURL = "http://api.bart.gov/api/stn.aspx?cmd=stninfo&orig=colm&json=y&key=QVM6-525T-955T-DWE9";
        RestTemplate bartRest = new RestTemplate();
        Map<String, Map<String , ?>> station = bartRest.getForObject(bartURL, HashMap.class);

        for(String s:station.keySet()) {
            System.out.println(s);
        }
    }
}
