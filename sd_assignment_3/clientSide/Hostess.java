package clientSide;


import genclass.GenericIO;
import interfaces.DepAirportInt;

import java.rmi.RemoteException;

/**
 *   Hostess thread.
 *
 *   It simulates the hostess life cycle.
 *   Static solution.
 */

public class Hostess extends Thread{

    /**
     * Hostess state.
     */
    private int hostessState;

    /**
     * Hostess identification.
     */
    private int hostessID;

    /**
     * Total number of passengers.
     */
    private int TOTAL;

    /**
     * Reference to the departure airport.
     */
    private DepAirportInt depAirport;


    /**
     * Instantiation of a Hostess thread.
     *
     * @param depAirport reference to departure airport
     * @param hostessID hostess id
     */
    public Hostess(DepAirportInt depAirport,  int hostessID, int TOTAL){
        this.TOTAL = TOTAL;
        this.hostessID=hostessID;
        this.depAirport=depAirport;
    }

    /**
     * Get Hostess state.
     *
     * @return Hostess State.
     */
    public int getHostessState() {
        return hostessState;
    }

    /**
     * Set hostess id.
     *
     * @param hostessID new hostess id.
     */
    public void setHostessID(int hostessID) {
        this.hostessID = hostessID;
    }

    /**
     * Get hostess ID.
     *
     * @return hostess ID.
     */
    public int getHostessID() {
        return hostessID;
    }

    /**
     * Set Hostess State.
     *
     * @param hostessState new costumer id.
     */
    public void setHostessState(int hostessState) {
        this.hostessState = hostessState;
    }

    /**
     * Life cycle of the Hostess.
     */
    @Override
    public void run() {
        while(true){
            try {
                if (!(depAirport.getFlew()!=TOTAL)) break;
            } catch (RemoteException e) {
                e.printStackTrace();
                System.exit(1);
            }
            try {
                depAirport.waitForNextFlight();
            } catch (RemoteException e) {
                e.printStackTrace();
                System.exit(1);
            }
            try {
                depAirport.prepareForPassBoarding();
            } catch (RemoteException e) {
                e.printStackTrace();
                System.exit(1);
            }
            while(true) {
                try {
                    if(depAirport.waitForNextPassenger()) break;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                try {
                    depAirport.checkDocuments();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
            try {
                depAirport.informPlaneReadyToTakeOff();
            } catch (RemoteException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        //GenericIO.writelnString("Hostess life cycle terminated!");
    }

}
