package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This data type defines the operational interface of a remote object
 * of type/shared region Airplane.
 */

public interface AirplaneInt extends Remote {

    /**
     * Operation board the plane
     * <p>
     * It is called by the passenger after having the documents checked
     */
    int boardThePlane(int passengerId) throws RemoteException;

    /**
     * Operation wait for the end of the flight
     * <p>
     * It is called by a Passenger while it doesn't reach its destination
     */

    void waitForEndOfFlight() throws RemoteException;

    /**
     * Operation leave the airplane
     * <p>
     * It is called by the passenger after the flight lands
     */

    void leaveThePlane(int passengerId) throws RemoteException;

    /**
     * Operation announce arrival.
     * <p>
     * It is called by the pilot after the airplane lands at the destination.
     */

    void announceArrival() throws RemoteException;

    /**
     * Operation park at the transfer gate.
     * <p>
     * It is called by the pilot once it has landed back at the departure airport.
     */

    void parkAtTransferGate() throws RemoteException;

    /**
     * Shut server operation
     * @throws RemoteException
     */
    void shutServer() throws RemoteException;
}