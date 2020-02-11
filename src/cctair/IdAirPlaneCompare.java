/*
 * class to access the Airplane objects to check it
 *
 */
package cctair;

import java.util.Comparator;

/**
 * we create an object of this class to sort and do a binary search in Arraylist
 * @author Jorge and Verônica
 */
public class IdAirPlaneCompare implements Comparator<AirPlane> {
    
    // method used to compare the airplane
    public int compare(AirPlane a1, AirPlane a2) {
        
        if (a1.getId() < a2.getId())
            return -1;
        if (a1.getId() > a2.getId())
            return 1;
        else return 0;
        
    }
    
}
    
