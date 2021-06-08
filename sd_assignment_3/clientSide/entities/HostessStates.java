package clientSide.entities;
/**
 *    Definition of the internal states of the hostess during his life cycle.
 */

public final class HostessStates
{
    /**
     *   The hostess is waiting for the next flight.
     */
    public static final int waitForFlight = 0;

    /**
     *   The hostess is waiting for a passenger to enter the queue.
     */
    public static final int waitForPassenger = 1;

    /**
     *   The hostess is checking the passenger's documents.
     */
    public static final int checkPassenger = 2;

    /**
     *   The hostess declares the airplane as ready to fly.
     */
    public static final int readyToFly = 3;


    private HostessStates() { }
}