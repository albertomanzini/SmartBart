package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;



public class Stations {

    @SerializedName(value = "station")
    private Station station;

    public Station getStation() {
        return this.station;
    }
}
