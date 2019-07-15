package it.uniupo.reti2.restAPI;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RoutesInfo {

    @SerializedName(value = "route")
    private ArrayList<InfoRoute> route;

    public ArrayList<InfoRoute> getRoute() {
        return this.route;
    }
}
