package clientSide.entities;
import interfaces.DepAirportInt;
import interfaces.DestAirportInt;
import interfaces.AirplaneInt;

import java.rmi.RemoteException;


/**
 *   Pilot thread.
 *
 *   It simulates the Pilot life cycle.
 *   Static solution.
 */

public class Pilot extends Thread{

    /**
     * pilot state.
     */
    private int Pilotstate;

    /**
     * pilot identification.
     */
    private int pilotID;

    /**
     * Total number of passengers.
     */
    private int TOTAL;

    /**
     * Reference to the departure airport.
     */
    private DepAirportInt depAirport;

    /**
     * Reference to the departure airport.
     */
    private DestAirportInt destAirport;

    /**
     * Reference to the airplane.
     */
    private AirplaneInt airplane;

    /**
     * Instantiation of a Pilot thread.
     *
     * @param depAirport reference to departure airport
     * @param destAirport reference to destination airport
     * @param airplane reference to airplane
     * @param pilotID pilot id
     * @param TOTAL total passengers
     */
    public Pilot (DepAirportInt depAirport, DestAirportInt destAirport, AirplaneInt airplane, int pilotID, int TOTAL) {
        this.TOTAL=TOTAL;
        this.pilotID=pilotID;
        this.depAirport=depAirport;
        this.destAirport=destAirport;
        this.airplane=airplane;
        Pilotstate=PilotStates.atTransferGate;
    }

    /**
     * Get pilot state.
     *
     * @return pilot state.
     */
    public int getPilotstate() {
        return Pilotstate;
    }

    /**
     * Set pilot state.
     *
     * @param pilotstate new pilot state
     */
    public void setPilotstate(int pilotstate) {
        Pilotstate = pilotstate;
    }

    /**
     * Get Pilot Id
     *
     * @return pilot id
     */
    public int getPilotID() {
        return pilotID;
    }

    /**
     * Set pilot id
     *
     * @param pilotID pilot id
     */
    public void setPilotID(int pilotID) {
        this.pilotID = pilotID;
    }


    /**
     * Life cycle of the Pilot
     */
    @Override
    public void run() {
        while(true) {
            try {
                if (!(depAirport.getFlew()<TOTAL)) break;
            } catch (RemoteException e) {
                e.printStackTrace();
                System.exit(1);
            }
            try {
                depAirport.informPlaneReadyForBoarding();
            } catch (RemoteException e) {
                e.printStackTrace();
                System.exit(1);
            }
            try {
                depAirport.waitForAllInBoard();
            } catch (RemoteException e) {
                e.printStackTrace();
                System.exit(1);
            }
            try {
                depAirport.flyToDestinationPoint();
            } catch (RemoteException e) {
                e.printStackTrace();
                System.exit(1);
            }
            try {
                airplane.announceArrival();
            } catch (RemoteException e) {
                e.printStackTrace();
                System.exit(1);
            }
            try {
                destAirport.flyToDeparturePoint();
            } catch (RemoteException e) {
                e.printStackTrace();
                System.exit(1);
            }
            try {
                airplane.parkAtTransferGate();
            } catch (RemoteException e) {
                e.printStackTrace();
                System.exit(1);
            }

        }
    }

    /**
     * Pilot in flight.
     *
     * Internal Operation.
     */
    public void fly() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
