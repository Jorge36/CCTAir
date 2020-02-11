/*
 * class to access the Pilot objects to check dates of flights
 *
 */
package cctair;

import java.util.Comparator;

/**
 * we create an object of this class to sort and do a binary search in Arraylist
 * @author Jorge and Verônica
 */
public class IdPilotCompare implements Comparator<Pilot> {
    
    // method used to compare if the pilot is valid
    public int compare(Pilot p1, Pilot p2) {
        
        if (p1.getId() < p2.getId())
            return -1;
        if (p1.getId() > p2.getId())
            return 1;
        else return 0;
        
    }
    
}
