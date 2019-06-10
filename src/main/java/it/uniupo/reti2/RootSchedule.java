package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

public class RootSchedule {

    @SerializedName(value = "date")
    private String date;

    @SerializedName(value = "sched_num")
    private String sched_num;

    @SerializedName(value = "station")
    private StationSchedule stationSchedule;

    public String getDate() {
        return this.date;
    }

    public String getSched_num() {
        return this.sched_num;
    }

    public StationSchedule getStationSchedule() {
        return this.stationSchedule;
    }
}
