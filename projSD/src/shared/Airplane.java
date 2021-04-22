package shared;

import entities.Passenger;
import entities.PassengerStates;
import structs.MemException;
import structs.MemFIFO;

public class Airplane {

    private MemFIFO<Integer> passengerIDs;
    private int occupation;
    private boolean boarded;


    public Airplane() {
        try {
            this.passengerIDs = new MemFIFO<>(new Integer [21]);
            occupation=0;
            boarded=false;
        } catch (MemException e) {
            e.printStackTrace();
        }
    }

    public boolean isBoarded() {
        return boarded;
    }

    public void setBoarded(boolean boarded) {
        this.boarded = boarded;
    }

    public int getOccupation() {
        return occupation;
    }

    public void setOccupation(int occupation) {
        this.occupation=occupation;
    }

    public MemFIFO<Integer> getPassengerIDs() {
        return passengerIDs;
    }

    public void setPassengerIDs(MemFIFO<Integer> passengerIDs) {
        this.passengerIDs = passengerIDs;
    }


    public synchronized void board() {
        int passengerId = ((Passenger) Thread.currentThread()).getPassengerId();
        ((Passenger) Thread.currentThread()).setPassengerState(PassengerStates.inFlight);
//
//        while (!boarded){
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        try {
            passengerIDs.write(passengerId);
            occupation++;
        } catch (MemException e) {
            e.printStackTrace();
        }

//        this.boarded=false;
        System.out.println("ENTROU NO AVIÃO O "+passengerId);
        notifyAll();

    }
}
