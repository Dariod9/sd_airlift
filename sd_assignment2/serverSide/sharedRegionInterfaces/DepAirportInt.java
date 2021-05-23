package serverSide.sharedRegionInterfaces;

import serverSide.serverProxys.AirplaneProxy;
import serverSide.serverProxys.DepartureAirportProxy;
import serverSide.sharedRegions.Airplane;
import serverSide.sharedRegions.DepAirport;
import structs.Message;
import structs.MessageException;
import structs.MessageType;
import structs.SimulatorParam;

/**
 *  Departure Airport Interface
 *
 *   It is responsible to validate and process the incoming message, execute the corresponding method on the
 *  Departure Airport and generate the outgoing message.
 *  Implementation of a client-server model of type 2 (server replication).
 *  communication is based on a communication channel under the TCP protocol.
 *
 */

public class DepAirportInt {
    /**
     * Departure Airport shared region
     *
     * @serial Field depAirport
     */
    private DepAirport depAirport;

    /**
     * Departure Airport Interface instantiation
     * @param depAirport shared region
     */
    public DepAirportInt(DepAirport depAirport){
        this.depAirport=depAirport;
    }

    /**
     * This function receives the incoming message and executes the correct function from the Departure Airport shared region and then
     * generates the reply message.
     * @param inMessage incoming message from the main
     */
    public Message processAndReply(Message inMessage) throws MessageException {
        Message outMessage = null;

        /* validation of the incoming message */
        switch (inMessage.getType()){
            case GET_FLEW:
                break;
            case WAIT_IN_QUEUE:
                if(inMessage.getPassengerID()<0 || inMessage.getPassengerID()>= SimulatorParam.NUM_PASSANGERS) throw new MessageException("Invalid Passenger ID",inMessage);
                break;
            case WAIT_FOR_NEXT_PASSENGER:
                break;
            case CHECK_DOCUMENTS:
                break;
            case INFORM_PLANE_READY_TO_TAKEOFF:
                break;
            case WAIT_FOR_NEXT_FLIGHT:
                break;
            case INFORM_PLANE_READY_FOR_BOARDING:
                break;
            case WAIT_FOR_ALL_IN_BOARD:
                break;
            case FLY_TO_DESTINATION_POINT:
                break;
            case PREPARE_FOR_PASS_BOARDING:
                break;
            case SHUTDOWN:
                break;
            default: throw new MessageException ("Message type invalid : ", inMessage);
        }

        /* processing */
        switch (inMessage.getType()){
            case GET_FLEW:
                int flew = depAirport.getFlew();
                outMessage = new Message(MessageType.ACK, flew);
                break;
            case WAIT_IN_QUEUE:
                depAirport.waitInQueue(inMessage.getPassengerID());
                outMessage = new Message(MessageType.ACK);
                break;
            case WAIT_FOR_NEXT_PASSENGER:
                boolean planeReady = depAirport.waitForNextPassenger();
                outMessage = new Message(MessageType.ACK, planeReady);
                break;
            case CHECK_DOCUMENTS:
                depAirport.checkDocuments();
                outMessage = new Message(MessageType.ACK);
                break;
            case INFORM_PLANE_READY_TO_TAKEOFF:
                depAirport.informPlaneReadyToTakeOff();
                outMessage = new Message(MessageType.ACK);
                break;
            case WAIT_FOR_NEXT_FLIGHT:
                depAirport.waitForNextFlight();
                outMessage = new Message(MessageType.ACK);
                break;
            case INFORM_PLANE_READY_FOR_BOARDING:
                depAirport.informPlaneReadyForBoarding();
                outMessage = new Message(MessageType.ACK);
                break;
            case WAIT_FOR_ALL_IN_BOARD:
                depAirport.waitForAllInBoard();
                outMessage = new Message(MessageType.ACK);
                break;
            case FLY_TO_DESTINATION_POINT:
                depAirport.flyToDestinationPoint();
                outMessage = new Message(MessageType.ACK);
                break;
            case PREPARE_FOR_PASS_BOARDING:
                depAirport.prepareForPassBoarding();
                outMessage = new Message(MessageType.ACK);
                break;
            case SHUTDOWN:
                depAirport.shutServer(); //TODO
                outMessage = new Message(MessageType.ACK);
                (((DepartureAirportProxy) (Thread.currentThread ())).getScon ()).setTimeout (10);
                break;
        }

        return (outMessage);
    }
}
