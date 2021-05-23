package structs;

/**
 * This enumerate data type specifies the various messages that the clients and servers exchange between them in a Distributed solution
 * for the AirLift Problem . Each message is tied to a function or an action crucial for the implementation.
 */
public enum MessageType {
    /**
     * Initialization parameter
     *    @serialField NO_MESSAGE
     */
    NO_MESSAGE,
    /**
     * Message Acknowledge
     */
    ACK,
    /**
     * Operation board the plane
     *
     */
    BOARD_THE_PLANE,
    /**
     * Operation get number of passenger at the destination
     */
    GET_FLEW,
    /**
     * Operation wait for the end of the flight
     */
    WAIT_FOR_END_OF_FLIGHT,
    /**
     * Operation leave the airplane
     */
    LEAVE_THE_PLANE,
    /**
     * Operation announce arrival.
     */
    ANNOUNCE_ARRIVAL,
    /**
     * Operation park at the transfer gate.
     */
    PARK_AT_TRANSFER_GATE,
    /**
     * Operation enter and wait in the Queue.
     */
    WAIT_IN_QUEUE,
    /**
     * Operation wait for the next passenger.
     */
    WAIT_FOR_NEXT_PASSENGER,
    /**
     * Operation check for documents.
     */
    CHECK_DOCUMENTS,
    /**
     * Operation inform that the plane is ready to take off.
     */
    INFORM_PLANE_READY_TO_TAKEOFF,
    /**
     * Operation wait for the next flight.
     */
    WAIT_FOR_NEXT_FLIGHT,
    /**
     * Operation inform that passengers can start boarding the plane.
     */
    INFORM_PLANE_READY_FOR_BOARDING,
    /**
     * Operation wait for the passengers.
     */
    WAIT_FOR_ALL_IN_BOARD,
    /**
     * Operation fly to destination.
     */
    FLY_TO_DESTINATION_POINT,
    /**
     * Hostess prepares for the passengers to board.
     */
    PREPARE_FOR_PASS_BOARDING,
    /**
     * Passenger entered the plane
     */
    PASSENGER_ENTERED_PLANE,
    /**
     * Operation fly to the departure airport.
     */
    FLY_TO_DEPARTURE_POINT,
    /**
     * Operation set pilot state.
     */
    SET_PILOT_STATE,
    /**
     * Operation set hostess state.
     */
    SET_HOSTESS_STATE,
    /**
     * Operation set hostess state with passenger ID.
     */
    SET_HOSTESS_STATE_ID,
    /**
     * Operation set passenger state.
     */
    SET_PASSENGER_STATE,
    /**
     * Operation add flight info.
     */
    ADD_FLIGHT_INFO,
    /**
     * Operation report summary
     */
    REPORT_SUMMARY,
    /**
     * Shuts down the server (client solicitation).
     */
    SHUTDOWN,

}
