package serverSide.main;

import serverSide.ServerCom;
import structs.SimulatorParam;

import java.net.SocketTimeoutException;

public class AirplaneMain {
    public static int terminated;

    public static void main (String [] args){

        //Arrival lounge port
        final int portNumb = SimulatorParam.arrivalLoungePort;

        ServerCom scon, sconi;
        ArrivalLoungeProxy alProxy;

        //Create listening channel
        scon = new ServerCom (portNumb);
        scon.start ();

        //Instantiate Stubs
        BaggageCollectionPointStub bcp = new BaggageCollectionPointStub();
        RepoStub repo = new RepoStub();

        //Instantiate Shared Region
        ArrivalLounge al = new ArrivalLounge(bcp, repo);

        //Instantiate Shared Region interface
        ArrivalLoungeInterface alInter = new ArrivalLoungeInterface(al);

        //Process Requests while clients not finished
        terminated = 0;
        while (terminated != 2)
        { 	try {
            //listening
            sconi = scon.accept ();
            //Launch proxy
            alProxy = new ArrivalLoungeProxy(sconi, alInter);
            alProxy.start ();
        } catch (SocketTimeoutException e) {}
        }
        //Terminate operations
        scon.end();
    }
}
