package entities;

public class Passenger extends Thread {

    private int Passengertate;

    public void travelToAirport() {
        Passengertate = PassengerStates.goingToAirport;
    }

    public void waitInQueue() {
        Passengertate = PassengerStates.inQueue;
    }

    public void showDocuments() {
        Passengertate = PassengerStates.inQueue;
    }

    public void waitForEndOfFlight() {
        Passengertate = PassengerStates.inFlight;
    }

    public void leaveThePlane() {
        Passengertate = PassengerStates.atDestination;
    }

    public void boardThePlane() {
        Passengertate = PassengerStates.inFlight;
    }

    public int getPassengertate() {
        return Passengertate;
    }

    public void setPassengertate(int passengertate) {
        Passengertate = passengertate;
    }

}

