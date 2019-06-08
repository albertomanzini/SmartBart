package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

public class Stop {

    @SerializedName(value = "@station")
    private String station;
    @SerializedName(value = "@load")
    private int load;
    @SerializedName(value = "@level")
    private String level;
    @SerializedName(value = "@origTime")
    private String originTime;
    @SerializedName(value = "@bikeflag")
    private int bikeflag;
}
