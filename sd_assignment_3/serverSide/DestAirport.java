package serverSide;

import genclass.*;
import clientSide.Pilot;
import clientSide.PilotStates;
import interfaces.DestAirportInt;
import interfaces.RepositoryInt;

import java.rmi.RemoteException;

/**
 *  Destination Airport
 *
 *  It is responsible for the flight back to the departure airport.
 *
 *  It does not contain any blocking point.
 *
 *  Communication is based in Java RMI.
 */

public class DestAirport implements DestAirportInt {

    /**
     * Reference to the repository.
     */

    private RepositoryInt repos;


    public boolean finished=false;

    /**
     * Destination Airport instantiation.
     * @param repos reference to the repository
     */

    public DestAirport(RepositoryInt repos) {
        this.repos=repos;
    }

    /**
     * Operation fly to the departure airport.
     *
     * It is called by the Pilot after all the passengers leave the plane.
     */

    public synchronized void flyToDeparturePoint() {
        try {
            repos.setPilotState(PilotStates.flyingBack);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        GenericIO.writelnString("Pilot "+Thread.currentThread().getName()+" is flying to departure point");
    }

    /**
     * Operation shut server
     *
     * it is called to set to true the boolean condition that shuts down the server
     */
    public synchronized void shutServer() {
        DestAirportMain.wakeUp();
        //GenericIO.writelnString("Shutting Dest Airport -> " + DestAirportMain.finished);
    }
}
