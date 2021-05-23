package clientSide.entities;


import clientSide.entitiesStubs.*;

/**
 *   Hostess thread.
 *
 *   It simulates the hostess life cycle.
 *   Static solution.
 */

public class Hostess extends Thread{

    /**
     * Hostess state.
     */
    private int hostessState;

    /**
     * Hostess identification.
     */
    private int hostessID;

    /**
     * Total number of passengers.
     */
    private int TOTAL;

    /**
     * Reference to the departure airport.
     */
    private final DepartureAirportStub depAirportStub;


    /**
     * Instantiation of a Hostess thread.
     *
     * @param depAirportStub reference to departure airport
     * @param hostessID hostess id
     */
    public Hostess(DepartureAirportStub depAirportStub,  int hostessID, int TOTAL){
        this.TOTAL = TOTAL;
        this.hostessID=hostessID;
        this.depAirportStub=depAirportStub;
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
        while(depAirportStub.getFlewHostess()!=TOTAL){
            depAirportStub.waitForNextFlight();
            depAirportStub.prepareForPassBoarding();
            while(true) {
                if(depAirportStub.waitForNextPassenger()) break;
                depAirportStub.checkDocuments();
            }
            depAirportStub.informPlaneReadyToTakeOff();
        }
    }



}
