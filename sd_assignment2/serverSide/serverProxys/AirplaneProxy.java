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
import serverSide.ServerCom;


/**
 *   Reference to a remote object.
 *
 *   It provides means to the setup of a communication channel and the message exchange.
 */
public class AirplaneProxy extends Thread {

	/**
	 *  Launched threads counter
	 *  @serialField nProxy
	 */

	private static int nProxy;


	/**
	 *  Communication channel
	 *    @serialField sconi
	 */

	private ServerCom sconi;

	/**
	 *  Arrival Lounge Interfacea
	 *    @serialField alInter
	 */
	private AirportInterface alInter;

	/**
	 *   Arrival Lounge Proxy Instantiation
	 *    @param sconi Communication channel
	 *    @param alInter Arrival Lounge Interface
	 */

	public AirplaneProxy(ServerCom sconi, AirportInterface alInter)
	{
		super ("Proxy_" + getProxyId ());

		this.sconi = sconi;
		this.alInter = alInter;
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
			outMessage = alInter.processAndReply (inMessage);
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
		Class<serverSide.Proxys.AirportProxy> cl = null;

		int proxyId;

		try
		{ cl = (Class<serverSide.Proxys.AirportProxy>) Class.forName ("serverSide.Proxys.AirportProxy");
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
}
