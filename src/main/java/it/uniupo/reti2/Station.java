package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Station {

    @SerializedName(value = "name")
    private String name;

    private String abbr;

    private String gtfs_latitude;

    public String getStnName() {
        return this.name;
    }

    public String getAbbr() {
        return this.abbr;
    }

    public String getGtfs_latitude() {
        return this.gtfs_latitude;
    }





}
