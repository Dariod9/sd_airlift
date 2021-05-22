package serverSide.sharedRegionInterfaces;

import clientSide.entities.Passenger;
import serverSide.serverProxys.AirplaneProxy;
import serverSide.sharedRegions.Airplane;
import structs.Message;
import structs.MessageException;
import structs.MessageType;

/**
 *  Airplane
 *
 *  It is responsible to keep track of it's occupation, the IDs of the passengers inside and if it has arrived the
 *  destination.
 *  All public methods are executed in mutual exclusion.
 *  There are two internal synchronization points: an array of blocking points, one per each passenger, where he
 *  waits for the flight to end so that he can exit the airplane; and one single blocking point for the pilot,
 *  where she waits for every passenger to leave the plane, in order to announce the arrival of the airplane.
 *
 */

public class AirplaneInt {

    /**
     * Airplane shared region
     *
     * @serial Field ap
     */
    private Airplane ap;

    /**
     * Airplane Interface instantiation
     * @param ap Airplane shared region
     */
    public AirplaneInt(Airplane ap){
        this.ap=ap;
    }

    /**
     * This function receives the incoming message and executes the correct function from the Airplane shared region and then
     * generates the reply message.
     * @param inMessage incoming message from the main
     */
    public Message processAndReply(Message inMessage) throws MessageException{
        Message outMessage = null;

        switch(inMessage.getType()){
            case BOARD_THE_PLANE:
                ap.boardThePlane();
                outMessage = new Message(MessageType.ACK);
                break;
            case WAIT_FOR_END_OF_FLIGHT:
                ap.waitForEndOfFlight();
                outMessage = new Message(MessageType.ACK);
                break;
            case LEAVE_THE_PLANE:
                ap.leaveThePlane();
                outMessage = new Message(MessageType.ACK);
                break;
            case ANNOUNCE_ARRIVAL:
                ap.announceArrival();
                outMessage = new Message(MessageType.ACK);
                break;
            case PARK_AT_TRANSFER_GATE:
                ap.parkAtTransferGate();
                outMessage = new Message(MessageType.ACK);
                break;
            case SHUTDOWN:
                //ap.shutServer(); //TODO
                outMessage = new Message(MessageType.ACK);
                (((AirplaneProxy) (Thread.currentThread ())).getScon ()).setTimeout (10);
                break;
            default: throw new MessageException ("Message type invalid : ", inMessage);
        }

        return (outMessage);
    }
}
