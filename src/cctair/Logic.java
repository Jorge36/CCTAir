package cctair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * class whic do the business logic and interact with the view and setup classes
 * @author Jorge and Verônica
 */
public class Logic implements LogicInterface {
    
    // declared as private - encapsulated
    private Menu view;
    private SetUp setup;
    
    // constructor to initalise Logic attributes
    public Logic(Menu view, SetUp setup) {
        this.view = view; 
        this.setup = setup;       
    }
    
    // method used to search for a flight with the id informed
    private Flight searchParticularFlight(int id) {
        
        // Local varibles to get all the flights from setup
        ArrayList<Flight> flights =  setup.getFlights();
        
        // An instance of IDFlightCompare is created to sort and to call the binarysearch method
        IdFlightCompare idFlightCompare = new IdFlightCompare();
        
        // Sort method is called first, to call the binary searh
        Collections.sort(flights, idFlightCompare);
        
        // A flight object must be created with the ID to sort and search (binarysearch)
        // because the IDFlightCompare is going to use this class to find a class#
        // which has the same id
        Flight flightAux = new Flight("", "", LocalDate.now());
        
        flightAux.setId(id);
        
        int index = Collections.binarySearch(flights, flightAux ,idFlightCompare);
        
        Flight newFlight = null; 
        
        if (index >= 0) {
            // Every flight in the system is created with its aircraft and pilot associated
            // we get the flight with the id equals to the formal parameter 
            Flight flight = flights.get(index);
        
            Pilot pilot = new Pilot(flight.getAircraft().getPilotAssignFlight(flight.getId()).getName(), flight.getAircraft().getPilotAssignFlight(flight.getId()).getRating(), flight.getAircraft().getPilotAssignFlight(flight.getId()).getId());
           
            AirPlane airplane = new AirPlane(flight.getAircraft().getMake(), flight.getAircraft().getModel() , flight.getAircraft().capacity(), pilot, flight.getAircraft().getId(), flight.getAircraft().getRating());
            
            try {
                airplane.confirmPilotInAircraftFlight(flight.getId()); // We always must confirm the pilot for that aircraft and flight
            }    
            catch(PilotNotAssignedException e) {
                System.out.println(e.getMessage());
            }   
            
            // create an instance of the Flight class
            newFlight = new Flight(flight.getOrigin(), flight.getDestination(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getDateOfFlight(), airplane, flight.getId());
        
        }
        
        return newFlight;
        
    }

    // method used to show all flights
    private ArrayList<Flight> searchAllFlight(String city) {
       
        // this method is similar to searchParticularFlight(int id), the only differene is in this 
        // method we search all the flights
        // This methods is also used by the program to show the flights from/to dublin
       Pilot p = null;
       AirPlane a = null;
       Flight f =  null;
       
       ArrayList<Flight> flights = new ArrayList<>();
        
       for (Flight flight : setup.getFlights()) {
           
           // If the city is not empty and the destination or origin of the flight is not equal to city, then loop continues with next iteration
           if (!"".equals(city) && !city.equalsIgnoreCase(flight.getDestination()) && !city.equalsIgnoreCase(flight.getOrigin()))
               continue;
          
           p = new Pilot(flight.getAircraft().getPilotAssignFlight(flight.getId()).getName(), flight.getAircraft().getPilotAssignFlight(flight.getId()).getRating(), flight.getAircraft().getPilotAssignFlight(flight.getId()).getId());
           
           a = new AirPlane(flight.getAircraft().getMake(), flight.getAircraft().getModel() , flight.getAircraft().capacity(), p, flight.getAircraft().getId(), flight.getAircraft().getRating());
            
           try {
                a.confirmPilotInAircraftFlight(flight.getId());
           }
           catch(PilotNotAssignedException e) {
                System.out.println(e.getMessage());
           }
           
           f = new Flight(flight.getOrigin(), flight.getDestination(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getDateOfFlight(), a, flight.getId());
           
           flights.add(f);
           
       }
         
        return flights;
   
    }   
    
    // method used to search for all airplanes from the method getAirplanes() in setup
    private ArrayList<AirPlane> searchAllAircraft() {
        
        return setup.getAirplanes();
    }
    
    // method used to search for a specific airplane by id informed.
    private AirPlane searchParticularAircraft(int id) {
        
        // Method very similar to searchParticularFlight(int id)
        // the only difference is in this method we use another implementation
        // of the compare
        
        ArrayList<AirPlane> airplanes = setup.getAirplanes();
        
        IdAirPlaneCompare idAirPlaneCompare = new IdAirPlaneCompare();
        
        Collections.sort(airplanes, idAirPlaneCompare);
        
        AirPlane airplane = new AirPlane("", 0, 0);
        
        airplane.setId(id);
        
        int index = Collections.binarySearch(airplanes, airplane, idAirPlaneCompare);
        
        if (index >= 0)
            return airplanes.get(index);
        else
            return null;   
        
    } 
    
    // method used to search for a specific pilot by id informed.
    private Pilot searchParticularPilot(int id) {
        
        // same like searchParticularFlight(int id) 
        ArrayList<Pilot> pilots = setup.getPilots();
        
        IdPilotCompare idPilotCompare = new IdPilotCompare();
        
        Collections.sort(pilots, idPilotCompare);
        
        Pilot pilot = new Pilot("");
        
        pilot.setId(id);
        
        int index = Collections.binarySearch(pilots, pilot, idPilotCompare);
        
        if (index >= 0)
            return pilots.get(index);
        else
            return null;           
    }
    
    // method used to search for all pilots
    private ArrayList<Pilot> searchAllPilot() {
        
        return setup.getPilots();
        
    }
        
    // method used to call the especific option from menu view. 
    // mainViewHandler which is called from menu when an event is thrown and we need to process information from setup
    // this method is used by the program to show information
    @Override
    public void mainViewHandler(MainEvent e) {
        
        switch(e.getId()) {
            
            case 1: // call the method printAllFlights from the view/menu to show all the flights found in searchAllFlight
                view.printAllFlights(searchAllFlight(""));
                break;
            case 2: // call the method printParticularObject from the view/menu to show a particular flights found in searchParticularFlight
                view.printParticularObject(searchParticularFlight(view.getId()));
                break;
            case 3: // call the method printAllAirCraft from the view/menu to show a particular flights found in searchAllAircraft
                view.printAllAirCraft(searchAllAircraft());
                break;
            case 4: // call the method printParticularObject from the view/menu to show a particular flights found in searchParticularAircraft
                view.printParticularObject(searchParticularAircraft(view.getId()));
                break;
            case 5: // same like option 3 but with pilots
                view.printAllPilots(searchAllPilot());
                break;
            case 6: // same like option 4 with pilots
                view.printParticularObject(searchParticularPilot(view.getId()));
                break;
            case 7: // call the method printParticularObject from the view/menu to show a particular flights found in searchParticularFlight
                view.printParticularObject(searchParticularFlight(view.getId()));
             
        }
                   
    }

    
    // method used to call the especific option from menu view. 
    // SecondViewHandler which is called from menu when an event is thrown and we need to process information from setup
    // this method is called by the program to interact with user and show a list of values
    // to the user. Then the user can choose a value
    @Override
    public void secondViewHandler(MainEvent e) {
          
        switch(e.getId()) {

            case 2: // print all the header of flights
                view.printAllHeaderFlights(searchAllFlight(""));
                break;
            case 4: // print all the header of aircraft
                view.printAllHeaderaAircraft(searchAllAircraft());
                break;
            case 6: // print all the header of pilots
                view.printAllHeaderPilots(searchAllPilot());
                break;
            case 7: // print all the origins and destinations
                view.printAllOrigins(searchAllOrigins());
                view.printAllDestinations(searchAllDestinations());
            
        }
                
    }
    
    // method used to call the especific option from menu view.
    // ThirdViewHandler which is called from menu when an event is thrown and we need to process information from setup
    // this method is called by the program to create a flight and search the best candidate (airplane and pilot)
    public void thirdViewHandler(MainEvent e) {
        
        // Csll the method searchBestAirplanePilot to look for a best candidate (airplane and pilot)
        AirPlane airplane = searchBestAirplanePilot();
        Flight flight = null;
        if (airplane == null) { // if we don't find a candidate, we create a new airplane and pilot
            // Create Airplane and Pilot using the random class
            Random rGen = new Random();
            int rating = rGen.nextInt(3);
            String[] namesOfPilot = setup.getNamesOfPilot();
            Pilot pilot = new Pilot(namesOfPilot[rGen.nextInt(namesOfPilot.length)],rating);
            
            String[][] typesOfAirplane = setup.gettypesOfAirplane();
            
            airplane = new AirPlane(typesOfAirplane[rGen.nextInt(typesOfAirplane.length)][0],
                                    Integer.parseInt(typesOfAirplane[rGen.nextInt(typesOfAirplane.length)][1]),
                                    Integer.parseInt(typesOfAirplane[rGen.nextInt(typesOfAirplane.length)][2]),
                                    pilot,
                                    rating);
            
            // Pilot is already assigned when we create the aiplane
            flight = new Flight(view.getOrigin(), view.getDestination(), view.getDateOfFlight(), airplane);
            // use the method overloaded to set arrival and departure time
            flight.schedule(view.getArrivalTime(), view.getDepartureTime());

            try {
                // confirm pilot to the aircraft with that flight
                airplane.confirmPilotInAircraftFlight(flight.getId());
                setup.getPilots().add(pilot);
                setup.getAirplanes().add(airplane);
                setup.getFlights().add(flight);
                view.flightCreated(flight);
                
            } catch (PilotNotAssignedException ex) {
                System.out.println(ex.getMessage());
            }
        
        } else { // we find a candidate (airplane and pilot) so only we need to create a flight
            
            flight = new Flight(view.getOrigin(), view.getDestination(), view.getDateOfFlight(), airplane);
            flight.schedule(view.getArrivalTime(), view.getDepartureTime());
            try {
                // confirm pilot to the aircraft with that flight
                airplane.confirmPilotInAircraftFlight(flight.getId());
                setup.getFlights().add(flight);
                view.flightCreated(flight);
                
            } catch (PilotNotAssignedException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
                          
    }
    
    // method used to print all flights from/to dublin
    // this handler is called before showing the menu.
    // it is going to call the method printFlightsToFromDublin from view/menu
    // to show the flights found in searchAllFlight with Dublin as actual parameter
    public void beforeStart(MainEvent e) {
              
        view.printFlightsToFromDublin(searchAllFlight("Dublin"));
           
    }
    
    // method used to search for the best airplane and pilot available
    private AirPlane searchBestAirplanePilot() {
        
        ArrayList<AirPlane> airplanes = setup.getAirplanes();
        ArrayList<Flight> flights = null;
        
        int index;
        DateFlightCompare dateFlightCompare = new DateFlightCompare();
        Flight flightAux = null;
        Pilot pilotAux  = null;
        AirPlane airplaneAux = null;
        int pos;
        int idFlight = 0;
        // we loop al the airplanes                
        for (AirPlane airplane: airplanes) {
            // we get the flights associated with this airplane
            flights = getFlights(airplane.getFlightsPilots());
            // sort the flights by date       
            Collections.sort(flights, dateFlightCompare);
        
            flightAux = new Flight("", "", view.getDateOfFlight());
            // we search for a flight with the dateOfFlight equal to the dateOfFligt of the new flight which the user want to create                       
            index = Collections.binarySearch(flights, flightAux ,dateFlightCompare);
        
            if (index >= 0) {
                // How the binarysearch dont work if there are many object with same date,
                // then we have to go to first object with same dateOfFlight
                for(pos = index; pos > 0; pos--) {
                    
                 if (flights.get(pos).getDateOfFlight().isBefore(view.getDateOfFlight())) {
                                
                        pos++;                
                        index = pos;
                        break;           
                    }  
                    
                }
                // Check if there is a flight which landed in the new origin
                for (pos = index; pos < flights.size(); pos++) {
                    
                    if (flights.get(pos).getDateOfFlight().isAfter(view.getDateOfFlight()))
                        break;
                                          
                    if (flights.get(pos).getDestination() == view.getOrigin() && 
                        flights.get(pos).getArrivalTime().isBefore(view.getDepartureTime())) {
                                
                        airplaneAux = airplane;
                        pilotAux = airplane.getPilotAssignFlight(flights.get(pos).getId());
                        airplaneAux.assignPilot(pilotAux);
                        idFlight = flights.get(pos).getId();
                        break;
                                        
                    }                               
                    
                }
                // if we find a candidate, we check that this aerplane and pilot has just a flight
                // no more than one
                if ((airplaneAux != null) && airplanePilotAvailable(airplaneAux, idFlight))
                    return airplaneAux;
                else {
                    
                    airplaneAux= null;
                    pilotAux = null;
                    idFlight = 0;
                    
                }
            }
        }
        return null;
    } 
    
    // method used to check if the airplane and pilot are available
    private boolean airplanePilotAvailable(AirPlane airPlane, int idFlight) {
        
        DateFlightCompare dateFlightCompare = new DateFlightCompare();
        
        ArrayList<Flight> flights = setup.getFlights();
        
        Collections.sort(flights, dateFlightCompare);
        
        Flight flightAux = new Flight("", "", view.getDateOfFlight());
                                    
        int index = Collections.binarySearch(flights, flightAux ,dateFlightCompare);
        
        int pos;
        
        if (index >= 0) {
            
                for(pos = index; pos > 0; pos--) {
                    
                 if (flights.get(pos).getDateOfFlight().isBefore(view.getDateOfFlight())) {
                                
                        pos++;                
                        index = pos;
                        break;           
                    }  
                    
                }
                // check if the aerplane and pilot has only a flight that day
                for (pos = index; pos < flights.size(); pos++) {
                    
                    flightAux = flights.get(pos);
                    
                    if (flights.get(pos).getDateOfFlight().isAfter(view.getDateOfFlight()))
                        break;
                    
                    // If there is another flight (different from the input) which has same aircraft and pilot -> return false
                    if (idFlight != flightAux.getId() && 
                       (flightAux.getAircraft().getId() == airPlane.getId() || 
                        airPlane.getPilotAssignFlight(idFlight).getId() == flightAux.getAircraft().getPilotAssignFlight(flightAux.getId()).getId()))
                        return false;
                    
                    
                }
                                
        }
          
        return true;
    }
    
    // method used to get pairs (idflight, pilot)
    private ArrayList<Flight> getFlights(Map<Integer,Pilot> flightPilots) {
        
        ArrayList<Flight> flights = new ArrayList<Flight>();
        
        Iterator it = flightPilots.entrySet().iterator();
        
        Map.Entry<Integer, Pilot> entry = null;
        
        while (it.hasNext()) {
            
            entry = (Map.Entry<Integer, Pilot>)it.next();
            
            flights.add(setup.getFlight(entry.getKey()));
            
        }
        
        return flights;     
    }
    
    // method used to get all destinations from setup class
    private String[] searchAllDestinations() {

        return setup.getDestinations();
    }

    // method used to get all origins from setup class
    private String[] searchAllOrigins() {

        return setup.getOrigins();
    }
    
}
