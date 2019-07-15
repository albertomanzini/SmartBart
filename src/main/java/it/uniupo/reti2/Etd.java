package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;

public class Etd {

    @SerializedName(value = "destination")
    private String destination;

    @SerializedName(value = "abbreviation")
    private String abbr;

    @SerializedName(value = "estimate")
    private ArrayList<Estimate> estimate;

    public void setDest() {
        Iterator<Estimate> iter = estimate.iterator();
        while (iter.hasNext()) {
            iter.next().setAbbr(this.abbr);
        }
    }

    /*public void setId() {

private int counter=0;
        Iterator<Estimate> iter = estimate.iterator();

        while (iter.hasNext()) {
            iter.next().trainId = counter++;
        }
        return;
    }*/

    public String getAbbr() {
        return abbr;
    }

    public String getDestination() {
        return destination;
    }

    public ArrayList<Estimate> getEstimate() {
        return estimate;
    }
}
