package serverSide.sharedRegionInterfaces;


import serverSide.serverProxys.AirplaneProxy;
import serverSide.serverProxys.DestinationAirportProxy;
import serverSide.sharedRegions.DepAirport;
import serverSide.sharedRegions.DestAirport;
import structs.Message;
import structs.MessageException;
import structs.MessageType;

/**
 *  Destination Airport Interface
 *
 *   It is responsible to validate and process the incoming message, execute the corresponding method on the
 *  Destination Airport and generate the outgoing message.
 *  Implementation of a client-server model of type 2 (server replication).
 *  communication is based on a communication channel under the TCP protocol.
 *
 */

public class DestAirportInt {
    /**
     * Destination Airport shared region
     *
     * @serial Field destAirport
     */
    private DestAirport destAirport;

    /**
     * Departure Airport Interface instantiation
     * @param destAirport shared region
     */
    public DestAirportInt(DestAirport destAirport){
        this.destAirport=destAirport;
    }

    /**
     * This function receives the incoming message and executes the correct function from the Destination Airport shared region and then
     * generates the reply message.
     * @param inMessage incoming message from the main
     */
    public Message processAndReply(Message inMessage) throws MessageException {
        Message outMessage = null;

        /* processing (No validations needed)*/
        switch (inMessage.getType()){
            case FLY_TO_DEPARTURE_POINT:
                destAirport.flyToDeparturePoint();
                outMessage = new Message(MessageType.ACK);
                break;
            case SHUTDOWN:
                destAirport.shutServer(); //TODO
                outMessage = new Message(MessageType.ACK);
                (((DestinationAirportProxy) (Thread.currentThread ())).getScon ()).setTimeout (10);
                break;
            default: throw new MessageException ("Message type invalid : ", inMessage);
        }
        return (outMessage);
    }
}
