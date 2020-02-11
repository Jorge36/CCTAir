/*
 * class to access the Pilot objects for using in exception
 *
 */
package cctair;

/**
 * Custom exception which is thrown when the user doesn't assign the pilot to the aircraft
 * and want to confirm this one for the flight
 * @author Jorge and Verônica
 */
public class PilotNotAssignedException extends Exception {

    /**
     * Creates a new instance of <code>PilotNotAssignedException</code> without
     * detail message.
     * 
     */
    public PilotNotAssignedException() {
    }

    /**
     * Constructs an instance of <code>PilotNotAssignedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PilotNotAssignedException(String msg) {
        super(msg);
    }
}
