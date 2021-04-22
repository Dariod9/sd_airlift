package entities;

import shared.Airplane;
import shared.DepAirport;
import shared.DestAirport;

public class Pilot extends Thread{

    private int Pilotstate;
    private int pilotID;
    private DepAirport depAirport;
    private DestAirport destAirport;
    private Airplane airplane;

    @Override
    public void run() {
        while(destAirport.getTotalArrived()<21) {
            informPlaneReadyForBoarding();
            depAirport.waitForAllInBoard();
            fly();
            destAirport.arrived();
            airplane.setOccupation(0);
        }
    }

    private void fly() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("VOEI");
    }

    public Pilot (DepAirport depAirport, DestAirport destAirport, Airplane airplane, int pilotID) {
        this.pilotID=pilotID;
        this.depAirport=depAirport;
        this.destAirport=destAirport;
        this.airplane=airplane;
        Pilotstate=PilotStates.atTransferGate;
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


    public int getPilotID() {
        return pilotID;
    }

    public void setPilotID(int pilotID) {
        this.pilotID = pilotID;
    }

    public void informPlaneReadyForBoarding() {
        Pilotstate = PilotStates.readyForBoarding;

        long a =2000+ 10* (long) Math.random();
        try {
            sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        depAirport.setPilotReady(true);
        System.out.println("Piloto diz que tá ready");
    }

    public void waitForAllInBoard() {
        Pilotstate = PilotStates.waitingForBoarding;
    }

    public void flyToDestinationPoint() {
        Pilotstate = PilotStates.flyingForward;
    }

    public void announceArrival() {
        Pilotstate = PilotStates.deBoarding;
    }

    public void flyToDeparturePoint() {
        Pilotstate = PilotStates.flyingBack;
    }

    public void parkAtTransferGate() {
        Pilotstate = PilotStates.atTransferGate;
    }

    public int getPilotstate() {
        return Pilotstate;
    }

    public void setPilotstate(int pilotstate) {
        Pilotstate = pilotstate;
    }
}
