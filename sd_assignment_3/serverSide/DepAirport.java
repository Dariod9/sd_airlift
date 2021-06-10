package serverSide;

import genclass.*;
import clientSide.*;
import interfaces.DepAirportInt;
import interfaces.RepositoryInt;
import utils.MemException;
import utils.MemFIFO;

import java.rmi.RemoteException;

/**
 *  Departure Airport
 *  It is responsible for many actions.
 *  Regarding the passengers, it controls the process of waiting in Queue.
 *  Regarding the hostess, it is responsible for the waiting for the next flight, the preparing of the boarding, the
 *  process of waiting for more passengers, the action of checking documents and signaling the plane to take off.
 *  Concerning the pilot, it is responsible for the process of informing that the plane is ready to board, waiting
 *  for it to happen and flying to the destination.
 *
 *  All public methods are executed in mutual exclusion.
 *
 *  There are seven internal synchronization points: an array of blocking points for the passengers, where all of them
 *  wait to be called by the hostess and wait while the documents aren't checked; four blocking points for the hostess,
 *  while she waits while the Queue is empty, while the documents are not shown to her, while the plain is not ready to
 *  fly and also while the pilot is not ready for the boarding process to start. Finally, one single blocking point for
 *  the pilot, where he waits for the plane to be ready for take off.
 *
 * Communication is based in Java RMI.
 */

public class DepAirport implements DepAirportInt {

    /**
     * List of all of the passengers' IDs.
     */

    private MemFIFO<Integer> passengerIDs;

    /**
     * Id of the passenger called by the hostess.
     */

    private int chamado = -1;

    /**
     * Status of the pilots' boarding process (ready for boarding or not).
     */

    private boolean pilotReady = false;

    /**
     * Status of the passengers' checking process (checked or not).
     */

    private boolean checked = false;

    /**
     * Length of the Queue.
     */

    private int fifoSize;

    /**
     * Status of the passengers' boarding process (boarded or not).
     */


    private int boarded;

    /**
     * Total number of passengers.
     */
    private int TOTAL;

    /**
     * minimum number of passengers.
     */
    private int MIN;

    /**
     * maximum number of passengers.
     */
    private int MAX;

    /**
     * Number of passengers who have travelled.
     */

    private int flew = 0;

    /**
     * State of documents
     */

    private boolean docsGiven=false;

    /**
     * Status of the take off action (ready or not).
     */

    private boolean readyTakeOff = false;

    /**
     * Status of the plane action (ready to fly or not).
     */

    private boolean planeReady = false;

    /**
     * Last passenger to fly
     */
    private int lastPassenger = 0;

    /**
     * Last passenger needed to fly
     */
    private boolean canFly = false;

    /**
     * Reference to the repository.
     */

    private RepositoryInt repos;

    /**
     * Passenger to run showDocuments()
     */
    private Passenger pass;

    /**
     * Passenger to run fly()
     */
    private Pilot pilot;

    /**
     * Departure Airport instantiaton.
     *
     * @param repos reference to the repository.
     */
    public DepAirport(RepositoryInt repos, int TOTAL, int MIN, int MAX) {

        try {
            this.passengerIDs = new MemFIFO(new Integer[21]);
            this.TOTAL=TOTAL;
            this.MIN=MIN;
            this.MAX=MAX;
            this.fifoSize = 0;
            this.boarded = 0;
            this.repos=repos;
        } catch (MemException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Get Passenger that flew
     *
     * @return flew
     */
    public synchronized int getFlew() {
        return this.flew;
    }


    /**
     * Operation enter and wait in the Queue.
     *
     * It is called by a passenger after he arrives to the airport, while he waits to be called by the hostess.
     *
     */
    public synchronized void waitInQueue(int passengerId) {

        try{
            repos.setPassengerState(passengerId,PassengerStates.inQueue);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(1);
        }


        try {
            passengerIDs.write(passengerId);
            fifoSize++;
            GenericIO.writelnString("Passenger "+ (Thread.currentThread()).getName() +" is Waiting in Queue");
        } catch (MemException e) {
            e.printStackTrace();
        }

        notifyAll();

        while (chamado != passengerId) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*
        * Static reference to Passenger thread
        * */
        //pass.showDocuments();

        docsGiven=true;

        notifyAll();

        while (!checked) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        checked = false;


    }

    /**
     * Operation wait for the next passenger.
     *
     * It is called by the hostess while there are no passengers in the Queue for her to call.
     * @return
     */
    public synchronized boolean waitForNextPassenger() {
        try {
            repos.setHostessState(HostessStates.waitForPassenger);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (flew >= TOTAL-MIN) {
            if (boarded == TOTAL - flew){
                planeReady = true;
                pilotReady = false;
                GenericIO.writelnString("Plane ready to take off");
                notifyAll();
            }
        }
        else {
            if (boarded == MAX) {
                planeReady = true;
                pilotReady = false;
                GenericIO.writelnString("Plane ready to take off");
                notifyAll();
            } else {
                if (fifoSize == 0 && boarded >= MIN) {
                    planeReady = true;
                    pilotReady = false;
                    GenericIO.writelnString("Plane ready to take off");
                    notifyAll();
                }
            }
        }

        return planeReady;
    }

    /**
     * Operation check for documents.
     *
     * It is called by the hostess when she checks the documents of a passenger.
     *
     */

    public synchronized void checkDocuments() {
        while (fifoSize == 0 ) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            chamado = passengerIDs.read();
            fifoSize--;

            repos.setHostessState(HostessStates.checkPassenger, chamado);
            GenericIO.writelnString("Hostess "+ Thread.currentThread().getName()+" called Passenger "+chamado);

            notifyAll();
        } catch (MemException | RemoteException e) {
            e.printStackTrace();
        }

        while (!docsGiven) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        checked=true;
        lastPassenger = chamado;
        boarded++;
        docsGiven=false;
        notifyAll();



    }

    /**
     * Operation inform that the plane is ready to take off.
     *
     * It is called by the hostess when the plane is ready to board.
     *
     */

    public synchronized void informPlaneReadyToTakeOff() {

        while (!planeReady || !canFly) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            repos.setHostessState(HostessStates.readyToFly);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        readyTakeOff=true;
        flew = flew + boarded;
        notifyAll();
        GenericIO.writelnString("Hostess: "+ Thread.currentThread().getName()+" everyone aboard");
        try {
            repos.setHostessState(HostessStates.waitForFlight);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    /**
     * Operation wait for the next flight.
     *
     * It is called by the hostess while she waits for the next flight to happen.
     *
     */

    public synchronized void waitForNextFlight() {

        GenericIO.writelnString(flew+" Passengers flew!");
        //boarded=0;
        while (!pilotReady) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Operation inform that passengers can start boarding the plane.
     *
     * It is called by the pilot after arriving to the transfer gate.
     */

    public synchronized void informPlaneReadyForBoarding() {
        try {
            repos.setPilotState(PilotStates.readyForBoarding);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        pilotReady = true;
        notifyAll();
        GenericIO.writelnString("Pilot "+ Thread.currentThread().getName()+" is ready");

        try {
            repos.setPilotState(PilotStates.waitingForBoarding);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Operation wait for the passengers.
     *
     * It is called by the pilot while he waits for enough passengers to enter the plane.
     */

    public synchronized void waitForAllInBoard() {

        while (!readyTakeOff) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Operation fly to destination.
     *
     * It is called by the pilot when the plane is ready to take off.
     *
     */

    public synchronized void flyToDestinationPoint() {
        try {
            repos.addFlightInfo(boarded);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            repos.setPilotState(PilotStates.flyingForward);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        GenericIO.writelnString("Pilot "+ Thread.currentThread().getName()+" is flying to destination point");
        boarded = 0;
        readyTakeOff = false;
        planeReady = false;

        /*
         * Static reference to Pilot thread
         * */
        //pilot.fly();

        notifyAll();
    }


    /**
     * Hostess prepares for the passengers to board.
     *
     * Internal Operation.
     */
    public synchronized void prepareForPassBoarding() {
        try {
            repos.setHostessState(HostessStates.waitForPassenger);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if the last passenger as entered the plane
     *
     * @param passengerID
     */
    public synchronized void passengerEnteredPlane(int passengerID){
        if(lastPassenger == passengerID) canFly=true;
        notifyAll();
    }

    /**
     * Operation shut server
     *
     * it is called to set to true the boolean condition that shuts down the server
     */
    public synchronized void shutServer() {
        DepAirportMain.wakeUp();
        //GenericIO.writelnString("Shutting Dep Airport -> " + DepAirportMain.finished);
    }
}
