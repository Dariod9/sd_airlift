package clientSide.entitiesStubs;

import clientSide.ClientCom;
import clientSide.entities.Pilot;
import structs.SimulatorParam;
import structs.Message;
import structs.MessageType;

public class DestinationAirportStub {
	/**
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
  */
  public DestinationAirportStub (){
      serverHostName = SimulatorParam.DestAirportHostName;
      serverPortNumb = SimulatorParam.DestAirportPort;
   }

	public void flyToDeparturePoint() {
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
		outMessage = new Message(MessageType.FLY_TO_DEPARTURE_POINT);
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
