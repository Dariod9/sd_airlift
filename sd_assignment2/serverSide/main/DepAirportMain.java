package serverSide.main;

import clientSide.entitiesStubs.DepartureAirportStub;
import clientSide.entitiesStubs.RepositoryStub;
import serverSide.serverProxys.DepartureAirportProxy;
import serverSide.sharedRegions.DepAirport;
import serverSide.sharedRegions.Repository;
import serverSide.ServerCom;
import serverSide.sharedRegionInterfaces.DepAirportInt;
import structs.SimulatorParam;

import java.net.SocketTimeoutException;

public class DepAirportMain {
    public static boolean finished;

    /**
     * This class implements the Departure Airport Main
     * instantiates the Repository Stub
     * instantiates the Departure Airport entity
     * instantiates the Departure Airport Interface
     * and launches the Departure Airport Proxy
     */
    public static void main(String[] args) {

        //Arrival lounge port
        final int portNumb = SimulatorParam.DepAirportPort;

        ServerCom scon, sconi;

        DepartureAirportProxy depAirportProxy;
        scon = new ServerCom(portNumb);
        scon.start();

        DepartureAirportStub daStub = new DepartureAirportStub();
        RepositoryStub repository = new RepositoryStub();

        DepAirport depAirport = new DepAirport(repository, SimulatorParam.NUM_PASSANGERS, 5, 10);


        DepAirportInt depAirportInt = new DepAirportInt(depAirport);

        //Create listening channel

        //Process Requests while clients not finished
        finished = false;
        while (!finished) {
            try {
                //listening
                sconi = scon.accept();
                //Launch proxy
                depAirportProxy = new DepartureAirportProxy(sconi, depAirportInt);
                depAirportProxy.start();
            } catch (SocketTimeoutException e) {
            }
        }
        //Terminate operations
        scon.end();
    }
}
