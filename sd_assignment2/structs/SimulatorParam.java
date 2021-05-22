package structs;

import java.util.Date;

/**
 * This class has stored all the simulation parameters
 * needed to run the program.
 */


// máquina: l040101-ws#.ua.pt
// login: sd34
// pass: qwerty
// IP pool: 22330 - 22339


public class SimulatorParam {


    public static int idPcSala=0;

    /**
     * Number of passengers
     */
    public static final int NUM_PASSANGERS = 25;
     */
    public static final int PLANE_CAPACITY_MAX = 10;

    public static final int PLANE_CAPACITY_MIN = 5;

    /**
     * Log file name to store the status of the program
     */
    public static final String fileName = "log" + new Date().toString().replace(' ', '_').replace(':', '_') + ".txt";

    /**
     * Arrival lounge host name
     * @serial arrivalLoungeHostName
     */
    public static final String DepAirportHostName = "localhost";

    /**
     * Arrival lounge port number
     * @serial arrivalLoungePort
     */
    public static final int DepAirportPort = 22330 ;


    public static final String DestAirportHostName = "localhost";
    /**
     * Arrival lounge port number
     * @serial arrivalLoungePort
     */
    public static final int DestAirportPort = 22331 ;


    public static final String AirplaneHostName = "localhost";

    /**
     * Arrival lounge port number
     * @serial arrivalLoungePort
     */
    public static final int AirplanePort = 22332 ;

}