package it.uniupo.reti2.restAPI;

public class Passenger {

    String cf;
    int bicycle;
    String trainId;
    String date;

    public Passenger(String cf, int bicycle, String trainId, String date) {
        this.cf = cf;
        this.bicycle = bicycle;
        this.trainId = trainId;
        this.date = date;
    }

    public String getCf() {
        return cf;
    }

    public int getBicycle() {
        return bicycle;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getDate() {
        return date;
    }
}
