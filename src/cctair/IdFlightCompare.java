package cctair;

import java.util.Comparator;

/**
 * we create an object of this class to sort and do a binary search in Arraylist
 * @author Jorge and Verônica
 */
public class IdFlightCompare implements Comparator<Flight> {
    
    //method used to compare the id by flight
    public int compare(Flight f1, Flight f2) {
        
        if (f1.getId() < f2.getId())
            return -1;
        if (f1.getId() > f2.getId())
            return 1;
        else return 0;
        
    }
    
}
