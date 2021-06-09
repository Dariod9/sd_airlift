package clientSide;
/**
 *    Definition of the internal states of the passenger during his life cycle.
 */

public final class PassengerStates
{
    /**
     *   The passenger is going to the departure airport.
     */
    public static final int goingToAirport = 0;

    /**
     *   The passenger is in queue.
     */
    public static final int inQueue = 1;

    /**
     *   The passenger is in flight.
     */
    public static final int inFlight = 2;

    /**
     *   The passenger as arrived at the destination.
     */
    public static final int atDestination = 3;


    private PassengerStates()
    { }
}