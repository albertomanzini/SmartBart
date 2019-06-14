package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;

public class RealTimeInfoStation {

    @SerializedName(value = "name")
    private String name;

    @SerializedName(value = "etd")
    private ArrayList<Etd> etd;

    public String getName() {
        return name;
    }

    public ArrayList<Etd> getEtd() {
        return etd;
    }
}
