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
            case SET_HOSTESS_STATE:
                if(inMessage.getHostessState() < 0 || inMessage.getHostessState() > 3) throw new MessageException("Number of hostess state invalid!",inMessage);
                else{
                    repo.setHostessState(inMessage.getHostessState());
                    outMessage = new Message(MessageType.ACK);
                }
                break;
            case SET_HOSTESS_STATE_ID:
                if(inMessage.getHostessState() < 0 || inMessage.getHostessState() > 3 ||
                        inMessage.getPassengerID()<0 || inMessage.getPassengerID()>21) throw new MessageException("Number of hostess state or passenger ID invalid!",inMessage);
                else{
                    repo.setHostessState(inMessage.getHostessState(),inMessage.getPassengerID());
                    outMessage = new Message(MessageType.ACK);
                }
                break;
            case SET_PASSENGER_STATE:
                if(inMessage.getPassengerState()<0 || inMessage.getPassengerState()>3 ||
                        inMessage.getPassengerID()<0 || inMessage.getPassengerID()>21) throw new MessageException("Number of passenger state or passenger ID invalid!",inMessage);
                else{
                    repo.setPassengerState(inMessage.getPassengerID(), inMessage.getPassengerState());
                    outMessage = new Message(MessageType.ACK);
                }
                break;
            case ADD_FLIGHT_INFO:
                if(inMessage.getNumPassengers()<0 || inMessage.getNumPassengers()>10) throw new MessageException("Number of passengers in plane invalid!",inMessage);
                else{
                    repo.addFlightInfo(inMessage.getNumPassengers());
                    outMessage = new Message(MessageType.ACK);
                }
                break;
            case REPORT_SUMMARY:
                repo.reportSummary();
                outMessage = new Message(MessageType.ACK);
                break;
            default: throw new MessageException ("Message type invalid : ", inMessage);
        }
        return (outMessage);
    }

}