package serverSide;

import clientSide.entities.Passenger;
import clientSide.entities.PassengerStates;
import clientSide.entities.Pilot;
import clientSide.entities.PilotStates;
import genclass.*;

import structs.MemException;
import structs.MemFIFO;

/**
 *  Airplane
 *
 *  It is responsible to keep track of it's occupation, the IDs of the passengers inside and if it has arrived the
 *  destination.
 *  All public methods are executed in mutual exclusion.
 *  There are two internal synchronization points: an array of blocking points, one per each passenger, where he
 *  waits for the flight to end so that he can exit the airplane; and one single blocking point for the pilot,
 *  where she waits for every passenger to leave the plane, in order to announce the arrival of the airplane.
 *
 */

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

        } catch (MemException e) {
            e.printStackTrace();
        }

        GenericIO.writelnString("Passenger "+ Thread.currentThread().getName()+" entered the plane");
        GenericIO.writelnString("There are "+occupation+" passengers aboard");
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
            GenericIO.writelnString("Every passenger left the plane!");
        }else {
            try {
                passenger = passengerIDs.read();
            } catch (MemException e) {
                e.printStackTrace();
            }
            empty=false;
            this.occupation--;
            GenericIO.writelnString("Passenger "+Thread.currentThread().getName()+" left the plane");

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
        GenericIO.writelnString("Pilot "+Thread.currentThread().getName()+" arrived at destination");
        while(!empty) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


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

        GenericIO.writelnString("Pilot "+Thread.currentThread().getName()+" parked at transfer gate");


    }
}
