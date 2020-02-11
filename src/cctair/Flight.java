package cctair;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * class which contain attributes and behaviour related to a Flight
 * @author Jorge and Verônica
 */
public class Flight {
    
    // declared as private - encapsulated
    private String origin;
    private String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private LocalDate dateOfFlight;
    private AirPlane aircraft; 
    private int id;
    private static int idCount = 1;

    // public getters and setters to allow access to variables - encapsulation
    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDateOfFlight() {
        return dateOfFlight;
    }

    public void setDateOfFlight(LocalDate dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
    }

    public AirPlane getAircraft() {
        return aircraft;
    }

    public void setAircraft(AirPlane aircraft) {
        this.aircraft = aircraft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
       
    // constructor to initalise Flight variables
    public Flight(String origin, String destination, LocalDate dateOfFlight) {

        this.origin = origin;
        this.destination = destination;
        this.dateOfFlight = dateOfFlight;
        
    }
    
    // constructor to initalise Flight partial variables with partial Airplane object
    public Flight(String origin, String destination, LocalDate dateOfFlight, AirPlane aircraft) {
        this.origin = origin;
        this.destination = destination;
        this.dateOfFlight = dateOfFlight;
        this.aircraft = aircraft;
        this.id = this.idCount;
        this.idCount++;
    }

    // constructor to initalise Flight variables with Airplane object
    public Flight(String origin, String destination, LocalTime departureTime, LocalTime arrivalTime, LocalDate dateOfFlight, AirPlane aircraft, int id) {
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.dateOfFlight = dateOfFlight;
        this.aircraft = aircraft;
        this.id = id;
    }
    
    // method to allow printing of flight variables by construction of a string
    @Override
    public String toString() {
        return "Flight Information: " +  System.lineSeparator() + 
               "Date: " + this.dateOfFlight +  System.lineSeparator() + 
               "From: " + this.origin + " to " + this.destination + System.lineSeparator() +
               "Flight time: " + this.departureTime + " to " + this.arrivalTime +  System.lineSeparator() + 
               "Plane Information: " +  System.lineSeparator() +
               this.aircraft;            
    }

    // method to allow printing of flight variables by construction of a string
    public String toString2(LocalTime localTime, String time) {
        return "Flight: " + this.origin + " to " + this.destination + " : " + time + " : " + localTime;
               
    }
        
    // method to allow printing of flight variables by construction of a string
    public String fullInfoFlighttoString() {
        
        return "Flight Information: " + System.lineSeparator() + "Flight ID: " + this.id + System.lineSeparator() + 
               "Date: " + this.dateOfFlight +  System.lineSeparator() + 
               "From: " + this.origin + " to " + this.destination + System.lineSeparator() +
               "Flight time: " + this.departureTime + " to " + this.arrivalTime +  System.lineSeparator() + 
               "Plane Information: " +  System.lineSeparator() +
               this.aircraft.toStringWithID();                          
    }

    // method to allow printing of flight variables by construction of a string
    public String headerFlightToString() {
        
        return "Flight ID: " + "Number: " + this.id + System.lineSeparator();                    
    }
    
    // method to set the arrivalTime to the object
    public void schedule(LocalTime arrivalTime) {
               
        this.arrivalTime = arrivalTime;

    }
    
     // method to set the arrivalTime and departureTime to the object
    public void schedule(LocalTime arrivalTime, LocalTime departureTime) {
                
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;

    }
    
}
