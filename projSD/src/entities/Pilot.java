package entities;

import shared.Airplane;
import shared.DepAirport;
import shared.DestAirport;

/**
 *   Pilot thread.
 *
 *   It simulates the Pilot life cycle.
 *   Static solution.
 */

public class Pilot extends Thread{

    /**
     * pilot state.
     */
    private int Pilotstate;

    /**
     * pilot identification.
     */
    private int pilotID;

    /**
     * Reference to the departure airport.
     */
    private final DepAirport depAirport;

    /**
     * Reference to the departure airport.
     */
    private final DestAirport destAirport;

    /**
     * Reference to the airplane.
     */
    private final Airplane airplane;

    /**
     * Instantiation of a Pilot thread.
     *
     * @param depAirport reference to departure airport
     * @param destAirport reference to destination airport
     * @param airplane reference to airplane
     * @param pilotID pilot id
     */
    public Pilot (DepAirport depAirport, DestAirport destAirport, Airplane airplane, int pilotID) {
        this.pilotID=pilotID;
        this.depAirport=depAirport;
        this.destAirport=destAirport;
        this.airplane=airplane;
        Pilotstate=PilotStates.atTransferGate;
    }

    /**
     * Get pilot state.
     *
     * @return pilot state.
     */
    public int getPilotstate() {
        return Pilotstate;
    }

    /**
     * Set pilot state.
     *
     * @param pilotstate new pilot state
     */
    public void setPilotstate(int pilotstate) {
        Pilotstate = pilotstate;
    }

    /**
     * Get Pilot Id
     *
     * @return pilot id
     */
    public int getPilotID() {
        return pilotID;
    }

    /**
     * Set pilot id
     *
     * @param pilotID pilot id
     */
    public void setPilotID(int pilotID) {
        this.pilotID = pilotID;
    }

    /**
     * Get Departure Airport
     *
     * @return departure airport
     */
    public DepAirport getDepAirport() {
        return depAirport;
    }

    /**
     * Life cycle of the Pilot
     */
    @Override
    public void run() {
        while(depAirport.getFlew()<21) {
            depAirport.informPlaneReadyForBoarding();
            depAirport.waitForAllInBoard();
            depAirport.flyToDestinationPoint();
            airplane.announceArrival();
            destAirport.flyToDeparturePoint();
            airplane.parkAtTransferGate();

        }
    }

    /**
     * Pilot in flight.
     *
     * Internal Operation.
     */
    public void fly() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("VOEI OH PRA LÁ");
    }

}
