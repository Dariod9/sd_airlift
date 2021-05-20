package entitiesInterfaces;


import genclass.*;

/**
 *   Passenger thread.
 *
 *   It simulates the passenger life cycle.
 *   Static solution.
 */


public Interface PassengerInt {

    /**
     * Get passenger id.
     *
     * @return passenger id.
     */
    int getPassengerId();

    /**
     * Set customer id.
     *
     * @param passengerId new costumer id.
     */
    void setpassengerId(int passengerId);

    /**
     * Get passenger State.
     *
     * @return passenger state.
     */
    int getPassengerState();

    /**
     * Set passenger state.
     *
     * @param passengerState new passenger state.
     */
    void setPassengerState(int passengerState);

    /**
     * Operation show documents.
     *
     * It is called by the passenger after being called by the hostess and before boarding the plane.
     *
     */

    void showDocuments();

    /**
     * Passenger travel to airport.
     *
     * Internal Operation.
     */
    void travelToAirport();

}

