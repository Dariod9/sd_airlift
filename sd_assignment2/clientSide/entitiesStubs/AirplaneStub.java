package clientSide.entitiesStubs;

import clientSide.ClientCom;
import clientSide.entities.Passenger;
import clientSide.entities.Pilot;
import genclass.GenericIO;
import structs.Message;
import structs.MessageType;
import structs.SimulatorParam;
import clientSide.*;


/**
 * Reference to a remote object.
 * <p>
 * It provides means to the setup of a communication channel and the message exchange.
 */

public class AirplaneStub {

    /**
     * Name of the computational system where the server is located.
     */

    private String serverHostName;

    /**
     * Number of the listening port at the computational system where the server is located.
     */

    private int serverPortNumb;

    /**
     * Instantiation of a remote reference
     *
     */

    public AirplaneStub() {
        serverHostName = SimulatorParam.AirplaneHostName;
        serverPortNumb = SimulatorParam.AirplanePort;
    }

    /**
     * Message exchange with the remote object.
     */

//    public void exchange() {
//
//        ClientCom com = new ClientCom(serverHostName, serverPortNumb);           // communication channel
//        Message fromClient,                                                        // input sentence
//                fromUser;                                                          // output sentence
//
//        while (!com.open()) {                                                      // open the connection
//            try {
//                Thread.currentThread().sleep((long) (10));
//            } catch (InterruptedException e) {
//            }
//        }
//
//        while ((fromClient = (Message) com.readObject()) != null) {                 // check receiving message
//            GenericIO.writelnString("Clien: " + fromClient);                      // print receiving message
//            if (fromClient.equals("Bye.")) break;                                  // check for continuation
//            GenericIO.writeString("Stub: ");                                     // read user reply
//            do {
//                fromUser = GenericIO.readlnString();
//            } while (fromUser == null);
//            com.writeObject(fromUser);                                             // send reply
//        }
//        com.close();                                                             // close the connection
//    }


    public void parkAtTransferGate() {

        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;
        Pilot p = (Pilot) Thread.currentThread();
        //Waits for connection
        while (!con.open()) {
            try {
                p.sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        //What should i do message with the flight number
        outMessage = new Message(MessageType.PARK_AT_TRANSFER_GATE);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if ((inMessage.getType() != MessageType.ACK)) {
            System.out.println("Thread " + p.getName() + ": Invalid type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        //Close connection
        con.close();
    }

    public void announceArrival() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;
        Pilot p = (Pilot) Thread.currentThread();
        //Waits for connection
        while (!con.open()) {
            try {
                p.sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        //What should i do message with the flight number
        outMessage = new Message(MessageType.ANNOUNCE_ARRIVAL);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if ((inMessage.getType() != MessageType.ACK)) {
            System.out.println("Thread " + p.getName() + ": Invalid type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        //Close connection
        con.close();

    }

    public void leaveThePlane() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;
        Pilot p = (Pilot) Thread.currentThread();
        //Waits for connection
        while (!con.open()) {
            try {
                p.sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        //What should i do message with the flight number
        outMessage = new Message(MessageType.LEAVE_THE_PLANE);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if ((inMessage.getType() != MessageType.ACK)) {
            System.out.println("Thread " + p.getName() + ": Invalid type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        //Close connection
        con.close();

    }

    public void waitForEndOfFlight() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;
        Pilot p = (Pilot) Thread.currentThread();
        //Waits for connection
        while (!con.open()) {
            try {
                p.sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        //What should i do message with the flight number
        outMessage = new Message(MessageType.WAIT_FOR_END_OF_FLIGHT);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if ((inMessage.getType() != MessageType.ACK)) {
            System.out.println("Thread " + p.getName() + ": Invalid type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        //Close connection
        con.close();

    }

    public void boardThePlane() {

        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;
        Pilot p = (Pilot) Thread.currentThread();
        //Waits for connection
        while (!con.open()) {
            try {
                p.sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        //What should i do message with the flight number
        outMessage = new Message(MessageType.BOARD_THE_PLANE);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        if ((inMessage.getType() != MessageType.ACK)) {
            System.out.println("Thread " + p.getName() + ": Invalid type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        //Close connection
        con.close();

    }


    public void shutServer() {
        //Open connection
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;
        Thread p = (Thread) Thread.currentThread();
        //Waits for connection
        while (!con.open()) {
            try {
                p.sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        //Shut down server message
        outMessage = new Message(MessageType.SHUTDOWN);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();

        //Message OK
        if ((inMessage.getType() != MessageType.ACK)) {
            System.out.println("Thread " + p.getName() + ": Invalid type!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        //Close connection
        con.close();
    }


}
