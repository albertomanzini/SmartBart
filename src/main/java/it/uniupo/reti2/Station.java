package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Station {

    private String name;
    private String abbr;
    private String gtfs_latitude;
    private String address;
    private String city;
    private String state;
    private int zipcode;

    public String getStnName() {
        return this.name;
    }
    public String getAbbr() {
        return this.abbr;
    }
    public String getGtfs_latitude() {
        return this.gtfs_latitude;
    }
    public String getAddress() {
        return this.address;
    }
    public String getCity() {
        return this.city;
    }
    public String getState() {
        return this.state;
    }
    public int getZipcode() {
        return this.zipcode;
    }





}
