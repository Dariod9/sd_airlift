package clientSide.entitiesInterfaces;


import serverSide.DepAirport;

/**
 *   Pilot thread.
 *
 *   It simulates the Pilot life cycle.
 *   Static solution.
 */

public interface PilotInt{

    /**
     * Get pilot state.
     *
     * @return pilot state.
     */
    int getPilotstate();

    /**
     * Set pilot state.
     *
     * @param pilotstate new pilot state
     */
    void setPilotstate(int pilotstate);

    /**
     * Get Pilot Id
     *
     * @return pilot id
     */
    int getPilotID();

    /**
     * Set pilot id
     *
     * @param pilotID pilot id
     */
    void setPilotID(int pilotID);

    /**
     * Get Departure Airport
     *
     * @return departure airport
     */
    DepAirport getDepAirport();


    /**
     * Pilot in flight.
     *
     * Internal Operation.
     */
    void fly();

}
