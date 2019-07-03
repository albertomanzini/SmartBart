package it.uniupo.reti2;

import it.uniupo.reti2.restAPI.RouteInfo;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gateway {

    private RootStation station;
    private RootRoute route;
    private ColmSchedule schedule;
    private RouteInfo routeInfo = new RouteInfo();
    private RootRealTimeInfo realTimeInfo;

    public Gateway() {

        String date=null;

        String bartRouteURL = "http://api.bart.gov/api/sched.aspx?cmd=routesched&route=7&key=QVM6-525T-955T-DWE9&time=00:00+am&json=y";
        String bartStnInfoURL = "http://api.bart.gov/api/stn.aspx?cmd=stninfo&orig=colm&json=y&key=QVM6-525T-955T-DWE9";
        String bartStnSchedule = "http://api.bart.gov/api/sched.aspx?cmd=stnsched&orig=colm"+date+"&key=MW9S-E7SL-26DU-VV8V&l=1&json=y";
        String bartRealTimeInfo = "http://api.bart.gov/api/etd.aspx?cmd=etd&orig=colm&key=MW9S-E7SL-26DU-VV8V&json=y";

        RestTemplate bartRest = new RestTemplate();
        station = bartRest.getForObject(bartStnInfoURL, RootStation.class);

        route = bartRest.getForObject(bartRouteURL, RootRoute.class);

        schedule = bartRest.getForObject(bartStnSchedule, ColmSchedule.class);

        realTimeInfo = bartRest.getForObject(bartRealTimeInfo, RootRealTimeInfo.class);

    }

    public String getStnName() {
        return this.station.getRoot().getStations().getStation().getStnName();
    }

    public String getStnAddress() {
        return this.station.getRoot().getStations().getStation().getAddress();
    }

    public String getStnCity() {
        return this.station.getRoot().getStations().getStation().getCity();
    }
    public String getStnState() {
        return this.station.getRoot().getStations().getStation().getState();
    }
    public int getStnZipcode() {
        return this.station.getRoot().getStations().getStation().getZipcode();
    }
    public RootRoute getRoute() {
        return this.route;
    }
    public RootSchedule getStationSchedule() {
        return this.schedule.getRootSchedule();
    }

    public RouteInfo getRouteInfo() {
        return routeInfo;
    }

    public RootRealTimeInfo getRealTimeInfo() {
        return realTimeInfo;
    }
}
