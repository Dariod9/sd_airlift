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
 *  Departure Airport
 *  It is responsible for many actions.
 *  Regarding the passengers, it controls the process of waiting in Queue.
 *  Regarding the hostess, it is responsible for the waiting for the next flight, the preparing of the boarding, the
 *  process of waiting for more passengers, the action of checking documents and signaling the plane to take off.
 *  Concerning the pilot, it is responsible for the process of informing that the plane is ready to board, waiting
 *  for it to happen and flying to the destination.
 *
 *  All public methods are executed in mutual exclusion.
 *
 *  There are seven internal synchronization points: an array of blocking points for the passengers, where all of them
 *  wait to be called by the hostess and wait while the documents aren't checked; four blocking points for the hostess,
 *  while she waits while the Queue is empty, while the documents are not shown to her, while the plain is not ready to
 *  fly and also while the pilot is not ready for the boarding process to start. Finally, one single blocking point for
 *  the pilot, where he waits for the plane to be ready for take off.
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

        switch (inMessage.getType()){
            case GET_FLEW:
                int flew = depAirport.getFlew();
                outMessage = new Message(MessageType.ACK, flew);
                break;
            case WAIT_IN_QUEUE:
                if(inMessage.getPassengerID()<0 || inMessage.getPassengerID()>= SimulatorParam.NUM_PASSANGERS) throw new MessageException("Invalid Passenger ID",inMessage);
                else {
                    depAirport.waitInQueue(inMessage.getPassengerID());
                    outMessage = new Message(MessageType.ACK);
                }
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
            default: throw new MessageException ("Message type invalid : ", inMessage);
        }
        return (outMessage);
    }
}
