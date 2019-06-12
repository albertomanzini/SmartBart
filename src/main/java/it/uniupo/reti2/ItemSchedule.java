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
    private String stationArr;

    public void setStationArr() {
        if (line.equals("ROUTE 2") || line.equals("ROUTE 8")) {
            stationArr = "Millbrae";
        }
        else if(line.equals("ROUTE 1")) {
            stationArr = "Antioch";
        }
        else if(line.equals("ROUTE 7")) {
            stationArr = "Richmond";
        }
    }

    public void setStationDep() {
        if (headStation.equals("ANTC")) {
            headStation = "Antioch";
        }
        else if(headStation.equals("RICH")) {
            headStation = "Richmond";
        }
        else if(headStation.equals("MLBR")) {
            headStation = "Millbrae";
        }
        else if(headStation.equals("SFIA")) {
            headStation = "San Francisco International Airport";
        }
    }
}
