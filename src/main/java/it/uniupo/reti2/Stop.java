package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

public class Stop {

    @SerializedName(value = "@station")
    String station;
    @SerializedName(value = "@load")
    int load;
    @SerializedName(value = "@level")
    String level;
    @SerializedName(value = "@bikeflag")
    int bikeflag;
}
