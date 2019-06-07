package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Train {

    @SerializedName(value = "@trainId")
    private String trainId;

    @SerializedName(value = "@trainIdx")
    int trainIdx;

    @SerializedName(value = "index")
    int index;

    @SerializedName(value = "stop")
    ArrayList<Stop> stops;

    public String getTrainId() {
        return this.trainId;
    }
}
