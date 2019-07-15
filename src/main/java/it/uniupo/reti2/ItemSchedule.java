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

    @SerializedName(value = "@price")
    private String price;

    @SerializedName(value = "@priceDouble")
    private String priceDouble;

    private String abbr = null;

    public void setStationArr() {
        if (line.equals("ROUTE 2") || line.equals("ROUTE 8")) {
            stationArr = "Millbrae";
        } else if (line.equals("ROUTE 1")) {
            stationArr = "Antioch";
        } else if (line.equals("ROUTE 7")) {
            stationArr = "Richmond";
        }
    }

    public void setStationDep() {
        if (headStation.equals("ANTC")) {
            headStation = "Antioch";
            this.abbr = "antc";
        } else if (headStation.equals("RICH")) {
            headStation = "Richmond";
            this.abbr = "rich";
        } else if (headStation.equals("MLBR")) {
            headStation = "Millbrae";
            this.abbr = "mlbr";
        } else if (headStation.equals("SFIA")) {
            headStation = "San Francisco International Airport";
            this.abbr = "sfia";
        } else if (headStation.equals("PITT")) {
            headStation = "Pittsburg/Bay Point";
            this.abbr = "pitt";

        }
    }

    public void setTrainId() {
        String[] temp = originTime.split(":");
        String[] temp1 = temp[1].split(" ");
        if (headStation.equals("ANTC")) {
            this.trainId = "1111" + temp[0] + temp1[0];
        } else if (headStation.equals("MLBR")) {
            this.trainId = "2222" + temp[0] + temp1[0];
        } else if (headStation.equals("RICH")) {
            this.trainId = "3333" + temp[0] + temp1[0];
        } else if (headStation.equals("SFIA")) {
            this.trainId = "4444" + temp[0] + temp1[0];
        } else {
            this.trainId = "5555" + temp[0] + temp1[0];
        }
    }

    public void setBikeFlag() {
        if (this.bikeFlag.equals("0")) {
            this.bikeFlag = "No";
        } else if (this.bikeFlag.equals("1")) {
            this.bikeFlag = "Yes";
        } else {
            this.bikeFlag = "ERROR";
        }
    }

    public void setPrice() {
        if (headStation.equals("Antioch")) {
            this.price = "8.45$";
        } else if (headStation.equals("Millbrae")) {
            this.price = "4.35$";
        } else if (headStation.equals("Richmond")) {
            this.price = "5.75$";
        } else if (headStation.equals("San Francisco International Airport")) {
            this.price = "8.65$";
        } else {
            this.price = "7.65$";
        }
    }

    public void setPriceDouble() {
        if (headStation.equals("Antioch")) {
            this.priceDouble = "16.90$";
        } else if (headStation.equals("Millbrae")) {
            this.priceDouble = "8.70$";
        } else if (headStation.equals("Richmond")) {
            this.priceDouble = "11.50$";
        } else if (headStation.equals("San Francisco International Airport")) {
            this.priceDouble = "17.30$";
        } else {
            this.priceDouble = "15.30$";
        }
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getStationDep() {
        return this.headStation;
    }

    public String getHeadStation() {
        return this.headStation;
    }

    public String getAbbr() {
        return this.abbr;
    }
}
