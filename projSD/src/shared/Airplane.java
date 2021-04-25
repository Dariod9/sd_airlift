package shared;

import entities.Passenger;
import entities.PassengerStates;
import entities.Pilot;
import entities.PilotStates;
import structs.MemException;
import structs.MemFIFO;

public class Airplane {

    /**
     * List of all the boarded passengers' IDs.
     */

    private MemFIFO<Integer> passengerIDs;

    /**
     * Number of passengers currently boarded.
     */

    private int occupation;

    /**
     * Status of the airplanes' occupation (empty or not)
     */

    private boolean empty = false;

    /**
     * Status of the flight (arrived or not)
     */

    private boolean arrived = false;

    /**
     * Reference to the repository.
     */

    private Repository repos;

    /**
     *  Airplane instantiation
     * @param repos reference to the repository.
     */

    public Airplane(Repository repos) {
        try {
            this.passengerIDs = new MemFIFO<>(new Integer [21]);
            occupation=0;
            this.repos=repos;

        } catch (MemException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Operation board the plane
     *
     *  It is called by the passenger after having the documents checked
     */

    public synchronized void boardThePlane() {
        int passengerId = ((Passenger) Thread.currentThread()).getPassengerId();

        ((Passenger) Thread.currentThread()).setPassengerState(PassengerStates.inFlight);
        repos.setPassengerState(passengerId,((Passenger) Thread.currentThread()).getPassengerState());

        try {
            passengerIDs.write(passengerId);
            this.occupation++;
            System.out.println("PASSENGER ENTERED, TOTAL: " + occupation);
        } catch (MemException e) {
            e.printStackTrace();
        }

        System.out.println("ENTROU NO AVIÃO O "+passengerId);
        //notifyAll();

    }

    /**
     * Operation wait for the end of the flight
     *
     * It is called by a Passenger while it doesn't reach its destination
     */

    public synchronized void waitForEndOfFlight(){
        int passengerId = ((Passenger) Thread.currentThread()).getPassengerId();

        while (!arrived) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Operation leave the airplane
     *
     * It is called by the passenger after the flight lands
     */

    public synchronized void leaveThePlane(){
        int passengerId = ((Passenger) Thread.currentThread()).getPassengerId();
        ((Passenger) Thread.currentThread()).setPassengerState(PassengerStates.atDestination);
        repos.setPassengerState(passengerId, ((Passenger) Thread.currentThread()).getPassengerState());

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

    /**
     * Operation announce arrival.
     *
     * It is called by the pilot after the airplane lands at the destination.
     */

    public synchronized void announceArrival() {
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.deBoarding);
        repos.setPilotState(((Pilot) Thread.currentThread()).getPilotstate());

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

    }

    /**
     * Operation park at the transfer gate.
     *
     * It is called by the pilot once it has landed back at the departure airport.
     */

    public synchronized void parkAtTransferGate() {
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.atTransferGate);
        repos.setPilotState(((Pilot) Thread.currentThread()).getPilotstate());

        arrived=false;
        notifyAll();

        System.out.println("Cheguei chegnado bagun'ancdo a coisa toda");

        if(((Pilot) Thread.currentThread()).getDepAirport().getFlew()==21)
            System.out.println("\u001B[32m" + " TRANSAÇÃO COMPLETA <3<3 " + "\u001B[0m");


    }
}
