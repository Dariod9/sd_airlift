package entities;

public final class PassengerStates
{
    /**
     *   The barber is resting while waiting for a customer.
     */

    public static final int goingToAirport = 0;

    /**
     *   The barber is cutting some customer hair.
     */

    public static final int inQueue = 1;

    /**
     *   It can not be instantiated.
     */

    public static final int inFlight = 2;

    /**
     *   The barber is cutting some customer hair.
     */

    public static final int atDestination = 3;

    /**
     *   The barber is cutting some customer hair.
     */

    private PassengerStates()
    { }
}