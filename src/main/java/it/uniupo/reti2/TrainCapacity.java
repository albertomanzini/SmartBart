package it.uniupo.reti2;

public class TrainCapacity {

    private int returnValue;
    private int capacity;

    public TrainCapacity(int returnValue, int capacity) {
        this.capacity=capacity;
        this.returnValue=returnValue;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getReturnValue() {
        return returnValue;
    }
}
