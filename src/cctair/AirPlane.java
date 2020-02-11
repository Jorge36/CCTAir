package cctair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * class contain attributes and behaviour related to a airplane
 * @author Jorge and Veronica
 */
public class AirPlane {
    
    // declared as private - encapsulated
    private String make;
    private int model;
    private int capacity;
    private Pilot pilot;
    private Map<Integer,Pilot> pilots;
    private int rating;
    private int id;
    private static int idCount = 1;
    
    // public getters and setters to allow access to variables - encapsulation
    public String getMake() {
        return make;
    }

    public int getModel() {
        return model;
    }

    public int capacity() {
        return capacity;
    }

    public Pilot getPilot() { // Return the last one assigned
        return pilot;
    }
    
    public int getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public Map<Integer, Pilot> getFlightsPilots() {
        return pilots;
    }

    // full consructor to initalise AirPlane variables with Pilot object
    public AirPlane(String make, int model, int capacity, Pilot pilot, int id, int rating) {
        
        this.make = make;
        this.model = model;
        this.capacity = capacity;
        this.pilot = pilot;
        this.id = id;     
        this.rating = rating;
        this.pilots = new HashMap<>();   
    }
 
    // partial constructor to initalise AirPlane variables with pilot object    
    public AirPlane(String make, int model, int capacity, Pilot pilot) {
        this.make = make;
        this.model = model;
        this.capacity = capacity;
        this.pilot = pilot;
        this.id = this.idCount;
        this.idCount++;
        this.pilots = new HashMap<>();
    }

    // partial constructor to initalise AirPlane variables
    public AirPlane(String make, int model, int capacity) {
        this.make = make;
        this.model = model;
        this.capacity = capacity;
    }

    // constructor to initalise AirPlane variables with partial Pilot object
    public AirPlane(String make, int model, int capacity, Pilot pilot, int rating) {
        this.make = make;
        this.model = model;
        this.capacity = capacity;
        this.pilot = pilot;
        this.rating = rating;
        this.id = this.idCount;
        this.idCount++;
        this.pilots = new HashMap<>();
    }
    
    // method to assign the pilot
    // accepts the name of a pilot and assigns the pilot to the airplane
    public boolean assignPilot(Pilot pilot) {
                    
        if (pilot.getRating() == this.rating) {
                
            this.pilot = pilot;
            return true;
                
        } 
        return false;
    }
    
    // method to assign the pilot in that flight informed by idFlight
    public void confirmPilotInAircraftFlight(int idFlight) throws PilotNotAssignedException { // Throw an ecxeption if pilot is null
        
        if (this.pilot != null)
            this.pilots.put(idFlight, this.pilot);
        else
            throw new PilotNotAssignedException("The pilot wasn't assigned to the aircraft");
    }
    
    // method to get the pilot informed by idFlight and return to the object pilot
    public Pilot getPilotAssignFlight(int idFlight) {
        return pilots.get(idFlight);
    }
        
    // method to allow printing of aircraft partial variables by construction of a string
    public String headerAirplaneToString() {
        
        return "Aircraft ID: " + "Number: " + this.id + " " + this.make + " " + this.model + System.lineSeparator();    
        
    }

    // method to allow printing of aircraft partial variables by construction of a string
    @Override
    public String toString() {
        
        return "Aircraft: " + this.make + " " + this.model + System.lineSeparator() + "Capacity: " + this.capacity + " seats" + System.lineSeparator() + "Pilot: " + this.pilot;

    }

    // method to allow printing of aircraft partial variables by construction of a string
    public String toStringWithID() {
        
        return "ID number: " + this.id + System.lineSeparator() + " Aircraft: " + this.make + " " + this.model + System.lineSeparator() + "Capacity: " + this.capacity + " seats" + System.lineSeparator() + "Pilot: " + this.pilot.toStringWithID();

    }
    
    // method to allow printing of aircraft variables by construction of a string
    public String fullInfoAirplaneToString() {
        
        String info = "Aircraft ID: " + "Number: " + this.id + System.lineSeparator(); 
                
        info = info + "Aircraft: " + this.make + " " + this.model + System.lineSeparator() + "Capacity: " + this.capacity + " seats" + System.lineSeparator();
        
        info = info + "Pilots assigned to this Aircraft: " +  System.lineSeparator();
        
        Iterator it = pilots.entrySet().iterator();
        
        Map.Entry<Integer, Pilot> p = null;
        
        while (it.hasNext()) {
            
            p = (Map.Entry<Integer, Pilot>)it.next();
            
            info = info + "Flight Number: " + p.getKey() + " Pilot: " + ((Pilot)p.getValue()).toStringWithID();
            
            if (it.hasNext()) 
               info = info + System.lineSeparator();
            
        }
        
        return info;
    }
    
}

