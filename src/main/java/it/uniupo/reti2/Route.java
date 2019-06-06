package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Route {

    @SerializedName(value = "train")
    private ArrayList<Train> trains;

    public ArrayList getTrains() {
        return this.trains;
    }
}
