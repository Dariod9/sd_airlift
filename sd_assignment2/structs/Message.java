import structs.MessageType;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1001L;

    private MessageType msgType = MessageType.NO_MESSAGE;

    private int flew;

    private boolean planeReady;

    public Message(MessageType type) {
        this.msgType = type;
    }

}
