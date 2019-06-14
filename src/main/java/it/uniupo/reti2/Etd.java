package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;

public class Etd {
    private int counter=0;
    @SerializedName(value = "destination")
    private String destination;

    @SerializedName(value = "estimate")
    public static ArrayList<Estimate> estimate;

    public void setId() {

        Iterator<Estimate> iter = estimate.iterator();

        int counter=0;

        while (iter.hasNext()) {
            iter.next().trainId = counter++;
        }
        return;
    }

    public String getDestination() {
        return destination;
    }

    public ArrayList<Estimate> getEstimate() {
        return estimate;
    }
}
