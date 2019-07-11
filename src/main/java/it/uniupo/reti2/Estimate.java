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

    private String timeArr;

    private String price;

    private String priceDouble;

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
        String[] temp = timeDep.split(":");
        Integer hour = Integer.parseInt(temp[0]);
        Integer min = Integer.parseInt(temp[1]);
        if (min > 10) {
            if (abbr.equals("ANTC")) {
                if (hour + 1 > 12) {
                    hour = 1;
                } else {
                    hour = hour + 1;
                }

                if (min + 37 > 60) {
                    hour++;
                    min = min + 37 - 60;
                } else {
                    min = min + 37;
                }
                min = min - 1;
                temp[1] = min.toString();
                this.trainId = "1111" + temp[0] + temp[1];
                this.timeArr = hour + ":" + min;
            } else if (abbr.equals("MLBR")) {
                if (hour + 1 > 12) {
                    hour = 1;
                }

                if (min + 11 > 60) {
                    hour++;
                    min = min + 11 - 60;
                } else {
                    min = min + 11;
                }
                min = min - 1;
                temp[1] = min.toString();
                this.trainId = "2222" + temp[0] + temp[1];
                this.timeArr = hour + ":" + min;
            } else if (abbr.equals("RICH")) {
                if (hour + 1 > 12) {
                    hour = 1;
                }

                if (min + 57 > 60) {
                    hour++;
                    min = min + 57 - 60;
                } else {
                    min = min + 57;
                }
                min = min - 1;
                temp[1] = min.toString();
                this.trainId = "3333" + temp[0] + temp[1];
                this.timeArr = hour + ":" + min;
            } else {
                if (hour + 1 > 12) {
                    hour = 1;
                }

                if (min + 10 > 60) {
                    hour++;
                    min = min + 10 - 60;
                } else {
                    min = min + 10;
                }
                temp[1] = min.toString();
                this.trainId = "4444" + temp[0] + temp[1];
                this.timeArr = hour + ":" + min;
            }
        } else {
            if (abbr.equals("ANTC")) {
                if (hour + 1 > 12) {
                    hour = 1;
                } else {
                    hour = hour + 1;
                }

                if (min + 37 > 60) {
                    hour++;
                    min = min + 37 - 60;
                } else {
                    min = min + 37;
                }
                min = min - 1;
                temp[1] = min.toString();
                this.trainId = "1111" + temp[0] + "0" + temp[1];
                this.timeArr = hour + ":" + min;
                ;
            } else if (abbr.equals("MLBR")) {
                if (hour + 1 > 12) {
                    hour = 1;
                }

                if (min + 11 > 60) {
                    hour++;
                    min = min + 11 - 60;
                } else {
                    min = min + 11;
                }
                min = min - 1;
                temp[1] = min.toString();
                this.trainId = "2222" + temp[0] + "0" + temp[1];
                this.timeArr = hour + ":" + min;
            } else if (abbr.equals("RICH")) {
                if (hour + 1 > 12) {
                    hour = 1;
                }

                if (min + 57 > 60) {
                    hour++;
                    min = min + 57 - 60;
                } else {
                    min = min + 57;
                }
                min = min - 1;
                temp[1] = min.toString();
                this.timeArr = hour + ":" + min;
                this.trainId = "3333" + temp[0] + "0" + temp[1];
            } else {
                if (hour + 1 > 12) {
                    hour = 1;
                }

                if (min + 10 > 60) {
                    hour++;
                    min = min + 10 - 60;
                } else {
                    min = min + 10;
                }
                temp[1] = min.toString();
                this.trainId = "4444" + temp[0] + "0" + temp[1];
                this.timeArr = hour + ":" + min;
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

        int temp = intDelay / 60;

        if (intDelay % 60 > 29) {
            temp++;
        }

        delay = String.valueOf(temp);
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public void setTrainIdTime(int min, int hour) {

        setTimeDep(min, hour);
        setTrainId();
        setDelay();
        setPrice();
        setPriceDouble();
    }

    public void setPrice () {
        if(abbr.equals("ANTC")) {
            this.price = "8.45$";
        } else if (abbr.equals("MLBR")) {
            this.price = "4.35$";
        } else if (abbr.equals("RICH")) {
            this.price = "5.75$";
        } else if (abbr.equals("SFIA")) {
            this.price = "8.65$";
        } else {
            this.price = "7.65$";
        }
    }

    public void setPriceDouble () {
        if(abbr.equals("ANTC")) {
            this.priceDouble = "16.90$";
        } else if (abbr.equals("MLBR")) {
            this.priceDouble = "8.70$";
        } else if (abbr.equals("RICH")) {
            this.priceDouble = "11.50$";
        } else if (abbr.equals("SFIA")) {
            this.priceDouble = "17.30$";
        } else {
            this.priceDouble = "15.30$";
        }
    }

    public String getTimeDep() {
        return timeDep;
    }
}

