package shared;

import genclass.*;
import entities.Passenger;
import entities.PassengerStates;
import entities.Pilot;
import entities.PilotStates;
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

public Interface AirplaneInt {

    /**
     *  Operation board the plane
     *
     *  It is called by the passenger after having the documents checked
     */

    void boardThePlane();

    /**
     * Operation wait for the end of the flight
     *
     * It is called by a Passenger while it doesn't reach its destination
     */

    void waitForEndOfFlight();

    /**
     * Operation leave the airplane
     *
     * It is called by the passenger after the flight lands
     */

    void leaveThePlane();

    /**
     * Operation announce arrival.
     *
     * It is called by the pilot after the airplane lands at the destination.
     */

    void announceArrival();

    /**
     * Operation park at the transfer gate.
     *
     * It is called by the pilot once it has landed back at the departure airport.
     */

    void parkAtTransferGate();
}
