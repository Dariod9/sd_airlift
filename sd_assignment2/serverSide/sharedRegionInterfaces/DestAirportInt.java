package serverSide.sharedRegionInterfaces;


import serverSide.serverProxys.AirplaneProxy;
import serverSide.serverProxys.DestinationAirportProxy;
import serverSide.sharedRegions.DepAirport;
import serverSide.sharedRegions.DestAirport;
import structs.Message;
import structs.MessageException;
import structs.MessageType;

/**
 *  Destination Airport
 *
 *  It is responsible for the flight back to the departure airport.
 *
 *  It does not contain any blocking point.
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
