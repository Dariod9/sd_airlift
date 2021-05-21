package structs;

public enum MessageType {
    /**
     * Initialization parameter
     *    @serialField NO_MESSAGE
     */
    NO_MESSAGE,
    /**
     * Operation board the plane
     *
     */
    BOARD_THE_PLANE,
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
     * Operation fly to the departure airport.
     */
    FLY_TO_DEPARTURE_POINT,
    /**
     * Shuts down the server (client solicitation).
     */
    SHUTDOWN,

}
