package shared;

import entities.HostessStates;
import entities.PassengerStates;
import entities.PilotStates;
import genclass.GenericIO;
import genclass.TextFile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Repository {
    private final String filename;
    private final int nPassengers;

    private static int flight_number = 0;

    private int [] passengerState;
    private int hostessState;
    private int pilotState;

    private int inqe;   // passengers in queue
    private int inpl;   // passengers in plane
    private int atds;   // passengers at destination

    private HashMap<Integer, Integer> flights_info;

    public Repository(int nPassangers) {
        this.filename = "logger";
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

    public int getInqe() {
        return inqe;
    }

    public void setInqe(int inqe) {
        this.inqe = inqe;
    }

    public int getInpl() {
        return inpl;
    }

    public void setInpl(int inpl) {
        this.inpl = inpl;
    }

    public int getAtds() {
        return atds;
    }

    public void setAtds(int atds) {
        this.atds = atds;
    }

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

    public synchronized void setHostessState(int state){
        this.hostessState = state;
        reportStatus("   hostess");
    }

    public synchronized void setHostessState(int state, int id_passenger){
        this.hostessState = state;

        writeSmallHeader(flight_number, "passenger " + id_passenger + " checked");

        reportStatus("   hostess");
    }

    public synchronized void setPassengerState(int id, int state){
        this.passengerState[id] = state;

        switch (state){
            case PassengerStates.inQueue:
                setInqe(getInqe() + 1);
                break;
            case PassengerStates.inFlight:
                setInpl(getInpl() + 1);
                setInqe(getInqe() - 1);
                break;
            case PassengerStates.atDestination:
                setAtds(getAtds() + 1);
                setInpl(getInpl() - 1);
                break;
        }

        reportStatus("   passenger");
    }

    public synchronized void addFlightInfo(int inpl){
        flights_info.put(flight_number, inpl);
    }

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