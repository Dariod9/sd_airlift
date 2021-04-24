package entities;

import shared.Airplane;
import shared.DepAirport;
import shared.DestAirport;

/**
 *   Hostess thread.
 *
 *   It simulates the hostess life cycle.
 *   Static solution.
 */

public class Hostess extends Thread {


    private int HostessState;
    private int hostessID;
    private DepAirport depAirport;
    private DestAirport destAirport;
    private Airplane airplane;

    public Hostess(DepAirport depAirport, DestAirport destAirport, Airplane airplane, int hostessID){
        this.hostessID=hostessID;
        this.destAirport=destAirport;
        this.depAirport=depAirport;
        this.airplane=airplane;
    }

    public int getHostessState() {
        return HostessState;
    }

    public void setHostessState(int hostessState) {
        HostessState = hostessState;
    }

    public int getHostessID() {
        return hostessID;
    }

    @Override
    public void run() {
        prepareForPassBoarding();
        int i=0;
        while(depAirport.getFlew()!=21){
            i++;
            System.out.println("corri "+i);
            depAirport.waitForNextPassenger();
            depAirport.checkDocuments();
            depAirport.informPlaneReadyToTakeOff();
            depAirport.waitForNextFlight();

        }

    }



    public void prepareForPassBoarding() {
        HostessState = HostessStates.waitForPassenger;
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
