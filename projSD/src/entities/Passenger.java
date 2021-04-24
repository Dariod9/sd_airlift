package entities;

import shared.Airplane;
import shared.DepAirport;
import shared.DestAirport;

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
     * Passenger called by the hostess.
     */
    private boolean called;

    /**
     * Passenger checked by the hostess.
     */
    private boolean checked;

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
     * @param depAirport
     * @param destAirport
     * @param airplane
     * @param id
     */
    public Passenger(DepAirport depAirport, DestAirport destAirport, Airplane airplane, int id) {

        this.called = false;
        this.checked = false;
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
        depAirport.showDocuments();
        airplane.boardThePlane();
        airplane.waitForEndOfFlight();
        airplane.leaveThePlane();
    }

    /**
     * Passenger travel to airport.
     *
     * Internal Operation.
     */
    public void travelToAirport() {
        passengerState = PassengerStates.goingToAirport;

        long a = 0;
//        if(passengerId>18)
        a = passengerId * 1500;//long) Math.random();
//        else
//            a=4000* (long) Math.random();
        try {
            sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Passageiro " + this.passengerId + " chegou ao aeroporto");
    }

}

