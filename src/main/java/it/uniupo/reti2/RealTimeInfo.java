package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RealTimeInfo {

    @SerializedName(value = "time")
    private String time;

    @SerializedName(value = "station")
    private ArrayList<RealTimeInfoStation> station;

    public String getTime() {
        return time;
    }

    public ArrayList<RealTimeInfoStation> getStation() {
        return station;
    }
}
