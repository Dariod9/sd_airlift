package interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This data type defines the operational interface of a remote object
 * of type/shared region Departure Airport.
 */

public interface DepAirportInt extends Remote{

    /**
     *  Get Passenger that flew
     *
     * @return flew
     */
    int getFlew() throws RemoteException;


    /**
     * Operation enter and wait in the Queue.
     *
     * It is called by a passenger after he arrives to the airport, while he waits to be called by the hostess.
     *
     */
    void waitInQueue(int passengerId) throws RemoteException;

    /**
     * Operation wait for the next passenger.
     *
     * It is called by the hostess while there are no passengers in the Queue for her to call.
     * @return
     */
    boolean waitForNextPassenger() throws RemoteException;

    /**
     * Operation check for documents.
     *
     * It is called by the hostess when she checks the documents of a passenger.
     *
     */

    void checkDocuments() throws RemoteException;

    /**
     * Operation inform that the plane is ready to take off.
     *
     * It is called by the hostess when the plane is ready to board.
     *
     */

    void informPlaneReadyToTakeOff() throws RemoteException;

    /**
     * Operation wait for the next flight.
     *
     * It is called by the hostess while she waits for the next flight to happen.
     *
     */

    void waitForNextFlight() throws RemoteException;

    /**
     * Operation inform that passengers can start boarding the plane.
     *
     * It is called by the pilot after arriving to the transfer gate.
     */

    void informPlaneReadyForBoarding() throws RemoteException;

    /**
     * Operation wait for the passengers.
     *
     * It is called by the pilot while he waits for enough passengers to enter the plane.
     */

    void waitForAllInBoard() throws RemoteException;

    /**
     * Operation fly to destination.
     *
     * It is called by the pilot when the plane is ready to take off.
     *
     */

    void flyToDestinationPoint() throws RemoteException;


    /**
     * Hostess prepares for the passengers to board.
     *
     * Internal Operation.
     */
    void prepareForPassBoarding() throws RemoteException;

    /**
     * Check if the last passenger as entered the plane
     *
     * @param passengerID
     */
    void passengerEnteredPlane(int passengerID) throws RemoteException;

    /**
     * Shut server operation
     * @throws RemoteException
     */
    void shutServer() throws RemoteException;

}
