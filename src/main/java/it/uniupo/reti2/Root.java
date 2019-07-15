package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Root {

    @SerializedName(value = "@id")
    private int id;

    private transient String uri =new String();

    @SerializedName(value = "stations")
    private Stations stations;

    public Stations getStations() {
        return this.stations;
    }

    public int getId() {
        return this.id;
    }
}
