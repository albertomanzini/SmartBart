package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Root {

    @SerializedName(value = "@id")
    private int id;

    private String uri =new String();

    @SerializedName(value = "stations")
    private Map<String, Stations> stations = new HashMap<>();

    public Map<String, Stations> getStations() {
        return this.stations;
    }

    public int getId() {
        return this.id;
    }
}
