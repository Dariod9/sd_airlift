package entities;

public class Hostess extends Thread {

    private int HostessState;

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

}
