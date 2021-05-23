package clientSide.entities;
import clientSide.entitiesStubs.DepartureAirportStub;
import clientSide.entitiesStubs.DestinationAirportStub;
import clientSide.entitiesStubs.AirplaneStub;
import structs.entitiesInterfaces.PilotInt;

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
     * Total number of passengers.
     */
    private int TOTAL;

    /**
     * Reference to the departure airport.
     */
    private final DepartureAirportStub depAirportStub;

    /**
     * Reference to the departure airport.
     */
    private final DestinationAirportStub destAirportStub;

    /**
     * Reference to the airplane.
     */
    private final AirplaneStub airplaneStub;

    /**
     * Instantiation of a Pilot thread.
     *
     * @param depAirportStub reference to departure airport
     * @param destAirportStub reference to destination airport
     * @param airplaneStub reference to airplane
     * @param pilotID pilot id
     */
    public Pilot (DepartureAirportStub depAirportStub, DestinationAirportStub destAirportStub, AirplaneStub airplaneStub, int pilotID, int TOTAL) {
        this.TOTAL=TOTAL;
        this.pilotID=pilotID;
        this.depAirportStub=depAirportStub;
        this.destAirportStub=destAirportStub;
        this.airplaneStub=airplaneStub;
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
     * Life cycle of the Pilot
     */
    @Override
    public void run() {
        while(depAirportStub.getFlewPilot()<TOTAL) {
            depAirportStub.informPlaneReadyForBoarding();
            depAirportStub.waitForAllInBoard();
            depAirportStub.flyToDestinationPoint();
            airplaneStub.announceArrival();
            destAirportStub.flyToDeparturePoint();
            airplaneStub.parkAtTransferGate();

        }
    }



}
