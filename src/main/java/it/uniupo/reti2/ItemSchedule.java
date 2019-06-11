package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

public class ItemSchedule {

    @SerializedName(value = "@line")
    private String line;
    @SerializedName(value = "@trainHeadStation")
    private String headStation;
    @SerializedName(value = "@origTime")
    private String originTime;
    @SerializedName(value = "@destTime")
    private String destTime;
    @SerializedName(value = "@trainIdx")
    private String trainIdx;
    @SerializedName(value = "@bikeflag")
    private String bikeFlag;
    @SerializedName(value = "@trainId")
    private String trainId;
    @SerializedName(value = "@load")
    private String load;
    private String stationDep;

    public void setStationDep() {
        if (line.equals("ROUTE 2") || line.equals("ROUTE 8")) {
            this.stationDep = "Millbrae";
        }
        else if(line.equals("ROUTE 1")) {
            this.stationDep = "Pittsburg";
        }
        else if(line.equals("ROUTE 7")) {
            this.stationDep = "Richmond";
        }
    }
}
