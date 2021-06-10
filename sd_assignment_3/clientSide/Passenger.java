package clientSide;

import interfaces.DepAirportInt;
import interfaces.AirplaneInt;
import genclass.*;

import java.rmi.RemoteException;

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
    private DepAirportInt depAirport;

    /**
     * Reference to the airplane.
     */
    private AirplaneInt airplane;

    /**
     * Instantiation of a Airplane thread.
     *
     * @param depAirport reference to departure airport
     * @param airplane reference to airplane
     * @param id passenger id
     */
    public Passenger(DepAirportInt depAirport, AirplaneInt airplane, int id) {
        this.passengerId = id;
        this.depAirport = depAirport;
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
        int passenger=0;
        travelToAirport();
        try {
            depAirport.waitInQueue(passengerId);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            passenger = airplane.boardThePlane(passengerId);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            depAirport.passengerEnteredPlane(passenger);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            airplane.waitForEndOfFlight();
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            airplane.leaveThePlane(passengerId);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(1);
        }
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
        //primeira multiplicação -> número de passageiros que chega de cada vez
        a = (passengerId/10)*2 * 1500;

        try {
            sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GenericIO.writelnString("Passenger "+Thread.currentThread().getName()+" arrived at the airport");
    }

}

