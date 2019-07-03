package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

public class RootSchedule {

    @SerializedName(value = "date")
    private String date;

    @SerializedName(value = "sched_num")
    private String sched_num;

    private String newDate;

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

    public void setNewDate() {

        String temp[] = this.date.split("/");

        String day = temp[1];
        String month = temp[0];

        if(Integer.parseInt(temp[1])<10) {
            day="0"+temp[1];
        }
        if(Integer.parseInt(temp[0])<10) {
            month="0"+temp[0];
        }
        this.newDate=month+"-"+day;

    }

    public String getNewDate() {
        return this.newDate;
    }
}
