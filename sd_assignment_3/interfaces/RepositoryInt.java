package interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;



/**
 * This data type defines the operational interface of a remote object
 * of type/shared region Repository.
 */

public interface RepositoryInt extends Remote {

    /**
     *  Get number of passengers in Queue
     * @return Number of passengers
     */

    int getInqe() throws RemoteException;

    /**
     *  Set number of passengers in Queue
     * @param inqe number of passengers
     */

     void setInqe(int inqe)throws RemoteException;

    /**
     *   Get number of passengers in flight.
     * @return number of passengers.
     */

     int getInpl() throws RemoteException;

    /**
     *  Set number of passengers in flight.
     * @param inpl number of passengers.
     */

     void setInpl(int inpl) throws RemoteException;

    /**
     *  Get number of passengers at the destination.
     * @return number of passengers
     */

     int getAtds() throws RemoteException;

    /**
     *  Set number of passengers at the destination.
     * @param atds number of passengers.
     */

     void setAtds(int atds) throws RemoteException;

    /**
     *  Set pilot state
     * @param state pilot state
     */

      void setPilotState(int state) throws RemoteException;

    /**
     *  Set hostess state.
     * @param state hostess state.
     */

    void setHostessState(int state) throws RemoteException;

    /**
     * Set hostess state after checking a passenger.
     * @param state hostess state.
     * @param id_passenger ID of the passenger checked.
     */

    void setHostessState(int state, int id_passenger) throws RemoteException;

    /**
     *  Set passenger state.
     * @param id passenger id.
     * @param state passenger state.
     */

    void setPassengerState(int id, int state) throws RemoteException;

    /**
     *  Link a flight number to its occupation.
     * @param inpl occupation.
     */

    void addFlightInfo(int inpl) throws RemoteException;

    /**
     *  Write a summary of the flights needed to transport all the passengers.
     *
     *  The current state of the pilot, hostess and passengers are organized in a line to be printed.
     *  Internal operation.
     */
    void reportSummary() throws RemoteException;

    /**
     * Shut server operation
     * @throws RemoteException
     */
    void shutServer() throws RemoteException;

}