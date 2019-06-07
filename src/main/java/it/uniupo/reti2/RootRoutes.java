package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

public class RootRoutes {

    @SerializedName(value = "route")
    private Route route;

    public Route getRoute() {
        return this.route;
    }
}
