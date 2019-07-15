package it.uniupo.reti2.restAPI;

import com.google.gson.annotations.SerializedName;

public class InfoRoute {

    @SerializedName(value = "name")
    private String name;
    @SerializedName(value = "abbr")
    private String abbr;
    @SerializedName(value = "routeID")
    private String routeID;

    public String getName() {
        return name;
    }

    public String getAbbr() {
        return abbr;
    }

    public String getRouteID() {
        return routeID;
    }
}

