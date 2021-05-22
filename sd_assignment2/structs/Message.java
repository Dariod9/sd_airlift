package structs;

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

    public MessageType getType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }
}