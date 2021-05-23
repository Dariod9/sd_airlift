package clientSide.entitiesStubs;

import clientSide.ClientCom;
import clientSide.entities.Hostess;
import clientSide.entities.Passenger;
import clientSide.entities.Pilot;


import commInfra.Message;
import commInfra.MessageType;
import commInfra.SimulatorParam;


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
  */
   public DepartureAirportStub (){
      serverHostName = SimulatorParam.DepAirportHostName;
      serverPortNumb = SimulatorParam.DepAirportPort;
   }

   /**
	*  Operation wait in queue.
	*
	*  It is called by the passenger when he arrives to the airport.
	*
	*/	
	
	public void waitInQueue() {

		ClientCom con = new ClientCom(serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Passenger p = (Passenger) Thread.currentThread();
		//Waits for connection
		while (!con.open()) {
			try {
				p.sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}


		String entity="id";
		//What should i do message with the flight number
		outMessage = new Message(MessageType.WAIT_IN_QUEUE, p.getPassengerId(), entity);
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
	
	/**
	*  Operation show documents.
	*
	*  It is called by the passenger when the hostess wants to check his documents.
	*
	*/	
	public int getFlewPilot(){

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
		outMessage = new Message(MessageType.GET_FLEW);
		con.writeObject(outMessage);
		inMessage = (Message) con.readObject();

		if ((inMessage.getType() != MessageType.ACK)) {
			System.out.println("Thread " + p.getName() + ": Invalid type!");
			System.out.println(inMessage.toString());
			System.exit(1);
		}

		//Close connection
		con.close();
		return inMessage.getFlew();
	}

	public int getFlewHostess(){

		ClientCom con = new ClientCom(serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Hostess p = (Hostess) Thread.currentThread();
		//Waits for connection
		while (!con.open()) {
			try {
				p.sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		//What should i do message with the flight number
		outMessage = new Message(MessageType.GET_FLEW);
		con.writeObject(outMessage);
		inMessage = (Message) con.readObject();

		if ((inMessage.getType() != MessageType.ACK)) {
			System.out.println("Thread " + p.getName() + ": Invalid type!");
			System.out.println(inMessage.toString());
			System.exit(1);
		}

		//Close connection
		con.close();
		return inMessage.getFlew();
	}
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
	
	public boolean waitForNextPassenger() {

		ClientCom con = new ClientCom(serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Hostess p = (Hostess) Thread.currentThread();
		//Waits for connection
		while (!con.open()) {
			try {
				p.sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		//What should i do message with the flight number
		outMessage = new Message(MessageType.WAIT_FOR_NEXT_PASSENGER);
		con.writeObject(outMessage);
		inMessage = (Message) con.readObject();

		if ((inMessage.getType() != MessageType.ACK)) {
			System.out.println("Thread " + p.getName() + ": Invalid type!");
			System.out.println(inMessage.toString());
			System.exit(1);
		}

		//Close connection
		con.close();
		return inMessage.isPlaneReady();
	 }

   /**
	*  Operation prepare for pass boarding.
	*
	*  It is called by the hostess when she is waiting for the plane to arrive to the transfer gate.
	*
	*/
	
	public void prepareForPassBoarding() {
		ClientCom con = new ClientCom(serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Hostess p = (Hostess) Thread.currentThread();
		//Waits for connection
		while (!con.open()) {
			try {
				p.sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		//What should i do message with the flight number
		outMessage = new Message(MessageType.PREPARE_FOR_PASS_BOARDING);
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
	
   /**
	*  Operation check documents.
	*
	*  It is called by the hostess to check the documents of the first passenger of the queue.
	*/

	public void checkDocuments() {
		ClientCom con = new ClientCom(serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Hostess p = (Hostess) Thread.currentThread();
		//Waits for connection
		while (!con.open()) {
			try {
				p.sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		//What should i do message with the flight number
		outMessage = new Message(MessageType.CHECK_DOCUMENTS);
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

   /**
	*  Operation inform plane ready for boarding.
	*
	*  It is called by the pilot after parking the plane at the transfer gate.
	*
	*/
	
	public void informPlaneReadyForBoarding() {

//		String decision = "";
		//Open connection
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
		outMessage = new Message(MessageType.INFORM_PLANE_READY_FOR_BOARDING);
		con.writeObject(outMessage);
		inMessage = (Message) con.readObject();

		if ((inMessage.getType() != MessageType.ACK)) {
			System.out.println("Thread " + p.getName() + ": Invalid type!");
			System.out.println(inMessage.toString());
			System.exit(1);
		}

//		switch (inMessage.getType()) {
//			//Passenger goes home
//			case ACK:
//				decision = "board";
//				break;
//		}
		//Close connection
		con.close();
//		return decision;

	}

	public void informPlaneReadyToTakeOff() {
		ClientCom con = new ClientCom(serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Hostess p = (Hostess) Thread.currentThread();
		//Waits for connection
		while (!con.open()) {
			try {
				p.sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		//What should i do message with the flight number
		outMessage = new Message(MessageType.INFORM_PLANE_READY_TO_TAKEOFF);
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

		ClientCom con = new ClientCom(serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Hostess p = (Hostess) Thread.currentThread();
		//Waits for connection
		while (!con.open()) {
			try {
				p.sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		//What should i do message with the flight number
		outMessage = new Message(MessageType.WAIT_FOR_NEXT_FLIGHT);
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

	public void waitForAllInBoard() {

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
		outMessage = new Message(MessageType.WAIT_FOR_ALL_IN_BOARD);
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

	public void flyToDestinationPoint() {
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
		outMessage = new Message(MessageType.FLY_TO_DESTINATION_POINT);
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

	public void passengerEnteredPlane() {
		ClientCom con = new ClientCom(serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Passenger p = (Passenger) Thread.currentThread();
		//Waits for connection
		while (!con.open()) {
			try {
				p.sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		String entity = "id";
		//What should i do message with the flight number
		outMessage = new Message(MessageType.PASSENGER_ENTERED_PLANE,p.getPassengerId(),entity);
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
