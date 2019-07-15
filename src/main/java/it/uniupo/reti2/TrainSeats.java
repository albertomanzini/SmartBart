package it.uniupo.reti2;

import it.uniupo.reti2.restAPI.Passenger;

import java.util.ArrayList;

public class TrainSeats {

    private int seats=5;
    private int bookedSeats;
    private String trainId;
    private String date;
    private int bikeCounter=0;
    private TrainCapacity trainCapacity;

    public TrainSeats(String trainId, String date) {

        this.seats=this.seats*1;
        this.trainId=trainId;
        this.date=date;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getDate() {
        return date;
    }

    public TrainCapacity bookingSeat(int bikeFlag) {
        if(bikeFlag==1) {
            if (bookedSeats<seats && bikeCounter<2) {
                bookedSeats++;
                bikeCounter++;
                return trainCapacity = new TrainCapacity(1,bookedSeats*50);
            }
            else if(bookedSeats<seats && bikeCounter>=2){
                bookedSeats++;
                return trainCapacity = new TrainCapacity(2, bookedSeats*50);
            }
            else {
                return trainCapacity = new TrainCapacity(0, bookedSeats*50);
            }
        }
        else {
            if (bookedSeats<seats) {
                bookedSeats++;
                return trainCapacity = new TrainCapacity(3, bookedSeats*50);
            }
            else {
                return trainCapacity = new TrainCapacity(0, bookedSeats*50);
            }
        }

    }
}
