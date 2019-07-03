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
    private String delay;

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

    public void setTrainId() {
        String[] temp=originTime.split(":");
        String[] temp1=temp[1].split(" ");
        if(headStation.equals("ANTC")) {
            this.trainId="1111"+temp[0]+temp1[0];
        } else if (headStation.equals("MLBR")) {
            this.trainId="2222"+temp[0]+temp1[0];
        } else if (headStation.equals("RICH")) {
            this.trainId="3333"+temp[0]+temp1[0];
        } else {
            this.trainId = "4444" + temp[0] + temp1[0];
        }
    }

    public void setDelay(String delay) {
        this.delay=delay;
    }
}
