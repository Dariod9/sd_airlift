package shared;

import genclass.*;
import entities.Pilot;
import entities.PilotStates;

/**
 *  Destination Airport
 *
 *  It is responsible for the flight back to the departure airport.
 *
 *  It does not contain any blocking point.
 */

public class DestAirport {

    /**
     * Reference to the repository.
     */

    private Repository repos;

    /**
     * Destination Airport instantiation.
     * @param repos reference to the repository
     */

    public DestAirport(Repository repos) {
        this.repos=repos;
    }

    /**
     * Operation fly to the departure airport.
     *
     * It is called by the Pilot after all the passengers leave the plane.
     */

    public synchronized void flyToDeparturePoint() {
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.flyingBack);
        repos.setPilotState((((Pilot) Thread.currentThread()).getPilotstate()));

        GenericIO.writelnString("Pilot "+Thread.currentThread().getName()+" is flying to departure point");
    }
}
