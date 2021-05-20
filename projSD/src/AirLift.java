import entities.Hostess;
import entities.Passenger;
import entities.Pilot;
import genclass.*;
import shared.Airplane;
import shared.DepAirport;
import shared.DestAirport;
import shared.Repository;

import java.util.Arrays;

/**
 * Simulation of the Air Lift Concurrent Implementation.
 * Static solution.
 */

public class AirLift {

    /**
     *  Main method.
     *
     *  @param args runtime arguments
     */
    public static void main(String [] args){

        final int TOTAL=21;                                                                     // total number of passengers
        final int MAX=5;                                                                        // max number of passengers
        final int MIN=5;                                                                        // min number of passengers

        boolean success;                                                                        // flag
        String fileName;                                                                        // file name
        char opt;                                                                               // selected option

        do
        { GenericIO.writeString ("Logging file name? ");
            fileName = GenericIO.readlnString ();
            if (fileName == null){
                fileName = "logger";
            }
            if (FileOp.exists (".", fileName)) {
                do {
                    GenericIO.writeString ("There is already a file with this name. Delete it (y - yes; n - no)? ");
                    opt = GenericIO.readlnChar ();
                } while ((opt != 'y') && (opt != 'n'));
                success = (opt == 'y');
            }
            else success = true;

        } while (!success);

        Repository repos = new Repository(TOTAL, fileName);                                     // reference to rhe repository
        DepAirport depAirport= new DepAirport(repos,TOTAL,MIN,MAX);                             // reference to the departure airport
        DestAirport destAirport= new DestAirport(repos);                                        // reference to the destination airport
        Airplane airplane = new Airplane(repos);                                                // reference to the airplane
        Passenger [] passageiros = new Passenger[TOTAL];                                        // array of passengers

        /* problem initialization */

        Pilot pilot= new Pilot(depAirport,destAirport,airplane,0,TOTAL);
        Hostess hostess= new Hostess(depAirport, destAirport, airplane, 0,TOTAL);

        for(int i=0;i<passageiros.length;i++){
            passageiros[i]=new Passenger(depAirport, destAirport, airplane, i);
        }

        /* start of the simulation */

        pilot.start();
        hostess.start();
        Arrays.stream(passageiros).forEach(x -> x.start());

        /* waiting for the end of the simulation */

        Arrays.stream(passageiros).forEach(x -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            hostess.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            pilot.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repos.reportSummary();
    }

}
