
package cctair;

import java.util.Comparator;

/**
 * we create an object of this class to sort and do a binary search in Arraylist
 * @author Jorge and Verônica
 */
public class DateFlightCompare implements Comparator<Flight> {
    
    // method used to compare if the date of flight is valid or not
    public int compare(Flight f1, Flight f2) {
        
        if (f1.getDateOfFlight().isBefore(f2.getDateOfFlight()))
            return -1;
        if (f1.getDateOfFlight().isAfter(f2.getDateOfFlight()))
            return 1;
        else return 0;
        
    }
    
}
