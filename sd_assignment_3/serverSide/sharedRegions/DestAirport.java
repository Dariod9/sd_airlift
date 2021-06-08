package serverSide.sharedRegions;

import genclass.*;
import clientSide.entities.Pilot;
import clientSide.entities.PilotStates;
import interfaces.DestAirportInt;
import interfaces.RepositoryInt;

import java.rmi.RemoteException;

/**
 *  Destination Airport
 *
 *  It is responsible for the flight back to the departure airport.
 *
 *  It does not contain any blocking point.
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
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.flyingBack);
        try {
            repos.setPilotState((((Pilot) Thread.currentThread()).getPilotstate()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        GenericIO.writelnString("Pilot "+Thread.currentThread().getName()+" is flying to departure point");
    }

    public synchronized void shutdown() {
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.flyingBack);

        finished=true;

    }
}
