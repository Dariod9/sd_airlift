package entities;

import shared.Airplane;
import shared.DepAirport;
import shared.DestAirport;

/**
 *   Hostess thread.
 *
 *   It simulates the hostess life cycle.
 *   Static solution.
 */

public class Hostess extends Thread {

    /**
     * Hostess state.
     */
    private int hostessState;

    /**
     * Hostess identification.
     */
    private int hostessID;

    /**
     * Reference to the departure airport.
     */
    private final DepAirport depAirport;

    /**
     * Reference to the destination airport.
     */
    private final DestAirport destAirport;

    /**
     * Reference to the airplane.
     */
    private final Airplane airplane;

    /**
     * Instantiation of a Hostess thread.
     *
     * @param depAirport reference to departure airport
     * @param destAirport reference to destination airport
     * @param airplane reference to airplane
     * @param hostessID hostess id
     */
    public Hostess(DepAirport depAirport, DestAirport destAirport, Airplane airplane, int hostessID){
        this.hostessID=hostessID;
        this.destAirport=destAirport;
        this.depAirport=depAirport;
        this.airplane=airplane;
    }

    /**
     * Get Hostess state.
     *
     * @return Hostess State.
     */
    public int getHostessState() {
        return hostessState;
    }

    /**
     * Set hostess id.
     *
     * @param hostessID new hostess id.
     */
    public void setHostessID(int hostessID) {
        this.hostessID = hostessID;
    }

    /**
     * Get hostess ID.
     *
     * @return hostess ID.
     */
    public int getHostessID() {
        return hostessID;
    }

    /**
     * Set Hostess State.
     *
     * @param hostessState new costumer id.
     */
    public void setHostessState(int hostessState) {
        this.hostessState = hostessState;
    }

    /**
     * Life cycle of the Hostess.
     */
    @Override
    public void run() {
        prepareForPassBoarding();
        int i=0;
        while(depAirport.getFlew()!=21){
            i++;
            System.out.println("corri "+i);
            depAirport.waitForNextPassenger();
            depAirport.checkDocuments();
            depAirport.informPlaneReadyToTakeOff();
            depAirport.waitForNextFlight();

        }

    }

    /**
     * Hostess prepares for the passengers to board.
     *
     * Internal Operation.
     */
    public void prepareForPassBoarding() {
        hostessState = HostessStates.waitForPassenger;
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
