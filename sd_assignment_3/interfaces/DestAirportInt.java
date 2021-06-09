package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This data type defines the operational interface of a remote object
 * of type/shared region Destination Airport.
 */

public interface DestAirportInt extends Remote{


    /**
     * Operation fly to the departure airport.
     *
     * It is called by the Pilot after all the passengers leave the plane.
     */

    void flyToDeparturePoint() throws RemoteException;

    /**
     * Shut server operation
     * @throws RemoteException
     */
    void shutServer() throws RemoteException;
}
