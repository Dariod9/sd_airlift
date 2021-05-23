package clientSide.main;
import clientSide.entitiesStubs.DepartureAirportStub;
import clientSide.entitiesStubs.DestinationAirportStub;
import clientSide.entitiesStubs.AirplaneStub;
import clientSide.entities.*;

public class HostessMain {
    public static void main(String args[]) {
        final int TOTAL=21;                                                                     // total number of passengers
        final int MAX=5;                                                                        // max number of passengers
        final int MIN=5;

        DepartureAirportStub depAirportStub = new DepartureAirportStub();
        DestinationAirportStub destAirportStub = new DestinationAirportStub();
        AirplaneStub airplaneStub = new AirplaneStub();

        Hostess hostess = new Hostess(depAirportStub,destAirportStub,airplaneStub,0,TOTAL);

        hostess.start();

        try{
            hostess.join();
        } catch(InterruptedException e){}

    }
}
