package clientSide.entitiesStubs;

import clientSide.ClientCom;
import genclass.GenericIO;
import structs.Message;
import structs.SimulatorParam;
import clientSide.*;



/**
 *   Reference to a remote object.
 *
 *   It provides means to the setup of a communication channel and the message exchange.
 */

public class AirplaneStub{

	/**
	 *  Name of the computational system where the server is located.
	 */

	private String serverHostName;

	/**
	 *  Number of the listening port at the computational system where the server is located.
	 */

	private int serverPortNumb;

	/**
	 *  Instantiation of a remote reference
	 *
	 *    @param hostName name of the computational system where the server is located
	 *    @param portNumb number of the listening port at the computational system where the server is located
	 */

	public AirplaneStub (){
		serverHostName = SimulatorParam.AirplaneHostName;
		serverPortNumb = SimulatorParam.AirplanePort;
	}

	/**
	 *  Message exchange with the remote object.
	 */

	public void exchange () {

		ClientCom com = new ClientCom (serverHostName, serverPortNumb);           // communication channel
		Message fromClient,                                                        // input sentence
				fromUser;                                                          // output sentence

		while (!com.open ()) {                                                      // open the connection
			try{
				Thread.currentThread ().sleep ((long) (10));
			}
			catch (InterruptedException e) {}
		}

		while ((fromClient = (Message) com.readObject ()) != null){                 // check receiving message
			GenericIO.writelnString ("Clien: " + fromClient);                      // print receiving message
			if (fromClient.equals ("Bye.")) break;                                  // check for continuation
			GenericIO.writeString ("Stub: ");                                     // read user reply
			do {
				fromUser = GenericIO.readlnString ();
			} while (fromUser == null);
			com.writeObject (fromUser);                                             // send reply
		}
		com.close ();                                                             // close the connection
	}


	public   void parkAtTransferGate() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);           // communication channel
		Message fromServer,                                                        // input sentence
				fromUser;                                                          // output sentence

		while (!com.open ()) {                                                      // open the connection
			try{
				Thread.currentThread ().sleep ((long) (10));
			}
			catch (InterruptedException e) {}
		}

		while ((fromServer = (String) com.readObject ()) != null){                 // check receiving message
			GenericIO.writelnString ("Clien: " + fromServer);                      // print receiving message
			if (fromServer.equals ("Bye.")) break;                                  // check for continuation
			GenericIO.writeString ("Stub: ");                                     // read user reply
			do {
				fromUser = GenericIO.readlnString ();
			} while (fromUser == null);
			com.writeObject (fromUser);                                             // send reply
		}
		com.close ();
	}

	public void announceArrival() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);           // communication channel
		Message fromServer,                                                        // input sentence
				fromUser;                                                          // output sentence

		while (!com.open ()) {                                                      // open the connection
			try{
				Thread.currentThread ().sleep ((long) (10));
			}
			catch (InterruptedException e) {}
		}

		while ((fromServer = (String) com.readObject ()) != null){                 // check receiving message
			GenericIO.writelnString ("Clien: " + fromServer);                      // print receiving message
			if (fromServer.equals ("Bye.")) break;                                  // check for continuation
			GenericIO.writeString ("Stub: ");                                     // read user reply
			do {
				fromUser = GenericIO.readlnString ();
			} while (fromUser == null);
			com.writeObject (fromUser);                                             // send reply
		}
		com.close ();

	}

	public void leaveThePlane(){
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);           // communication channel
		Message fromServer,                                                        // input sentence
				fromUser;                                                          // output sentence

		while (!com.open ()) {                                                      // open the connection
			try{
				Thread.currentThread ().sleep ((long) (10));
			}
			catch (InterruptedException e) {}
		}

		while ((fromServer = (String) com.readObject ()) != null){                 // check receiving message
			GenericIO.writelnString ("Clien: " + fromServer);                      // print receiving message
			if (fromServer.equals ("Bye.")) break;                                  // check for continuation
			GenericIO.writeString ("Stub: ");                                     // read user reply
			do {
				fromUser = GenericIO.readlnString ();
			} while (fromUser == null);
			com.writeObject (fromUser);                                             // send reply
		}
		com.close ();

	}

	public   void waitForEndOfFlight(){
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);           // communication channel
		Message fromServer,                                                        // input sentence
				fromUser;                                                          // output sentence

		while (!com.open ()) {                                                      // open the connection
			try{
				Thread.currentThread ().sleep ((long) (10));
			}
			catch (InterruptedException e) {}
		}

		while ((fromServer = (String) com.readObject ()) != null){                 // check receiving message
			GenericIO.writelnString ("Clien: " + fromServer);                      // print receiving message
			if (fromServer.equals ("Bye.")) break;                                  // check for continuation
			GenericIO.writeString ("Stub: ");                                     // read user reply
			do {
				fromUser = GenericIO.readlnString ();
			} while (fromUser == null);
			com.writeObject (fromUser);                                             // send reply
		}
		com.close ();

	}

	public   void boardThePlane() {
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);           // communication channel
		Message fromServer,                                                        // input sentence
				fromUser;                                                          // output sentence

		while (!com.open ()) {                                                      // open the connection
			try{
				Thread.currentThread ().sleep ((long) (10));
			}
			catch (InterruptedException e) {}
		}

		while ((fromServer = (String) com.readObject ()) != null){                 // check receiving message
			GenericIO.writelnString ("Clien: " + fromServer);                      // print receiving message
			if (fromServer.equals ("Bye.")) break;                                  // check for continuation
			GenericIO.writeString ("Stub: ");                                     // read user reply
			do {
				fromUser = GenericIO.readlnString ();
			} while (fromUser == null);
			com.writeObject (fromUser);                                             // send reply
		}
		com.close ();

	}


	public void shutServer() {
		//Open connection
		ClientCom con = new ClientCom (serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Thread p = (Thread) Thread.currentThread();
		//Waits for connection
		while (!con.open ()){
			try{
				p.sleep ((long) (10));
			}catch (InterruptedException e) {}
		}

		//Shut down server message
		outMessage = new Message (MessageType.SHUTDOWN);
		con.writeObject (outMessage);
		inMessage = (Message) con.readObject ();

		//Message OK
		if ((inMessage.getType () != MessageType.ACK)){
			System.out.println ("Thread " + p.getName () + ": Invalid type!");
			System.out.println (inMessage.toString ());
			System.exit (1);
		}
		//Close connection
		con.close();
	}


}
