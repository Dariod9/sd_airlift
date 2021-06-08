package serverSide.main;

import interfaces.RepositoryInt;
import interfaces.Register;
import serverSide.sharedRegions.Repository;
import utils.SimulatorParam;

import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This data type instantiates and registers a remote object, in this particular
 * case the  Repository shared region, that will run mobile code.
 * Communication is based in Java RMI.
 */
public class RepositoryMain {


    /**
     * Boolean that is true if the shared region has already ended its execution.
     */
    private static boolean ended;

    /**
     * Main task that instantiates the remote object and its stub.
     * It also instantiates the Locate Registry that has the RMI registrations
     * for the other shared regions. If this shared region needs a stub of another,
     * it looks it up on the registry before instantiating its own remote object.
     * Finally it binds its stub on the Registry Handler so other shared regions
     * and/or clients can access its methods.
     * After its lifecyle ends, it unbinds the previous registration.
     */
    public static void main(String[] args) {
        Registry registry = null;
        Register reg = null;
        String objectName;
        Repository Repository;
        RepositoryInt repositoryInt = null;
        ended = false;

        /* create and install the security manager */
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());
        System.out.println("Security manager was installed!");

        /* instantiate a remote object that runs mobile code and generate a stub for it */
        Repository = new Repository(21, "logger.txt");
        objectName = "Repository";

        try {
            repositoryInt =
                    (RepositoryInt) UnicastRemoteObject.exportObject(
                            Repository, SimulatorParam.RepositoryPort);
        } catch (RemoteException e) {
            System.out.println(objectName + " stub generation exception: "
                    + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Stub was generated!");

        /* register it with the  registry service */
        try {
            registry = LocateRegistry.getRegistry(
                    SimulatorParam.RegistryName,
                    SimulatorParam.RepositoryPort);
        } catch (RemoteException e) {
            System.out.println("RMI registry creation exception: " +
                    e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("RMI registry was created!");

        try {
            reg = (Register) registry.lookup("RegisterHandler");
        } catch (RemoteException e) {
            System.out.println("RegisterRemoteObject lookup exception: " +
                    e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("RegisterRemoteObject not bound exception: " +
                    e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        try {
            reg.bind(objectName, repositoryInt);
        } catch (RemoteException e) {
            System.out.println(objectName + " registration exception: " +
                    e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (AlreadyBoundException e) {
            System.out.println(objectName + " already bound exception: " +
                    e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(objectName + " object was registered!");


        while (!ended) {
            try {
//                shutdown.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        try {
            reg.unbind(objectName);
        } catch (RemoteException e) {
            System.out.println(objectName + " unregistration exception: " +
                    e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println(objectName + " not bound exception: " +
                    e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(objectName + " object was unregistered!");

        try {
            UnicastRemoteObject.unexportObject(Repository, true);
        } catch (NoSuchObjectException e) {
            System.out.println(objectName + " stub destruction exception: "
                    + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Stub was destroyed!");
    }

}