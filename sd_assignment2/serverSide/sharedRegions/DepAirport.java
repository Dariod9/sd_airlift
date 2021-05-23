package serverSide.sharedRegions;

import clientSide.entities.Hostess;
import clientSide.entitiesStubs.RepositoryStub;
import genclass.*;
import clientSide.entities.*;
import serverSide.main.DepAirportMain;
import serverSide.serverProxys.DepartureAirportProxy;
import structs.MemException;
import structs.MemFIFO;

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
 */

public class DepAirport {

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
     * Number of passengers who have travalled.
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
     * Reference to the repository.
     */

    private RepositoryStub repos;

    /**
     * Departure Airport instantiaton.
     *
     * @param repos reference to the repository.
     */
    public DepAirport(RepositoryStub repos, int TOTAL, int MIN, int MAX) {

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
        ((DepartureAirportProxy) Thread.currentThread()).setPassengerState(PassengerStates.inQueue);
        repos.setPassengerState(passengerId,PassengerStates.inQueue);

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

        ((DepartureAirportProxy) Thread.currentThread()).showDocuments();
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

        ((DepartureAirportProxy) Thread.currentThread()).setHostessState(HostessStates.waitForPassenger);
        repos.setHostessState(HostessStates.waitForPassenger);

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

        int hostessId = ((DepartureAirportProxy) Thread.currentThread()).getHostessID();

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

            ((DepartureAirportProxy) Thread.currentThread()).setHostessState(HostessStates.checkPassenger);
            repos.setHostessState(HostessStates.checkPassenger, chamado);
            GenericIO.writelnString("Hostess "+ Thread.currentThread().getName()+" called Passenger "+chamado);

            notifyAll();
        } catch (MemException e) {
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

        while (!planeReady) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ((DepartureAirportProxy) Thread.currentThread()).setHostessState(HostessStates.readyToFly);
        repos.setHostessState(HostessStates.readyToFly);

        flew = flew + boarded;
        readyTakeOff=true;
        notifyAll();
        GenericIO.writelnString("Hostess: "+ Thread.currentThread().getName()+" everyone aboard");
        ((DepartureAirportProxy) Thread.currentThread()).setHostessState(HostessStates.waitForFlight);
        repos.setHostessState(HostessStates.waitForFlight);


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
        int pilotId = ((DepartureAirportProxy) Thread.currentThread()).getPilotID();
        ((DepartureAirportProxy) Thread.currentThread()).setPilotstate(PilotStates.readyForBoarding);
        repos.setPilotState(PilotStates.readyForBoarding);

        pilotReady = true;
        notifyAll();
        GenericIO.writelnString("Pilot "+ Thread.currentThread().getName()+" is ready");

        ((DepartureAirportProxy) Thread.currentThread()).setPilotstate(PilotStates.waitingForBoarding);
        repos.setPilotState(PilotStates.waitingForBoarding);

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
        repos.addFlightInfo(boarded);
        int pilotId = ((DepartureAirportProxy) Thread.currentThread()).getPilotID();
        ((DepartureAirportProxy) Thread.currentThread()).setPilotstate(PilotStates.flyingForward);
        repos.setPilotState(PilotStates.flyingForward);
        GenericIO.writelnString("Pilot "+ Thread.currentThread().getName()+" is flying to destination point");
        boarded = 0;
        readyTakeOff = false;
        planeReady = false;
        ((DepartureAirportProxy) Thread.currentThread()).fly();
        notifyAll();
    }


    /**
     * Hostess prepares for the passengers to board.
     *
     * Internal Operation.
     */
    public synchronized void prepareForPassBoarding() {
        int hostessId = ((DepartureAirportProxy) Thread.currentThread()).getHostessID();
        ((DepartureAirportProxy) Thread.currentThread()).setHostessState(HostessStates.waitForPassenger);
        repos.setHostessState(HostessStates.waitForPassenger);
    }

    public synchronized void shutServer() {
        DepAirportMain.finished=true;
    }



}
