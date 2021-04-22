package entities;

import shared.Airplane;
import shared.DepAirport;
import shared.DestAirport;

import java.time.format.DecimalStyle;

public class Hostess extends Thread {

    private int HostessState;
    private int hostessID;
    private DepAirport depAirport;
    private DestAirport destAirport;
    private Airplane airplane;

    @Override
    public void run() {

        int i=0;
        while(destAirport.getTotalArrived()!=21){
            i++;
            System.out.println("corri "+i);
            depAirport.checkPassenger();
            depAirport.waitForNextPassenger();
        }
//        System.out.println("TUDO ENVIADO");
    }

    public Hostess(DepAirport depAirport, DestAirport destAirport, Airplane airplane, int hostessID){
        this.hostessID=hostessID;
        this.destAirport=destAirport;
        this.depAirport=depAirport;
        this.airplane=airplane;
    }

    public DepAirport getDepAirport() {
        return depAirport;
    }

    public void setDepAirport(DepAirport depAirport) {
        this.depAirport = depAirport;
    }

    public DestAirport getDestAirport() {
        return destAirport;
    }

    public void setDestAirport(DestAirport destAirport) {
        this.destAirport = destAirport;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }


    public void prepareForPassBoarding() {
        HostessState = HostessStates.waitForPassenger;
    }

    public void checkDocuments() {
        HostessState = HostessStates.checkPassenger;
    }

    public void waitForNextPassenger() {
        HostessState = HostessStates.waitForPassenger;
    }

    public void informPlaneReadyToTakeOf() {
        HostessState = HostessStates.readyToFly;
    }

    public void waitForNextFlight() {
        HostessState = HostessStates.waitForFlight;
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

    public void setHostessID(int hostessID) {
        this.hostessID = hostessID;
    }
}
