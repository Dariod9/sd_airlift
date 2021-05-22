package serverSide.sharedRegionInterfaces;

import entities.HostessStates;
import entities.PassengerStates;
import entities.PilotStates;
import genclass.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *  Repository
 *  It is responsible to keep the visible internal state of the problem and print it in the logging file.
 *  All public methods are executed in mutual exclusion.
 *  It contains no internal synchronization points.
 */

public interface RepositoryInt {

    /**
     *  Get number of passengers in Queue
     * @return Number of passengers
     */

    int getInqe();

    /**
     *  Set number of passengers in Queue
     * @param inqe number of passengers
     */

    void setInqe(int inqe);

    /**
     *   Get number of passengers in flight.
     * @return number of passengers.
     */

    int getInpl();

    /**
     *  Set number of passengers in flight.
     * @param inpl number of passengers.
     */

    void setInpl(int inpl);

    /**
     *  Get number of passengers at the destination.
     * @return number of passengers
     */

    int getAtds();

    /**
     *  Set number of passengers at the destination.
     * @param atds number of passengers.
     */

    void setAtds(int atds);

    /**
     *  Set pilot state
     * @param state pilot state
     */

    void setPilotState(int state);

    /**
     *  Set hostess state.
     * @param state hostess state.
     */

    void setHostessState(int state);

    /**
     * Set hostess state after checking a passenger.
     * @param state hostess state.
     * @param id_passenger ID of the passenger checked.
     */

    void setHostessState(int state, int id_passenger);

    /**
     *  Set passenger state.
     * @param id passenger id.
     * @param state passenger state.
     */

    void setPassengerState(int id, int state);

    /**
     *  Link a flight number to its occupation.
     * @param inpl occupation.
     */

    void addFlightInfo(int inpl);
}