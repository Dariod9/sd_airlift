package entities;

public final class HostessStates
{
    /**
     *   The barber is resting while waiting for a customer.
     */

    public static final int waitForFlight = 0;

    /**
     *   The barber is cutting some customer hair.
     */

    public static final int waitForPassenger = 1;

    /**
     *   It can not be instantiated.
     */

    public static final int checkPassenger = 2;

    /**
     *   The barber is cutting some customer hair.
     */

    public static final int readyToFly = 3;

    /**
     *   The barber is cutting some customer hair.
     */

    private HostessStates()
    { }
}