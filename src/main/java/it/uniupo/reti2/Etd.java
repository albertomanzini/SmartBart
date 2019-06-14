package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;

public class Etd {

    @SerializedName(value = "destination")
    private String destination;

    @SerializedName(value = "estimate")
    private ArrayList<Estimate> estimate;

    public void setId() {
        Iterator<Estimate> iter = estimate.iterator();
        int counter=0;

        while (iter.hasNext()) {
            iter.next().id = counter++;
        }
    }

    public String getDestination() {
        return destination;
    }

    public ArrayList<Estimate> getEstimate() {
        return estimate;
    }
}
