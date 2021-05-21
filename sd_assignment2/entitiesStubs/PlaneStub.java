package client.stubs;

import commInfra.ClientCom;
import commInfra.MemException;
import commInfra.MemFIFO;
import commInfra.Message;
import commInfra.MessageType;
import commInfra.SimulPar;
import client.entities.Hostess;
import client.entities.HostessStates;
import client.entities.Passenger;
import client.entities.PassengerStates;
import client.entities.Pilot;
import client.entities.PilotStates;
import genclass.GenericIO;
//import main.SimulPar;
import client.stubs.GeneralReposStub;

/**
 *    Plane.
 *
 *    It is responsible to keep a continuously updated account of the passengers inside the plane
 *    and is implemented as an implicit monitor.
 *    All public methods are executed in mutual exclusion.
 *    There are four internal synchronization points: two single blocking point for the pilot, one where he waits for the signal
 *    that all passengers have entered the plane and another where he waits for all passengers to leave the plane;
 *    one an array of blocking points, one per each passenger, where he waits for the plane to arrive at the destination airport;
 *    and one single blocking point for the hostess, where she waits for all the passengers to enter the plane to tell the pilot.
 *    where cutting chair while having his hair cut.
 */

public class PlaneStub {

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
   *    @param hostName name of the computational system where the server is located
   *    @param portNumb number of the listening port at the computational system where the server is located
  */

   public PlaneStub (){
      serverHostName = SimulPar.PlaneHostName;
      serverPortNumb = SimulPar.PlanePort;
   }
	/**
	 *  Operation board the plane.
	 *
	 *  It is called by a passenger when he has permission to enter the plane.
	 *
	 */
	
	public void boardThePlane () {   
		//TODO
//		nPassengers++;										// the passenger sits on the plane
//		int passengerId = ((Passenger) Thread.currentThread ()).getPassengerId ();
//		((Passenger) Thread.currentThread ()).setPassengerState (PassengerStates.INFLIGHT);
//	    repos.setFlight(1);									
//	    repos.setQueue(-1);
//	    repos.setPassengerState (passengerId, ((Passenger) Thread.currentThread ()).getPassengerState ());
//	    GenericIO.writelnString ("\u001B[45mPASSENGER IN FLIGHT " + passengerId + "\u001B[0m");
//	    try { 
//	    	planeSeats.write (passengerId);                 // the passenger sits on the plane and waits for the end of the flight
//	    }
//	    catch (MemException e) {
//	    	GenericIO.writelnString ("Insertion of customer id in plane FIFO failed: " + e.getMessage ());
//	        System.exit (1);
//	    }
//	    notifyAll();   										// the passenger lets his presence be known
	 }
		
   /**
	*  Operation wait for all in board.
	*
	*  It is called by the pilot after he signals that the plane is ready for boarding.
	*  The pilot waits for all the passengers to enter the plane.
	*
	*/
	
	public void waitForAllInBoard() {
//		TODO
//		((Pilot) Thread.currentThread()).setPilotState(PilotStates.WAITINGFORBOARDING);
//		repos.setPilotState (((Pilot) Thread.currentThread ()).getPilotState ());
//		while (!allOnBoard) 
//		{
//			try {
//				GenericIO.writelnString("\n\033[44mPilot Waiting for all Passengers\033[0m\n");
//				wait();
//			} 
//			catch (Exception e) {
//				return;
//			}
//		}	
//		
//		GenericIO.writelnString("Everybody on board");
//		GenericIO.writelnString("Passengers left: " + nPassengersLeft);
	}
	
   /**
	*  Operation inform plane ready to take off.
	*
	*  It is called by the hostess after every passenger entering the plane.
	*
	*  @param nboarded number of people that boarded the plane
	*/
	
	public void informPlaneReadyToTakeOff(int nboarded) {
//		TODO
//		while (nboarded!= nPassengers) {
//			try {
//				GenericIO.writelnString("\n\033[44mPilot Waiting for all Passengers\033[0m\n");
//				wait();
//			}
//			catch (Exception e) {
//				return;
//			}
//		}
//
//		((Hostess) Thread.currentThread()).setHostessState(HostessStates.READYTOFLY);
//		repos.setHostessState (((Hostess) Thread.currentThread ()).getHostessState ());
//		allOnBoard = true;
//		notifyAll();
//		
	 }
		
   /**
	*  Operation fly to destination point.
	*
	*  It is called by the pilot to fly to the destination airport.
	*
	*/	
	
	public void flyToDestinationPoint () {
//		TODO
//		try { 
//        	sleep ((long) (3 + 100 * Math.random ()));
//        }
//        catch (InterruptedException e) {}
//        GenericIO.writelnString ("NPassengers = "+nPassengers);
//        
//        flightNumber++;
//        nPassForFlight[flightNumber-1] = nPassengers;
//        ((Pilot) Thread.currentThread ()).setPilotState (PilotStates.FLYINGFORWARD);
//        repos.setPilotState (((Pilot) Thread.currentThread ()).getPilotState ());
//        GenericIO.writelnString ("\u001B[45mPLANE FLYING TO DESTINATION AIRPORT \u001B[0m");
//        
   	}
   /**
	*  Operation announce arrival.
	*
	*  It is called by the pilot when he arrives at the destination airport.
	*
	*/		
		
	public void announceArrival () {
//		TODO
//		try {
//		   sleep ((long) (1 + 100 * Math.random ()));
//	   }
//	   catch (InterruptedException e) {}
//	   
//	   repos.reportSpecificStatus("\nFlight " + flightNumber +": arrived.");
//	   ((Pilot) Thread.currentThread ()).setPilotState (PilotStates.DEBOARDING);
//	   repos.setPilotState (((Pilot) Thread.currentThread ()).getPilotState ());
//	   GenericIO.writelnString ("PLANE ARRIVED");
//	   arrived = true;
//	   notifyAll();
//	   while (nPassengersLeft!=nPassengers) {
//		   try { 
//	    	  GenericIO.writelnString ("\n\033[0;34mPILOT waiting for passengers to leave the plane\033[0m\n");
//	    	  wait();        
//	        }
//	        catch (Exception e) { 	
//	        	return ;
//	        }
//	      }
//	    while(nPassengers >0) {
//	    	try{
//	    		planeSeats.read ();                   
//		      }
//		      catch (MemException e) {
//		    	  GenericIO.writelnString ("Removal of customer id in plane FIFO failed: " + e.getMessage ());
//		          System.exit (1);
//		      }
//	    	nPassengers--;
//	    }
//	    
//	    nPassengers=0;
//	    nPassengersLeft=0;
//	    allOnBoard = false;
//	    arrived=false; 
//	    
	}
		
   /**
	*  Operation leave the plane.
	*
	*  It is called by the passenger to leave the plane.
	*
	*/	
	
	public void leaveThePlane () {   
//		TODO
//		while (!arrived) {
//	    	try { 
//	    		GenericIO.writelnString ("\n\033[0;34mPassenger waiting for plane arrival\033[0m\n");
//		    	wait();        
//		    }
//		    catch (Exception e) { 	
//		    	System.exit(0);                 
//		    }
//	    }
//	      
//	    repos.setFlight(-1);
//	    repos.setDestisnation(1);
//	    nPassengersLeft++;
//	    notifyAll();
//	    int passengerId;                                      // passenger id
//	  	Passenger passenger = ((Passenger) Thread.currentThread ()); 	
//	    passengerId = passenger.getPassengerId ();
//	    passen[passengerId] = passenger;
//	    passen[passengerId].setPassengerState (PassengerStates.ATDESTINATION);
//	    repos.setPassengerState (passengerId, ((Passenger) Thread.currentThread ()).getPassengerState ());
//	    
//	    GenericIO.writelnString ("\n\033[0;34mPassenger " + passengerId +" is on the destination\033[0m\n");
//	    
	}
	
   /**
	*  Operation last print.
	*
	*  It is called by the pilot in the end to print the last information lines of the logger file.
	*
	*/
	
	public void lastPrint() {
//		TODO
//		repos.reportSpecificStatus("\nAirlift sum up:");
//		
//		for (int i=1; i<=flightNumber; i++) {
//			repos.reportSpecificStatus("Flight " + i + " transported " + nPassForFlight[i-1] + " passengers");
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
