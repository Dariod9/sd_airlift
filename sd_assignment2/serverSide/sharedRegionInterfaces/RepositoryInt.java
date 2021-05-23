package serverSide.sharedRegionInterfaces;

import serverSide.sharedRegions.Repository;
import structs.Message;
import structs.MessageException;
import structs.MessageType;
import structs.SimulatorParam;

/**
 *  Repository Interface
 *
 *   It is responsible to validate and process the incoming message, execute the corresponding method on the
 *  Repository and generate the outgoing message.
 *  Implementation of a client-server model of type 2 (server replication).
 *  communication is based on a communication channel under the TCP protocol.
 *
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

        /* validation of the incoming message */
        switch (inMessage.getType()){
            case SET_PILOT_STATE:
                if(inMessage.getPilotState() < 0 || inMessage.getPilotState() > SimulatorParam.PILOT_STATES) throw new MessageException("Number of pilot state invalid!",inMessage);
                break;
            case SET_HOSTESS_STATE:
                if(inMessage.getHostessState() < 0 || inMessage.getHostessState() > SimulatorParam.HOSTESS_STATES) throw new MessageException("Number of hostess state invalid!",inMessage);
                break;
            case SET_HOSTESS_STATE_ID:
                if(inMessage.getHostessState() < 0 || inMessage.getHostessState() > SimulatorParam.HOSTESS_STATES ||
                        inMessage.getPassengerID()<0 || inMessage.getPassengerID()>= SimulatorParam.NUM_PASSANGERS) throw new MessageException("Number of hostess state or passenger ID invalid!",inMessage);
                break;
            case SET_PASSENGER_STATE:
                if(inMessage.getPassengerState()<0 || inMessage.getPassengerState()>SimulatorParam.PASSENGER_STATES ||
                        inMessage.getPassengerID()<0 || inMessage.getPassengerID()>=SimulatorParam.NUM_PASSANGERS) throw new MessageException("Number of passenger state or passenger ID invalid!",inMessage);
                break;
            case ADD_FLIGHT_INFO:
                if(inMessage.getNumPassengers()<0 || inMessage.getNumPassengers()>SimulatorParam.PLANE_CAPACITY_MAX) throw new MessageException("Number of passengers in plane invalid!",inMessage);
                break;
            case REPORT_SUMMARY:
                break;
            default: throw new MessageException ("Message type invalid : ", inMessage);
        }

        /* processing */
        switch (inMessage.getType()){
            case SET_PILOT_STATE:
                repo.setPilotState(inMessage.getPilotState());
                outMessage = new Message(MessageType.ACK);
                break;
            case SET_HOSTESS_STATE:
                repo.setHostessState(inMessage.getHostessState());
                outMessage = new Message(MessageType.ACK);
                break;
            case SET_HOSTESS_STATE_ID:
                repo.setHostessState(inMessage.getHostessState(),inMessage.getPassengerID());
                outMessage = new Message(MessageType.ACK);
                break;
            case SET_PASSENGER_STATE:
                repo.setPassengerState(inMessage.getPassengerID(), inMessage.getPassengerState());
                outMessage = new Message(MessageType.ACK);
                break;
            case ADD_FLIGHT_INFO:
                repo.addFlightInfo(inMessage.getNumPassengers());
                outMessage = new Message(MessageType.ACK);
                break;
            case REPORT_SUMMARY:
                repo.reportSummary();
                outMessage = new Message(MessageType.ACK);
                break;
        }

        return (outMessage);
    }

}