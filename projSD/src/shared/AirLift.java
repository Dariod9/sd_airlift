package shared;

import entities.Hostess;
import entities.Passenger;
import entities.Pilot;

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

        final int MAX=21;                                                                   // total number of passengers
        Repository repos = new Repository(MAX);                                             // reference to rhe repository
        DepAirport depAirport= new DepAirport(repos);                                       // reference to the departure airport
        DestAirport destAirport= new DestAirport(repos);                                    // reference to the destination airport
        Airplane airplane = new Airplane(repos);                                            // reference to the airplane
        Passenger [] passageiros = new Passenger[MAX];                                      // array of passengers


        /* problem initialization */

        Pilot pilot= new Pilot(depAirport,destAirport,airplane,0);
        Hostess hostess= new Hostess(depAirport, destAirport, airplane, 0);

        System.out.println("A INICIAR PASSAGEIROS");
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
