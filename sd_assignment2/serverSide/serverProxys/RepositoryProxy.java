package serverSide.serverProxys;

import serverSide.Repository;
import serverSide.ServerCom;
import serverSide.sharedRegionInterfaces.AirplaneInt;
import structs.Message;
import structs.MessageException;

public class RepositoryProxy extends Thread{
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
    private AirplaneInt alInter;

    /**
     *   Arrival Lounge Proxy Instantiation
     *    @param sconi Communication channel
     *    @param alInter Arrival Lounge Interface
     */

    public RepositoryProxy (ServerCom sconi, AirplaneInt alInter)
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


