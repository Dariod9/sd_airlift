package serverSide.main;

import clientSide.entitiesStubs.DestinationAirportStub;
import clientSide.entitiesStubs.RepositoryStub;
import serverSide.serverProxys.DestinationAirportProxy;
import serverSide.sharedRegionInterfaces.DestAirportInt;
import serverSide.sharedRegions.DestAirport;
import serverSide.ServerCom;
import commInfra.SimulatorParam;

import java.net.SocketTimeoutException;

public class DestAirportMain {
    public static boolean finished;

    /**
     * This class implements the Destination Airport Main
     * instantiates the Repository Stub
     * instantiates the Destination Airport entity
     * instantiates the Destination Airport Interface
     * and launches the Destination Airport Proxy
     */
    public static void main(String[] args) {

        //Arrival lounge port
        final int portNumb = SimulatorParam.DestAirportPort;

        ServerCom scon, sconi;

        DestinationAirportProxy destAirportProxy;
        scon = new ServerCom(portNumb);
        scon.start();

        DestinationAirportStub daStub = new DestinationAirportStub();
        RepositoryStub repository = new RepositoryStub();

        DestAirport destAirport = new DestAirport(repository);


        DestAirportInt destAirportInt = new DestAirportInt(destAirport);

        //Create listening channel

        //Process Requests while clients not finished
        finished = false;
        while (!finished) {
            try {
                //listening
                sconi = scon.accept();
                //Launch proxy
                destAirportProxy = new DestinationAirportProxy(sconi, destAirportInt);
                destAirportProxy.start();
            } catch (SocketTimeoutException e) {
            }
        }
        //Terminate operations
        scon.end();
    }
}
