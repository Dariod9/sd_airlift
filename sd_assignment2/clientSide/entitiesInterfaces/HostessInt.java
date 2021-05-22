package clientSide.entitiesInterfaces;
/**
 *   Hostess thread.
 *
 *   It simulates the hostess life cycle.
 *   Static solution.
 */

public interface HostessInt{

    /**
     * Get Hostess state.
     *
     * @return Hostess State.
     */
    int getHostessState();
    /**
     * Set hostess id.
     *
     * @param hostessID new hostess id.
     */
    void setHostessID(int hostessID);

    /**
     * Get hostess ID.
     *
     * @return hostess ID.
     */
    int getHostessID();

    /**
     * Set Hostess State.
     *
     * @param hostessState new costumer id.
     */
    void setHostessState(int hostessState);

}
