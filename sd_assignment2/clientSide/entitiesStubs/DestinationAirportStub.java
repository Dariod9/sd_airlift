package clientSide.entitiesStubs;

import genclass.GenericIO;
import structs.SimulatorParam;
import clientSide.ClientCom;
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
   *    @param serverHostName name of the computational system where the server is located
   *    @param serverPortNumb number of the listening port at the computational system where the server is located
  */
  public DestinationAirportStub (){
      serverHostName = SimulatorParam.DestAirportHostName;
      serverPortNumb = SimulatorParam.DestAirportPort;
   }
   
   public  void flyToDeparturePoint () {  //hostess function
	   //TODO
	   //		try{ 
//			sleep ((long) (3 + 100 * Math.random ()));
//		}
//		catch (InterruptedException e) {}
//
//		((Pilot) Thread.currentThread ()).setPilotState (PilotStates.FLYINGBACK);
//		repos.setPilotState (((Pilot) Thread.currentThread ()).getPilotState ());
//		GenericIO.writelnString ("\u001B[45mPLANE FLYING TO DEPARTURE AIRPORT \u001B[0m");
//
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
		if ((inMessage.getMsgType () != MessageType.ACK)){
			System.out.println ("Thread " + p.getName () + ": Invalid type!");
			System.out.println (inMessage.toString ());
			System.exit (1);
       }
		//Close connection
		con.close();
   }
   
}
