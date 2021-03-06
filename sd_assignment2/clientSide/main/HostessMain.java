package clientSide.main;
import clientSide.entitiesStubs.DepartureAirportStub;
import clientSide.entitiesStubs.DestinationAirportStub;
import clientSide.entitiesStubs.AirplaneStub;
import clientSide.entities.*;
import genclass.GenericIO;

/**
 * This class implements the Hostess Main
 * instantiates the shared region Stubs which the Hostess interacts with
 * instantiates the Hostess entity, starts it thread and, in the end, joins it
 */
public class HostessMain {
    public static void main(String args[]) {
        final int TOTAL=21;                                                                     // total number of passengers
        final int MAX=5;                                                                        // max number of passengers
        final int MIN=5;

        DepartureAirportStub depAirportStub = new DepartureAirportStub();

        Hostess hostess = new Hostess(depAirportStub,0,TOTAL);

        hostess.start();
        GenericIO.writelnString(    "Hostess thread" + Thread.currentThread().getName() + "Started");

        try{
            hostess.join();
        } catch(InterruptedException e){}

    }
}
