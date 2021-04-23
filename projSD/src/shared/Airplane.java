package shared;

import entities.Passenger;
import entities.PassengerStates;
import entities.Pilot;
import entities.PilotStates;
import structs.MemException;
import structs.MemFIFO;

public class Airplane {

    private MemFIFO<Integer> passengerIDs;
    private int occupation;
    private boolean boarded;
    private boolean empty = false;
    private boolean arrived = false;
    //private int totalArrived;


    public Airplane(Repository repos) {
        try {
            this.passengerIDs = new MemFIFO<>(new Integer [21]);
            occupation=0;
            boarded=false;
        } catch (MemException e) {
            e.printStackTrace();
        }
    }


//    public int getTotalArrived() {
//        return totalArrived;
//    }
//
//    public void setTotalArrived(int totalArrived) {
//        this.totalArrived = totalArrived;
//    }

    public boolean isBoarded() {
        return boarded;
    }

    public void setBoarded(boolean boarded) {
        this.boarded = boarded;
    }

    public int getOccupation() {
        return this.occupation;
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



    public synchronized void boardThePlane() {
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
            this.occupation++;
            System.out.println("PASSENGER ENTERED, TOTAL: " + occupation);
        } catch (MemException e) {
            e.printStackTrace();
        }

//        this.boarded=false;
        System.out.println("ENTROU NO AVIÃO O "+passengerId);
        notifyAll();

    }

    public synchronized void waitForEndOfFlight(){
        int passengerId = ((Passenger) Thread.currentThread()).getPassengerId();
        ((Passenger) Thread.currentThread()).setPassengerState(PassengerStates.inFlight);

        while (!arrived) {
//            System.out.println("PRINT DO VALE");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void leaveThePlane(){
        int passengerId = ((Passenger) Thread.currentThread()).getPassengerId();
        ((Passenger) Thread.currentThread()).setPassengerState(PassengerStates.atDestination);

        int passenger;

        if(this.occupation==1){
            this.occupation--;
            empty=true;
            System.out.println("SAIRAM TODOS");
        }else {
            try {
                passenger = passengerIDs.read();
            } catch (MemException e) {
                e.printStackTrace();
            }
            empty=false;
            this.occupation--;
            System.out.println("SAÍ do AVIAO, TAVAM LÁ "+ occupation);

        }
        notifyAll();

    }

    public synchronized void announceArrival() {
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.deBoarding);

//        totalArrived+= ((Pilot) Thread.currentThread()).getAirplane().getOccupation();
//        System.out.println("CHEGUEI POTAS, COM "+totalArrived+" WIIS");
        arrived=true;
        notifyAll();
        while(!empty) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Erro na waitInQueue: " + e);
            }
        }

        //notifyAll();

        //((Pilot) Thread.currentThread()).getAirplane().setOccupation(0); //vai ter que ser mudado
    }

    public synchronized void parkAtTransferGate() {
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.atTransferGate);

        arrived=false;
        notifyAll();

        System.out.println("Cheguei chegnado bagun'ancdo a coisa toda");

        if(((Pilot) Thread.currentThread()).getDepAirport().getFlew()==21)
            System.out.println("\u001B[32m" + " TRANSAÇÃO COMPLETA <3<3 " + "\u001B[0m");


    }
}
