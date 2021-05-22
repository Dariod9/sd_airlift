package serverSide;

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

public class Repository {

    /**
     * Name of the logging file.
     */

    private final String filename;

    /**
     * Number of iterations of the passenger life cycle.
     */

    private final int nPassengers;

    /**
     * Number of departing flights.
     */

    private static int flight_number = 0;

    /**
     * State of the passengers.
     */

    private int [] passengerState;

    /**
     *  State of the hostess.
     */

    private int hostessState;

    /**
     * State of the pilot.
     */

    private int pilotState;

    /**
     * Passengers in Queue
     */

    private int inqe;   // passengers in queue

    /**
     * Passengers in Flight
     */

    private int inpl;   // passengers in plane

    /**
     * Passengers at the Destination.
     */

    private int atds;   // passengers at destination

    /**
     * Association of the flight to its' occupation.
     */

    private HashMap<Integer, Integer> flights_info;

    /**
     *  Instantiation of a repository object.
     *
     * @param nPassangers number of iterations of the passenger life cycle
     *
     */

    public Repository(int nPassangers, String filename) {
        this.filename = filename;
        this.nPassengers = nPassangers;
        this.passengerState = new int [nPassangers];
        for(int i = 0; i < nPassangers; i++){
            passengerState[i] = PassengerStates.goingToAirport;
        }
        this.hostessState = HostessStates.waitForFlight;
        this.pilotState = PilotStates.atTransferGate;

        this.inqe = 0;
        this.inpl = 0;
        this.atds = 0;

        flights_info = new HashMap<>();

        writeHeader();
    }

    /**
     *  Get number of passengers in Queue
     * @return Number of passengers
     */

    public int getInqe() {
        return inqe;
    }

    /**
     *  Set number of passengers in Queue
     * @param inqe number of passengers
     */

    public void setInqe(int inqe) {
        this.inqe = inqe;
    }

    /**
     *   Get number of passengers in flight.
     * @return number of passengers.
     */

    public int getInpl() {
        return inpl;
    }

    /**
     *  Set number of passengers in flight.
     * @param inpl number of passengers.
     */

    public void setInpl(int inpl) {
        this.inpl = inpl;
    }

    /**
     *  Get number of passengers at the destination.
     * @return number of passengers
     */

    public int getAtds() {
        return atds;
    }

    /**
     *  Set number of passengers at the destination.
     * @param atds number of passengers.
     */

    public void setAtds(int atds) {
        this.atds = atds;
    }

    /**
     *  Set pilot state
     * @param state pilot state
     */

    public synchronized void setPilotState(int state){
        if (this.pilotState != state){
            if(state == PilotStates.deBoarding){
                writeSmallHeader(flight_number, "flight arrived");
            }
            else if(state == PilotStates.flyingBack){
                writeSmallHeader(flight_number, "returning");
            }
        }
        this.pilotState = state;
        reportStatus("   pilot");
    }

    /**
     *  Set hostess state.
     * @param state hostess state.
     */

    public synchronized void setHostessState(int state){
        this.hostessState = state;
        reportStatus("   hostess");
    }

    /**
     * Set hostess state after checking a passenger.
     * @param state hostess state.
     * @param id_passenger ID of the passenger checked.
     */

    public synchronized void setHostessState(int state, int id_passenger){
        this.hostessState = state;

        writeSmallHeader(flight_number, "passenger " + id_passenger + " checked");
        setInqe(getInqe() - 1);
        reportStatus("   hostess");
    }

    /**
     *  Set passenger state.
     * @param id passenger id.
     * @param state passenger state.
     */

    public synchronized void setPassengerState(int id, int state){
        this.passengerState[id] = state;

        switch (state){
            case PassengerStates.inQueue:
                inqe += 1;
                break;
            case PassengerStates.inFlight:
                inpl += 1;

                break;
            case PassengerStates.atDestination:
                atds += 1;
                inpl -= 1;
                break;
        }

        reportStatus("   passenger");
    }

    /**
     *  Link a flight number to its occupation.
     * @param inpl occupation.
     */

    public synchronized void addFlightInfo(int inpl){
        flights_info.put(flight_number, inpl);
    }

    /**
     *  Write the header to the logging file.
     *
     *  The passengers are sleeping and the hostess and pilot are carrying out normal duties.
     *  Internal operation.
     */

    private void writeHeader(){
        TextFile log = new TextFile ();                      // instantiation of a text file handler

        if (!log.openForWriting(".", filename)) {
            GenericIO.writelnString ("The operation of creating the file " + filename + " failed!");
            System.exit (1);
        }
        log.writelnString ("                Airlift - Description of internal state");
        //log.writelnString ("\nNumber of iterations = " + nIter + "\n");
        log.writelnString ("  PT    HT   P00   P01   P02   P03   P04   P05   P06   P07   P08   P09   P10   P11   P12   P13   P14   P15   P16   P17   P18   P19   P20   InQ  InF  PTAL  Entity");

        if (!log.close ()) {
            GenericIO.writelnString ("The operation of closing the file " + filename + " failed!");
            System.exit (1);
        }
        reportStatus("   header");
    }

    /**
     *  Write a state line at the beginning of the logging file with the initial states.
     *
     *  The current state of the pilot, hostess and passengers are organized in a line to be printed.
     *  Internal operation.
     */

    private void writeSmallHeader(int nFlight, String message){
        TextFile log = new TextFile ();                      // instantiation of a text file handler

        if (!log.openForAppending(".", filename)) {
            GenericIO.writelnString ("The operation of creating the file " + filename + " failed!");
            System.exit (1);
        }

        log.writelnString(""); // linha em branco
        log.writelnString(" Flight " + nFlight + ": " + message);

        if (!log.close ()) {
            GenericIO.writelnString ("The operation of closing the file " + filename + " failed!");
            System.exit (1);
        }
    }

    /**
     *  Write a state line at the end of the logging file.
     *
     *  The current state of the pilot, hostess and passengers are organized in a line to be printed.
     *  Internal operation.
     */

    private void reportStatus(String teste){
        TextFile log = new TextFile ();                      // instantiation of a text file handler

        String lineStatus = "";                              // state line to be printed

        if (!log.openForAppending (".", filename)) {
            GenericIO.writelnString ("The operation of opening for appending the file " + filename + " failed!");
            System.exit (1);
        }

        // estado do piloto
        switch (pilotState){
            case PilotStates.atTransferGate:
                lineStatus += " ATRG ";
                break;
            case PilotStates.readyForBoarding:
                lineStatus += " RDFB ";
                flight_number++;
                writeSmallHeader(flight_number, "boarding started");
                break;
            case PilotStates.waitingForBoarding:
                lineStatus += " WTFB ";
                break;
            case PilotStates.flyingForward:
                lineStatus += " FLFW ";
                writeSmallHeader(flight_number, "departed with " + flights_info.get(flight_number) + " passengers");
                break;
            case PilotStates.deBoarding:
                lineStatus += " DRPP ";
                break;
            case PilotStates.flyingBack:
                lineStatus += " FLBK ";
                break;
        }

        // estado da hostess
        switch (hostessState){
            case HostessStates.waitForFlight:
                lineStatus += " WTFL ";
                break;
            case HostessStates.waitForPassenger:
                lineStatus += " WTPS ";
                break;
            case HostessStates.checkPassenger:
                lineStatus += " CKPS ";
                break;
            case HostessStates.readyToFly:
                lineStatus += " RDTF ";
                break;
        }

        // estado dos passageiros
        for(int i = 0; i < nPassengers; i++){
            switch (passengerState[i]){
                case PassengerStates.goingToAirport:
                    lineStatus += " GTAP ";
                    break;
                case PassengerStates.inQueue:
                    lineStatus += " INQE ";
                    break;
                case PassengerStates.inFlight:
                    lineStatus += " INFL ";
                    break;
                case PassengerStates.atDestination:
                    lineStatus += " ATDS ";
                    break;
            }
        }

        // number passenger in queue to board the plane
        lineStatus += "  " + inqe;
        // number passenger in the plane
        lineStatus += "    " + inpl;
        // number passenger that have already flew
        lineStatus += "    " + atds;

        // escrever linha no ficheiro
        log.writelnString (lineStatus + "\t"+teste);

        if (!log.close ())
        { GenericIO.writelnString ("The operation of closing the file " + filename + " failed!");
            System.exit (1);
        }
    }

    /**
     *  Write a summary of the flights needed to transport all the passengers.
     *
     *  The current state of the pilot, hostess and passengers are organized in a line to be printed.
     *  Internal operation.
     */

    public synchronized void reportSummary(){
        TextFile log = new TextFile ();                      // instantiation of a text file handler

        if (!log.openForAppending(".", filename)) {
            GenericIO.writelnString ("The operation of creating the file " + filename + " failed!");
            System.exit (1);
        }

        log.writelnString("\nAirLift sum up:");
        Set<Map.Entry<Integer, Integer>> set = flights_info.entrySet();
        Iterator<Map.Entry<Integer, Integer>> i = set.iterator();
        while(i.hasNext()) {
            HashMap.Entry<Integer, Integer> me = i.next();
            log.writelnString("Flight " + me.getKey() + " transported " + me.getValue() + " passengers");
        }

        if (!log.close ())
        { GenericIO.writelnString ("The operation of closing the file " + filename + " failed!");
            System.exit (1);
        }
    }
}