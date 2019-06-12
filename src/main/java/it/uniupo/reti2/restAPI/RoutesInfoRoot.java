package it.uniupo.reti2.restAPI;

import com.google.gson.annotations.SerializedName;

public class RoutesInfoRoot {

    @SerializedName(value = "routes")
    private RoutesInfo routes;

    public RoutesInfo getRoutes() {
        return this.routes;
    }
}
