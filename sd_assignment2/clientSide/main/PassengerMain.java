package clientSide.main;
import clientSide.entitiesStubs.DepartureAirportStub;
import clientSide.entitiesStubs.DestinationAirportStub;
import clientSide.entitiesStubs.AirplaneStub;
import clientSide.entities.*;

import java.util.Arrays;
import genclass.GenericIO;

/**
 * This class implements the Passenger Main
 * instantiates the shared region Stubs which the Passenger interacts with
 * instantiates the Passenger entity, starts it thread and, in the end, joins it
 */
public class PassengerMain {
    public static void main(String args[]) {
        final int TOTAL=21;                                                                     // total number of passengers
        final int MAX=5;                                                                        // max number of passengers
        final int MIN=5;

        DepartureAirportStub depAirportStub = new DepartureAirportStub();
        DestinationAirportStub destAirportStub = new DestinationAirportStub();
        AirplaneStub airplaneStub = new AirplaneStub();
        Passenger[] passengers = new Passenger[TOTAL];

        for(int i=0;i<passengers.length;i++){
            passengers[i] = new Passenger(depAirportStub, destAirportStub, airplaneStub,i);
        }


        Arrays.stream(passengers).forEach(x -> x.start());
        GenericIO.writelnString("Passengers threads Started");


        Arrays.stream(passengers).forEach(x -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
