package it.uniupo.reti2.restAPI;

public class Passenger {

    String cf;
    int bicycle;
    String trainId;

    public Passenger(String cf, int bicycle, String trainId) {
        this.cf=cf;
        this.bicycle=bicycle;
        this.trainId=trainId;
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
}
