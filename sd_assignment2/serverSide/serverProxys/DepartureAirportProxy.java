package serverSide.serverProxys;

import serverSide.ServerCom;
import serverSide.sharedRegionInterfaces.*;
import structs.Message;
import structs.MessageException;
import structs.entitiesInterfaces.HostessInt;
import structs.entitiesInterfaces.PassengerInt;
import structs.entitiesInterfaces.PilotInt;

/**
 *   Reference to a remote object.
 *
 *   It provides means to the setup of a communication channel and the message exchange.
 */
public class DepartureAirportProxy extends Thread implements PilotInt, HostessInt, PassengerInt {

	/**
	 *  Launched threads counter
	 *  @serialField nProxy
	 */

	private static int nProxy;

	private int hostessID;
	private int hostessState;
	private int pilotID;
	private int pilotState;
	private int passengerID;
	private int passengerState;

	/**
	 *  Communication channel
	 *    @serialField sconi
	 */

	private ServerCom sconi;

	/**
	 *  Departure Airport Interfacea
	 *    @serialField alInter
	 */
	private DepAirportInt depApInter;

	/**
	 *   Departure Airport Proxy Instantiation
	 *    @param sconi Communication channel
	 *    @param depApInter Arrival Lounge Interface
	 */

	public DepartureAirportProxy (ServerCom sconi, DepAirportInt depApInter)
	{
		super ("Proxy_" + getProxyId ());

		this.sconi = sconi;
		this.depApInter = depApInter;
	}


	/**
	 *  Service provider agent thread life cycle.
	 */
	@Override
	public void run ()
	{
		//Input message
		Message inMessage = null,
				//Output message
				outMessage = null;

		//Read client message
		inMessage = (Message) sconi.readObject ();
		try
		{
			//Process message
			outMessage = depApInter.processAndReply (inMessage);
		}
		catch (MessageException e)
		{ System.out.println ("Thread " + getName () + ": " + e.getMessage () + "!");
			System.out.println (e.getMessageVal ().toString ());
			System.exit (1);
		}
		//Send message to client
		sconi.writeObject (outMessage);
		//Close communication channel
		sconi.close ();
	}

	/**
	 * Instantiation ID generation
	 *    @return Instantiation ID
	 */
	private static int getProxyId ()
	{
		Class<serverSide.serverProxys.DepartureAirportProxy> cl = null;

		int proxyId;

		try
		{ cl = (Class<serverSide.serverProxys.DepartureAirportProxy>) Class.forName ("serverSide.serverProxys.DepartureAirportProxy");
		}
		catch (ClassNotFoundException e)
		{ System.out.println ("Proxy al data type not found!");
			e.printStackTrace ();
			System.exit (1);
		}

		synchronized (cl)
		{ proxyId = nProxy;
			nProxy += 1;
		}

		return proxyId;
	}

	public ServerCom getScon ()
	{
		return sconi;
	}

	@Override
	public int getHostessState() {
		return this.hostessState;
	}

	@Override
	public void setHostessState(int hostessState) {
		this.hostessState=hostessState;
	}

	@Override
	public void setHostessID(int hostessID) {
		this.hostessID=hostessID;
	}

	@Override
	public int getHostessID() {
		return this.hostessID;
	}

	@Override
	public int getPassengerId() {
		return this.passengerID;
	}

	@Override
	public void setpassengerId(int passengerId) {
		this.passengerID=passengerId;
	}

	@Override
	public int getPassengerState() {
		return this.passengerState;
	}

	@Override
	public void setPassengerState(int passengerState) {
		this.passengerState=passengerState;
	}

	@Override
	public void showDocuments() {

	}

	@Override
	public void travelToAirport() {

	}

	@Override
	public int getPilotstate() {
		return this.pilotState;
	}

	@Override
	public void setPilotstate(int pilotstate) {
		this.pilotState=pilotstate;
	}

	@Override
	public int getPilotID() {
		return this.pilotID;
	}

	@Override
	public void setPilotID(int pilotID) {
		this.pilotID=pilotID;
	}

	@Override
	public void fly() {

	}
}
