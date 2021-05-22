package clientSide.main;
import clientSide.entitiesStubs.DepartureAirportStub;
import clientSide.entitiesStubs.DestinationAirportStub;
import clientSide.entitiesStubs.AirplaneStub;
import clientSide.entities.*;


/**
 * This class implements the Pilot Main. It instantiates the shared region Stubs which the Pilot interacts with
 * and most importantly it instantiates the Pilot entity, starts its thread and finally joins it and shutdowns the servers related
 * to the Stubs previously instantiated.
 */
public class PilotMain {
    public static void main(String args[]) {
        final int TOTAL=21;                                                                     // total number of passengers
        final int MAX=5;                                                                        // max number of passengers
        final int MIN=5;

        DepartureAirportStub depAirportStub = new DepartureAirportStub();
        DestinationAirportStub destAirportStub = new DestinationAirportStub();
        AirplaneStub airplaneStub = new AirplaneStub();

        Pilot pilot = new Pilot(depAirportStub,destAirportStub,airplaneStub,0,TOTAL);

        pilot.start();

        try{
            pilot.join();
        } catch(InterruptedException e){}

        depAirportStub.shutServer();
        destAirportStub.shutServer();
        airplaneStub.shutServer();

    }
}