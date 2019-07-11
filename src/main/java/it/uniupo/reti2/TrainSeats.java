package it.uniupo.reti2;

import it.uniupo.reti2.restAPI.Passenger;

import java.util.ArrayList;

public class TrainSeats {

    private int seats=10;
    private int bookedSeats;
    private String trainId;
    private String date;
    private int bikeCounter=0;

    public TrainSeats(String trainId, String date) {

        this.seats=this.seats*10;
        this.trainId=trainId;
        this.date=date;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getDate() {
        return date;
    }

    public int bookingSeat(int bikeFlag) {
        if(bikeFlag==1) {
            if (bookedSeats<seats && bikeCounter<10) {
                bookedSeats++;
                bikeCounter++;
                return 1;
            }
            else if(bookedSeats<seats && bikeCounter>=10){
                bookedSeats++;
                return 2;
            }
            else {
                return 0;
            }
        }
        else {
            if (bookedSeats<seats) {
                bookedSeats++;
                return 1;
            }
            else {
                return 0;
            }
        }

    }
}
