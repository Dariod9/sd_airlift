package clientSide.entitiesStubs;

import clientSide.ClientCom;
import clientSide.entities.Pilot;
import genclass.GenericIO;
import genclass.TextFile;
import structs.Message;
import structs.MessageType;
import structs.SimulatorParam;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GeneralReposStub {
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

   public GeneralReposStub (){
      serverHostName = SimulatorParam.ReposHostName;
      serverPortNumb = SimulatorParam.ReposPort;
   }

	public synchronized void setPilotState(int state){
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
		outMessage = new Message(MessageType.SET_PILOT_STATE);
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

	public synchronized void setHostessState(int state){
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
		outMessage = new Message(MessageType.SET_HOSTESS_STATE);
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

	public synchronized void setHostessState(int state, int id_passenger){
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
		outMessage = new Message(MessageType.SET_HOSTESS_STATE_ID);
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

	public synchronized void setPassengerState(int id, int state){
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
		outMessage = new Message(MessageType.SET_PASSENGER_STATE);
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

	public synchronized void addFlightInfo(int inpl){

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
		outMessage = new Message(MessageType.ADD_FLIGHT_INFO);
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
	 *  Write the header to the logging file.
	 *
	 *  The passengers are sleeping and the hostess and pilot are carrying out normal duties.
	 *  Internal operation.
	 */

	private void writeHeader(){
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
		outMessage = new Message(MessageType.WRITE_HEADER);
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
	 *  Write a state line at the beginning of the logging file with the initial states.
	 *
	 *  The current state of the pilot, hostess and passengers are organized in a line to be printed.
	 *  Internal operation.
	 */

	private void writeSmallHeader(int nFlight, String message){
		ClientCom con = new ClientCom(serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Thread p = Thread.currentThread();
		//Waits for connection
		while (!con.open()) {
			try {
				p.sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		//What should i do message with the flight number
		outMessage = new Message(MessageType.WRITE_SMALL_HEADER);
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
	 *  Write a state line at the end of the logging file.
	 *
	 *  The current state of the pilot, hostess and passengers are organized in a line to be printed.
	 *  Internal operation.
	 */

	private void reportStatus(String teste){
		ClientCom con = new ClientCom(serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Thread p =  Thread.currentThread();
		//Waits for connection
		while (!con.open()) {
			try {
				p.sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		//What should i do message with the flight number
		outMessage = new Message(MessageType.REPORT_STATUS);
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
	 *  Write a summary of the flights needed to transport all the passengers.
	 *
	 *  The current state of the pilot, hostess and passengers are organized in a line to be printed.
	 *  Internal operation.
	 */

	public synchronized void reportSummary(){
		ClientCom con = new ClientCom(serverHostName, serverPortNumb);
		Message inMessage, outMessage;
		Thread p =  Thread.currentThread();
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
