package it.uniupo.reti2.restAPI;

import org.springframework.web.client.RestTemplate;

public class RouteInfo {

    private RootRoutesInfo routes;

    public RouteInfo() {

        String url = "http://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&json=y";

        RestTemplate bartRest = new RestTemplate();

        routes = bartRest.getForObject(url, RootRoutesInfo.class);
    }

    public RootRoutesInfo getRoutes() {
        return routes;
    }
}
