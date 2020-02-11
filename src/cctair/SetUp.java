package cctair;

// imports to use in class
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class to create and fill the data structures needed for the program to run. 
 * create data structures of completed flights / airplanes / pilots
 * @author Jorge and Verônica
 */
public class SetUp {
   
    // list of cities for origin airport
    private String[] origins = {"Dublin","Porto","Madrid","Rome", "Paris", "London", "Manchester"};       
    
    // list of destinations for destinatination airport
    private String[] destinations = {"Dublin","Porto","Madrid","Rome", "Paris", "London", "Manchester"};  
    
    // list of type of airplane (make,model,capacity)
    private String[][] typesOfAirplane = {{"Airbus", "340", "375"},
                                          {"Boeing", "747", "660"},
                                          {"Boeing", "777", "396"},
                                          {"Tupolev", "334","102"},
                                          {"Antonov", "148", "85"},
                                          {"ATR", "42", "52"},
                                          {"Airbus Beluga", "380","900"},
                                          {"Boeing Dreamlifter", "747","16"}};
   
            
    // list of names to be used for pilots 
    private String[] namesOfPilot = {"Paulo","Fabio","Claudia","Jorge","Veronica","John","David"};       
        
    // create arrays of instance of the objects
    private ArrayList<Flight> flights =  new ArrayList<Flight>();
    private ArrayList<AirPlane> airplanes =  new ArrayList<AirPlane>();
    private ArrayList<Pilot> pilots =  new ArrayList<Pilot>();
    
    // randomly select from these data structures to populate the fields within your objects    
    // create an instance of the Random class
    private Random rGen = new Random();
     
    // method to fill the array of objects 
    public void BuildThem() {
                
            // starting the variables to be use in this method
            Pilot pilot = null;
            AirPlane airplane = null;
            Flight flight = null;
            int rating;
            LocalTime departureTime = null;
            LocalTime arrivalTime = null;
            LocalDate dateOfFlight = null;
            int day = 1; //used to increment the day of the flight
            int hour = 9;  //hours start at 9am
            String origin = "";
            String destination = "";
            int typesOfPlane;
                       
            for(int i = 1; i <= 14; i++) {
                
                rating = rGen.nextInt(3);
                
                pilot = new Pilot(namesOfPilot[rGen.nextInt(namesOfPilot.length)],rating);
                
                pilots.add(pilot);
                
                typesOfPlane = rGen.nextInt(typesOfAirplane.length);
                
                // we assign the pilot
                // create an new instance of the airplane
                airplane = new AirPlane(typesOfAirplane[typesOfPlane][0],
                                        Integer.parseInt(typesOfAirplane[typesOfPlane][1]),
                                        Integer.parseInt(typesOfAirplane[typesOfPlane][2]),
                                        pilot,
                                        rating); 
                

                
                dateOfFlight = LocalDate.now().plusDays(day); //get current day and increment from now
                departureTime = LocalTime.of(hour, 0, 0);
                arrivalTime = departureTime.plusHours(2);
                
                origin = origins[rGen.nextInt(origins.length)];
                
                // make sure get the destination different from origin
                do {
                    destination = destinations[rGen.nextInt(destinations.length)];
                }
                while(origin==destination);
                
                // create an new instance of the flight
                flight = new Flight(origin, 
                                    destination, 
                                    dateOfFlight,
                                    airplane);    
                
                flight.schedule(arrivalTime, departureTime);
                
                // confirmation of the pilot passing the number of the flight
                try {
                    airplane.confirmPilotInAircraftFlight(flight.getId()); 
                }    
                catch(PilotNotAssignedException e) {
                    System.out.println(e.getMessage());
                }   

                
                airplanes.add(airplane); //add the airplane in the object
                flights.add(flight); //add the flight in the object
                
                day++;
                hour++;       
            }
            
            //c. Use the second version of the schedule method to set the time 
            // schedule for a flight. 
            
            flight = flights.get(0);
            departureTime = flight.getDepartureTime();
            departureTime = departureTime.plusMinutes(15);
            arrivalTime = flight.getArrivalTime();
            arrivalTime = arrivalTime.plusMinutes(15);
            flight.schedule(arrivalTime, departureTime);
            
            // d.Use the first version of the schedule method to update the 
            // arrival time for a flight in the list. 
            
            flight = flights.get(1);
            arrivalTime = flight.getArrivalTime();
            arrivalTime = arrivalTime.minusMinutes(10);
            flight.schedule(arrivalTime);
    } 

    // public getters and setters to allow access to variables - encapsulation
    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public ArrayList<AirPlane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(ArrayList<AirPlane> airplanes) {
        this.airplanes = airplanes;
    }

    public ArrayList<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(ArrayList<Pilot> pilots) {
        this.pilots = pilots;
    }

    public String[] getOrigins() {
        return origins;
    }

    public String[] getDestinations() {
        return destinations;
    }
    
    public String[] getNamesOfPilot() { 
        return this.namesOfPilot;  
    }
    
    public String[][] gettypesOfAirplane() {
        return this.typesOfAirplane;
    }
    
    // method to get the flight with the idFlight informed
    public Flight getFlight(int idFlight) {
        
        IdFlightCompare idFlightCompare = new IdFlightCompare();
        
        Collections.sort(flights, idFlightCompare);
        
        Flight flightAux = new Flight("", "", LocalDate.now());
        
        flightAux.setId(idFlight);
        
        int index = Collections.binarySearch(flights, flightAux ,idFlightCompare);
                
        if (index >= 0) {
        
            return flights.get(index);
        
        }
        return null;
    }
    
}
