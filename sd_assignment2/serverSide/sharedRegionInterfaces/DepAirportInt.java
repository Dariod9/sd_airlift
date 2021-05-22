package serverSide.sharedRegionInterfaces;

import serverSide.sharedRegions.Airplane;
import serverSide.sharedRegions.DepAirport;
import structs.Message;
import structs.MessageException;

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
        return (outMessage);
    }
}
