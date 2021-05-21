package structs;

/**
 *  This class specifies an exception to be thrown if a message is invalid.
 */

public class MessageException extends Exception
{
    /**
     *  Message that has originated the exception
     *    @serialField msg
     */

    private Message msg;

    /**
     *  Message Exception instantiation
     *
     *    @param errorMessage text describing the error situation
     *    @param msg message that caused the exception
     */
    public MessageException (String errorMessage, Message msg)
    {
        super (errorMessage);
        this.msg = msg;
    }

    /**
     *  Returning the message that originated the exception for identifying purposes
     *
     *    @return mensagem
     */
    public Message getMessageVal ()
    {
        return (msg);
    }
}