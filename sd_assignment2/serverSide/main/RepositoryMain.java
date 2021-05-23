package serverSide.main;

import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;

import serverSide.ServerCom;
import serverSide.serverProxys.RepositoryProxy;
import serverSide.sharedRegionInterfaces.RepositoryInt;
import serverSide.sharedRegions.Repository;
import structs.SimulatorParam;


/**
 * This class implements the Repository Main that instantiates the shared region Stubs
 * that are part of Repository arguments, instantiates the Repository Interface
 * and launches the Repository Proxy.
 */
public class RepositoryMain {

    public static boolean finished;

    public static void main(String[] args) {

        //Repository port
        final int portNumb = SimulatorParam.RepositoryPort;

        ServerCom scon, sconi;
        RepositoryProxy repoProxy;

        //Create listening channel
        scon = new ServerCom(portNumb);
        scon.start();

        //Instantiate Shared Region
        Repository repo = null;
        repo = new Repository(SimulatorParam.NUM_PASSANGERS, SimulatorParam.fileName);

        //Instantiate Shared Region interface
        RepositoryInt repoInter = new RepositoryInt(repo);

        //Process Requests while clients not finished
        finished = false;
        while (!finished)
        { 	try {
            //listening
            sconi = scon.accept ();
            //Launch proxy
            repoProxy = new RepositoryProxy(sconi, repoInter);
            repoProxy.start ();
        } catch (SocketTimeoutException e) {}
        }
        //Terminate operations
        scon.end();
        repo.reportSummary();
    }

}