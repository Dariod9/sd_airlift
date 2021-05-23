package structs.entitiesInterfaces;


import clientSide.entitiesStubs.DepartureAirportStub;

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



}
