package it.uniupo.reti2;

import com.google.gson.annotations.SerializedName;

public class Estimate {

    @SerializedName(value = "minutes")
    private String minutes;

    @SerializedName(value = "platform")
    private String platform;

    @SerializedName(value = "length")
    private String length;

    @SerializedName(value = "hexcolor")
    private String hexcolor;

    @SerializedName(value = "bikeflag")
    private String bikeFlag;

    @SerializedName(value = "delay")
    private String delay;

    private String trainId;

    private String destination;

    private String timeDep;

    public String getLength() {
        return length;
    }

    public String getMinutes() {
        return minutes;
    }

    public String getPlatform() {
        return platform;
    }

    public String getHexcolor() {
        return hexcolor;
    }

    public String getBikeFlag() {
        return bikeFlag;
    }

    public String getDelay() {
        return delay;
    }

    public void setTrainId() {
        if(minutes.equals("Leaving")) {
            this.trainId="0"+platform;
        }
        else {
            this.trainId=minutes+platform;
        }
    }

    public void setTimeDep(String timeDep) {

        this.timeDep=timeDep;
    }
}

