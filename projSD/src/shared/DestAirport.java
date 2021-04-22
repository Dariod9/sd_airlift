package shared;

import entities.Pilot;
import entities.PilotStates;

public class DestAirport {

    private int totalArrived;

    public DestAirport() {
        this.totalArrived=0;
    }

    public int getTotalArrived() {
        return totalArrived;
    }

    public void setTotalArrived(int totalArrived) {
        this.totalArrived = totalArrived;
    }


    public synchronized void arrived() {
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.waitingForBoarding);
        totalArrived+= ((Pilot) Thread.currentThread()).getAirplane().getOccupation();
        System.out.println("CHEGUEI POTAS, COM "+totalArrived+" WIIS");
        ((Pilot) Thread.currentThread()).getAirplane().setOccupation(0);
    }
}
