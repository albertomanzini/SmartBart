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

    private String destination;

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

    public void setDestination(String destination) {
        this.destination = destination;
    }
}

