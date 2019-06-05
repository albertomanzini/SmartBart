package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Stations {

    private Map<String, String> station = new HashMap<>();

    public Map getStation() {
        return this.station;
    }
}
