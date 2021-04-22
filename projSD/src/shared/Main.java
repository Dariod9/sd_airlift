package shared;

import entities.Hostess;
import entities.Passenger;
import entities.Pilot;
import structs.MemException;
import structs.MemFIFO;

import java.util.Arrays;

public class Main {
    public static void main(String [] args){

        DepAirport depAirport= new DepAirport();
        DestAirport destAirport= new DestAirport();
        Airplane airplane = new Airplane();

        Passenger [] passageiros = new Passenger[21];
        Pilot pilot= new Pilot(depAirport,destAirport,airplane,0);
        Hostess hostess= new Hostess(depAirport, destAirport, airplane, 0);

        System.out.println("A INICIAR PASSAGEIROS");
        for(int i=0;i<passageiros.length;i++){
            passageiros[i]=new Passenger(depAirport, destAirport, airplane, i);
        }

        Arrays.stream(passageiros).forEach(x -> x.start());
        pilot.start();
        hostess.start();








    }

}
