package it.uniupo.reti2;

public class TrainSeats {

    int seats;
    int bookedSeats;
    int bikeNumber;

    int bikeCounter=0;

    public TrainSeats(int lenght) {
        this.seats=lenght*40;
    }

    public int bookingSeat() {
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
}
