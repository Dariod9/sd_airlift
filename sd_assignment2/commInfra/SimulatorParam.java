package commInfra;

import java.util.Date;

/**
 * This class has stored all the simulation parameters
 * needed to run the program.
 */


// máquina: l040101-ws#.ua.pt
// login: sd304
// pass: qwerty
// IP pool: 22330 - 22339



public class SimulatorParam {


    public static int idPcSala=0;

    /**
     * Number of passengers
     */
    public static final int NUM_PASSANGERS = 21;
    public static final int PLANE_CAPACITY_MAX = 10;

    public static final int PLANE_CAPACITY_MIN = 5;
    /**
     * Number of pilot states
     */
    public static final int PILOT_STATES = 6;
    /**
     * Number of hostess states
     */
    public static final int HOSTESS_STATES = 4;
    /**
     * Number of passenger states
     */
    public static final int PASSENGER_STATES = 4;

    /**
     * Log file name to store the status of the program
     */
    public static final String fileName = "log" + new Date().toString().replace(' ', '_').replace(':', '_') + ".txt";

    /**
     * Arrival lounge host name
     * @serial arrivalLoungeHostName
     */
    public static final String DepAirportHostName = "l040101-ws05.ua.pt";

    /**
     * Arrival lounge port number
     * @serial arrivalLoungePort
     */
    public static final int DepAirportPort = 22330 ;


    public static final String DestAirportHostName = "l040101-ws06.ua.pt";
    /**
     * Arrival lounge port number
     * @serial arrivalLoungePort
     */
    public static final int DestAirportPort = 22331 ;


    public static final String AirplaneHostName = "l040101-ws04.ua.pt";

    /**
     * Arrival lounge port number
     * @serial arrivalLoungePort
     */
    public static final int AirplanePort = 22332 ;

    public static final String RepositoryHostName = "l040101-ws07.ua.pt";

    /**
     * Arrival lounge port number
     * @serial arrivalLoungePort
     */
    public static final int RepositoryPort = 22333 ;

}