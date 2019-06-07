package it.uniupo.reti2;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Gateway {

    private RootStation station;
    private RootRoute route;

    public Gateway() {

        String bartRouteURL = "http://api.bart.gov/api/sched.aspx?cmd=routesched&route=1&key=QVM6-525T-955T-DWE9&time=00:00+am&json=y";
        String bartStnInfoURL = "http://api.bart.gov/api/stn.aspx?cmd=stninfo&orig=colm&json=y&key=QVM6-525T-955T-DWE9";
        RestTemplate bartRest = new RestTemplate();
        station = bartRest.getForObject(bartStnInfoURL, RootStation.class);

        route = bartRest. getForObject(bartRouteURL, RootRoute.class);

    }

    public String getNameStn() {
        return this.station.getRoot().getStations().getStation().getStnName();
    }
}
