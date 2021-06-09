package serverSide;

import genclass.*;
import clientSide.Passenger;
import clientSide.PassengerStates;
import clientSide.Pilot;
import clientSide.PilotStates;
import interfaces.AirplaneInt;
import interfaces.RepositoryInt;
import utils.MemException;
import utils.MemFIFO;

import java.rmi.RemoteException;

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
 * Communication is based in Java RMI.
 */

public class Airplane implements AirplaneInt {

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

    private RepositoryInt repos;

    /**
     *  Airplane instantiation
     * @param repos reference to the repository.
     */

    public Airplane(RepositoryInt repos) {
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

    public synchronized int boardThePlane(int passengerId) {

        try {
            repos.setPassengerState(passengerId,PassengerStates.inFlight);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            passengerIDs.write(passengerId);
            this.occupation++;

        } catch (MemException e) {
            e.printStackTrace();
        }

        GenericIO.writelnString("Passenger "+ Thread.currentThread().getName()+" entered the plane");
        GenericIO.writelnString("There are "+occupation+" passengers aboard");
        //notifyAll();
        return passengerId;
    }

    /**
     * Operation wait for the end of the flight
     *
     * It is called by a Passenger while it doesn't reach its destination
     */

    public synchronized void waitForEndOfFlight(){
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

    public synchronized void leaveThePlane(int passengerId){
        try {
            repos.setPassengerState(passengerId, PassengerStates.atDestination);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

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
        try {
            repos.setPilotState(PilotStates.deBoarding);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
        try {
            repos.setPilotState(PilotStates.atTransferGate);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        arrived=false;
        notifyAll();

        GenericIO.writelnString("Pilot "+Thread.currentThread().getName()+" parked at transfer gate");
    }

    /**
     * Operation shut server
     *
     * it is called to set to true the boolean condition that shuts down the server
     */
    public synchronized void shutServer() {
        AirplaneMain.finished=true;
        GenericIO.writelnString("Shutting Airplane -> " + AirplaneMain.finished);
    }
}
