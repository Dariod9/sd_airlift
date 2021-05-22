package serverSide.sharedRegionInterfaces;

import serverSide.sharedRegions.Repository;
import structs.Message;
import structs.MessageException;
import structs.MessageType;

/**
 *  Repository
 *  It is responsible to keep the visible internal state of the problem and print it in the logging file.
 *  All public methods are executed in mutual exclusion.
 *  It contains no internal synchronization points.
 */

public class RepositoryInt {
    /**
     * Repository shared region
     *
     * @serial Field repo
     */
    private Repository repo;

    /**
     * Repository Interface instantiation
     * @param repo Repository shared region
     */
    public RepositoryInt(Repository repo){
        this.repo=repo;
    }

    /**
     * This function receives the incoming message and executes the correct function from the Repository shared region and then
     * generates the reply message.
     * @param inMessage incoming message from the main
     */
    public Message processAndReply(Message inMessage) throws MessageException {
        Message outMessage = null;

        switch (inMessage.getType()){
            case SET_PILOT_STATE:
                if(inMessage.getPilotState() < 0 || inMessage.getPilotState() > 5) throw new MessageException("Number of pilot state invalid!",inMessage);
                else{
                    repo.setPilotState(inMessage.getPilotState());
                    outMessage = new Message(MessageType.ACK);
                }
                break;
            default: throw new MessageException ("Message type invalid : ", inMessage);
        }
        return (outMessage);
    }

}