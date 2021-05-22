package serverSide.main;

import clientSide.entitiesStubs.AirplaneStub;
import clientSide.entitiesStubs.RepositoryStub;
import serverSide.sharedRegions.Airplane;
import serverSide.ServerCom;
import serverSide.serverProxys.AirplaneProxy;
import serverSide.sharedRegionInterfaces.AirplaneInt;
import structs.SimulatorParam;

import java.net.SocketTimeoutException;

/**
 * This class implements the Airplane Main
 * instantiates the Repository Stub
 * instantiates the Airplane entity
 * instantiates the Airplane Interface
 * and launches the Airplane Proxy
 */
public class AirplaneMain {
    public static boolean finished;

    public static void main(String[] args) {

            //Arrival lounge port
            final int portNumb = SimulatorParam.AirplanePort;

            ServerCom scon, sconi;

        AirplaneProxy alProxy;
        scon = new ServerCom(portNumb);
        scon.start();

        AirplaneStub alStub = new AirplaneStub();
        RepositoryStub repository = new RepositoryStub();

        Airplane airplane = new Airplane(repository);


        AirplaneInt airplaneInt = new AirplaneInt(airplane);

        //Create listening channel

        //Process Requests while clients not finished
        finished = false;
        while (!finished) {
            try {
                //listening
                sconi = scon.accept();
                //Launch proxy
                alProxy = new AirplaneProxy(sconi, airplaneInt);
                alProxy.start();
            } catch (SocketTimeoutException e) {
            }
        }
        //Terminate operations
        scon.end();
    }
}
