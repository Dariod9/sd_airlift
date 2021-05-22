package clientSide.entitiesStubs;

import clientSide.ClientCom;
import clientSide.entities.Pilot;
import clientSide.entities.PilotStates;
import structs.Message;
import structs.MessageType;
import structs.SimulatorParam;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RepositoryStub {
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

   public RepositoryStub(){
      serverHostName = SimulatorParam.RepositoryHostName;
      serverPortNumb = SimulatorParam.RepositoryPort;
   }

	public void setPilotState(int state){
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

		String entity="pilot";
		//What should i do message with the flight number
		outMessage = new Message(MessageType.SET_PILOT_STATE, state, entity);
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
	 *  Set hostess state.
	 * @param state hostess state.
	 */

	public void setHostessState(int state){
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

		String entity="hostess";
		//What should i do message with the flight number
		outMessage = new Message(MessageType.SET_HOSTESS_STATE, state, entity);
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
	 * Set hostess state after checking a passenger.
	 * @param state hostess state.
	 * @param id_passenger ID of the passenger checked.
	 */

	public void setHostessState(int state, int id_passenger){
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

		String entity="hostess";
		//What should i do message with the flight number
		outMessage = new Message(MessageType.SET_HOSTESS_STATE_ID, state, id_passenger, entity);
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
	 *  Set passenger state.
	 * @param id passenger id.
	 * @param state passenger state.
	 */

	public void setPassengerState(int id, int state){
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

		String entity="passenger";
		//What should i do message with the flight number
		outMessage = new Message(MessageType.SET_PASSENGER_STATE,state,id,entity);
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
	 *  Link a flight number to its occupation.
	 * @param inpl occupation.
	 */

	public void addFlightInfo(int inpl){

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

		String entity="flight";
		//What should i do message with the flight number
		outMessage = new Message(MessageType.ADD_FLIGHT_INFO, inpl, entity);
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

	public void reportSummary(){
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

		//What should i do message with the flight number
		outMessage = new Message(MessageType.REPORT_SUMMARY);
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
		Thread p =  Thread.currentThread();
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
