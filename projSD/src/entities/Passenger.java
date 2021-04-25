package entities;

import shared.Airplane;
import shared.DepAirport;
import shared.DestAirport;
import genclass.*;

/**
 *   Passenger thread.
 *
 *   It simulates the passenger life cycle.
 *   Static solution.
 */


public class Passenger extends Thread {

    /**
     * Passenger identification.
     */
    private int passengerId;

    /**
     * Passenger State.
     */
    private int passengerState;

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
     * Instantiation of a Airplane thread.
     *
     * @param depAirport reference to departure airport
     * @param destAirport reference to destination airport
     * @param airplane reference to airplane
     * @param id passenger id
     */
    public Passenger(DepAirport depAirport, DestAirport destAirport, Airplane airplane, int id) {
        this.passengerId = id;
        this.depAirport = depAirport;
        this.destAirport = destAirport;
        this.airplane = airplane;
    }


    /**
     * Get passenger id.
     *
     * @return passenger id.
     */
    public int getPassengerId() {
        return passengerId;
    }

    /**
     * Set customer id.
     *
     * @param passengerId new costumer id.
     */
    public void setpassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    /**
     * Get passenger State.
     *
     * @return passenger state.
     */
    public int getPassengerState() {
        return passengerState;
    }

    /**
     * Set passenger state.
     *
     * @param passengerState new passenger state.
     */
    public void setPassengerState(int passengerState) {
        this.passengerState = passengerState;
    }



    /**
     * Life cycle of the Passenger.
     */
    @Override
    public void run() {
        travelToAirport();
        depAirport.waitInQueue();
        airplane.boardThePlane();
        airplane.waitForEndOfFlight();
        airplane.leaveThePlane();
    }

    /**
     * Operation show documents.
     *
     * It is called by the passenger after being called by the hostess and before boarding the plane.
     *
     */

    public void showDocuments() {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Passenger travel to airport.
     *
     * Internal Operation.
     */
    public void travelToAirport() {
        passengerState = PassengerStates.goingToAirport;

        long a = 0;
        a = (passengerId/5)*2 * 1500;

        try {
            sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GenericIO.writelnString("Passenger "+Thread.currentThread().getName()+" arrived at the airport");
    }

}

