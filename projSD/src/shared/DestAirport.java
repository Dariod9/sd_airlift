package shared;

import entities.Pilot;
import entities.PilotStates;

public class DestAirport {

    private Repository repos;

    public DestAirport(Repository repos) {
        this.repos=repos;
    }

    public synchronized void flyToDeparturePoint() {
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.flyingBack);
        repos.setPilotState((((Pilot) Thread.currentThread()).getPilotstate()));
        
        System.out.println("VOEI OH PRA CÁ");
    }
}
