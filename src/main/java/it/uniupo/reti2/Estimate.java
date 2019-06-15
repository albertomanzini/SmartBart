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
        if (minutes.equals("Leaving")) {
            this.trainId = platform + "0";
        } else {
            this.trainId = minutes + platform;
        }
    }

    public void setTimeDep(int min, int hour) {

        int minTemp;

        //se è "leaving"
        try {
            int intMin = Integer.parseInt(minutes);
        } catch (NumberFormatException e) {
            minutes = "0";
        }

        int intMin = Integer.parseInt(minutes);

        min = intMin + min;

        if (min >= 60) {
            int temp = min - 60;
            Integer hourTempo = hour + 1;
            minTemp = temp;

            if (minTemp < 10) {
                this.timeDep = hourTempo + ":0" + minTemp;
            } else {
                this.timeDep = hourTempo + ":" + minTemp;
            }
        } else {
            if (min < 10) {
                this.timeDep = hour + ":0" + min;
            } else {
                this.timeDep = hour + ":" + min;
            }
        }
    }

    public void setDelay() {
        int intDelay = Integer.parseInt(delay);

        int temp = intDelay/60;

        if (intDelay%60 > 29) {
            temp++;
        }

        delay = String.valueOf(temp);
    }

    public void setTrainIdTime(int min, int hour) {
        setTrainId();
        setTimeDep(min, hour);
        setDelay();
    }

    public String getTimeDep() {
        return timeDep;
    }
}

