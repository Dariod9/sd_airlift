package structs;

import clientSide.entities.*;
import structs.MessageType;

import java.io.Serializable;
/**
 * This class defines the constructors of the various messages that the clients and servers exchange between them in a Distributed solution
 * for the AirLift Problem . The communication is based in message exchanges of type Message in a TCP channel.
 */
public class Message implements Serializable {

    /**
     * Serialization key
     * @serial Field serialVersionUID
     */
    private static final long serialVersionUID = 1001L;

    /**
     * Message types
     */
    private MessageType msgType = MessageType.NO_MESSAGE;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getFlew() {
        return flew;
    }

    public void setFlew(int flew) {
        this.flew = flew;
    }

    public boolean isPlaneReady() {
        return planeReady;
    }

    public void setPlaneReady(boolean planeReady) {
        this.planeReady = planeReady;
    }

    /**
     * Number of passengers that already flew
     */
    private int flew;

    /**
     * Plane ready to fly
     */
    private boolean planeReady;
    /**
     * Pilot State
     */
    private int pilotState;
    /**
     * Passenger State
     */
    private int passengerState;
    /**
     * Passenger ID
     */
    private int passengerID;
    /**
     * Hostess State
     */
    private int hostessState;
    /**
     * Number of Passengers
     */
    private int numPassengers;

    /**
     * Message instantiaton.
     *
     * @param type reference to the MessageType.
     */
    public Message(MessageType type) {
        this.msgType = type;
    }

    public Message(MessageType type, int flew) {
        this.msgType = type;
        this.flew=flew;
    }

    public Message(MessageType type, boolean planeReady) {
        this.msgType = type;
        this.planeReady=planeReady;
    }

    public Message(MessageType type, int state, String entity) {
        this.msgType = type;
        switch (entity){
            case "pilot": this.pilotState=state;
                            break;
            case "hostess": this.hostessState=state;
                            break;
            case "passenger": this.passengerState=state;
                                break;
            case "flight": this.numPassengers=state;
                            break;
            default:break;
        }
    }


    public Message(MessageType type, int state, int id_passenger, String entity) {
        this.msgType = type;
        if(entity.equalsIgnoreCase("passenger")){
            this.passengerState=state;
        }
        else{
            this.hostessState=state;
        }

        this.passengerID=id_passenger;

    }


    public MessageType getType() {
        return msgType;
    }

    public int getPassengerState(){
        return this.passengerState;
    }

    public int getHostessState() {
        return this.hostessState;
    }

    public int getPilotState(){
        return this.pilotState;
    }

    public int getNumPassengers(){
        return this.numPassengers;
    }

    public int getPassengerID(){
        return this.passengerID;
    }


    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }
}
