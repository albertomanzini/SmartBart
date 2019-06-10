package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StationSchedule {

    @SerializedName(value = "name")
    private String names;
    @SerializedName(value = "abbr")
    private String abbr;

    @SerializedName(value = "item")
    private ArrayList<ItemSchedule> itemSchedule;

    public String getName() {
        return this.names;
    }

    public String getAbbr() {
        return this.abbr;
    }

    public ArrayList<ItemSchedule> getItemSchedule() {
        return this.itemSchedule;
    }
}
