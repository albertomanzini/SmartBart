package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

public class RootRoutes {

    @SerializedName(value = "uri")
    private transient String uri;
    @SerializedName(value = "date")
    private String date;
    @SerializedName(value = "sched_num")
    private int scheNum;

    @SerializedName(value = "route")
    private Route route;

    public Route getRoute() {
        return this.route;
    }

    public int getScheNum() {
        return this.scheNum;
    }
}
