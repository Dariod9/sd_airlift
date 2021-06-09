package clientSide;
import clientSide.Hostess;
import clientSide.Passenger;
import genclass.GenericIO;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

import interfaces.*;
import utils.SimulatorParam;

/**
 * This data type instantiates an active entity, in this case the Passenger,
 * which looks up for remote shared regions on Locate Registry and executes
 * their methods remotely.
 * Communication is based in Java RMI.
 */
public class PassengerMain {

    /**
     * Main task that instantiates an active entity.
     * It also instantiates the Locate Registry that has the RMI registrations
     * for the other shared regions, so that it can execute its methods remotely.
     */
    public static void main(String[] args) {
        Registry registry = null;
        DepAirportInt depAirportInt = null;
        AirplaneInt airplaneInt = null;

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
            depAirportInt = (DepAirportInt) registry.lookup("DepAirport");
            airplaneInt = (AirplaneInt) registry.lookup("Airplane");
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

        // entities initialization
        Passenger[] passengers = new Passenger[SimulatorParam.TOTAL];


        for (int i = 0; i < SimulatorParam.TOTAL; i++) {
            passengers[i]=new Passenger(depAirportInt, airplaneInt, i);
        }

        // start of the simulation
        for (int i = 0; i < SimulatorParam.TOTAL; i++)
            passengers[i].start();

        // end of the simulation
        for (int i = 0; i < SimulatorParam.TOTAL; i++) {
            try {
                passengers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // end of the simulation

    }
}