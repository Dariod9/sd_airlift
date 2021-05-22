package serverSide.sharedRegionInterfaces;


import serverSide.sharedRegions.DepAirport;
import serverSide.sharedRegions.DestAirport;
import structs.Message;
import structs.MessageException;

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
        return (outMessage);
    }
}
