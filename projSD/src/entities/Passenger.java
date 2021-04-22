package entities;

import shared.Airplane;
import shared.DepAirport;
import shared.DestAirport;

public class Passenger extends Thread {

    private int passengerId;

    private boolean called;
    private boolean checked;
    private final DepAirport depAirport;
    private final DestAirport destAirport;
    private final Airplane airplane;


    public Passenger(DepAirport depAirport, DestAirport destAirport, Airplane airplane, int id){

        this.called=false;
        this.checked=false;
        this.passengerId=id;
        this.depAirport=depAirport;
        this.destAirport=destAirport;
        this.airplane=airplane;

    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isCalled() {
        return called;
    }

    public void setCalled(boolean called) {
        this.called = called;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public DepAirport getDepAirport() {
        return depAirport;
    }

    public DestAirport getDestAirport() {
        return destAirport;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public int getPassengerState() {
        return passengerState;
    }

    public void setPassengerState(int passengerState) {
        this.passengerState = passengerState;
    }

    @Override
    public void run() {
        System.out.println("FEZ RUN");
        travelToAirport();
        depAirport.waitInQueue();
        depAirport.showDocuments();
//        depAirport.boarded(true);
        airplane.board();
    }

    private int passengerState;

    public void travelToAirport() {
        passengerState = PassengerStates.goingToAirport;

        long a=0;
        if(passengerId>15)
            a =passengerId*1500;//long) Math.random();
        else
            a=4000* (long) Math.random();
        try {
            sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Passageiro "+this.passengerId+" chegou ao aeroporto");
    }

    public void showDocuments() {
        passengerState = PassengerStates.inQueue;
    }

    public void waitForEndOfFlight() {
        passengerState = PassengerStates.inFlight;
    }

    public void leaveThePlane() {
        passengerState = PassengerStates.atDestination;
    }

    public void boardThePlane() {
        passengerState = PassengerStates.inFlight;
    }

    public int getpassengerState() {
        return passengerState;
    }

    public void setpassengerState(int passengerState) {
        passengerState = passengerState;
    }

}

