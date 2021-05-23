package serverSide.sharedRegionInterfaces;

import serverSide.serverProxys.AirplaneProxy;
import serverSide.sharedRegions.Airplane;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import commInfra.SimulatorParam;

/**
 *  Airplane Interface
 *
 *   It is responsible to validate and process the incoming message, execute the corresponding method on the
 *  Airplane and generate the outgoing message.
 *  Implementation of a client-server model of type 2 (server replication).
 *  communication is based on a communication channel under the TCP protocol.
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

        /* validation of the incoming message */
        switch(inMessage.getType()){
            case BOARD_THE_PLANE:
                if(inMessage.getPassengerID()<0 || inMessage.getPassengerID()>= SimulatorParam.NUM_PASSANGERS) throw new MessageException("Invalid Passenger ID",inMessage);
                break;
            case WAIT_FOR_END_OF_FLIGHT: break;
            case LEAVE_THE_PLANE:
                if(inMessage.getPassengerID()<0 || inMessage.getPassengerID()>= SimulatorParam.NUM_PASSANGERS) throw new MessageException("Invalid Passenger ID",inMessage);
                break;
            case ANNOUNCE_ARRIVAL: break;
            case PARK_AT_TRANSFER_GATE: break;
            case SHUTDOWN:  break;
            default: throw new MessageException ("Message type invalid : ", inMessage);
        }

        /* processing */
        switch(inMessage.getType()){
            case BOARD_THE_PLANE:
                ap.boardThePlane(inMessage.getPassengerID());
                outMessage = new Message(MessageType.ACK);
                break;
            case WAIT_FOR_END_OF_FLIGHT:
                ap.waitForEndOfFlight();
                outMessage = new Message(MessageType.ACK);
                break;
            case LEAVE_THE_PLANE:
                ap.leaveThePlane(inMessage.getPassengerID());
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
                ap.shutServer(); //TODO
                outMessage = new Message(MessageType.ACK);
                (((AirplaneProxy) (Thread.currentThread ())).getScon ()).setTimeout (10);
                break;
        }

        return (outMessage);
    }
}
