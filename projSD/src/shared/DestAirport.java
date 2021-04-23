package shared;

import entities.Pilot;
import entities.PilotStates;

public class DestAirport {

    public synchronized void flyToDeparturePoint() {
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.flyingBack);

        System.out.println("VOEI OH PRA CÁ");
    }
}
