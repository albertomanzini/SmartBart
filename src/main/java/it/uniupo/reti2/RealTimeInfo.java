package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RealTimeInfo {

    @SerializedName(value = "station")
    private ArrayList<RealTimeInfoStation> station;

    public ArrayList<RealTimeInfoStation> getStation() {
        return station;
    }
}
