package entities;

public class Pilot extends Thread{


    private int Pilotstate;

    public  void informPlaneReadyForBoarding() {
        Pilotstate = PilotStates.readyForBoarding;
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
