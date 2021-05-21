package client.stubs;

import genclass.GenericIO;

import commInfra.ClientCom;
import commInfra.MemException;
import commInfra.Message;
import commInfra.MessageType;
import commInfra.ClientCom;
import commInfra.SimulPar;

import client.entities.Hostess;
import client.entities.HostessStates;
import client.entities.Pilot;
import client.entities.PilotStates;
import client.entities.Passenger;
import client.entities.PassengerStates;


/**
 *   Reference to a remote object.
 *
 *   It provides means to the setup of a communication channel and the message exchange.
 */

public class DepartureAirportStub{
	
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

   public DepartureAirportStub (){
      serverHostName = SimulPar.DepartureAirportHostName;
      serverPortNumb = SimulPar.DepartureAirportPort;
   }

  /**
   *  Message exchange with the remote object.
   */

   public void exchange () {
 
     ClientCom com = new ClientCom (serverHostName, serverPortNumb);           // communication channel
     String fromServer,                                                        // input sentence
            fromUser;                                                          // output sentence

     while (!com.open ()) {                                                      // open the connection
      try{
        Thread.currentThread ().sleep ((long) (10));
       }
       catch (InterruptedException e) {}
     }
     
     while ((fromServer = (String) com.readObject ()) != null){                 // check receiving message
    	 GenericIO.writelnString ("Server: " + fromServer);                      // print receiving message
       if (fromServer.equals ("Bye.")) break;                                  // check for continuation
       GenericIO.writeString ("Client: ");                                     // read user reply
       do {
        fromUser = GenericIO.readlnString ();
       } while (fromUser == null);
       com.writeObject (fromUser);                                             // send reply
     }
     com.close ();                                                             // close the connection
   }
   
   /**
	*  Operation wait in queue.
	*
	*  It is called by the passenger when he arrives to the airport.
	*
	*/	
	
	public void waitInQueue() {
		
		int passengerId; // passenger id
		Passenger passenger = ((Passenger) Thread.currentThread());
		passengerId = passenger.getPassengerId();
		
		ClientCom com = new ClientCom (serverHostName, serverPortNumb);           // communication channel
	     String fromServer,                                                        // input sentence
	            fromUser;                                                          // output sentence

	     while (!com.open ()) {                                                      // open the connection
	      try{
	        Thread.currentThread ().sleep ((long) (10));
	       }
	       catch (InterruptedException e) {}
	     }
	     fromUser = "";
	     com.writeObject (fromUser);  
		
	}
	
	/**
	*  Operation show documents.
	*
	*  It is called by the passenger when the hostess wants to check his documents.
	*
	*/	
	
	public void showDocuments() {
//		TODO
//		Passenger passenger = ((Passenger) Thread.currentThread());
//		int passengerId = passenger.getPassengerId();
//		passen[passengerId] = passenger;
//
//		calledPassengerDocuments = passengerId;
//		GenericIO.writelnString("\033[42m-Passenger " + passengerId + " giving his doccuments\033[0m");
//		notifyAll();
//		while (passenger.getPassengerState() != PassengerStates.INFLIGHT) { 
//			try {
//				wait();
//			} 
//			catch (InterruptedException e) {}
//		}
	
	}
	 
   /**
	*  Operation wait for next passenger.
	*
	*  It is called by the hostess when the plane isn't ready to fly and she has to wait for passengers.
	*
	*  @return passengerId returns the Id from the passenger that is in front of the queue.
	*/	
	
	public int waitForNextPassenger() { 
//		TODO
//		GenericIO.writelnString("\033[41mPassengers in line " + nLine + "\033[0m");
//		((Hostess) Thread.currentThread()).setHostessState(HostessStates.WAITFORPASSENGER);
//		repos.setHostessState (((Hostess) Thread.currentThread ()).getHostessState ());
//		GenericIO.writelnString("\033[41mPassengers in nleft " + nLeft + "\033[0m");
//		GenericIO.writelnString("\033[41mPassengers in passengersOnBoard " + passengersOnBoard + "\033[0m");
//		
//		if ((passengersOnBoard >= SimulPar.minInPlane && nLine == 0) || passengersOnBoard == SimulPar.maxInPlane
//				|| ( nLine == 0 && nLeft==0)) {
//			notifyAll();
//			return -passengersOnBoard;
//		}
//		while (nLine == 0) {
//			try {
//				wait();
//			} 
//			catch (Exception e) {
//				return -SimulPar.nPassengers-1;
//			}
//		}
//		
//		if (nLine > 0)
//			nLine -= 1;
//
//		int passengerId;
//		
//		try {
//			passengerId = waitingLine.read();
//			if ((passengerId < 0) || (passengerId >= SimulPar.nPassengers))
//				throw new MemException("illegal customer id!");
//		} 
//		catch (MemException e) {
//			GenericIO.writelnString("Retrieval of customer id from waiting FIFO failed: " + e.getMessage());
//			passengerId = -SimulPar.nPassengers-1;
//			System.exit(1);
//		}
//		
//		calledPassengerId = passengerId;
//		notifyAll();
//
//		return passengerId;
		return 1;
		
	 }

   /**
	*  Operation prepare for pass boarding.
	*
	*  It is called by the hostess when she is waiting for the plane to arrive to the transfer gate.
	*
	*/
	
	public void prepareForPassBoarding() {
		//TODO
//		while (!plane_ready_boarding){
//			
//			try {
//				GenericIO.writelnString("\n\033[0;34mHostess Waiting for Plane\033[0m\n");
//				wait();
//			} 
//			catch (Exception e) {}
//			
//		}
//		
//		next_fly = false;
//		plane_ready_boarding = false;
//		passengersOnBoard = 0;
		
	}
	
   /**
	*  Operation check documents.
	*
	*  It is called by the hostess to check the documents of the first passenger of the queue.
	*
	*  @param waitPassengerId receives the id of the passenger that is having his documents checked
	*/

	public void checkDocuments(int waitPassengerId) {
		//TODO
//		GenericIO.writelnString("\n\033[42m----Enter Check Documents----\033[0m");
//
//		while (waitPassengerId != calledPassengerDocuments) {
//			try {
//				wait();
//			} 
//			catch (InterruptedException e) {}
//		}	
//		
//		((Hostess) Thread.currentThread()).setHostessState(HostessStates.CHECKPASSENGER);
//		repos.setHostessState (((Hostess) Thread.currentThread ()).getHostessState (),waitPassengerId );
//
//		GenericIO.writelnString("Checking Doccuments of passenger " + waitPassengerId);
//		passen[waitPassengerId].setPassengerState(PassengerStates.INFLIGHT);
//		passengersOnBoard++;
//		nLeft--;
//		notifyAll();
//				
//		GenericIO.writelnString("Passengers on Board " + passengersOnBoard);

	}

   /**
	*  Operation inform plane ready for boarding.
	*
	*  It is called by the pilot after parking the plane at the transfer gate.
	*
	*/
	
	public void informPlaneReadyForBoarding() {
		 //TODO
//		flightNumber++;
//		repos.reportSpecificStatus("\nFlight " + flightNumber + ": boarding started."); 
//		 
//		((Pilot) Thread.currentThread()).setPilotState(PilotStates.READYFORBOARDING);
//		repos.setPilotState (((Pilot) Thread.currentThread ()).getPilotState ());
//		plane_ready_boarding = true;
//		GenericIO.writelnString("Plane ready to flight");
//		notifyAll();

	}

	public void informPlaneReadyToTakeOff() {
		//TODO
//		flightNumber++;
//		repos.reportSpecificStatus("\nFlight " + flightNumber + ": boarding started.");
//
//		((Pilot) Thread.currentThread()).setPilotState(PilotStates.READYFORBOARDING);
//		repos.setPilotState (((Pilot) Thread.currentThread ()).getPilotState ());
//		plane_ready_boarding = true;
//		GenericIO.writelnString("Plane ready to flight");
//		notifyAll();

	}

   /**
	*  Operation park at transfer gate.
	*
	*  It is called by the pilot after the flight back to park the plane at the transfer gate.
	*
	*/
	
	public void parkAtTransferGate() {
		//TODO
//		((Pilot) Thread.currentThread()).setPilotState(PilotStates.ATTRANSFERGATE);
//		repos.setPilotState (((Pilot) Thread.currentThread ()).getPilotState ());
//		GenericIO.writelnString("PLANE AT TRANSFER GATE");
//		next_fly = true;
//		notifyAll();

	}
	
   /**
	*  Operation check end of the day.
	*
	*  Checks if all the passengers have traveled to the destination airport.
	*
	*  @return true if all the passengers have traveled to the destination airport, false otherwise.
	*
	*/

	public Boolean CheckEndOfDay() {
		//TODO
		return false;//nLeft == 0;
	}
	
   /**
	*  Operation wait for next flight.
	*
	*  It is called by the hostess when the plane left the departure airport and 
	*  she has to wait for the next flight to arrive.
	*
	*/

	public void waitForNextFlight() {
		//TODO 
		//		((Hostess) Thread.currentThread()).setHostessState(HostessStates.WAITFORFLIGHT);
//		repos.setHostessState (((Hostess) Thread.currentThread ()).getHostessState ()); 
//		while (!next_fly) {
//			
//			try {
//				wait();
//			} 
//			catch (Exception e) {
//				return;
//			}
//			
//		}
		
	}

	public void waitForAllInBoard() {
		//TODO
		//		((Hostess) Thread.currentThread()).setHostessState(HostessStates.WAITFORFLIGHT);
//		repos.setHostessState (((Hostess) Thread.currentThread ()).getHostessState ());
//		while (!next_fly) {
//
//			try {
//				wait();
//			}
//			catch (Exception e) {
//				return;
//			}
//
//		}

	}
	public void prepareForPassBoarding() {
		//TODO
		//		((Hostess) Thread.currentThread()).setHostessState(HostessStates.WAITFORFLIGHT);
//		repos.setHostessState (((Hostess) Thread.currentThread ()).getHostessState ());
//		while (!next_fly) {
//
//			try {
//				wait();
//			}
//			catch (Exception e) {
//				return;
//			}
//
//		}

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
