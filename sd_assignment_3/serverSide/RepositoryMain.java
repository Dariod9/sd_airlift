package serverSide;

import genclass.GenericIO;
import interfaces.RepositoryInt;
import interfaces.Register;
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
    public static boolean finished;

    /**
     * Instance of a monitor.
     */
    private static Lock mutex;

    /**
     * Condition variable where the main thread waits until the shared region
     * ends its life cycle to unbind it in the registry.
     */
    private static Condition shutdown;

    /**
     * Main task that instantiates the remote object and its stub.
     * It also instantiates the Locate Registry that has the RMI registrations
     * for the other shared regions. If this shared region needs a stub of another,
     * it looks it up on the registry before instantiating its own remote object.
     * Finally it binds its stub on the Registry Handler so other shared regions
     * and/or clients can access its methods.
     * After its lifecyle ends, it unbinds the previous registration.
     */
    public static void main(String[] args) throws RemoteException {
        Registry registry = null;
        Register reg = null;
        String objectName;
        Repository Repository;
        RepositoryInt repositoryInt = null;
        mutex = new ReentrantLock();
        shutdown = mutex.newCondition();
        finished = false;

        /* create and install the security manager */
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());
        System.out.println("Security manager was installed!");

        /* instantiate a remote object that runs mobile code and generate a stub for it */
        Repository = new Repository(21, "logger.txt");
        objectName = "Repository";
        GenericIO.writelnString ("hostname " + args[0]);
        GenericIO.writelnString ("Port " + args[1]);
        GenericIO.writelnString ("PORTS " + args[2]);
        try {
            repositoryInt =
                    (RepositoryInt) UnicastRemoteObject.exportObject(
                            Repository, Integer.parseInt(args[2]));
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
                    args[0],
                    Integer.parseInt(args[1]));
        } catch (RemoteException e) {
            System.out.println("RMI registry creation exception: " +
                    e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("RMI registry was created!");

        try {
            reg = (Register) registry.lookup("Register");
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


        mutex.lock();

        while (!finished){
            try{
                shutdown.await();
            } catch (InterruptedException e) {}
        }

        mutex.unlock();

         /*
        Flights Sum Up
         */
        Repository.reportSummary();

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

    /**
     * Method that signals the main thread to unbind the shared region from the
     * registry.
     */
    public static void wakeUp() {
        mutex.lock();

        finished = true;
        shutdown.signal();

        mutex.unlock();
    }

}