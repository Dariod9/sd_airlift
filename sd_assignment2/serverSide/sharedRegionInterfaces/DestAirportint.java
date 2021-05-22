package serverSide.sharedRegionInterfaces;

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

public interface DestAirportint {

    /**
     * Operation fly to the departure airport.
     *
     * It is called by the Pilot after all the passengers leave the plane.
     */

    void flyToDeparturePoint();
}
