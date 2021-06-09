package clientSide;
/**
 *    Definition of the internal states of the pilot during his life cycle.
 */

public final class PilotStates
{
    /**
     *   The pilot as arrived at transfer gate.
     */
    public static final int atTransferGate = 0;

    /**
     *   The pilot is ready for the passengers to board the plane.
     */
    public static final int readyForBoarding = 1;

    /**
     *   The pilot is waiting for boarding.
     */
    public static final int waitingForBoarding = 2;

    /**
     *   The pilot is flying forward.
     */
    public static final int flyingForward = 3;

    /**
     *   The pilot is waiting for all the passengers to deboard the plane.
     */
    public static final int deBoarding = 4;

    /**
     *   The pilot is flying back.
     */
    public static final int flyingBack = 5;

    private PilotStates()
    { }

}