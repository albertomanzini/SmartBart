package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Train {

    @SerializedName(value = "@trainId")
    private String trainId;

    @SerializedName(value = "@trainIdx")
    private int trainIdx;

    @SerializedName(value = "index")
    private int index;

    @SerializedName(value = "stop")
    private transient Stop[] stops;

    public String getTrainId() {
        return this.trainId;
    }
}
