package serverSide;

import java.rmi.NoSuchObjectException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import genclass.GenericIO;
import interfaces.*;
import utils.*;

/**
 * This data type instantiates and registers a remote object, in this particular
 * case the Airplane shared region, that will run mobile code.
 * Communication is based in Java RMI.
 */
public class AirplaneMain {

    /**
     * Boolean that is true if the shared region has already ended its execution.
     */
    public static boolean finished;

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
        Airplane airplane;
        String objectName;
        AirplaneInt airplaneInt = null;
        RepositoryInt repositoryInt = null;
        finished = false;

        /* create and install the security manager */
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());
        System.out.println("Security manager was installed!");

        try {
            registry = LocateRegistry.getRegistry(
                    SimulatorParam.RegistryName,
                    SimulatorParam.RegistryPort);
        } catch (RemoteException e) {
            System.out.println("RMI registry creation exception: " +
                    e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("RMI registry was created!");

        try {
            repositoryInt = (RepositoryInt)
                    registry.lookup("Repository");
        } catch (RemoteException e) {
            System.out.println("Shared Region look up exception: " +
                    e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("Shared Region not bound exception: " +
                    e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        /* instantiate a remote object that runs mobile code and generate a stub for it */
        airplane = new Airplane(repositoryInt);
        objectName = "Airplane";

        try {
            airplaneInt =
                    (AirplaneInt) UnicastRemoteObject.exportObject(
                            airplane, SimulatorParam.AirplanePort);
        } catch (RemoteException e) {
            System.out.println(objectName + " stub generation exception: "
                    + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Stub was generated!");

        /* register it with the general registry service */
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
            reg.bind(objectName, airplaneInt);
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


        while (true) {
            GenericIO.writelnString("finished? " + finished);
            try {
                if(finished) break;
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
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
            UnicastRemoteObject.unexportObject(airplane, true);
        } catch (NoSuchObjectException e) {
            System.out.println(objectName + " stub destruction exception: "
                    + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Stub was destroyed!");
    }

}