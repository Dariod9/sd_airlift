package shared;

import entities.Hostess;
import entities.Passenger;
import entities.Pilot;

import java.util.Arrays;

public class AirLift {
    public static void main(String [] args){

        int nr=21;
        Repository repos = new Repository(nr);
        DepAirport depAirport= new DepAirport(repos);
        DestAirport destAirport= new DestAirport(repos);
        Airplane airplane = new Airplane(repos);

        Passenger [] passageiros = new Passenger[nr];
        Pilot pilot= new Pilot(depAirport,destAirport,airplane,0);
        Hostess hostess= new Hostess(depAirport, destAirport, airplane, 0);

        System.out.println("A INICIAR PASSAGEIROS");
        for(int i=0;i<passageiros.length;i++){
            passageiros[i]=new Passenger(depAirport, destAirport, airplane, i);
        }

        pilot.start();
        hostess.start();
        Arrays.stream(passageiros).forEach(x -> x.start());

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
