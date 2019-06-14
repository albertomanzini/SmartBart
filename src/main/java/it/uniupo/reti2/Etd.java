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

    public static int setId(int counter) {
        Iterator<Estimate> iter = estimate.iterator();


        while (iter.hasNext()) {
            iter.next().id = counter++;
        }
        return counter;
    }

    public String getDestination() {
        return destination;
    }

    public ArrayList<Estimate> getEstimate() {
        return estimate;
    }
}
