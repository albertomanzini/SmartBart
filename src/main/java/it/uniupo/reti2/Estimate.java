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

    private String abbr;

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
        String[] temp=timeDep.split(":");
        Integer min=Integer.parseInt(temp[1]);
        min=min-1;
        temp[1]=min.toString();
        if(min>10) {
            if(abbr.equals("ANTC")) {
                this.trainId="1111"+temp[0]+temp[1];
            } else if (abbr.equals("MLBR")) {
                this.trainId="2222"+temp[0]+temp[1];
            } else if (abbr.equals("RICH")) {
                this.trainId="3333"+temp[0]+temp[1];
            } else {
                this.trainId = "4444" + temp[0] + temp[1];
            }
        }
        else {
            if(abbr.equals("ANTC")) {
                this.trainId="1111"+temp[0]+"0"+temp[1];
            } else if (abbr.equals("MLBR")) {
                this.trainId="2222"+temp[0]+"0"+temp[1];
            } else if (abbr.equals("RICH")) {
                this.trainId="3333"+temp[0]+"0"+temp[1];
            } else {
                this.trainId = "4444" + temp[0] +"0"+ temp[1];
            }
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

            do {
                min = min - 60;
                hour++;
            } while (min >= 60);

            if (min < 10) {
                this.timeDep = hour + ":0" + min;
            } else {
                this.timeDep = hour + ":" + min;
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

    public void setAbbr(String abbr) {
        this.abbr=abbr;
    }

    public void setTrainIdTime(int min, int hour) {

        setTimeDep(min, hour);
        setTrainId();
        setDelay();
    }

    public String getTimeDep() {
        return timeDep;
    }
}

