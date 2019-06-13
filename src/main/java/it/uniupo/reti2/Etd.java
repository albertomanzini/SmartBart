package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Etd {

    @SerializedName(value = "destination")
    private String destination;

    @SerializedName(value = "estimate")
    private ArrayList<Estimate> estimate;

    public String getDestination() {
        return destination;
    }

    public ArrayList<Estimate> getEstimate() {
        return estimate;
    }
}
