/*
 * class to access the menu objects
 */
package cctair;

import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * View of the program. (Interactions with the user and print messages on the screen)
 * @author Jorge and Verônica
 */
public class Menu {
    
    // declared as private - encapsulated
    // it is logic class which is listening all the time . if the user interact with the program
    // this variable will access to the Logic object to do the "business" logic
    private LogicInterface listener;

    private int id;
    private String origin;
    private String destination;
    private LocalTime departureTime = null;
    private LocalTime arrivalTime = null;
    private LocalDate dateOfFlight = null;
    private String[] valuesOrigins;
    private String[] valuesDestinations;
    private char answerYN;
    private int howManyFlight;
   
    // constants to show messages aroud the project. 
    private final String msgWelcome = "**** Welcome to the AirLines *****";
    private final String msgFirstQuestion = "What would you like to do?";
    private final String msgSecondQuestion = "Would you like to do anything else?";
    private final String msgOption1 = "[1] - Display all flights on the system";
    private final String msgoption2 = "[2] - View a particular Flight";
    private final String msgOption3 = "[3] - View all available aircraft";
    private final String msgOption4 = "[4] - View a particular aircraft";
    private final String msgOption5 = "[5] - View all Pilots";
    private final String msgOption6 = "[6] - View a particular Pilot";
    private final String msgOption7 = "[7] - Create a flight(s)";
    private final String msgOption0 = "[0] - Exit";
    private final String msgChooseOption = "Choose an option [0-7]: ";
    private final String msgAskIdFlight = "What flight would you like to view? ";
    private final String msgAskIdAircraft = "What Aircraft would you like to view? ";
    private final String msgAskIdPilot = "What Pilot would you like to view? ";
    private final String msgProgramFinished = "The program finished";
    private final String msgIdNotFound = "The id entered does not exist";
    private final String msgIdNotValid = "Enter a Id valid";
    private final String msgAskOriginDestination = "What Origin and destination would you like to choose? (2 values separated by a space) ";
    private final String msgAskDayOfFlight = "Please, entered the date of the flight: (yyyy-MM-dd) ";
    private final String msgIdDestOriNotValid = "The Origin and Destination are equals";
    private final String msgTimeOfFlightNotValid = "The times are invalid";
    private final String msgDateOfFlightNotValid = "The date of the flight is not valid";
    private final String msgAskTimeOfFlight = "Please, entered the departure time and arrival time of the flight: (hh:MM:ss) ";
    private final String msgAskTimeOfFlightAgain = "Please, entered the departure time and arrival time of the flight again: (hh:MM:ss) ";
    private final String msgAskDayOfFlightAgain = "Please, entered the date of the flight again: (yyyy-MM-dd) ";
    private final String msgValuesNotValid = "The Value/s are not valid";
    private final String msgSuccessFirstPart = "The flight number ";
    private final String msgSuccessSecondPart = " was created successfully ";
    private final String msgNoFlightsDublin = "No flights from/to Dublin";
    private final String msgFlightsDublin = "Flights from/to Dublin";
    private final String msgAddFlights = "Would you like to add new Flights (Y/N)? ";
    private final String msgHowManyFlights = "How many flighst would like to add (up to 5)? With 0 leave this option ";
    private final String msgOrigin = "Origin= ";
    private final String msgDestination = "Destination= ";
    private final String msgDepartureGreaterArrival = "The departure time is greater than arrival time";
    private final String msgTimesLowerCurrent = "The times typed are lower than current time";
    
    // public getters and setters to allow access to variables - encapsulation
    public int getId() {
        return id;
    }
   
    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDate getDateOfFlight() {
        return dateOfFlight;
    }
           
    public void addListener(LogicInterface toAdd) {
       
       listener = toAdd;
       
    }
    
    // method to show the menu view
    public void mainMenu(){
       
        // this method put text in constants variables eg. **** Welcome to the AirLines *****  
        
        int choice = 0;
        boolean finish = false;
        Scanner in = null;
        MainEvent e = null;
        boolean valid;
        
        System.out.println(msgWelcome);
        beforeStart();
        System.out.println(msgFirstQuestion);
        
        do{
            // write code to display all possible user choices
            System.out.println(msgOption1);
            System.out.println(msgoption2);
            System.out.println(msgOption3);
            System.out.println(msgOption4);
            System.out.println(msgOption5);
            System.out.println(msgOption6);
            System.out.println(msgOption7);
            System.out.println(msgOption0);
            System.out.print(msgChooseOption);
            
            try {
                // Scanner for user input
                in = new Scanner(new InputStreamReader(System.in));
                choice = in.nextInt();  
                if(choice>=0 && choice<=7) {
                    e = new MainEvent(choice);
                    switch(choice) {
                        
                        case 0:
                            finish = true;
                            exit();
                            break;
                        case 1:
                            listener.mainViewHandler(e);
                            break;
                        case 2:
                            // Create a input method here to ask id
                            //set the variable String idFlight
                            valid = askId(msgAskIdFlight, choice);
                            if (valid == true)
                                listener.mainViewHandler(e);
                            break;
                        case 3:
                            listener.mainViewHandler(e);
                            break;
                        case 4:
                            // Create a input method here to ask id
                            //set the variable String idAircraft
                            valid = askId(msgAskIdAircraft, choice);
                            if (valid == true)
                                listener.mainViewHandler(e);
                            break;
                        case 5:
                            listener.mainViewHandler(e);
                            break;
                        case 6:
                            // Create a input method here to ask id
                            //set the variable String idPilot
                            valid = askId(msgAskIdPilot, choice);
                            if (valid == true)
                                listener.mainViewHandler(e);
                            break;
                        case 7:
                            valid = askCreateFlight(choice);
                            if (valid == true)
                                listener.mainViewHandler(e);
                    }
                
                }
                else 
                    System.out.print(msgChooseOption); 
                
                
            } catch (Exception ex) {
                System.out.print(msgChooseOption);
            }
            
            if (!finish) {
                
                System.out.println(msgSecondQuestion);
                
            }
            
        }while(!finish); 
        
    }
        
   // method to print all flights from object flight
   public void printAllFlights(ArrayList<Flight> flights) {
              
       for (Flight flight : flights) {
           
           System.out.println(System.lineSeparator() + flight.fullInfoFlighttoString() + System.lineSeparator());
   
       }
       
   }
   
   // method to printl all header flights from object flight
   public void printAllHeaderFlights(ArrayList<Flight> flights) {
       
              
       for (Flight flight : flights) {
           
           System.out.println(System.lineSeparator() +  flight.headerFlightToString());

       }       
       
   }
   
   // method to print all pilots from object pilot
   public void printAllPilots(ArrayList<Pilot> pilots) {
       
       for (Pilot pilot : pilots) {
           
           System.out.println(System.lineSeparator() +  pilot.fullInfoPilotToString() + System.lineSeparator());

       }
       
   }

    // method to printl all header pilots from object pilot
   public void printAllHeaderPilots(ArrayList<Pilot> pilots) {
       
       for (Pilot pilot : pilots) {
           
           System.out.println(System.lineSeparator() +  pilot.headerPilotToString());

       }       
       
   }   
   
   // method to print all header airplane from object airplane
   public void printAllAirCraft(ArrayList<AirPlane> airplanes) {
       
       for (AirPlane airplane : airplanes) {
           
           System.out.println(System.lineSeparator() +  airplane.fullInfoAirplaneToString() + System.lineSeparator());
           
       }
       
   }
   
   // method to print all header airplane from object airplane
   public void printAllHeaderaAircraft(ArrayList<AirPlane> airplanes) {
       
       for (AirPlane airplane : airplanes) {
           
           System.out.println(System.lineSeparator() +  airplane.headerAirplaneToString());

       }       
       
   }   
   
   // method to print particular object (airplane, pilot or flight) from the object
   public void printParticularObject(Object object) {
              
       if (object != null)
          if (object instanceof AirPlane) 
            System.out.println(System.lineSeparator() + ((AirPlane)object).fullInfoAirplaneToString() + System.lineSeparator()); 
          if (object instanceof Pilot)
            System.out.println(System.lineSeparator() + ((Pilot)object).toStringWithID() + System.lineSeparator()); 
          if (object instanceof Flight)
            System.out.println(System.lineSeparator() + ((Flight)object).fullInfoFlighttoString() + System.lineSeparator());   
       else  
            System.out.println(System.lineSeparator() + msgIdNotFound + System.lineSeparator());       
   }
   
   // method to print all destinations by destination
   public void printAllDestinations(String[] destinations) {
       
       System.out.print(msgDestination);
       for(int i=0; i < destinations.length; i++) {
           
           System.out.print("{" + (i + 1) + " = " + destinations[i] + "}");
           
       } 
       System.out.println(System.lineSeparator());
       valuesDestinations =  destinations;
   }
   
   // method to print all origins by origins
   public void printAllOrigins(String[] origins) {
       
       System.out.print(msgOrigin);
       for(int i=0; i < origins.length; i++) {
           
           System.out.print("{" + (i + 1) + " = " + origins[i] + "}");  
       }       
       System.out.println(System.lineSeparator());
       valuesOrigins = origins;
   }   
      
   private void exit() {
       
       System.out.println(System.lineSeparator() + msgProgramFinished + System.lineSeparator());    
   }
 
   // method used to create a flight
   public void flightCreated(Flight flight) {
       
       
       this.id = flight.getId();
       System.out.println(msgSuccessFirstPart + this.id + msgSuccessSecondPart + System.lineSeparator());
       
   }
   
   // method used to ask the id for the object
   private boolean askId(String msgAsk, int choice) {
       
       Scanner in = null;
       MainEvent e = new MainEvent(choice);

       try{
           
           listener.secondViewHandler(e);
           System.out.print(msgAsk);
           in = new Scanner(new InputStreamReader(System.in));
           this.id = in.nextInt();
           return true;
                  
        } catch (Exception ex) {
           System.out.print(msgIdNotValid);
           return false;
        }  
                          
   }
   
   // method to ask the values to users to create a flight
   private boolean askCreateFlight(int choice) {
       
        MainEvent e = new MainEvent(choice);
        boolean valid = false;
           
        listener.secondViewHandler(e);
        // ask values for destination and origin
        valid = askValues(msgAskOriginDestination, msgAskOriginDestination, msgIdDestOriNotValid, 3);
        if (!valid)
            return false;
        // ask values for the date of the flight
        valid = askValues(msgAskDayOfFlight, msgAskDayOfFlightAgain, msgDateOfFlightNotValid, 2);
        if (!valid)
            return false;         
        // ask values for the departure time and arrival time
        valid = askValues(msgAskTimeOfFlight, msgAskTimeOfFlightAgain, msgTimeOfFlightNotValid, 1);
        if (!valid)
            return false;
        // call the listener (logic class) to search for an aiplane and pilot, and create a flight
        listener.thirdViewHandler(e);
        return true;                          
   }
   
   // generic method to ask values to the user
   private boolean askValues(String msg1, String msg2, String msg3, int typeOfValue) {
       
       Scanner in = null;
       boolean valid = false;
       int howMany = 1;
       
       System.out.print(msg1);

       do {
       
            try {

                 in = new Scanner(new InputStreamReader(System.in));
                 switch(typeOfValue) {
                     
                     case 1: valid = validTime(in); // departure time and arrival time
                             break;
                     case 2: valid = validDate(in); // Day of the flight
                             break;
                     case 3: valid = validOriginDestination(in);
                             break;
                     case 4: valid = validYesNo(in);
                             break;
                     case 5: valid = validHowManyFlights(in);

                     
                 }
                 
                 if (!valid) {
                     
                    System.out.println(msg3);
                    howMany++;

                 }
                 
                 if (!valid && howMany <= 3) {
                     
                     System.out.println(msg2);
                 }

            } catch(DateTimeException ex1) {

                System.out.println(msgValuesNotValid);
                howMany++;

                if (howMany <= 3) 
                    System.out.println(msg2);

            } catch(IndexOutOfBoundsException ex2) {
                
                System.out.println(msg3); 
                howMany++;
                
                if (howMany <= 3) 
                    System.out.println(msg2);
                
            } catch(Exception ex1) {
                
                System.out.println(msgValuesNotValid);
                howMany++;
                
                if (howMany <= 3) 
                    System.out.println(msg2);
    
            }
       }
       while(!valid && howMany <= 3);
       
       return valid;
   }

   // method used to validate the date
   private boolean validDate(Scanner in) {
       
       this.dateOfFlight = LocalDate.parse(in.next()); 
       
        // date equals to today or after today 
       if (this.dateOfFlight.isAfter(LocalDate.now()) || this.dateOfFlight.isEqual(LocalDate.now())) 
            return true;
       else {
            this.dateOfFlight = null;
            return false;
        }
          
   }
   
   // method used to validate the time
   private boolean validTime(Scanner in) {
       
        this.departureTime = LocalTime.parse(in.next());
        this.arrivalTime = LocalTime.parse(in.next());
        // if the day of the flight is today, then the times has to start after current time
        // else the time can be any
        if (this.dateOfFlight.isEqual(LocalDate.now()) && 
            this.departureTime.isAfter(LocalTime.now()) && 
            this.departureTime.isBefore(this.arrivalTime) &&
            !this.departureTime.equals(this.arrivalTime)) 
            return true;
        else if (!this.dateOfFlight.isEqual(LocalDate.now()) && this.departureTime.isBefore(this.arrivalTime) && !this.departureTime.equals(this.arrivalTime)) 
            return true;  
        else { 
            
            if (this.dateOfFlight.isEqual(LocalDate.now()))
                System.out.println(msgTimesLowerCurrent);
            else
                System.out.println(msgDepartureGreaterArrival);
 
            this.departureTime = null;
            this.arrivalTime = null;
            return false;
            
        }                   
   }
   
   // method use to check if the origin and destination are valids
   private boolean validOriginDestination(Scanner in) {
       
        int idOrigin = in.nextInt();
        int idDestination = in.nextInt();
        this.origin = valuesOrigins[idOrigin-1]; 
        this.destination = valuesDestinations[idDestination-1];

        if (this.origin == this.destination) {
            
            this.origin = "";
            this.destination = "";
            return false;
            
        }
        return true;

   }
   
   // method to validate the input for Y, y, N, n
   private boolean validYesNo(Scanner in) {
       
       in = new Scanner(new InputStreamReader(System.in));
       this.answerYN = in.next().charAt(0);
       if (this.answerYN == 'Y' || this.answerYN == 'y' || this.answerYN == 'N' || this.answerYN == 'n')
           return true;
       else 
           return false;
           
   }
   
   // method to validate the quantity of flights will be create. Max 5. 
   private boolean validHowManyFlights(Scanner in) {
       
       in = new Scanner(new InputStreamReader(System.in));
       this.howManyFlight = in.nextInt();
       
       //allow a user to create up to 5 flighs
       if (this.howManyFlight  >= 0 || this.howManyFlight <= 5)
           return true;
       else 
           return false;       
   }
   
   // this method is called from menu before starting to intercact with users
   // and ask them if the want to create new flight
   public void beforeStart() {
       
        MainEvent e = new MainEvent();
        boolean valid = false;
        
        valid = askValues(msgAddFlights, msgAddFlights, msgValuesNotValid, 4);
        if (valid && this.answerYN != 'N' && this.answerYN != 'n') {

            valid = askValues(msgHowManyFlights, msgHowManyFlights, msgValuesNotValid, 5);
            if (valid && this.howManyFlight != 0) {

                for (int i = 1; i <= howManyFlight; i++) {

                    valid = askCreateFlight(7);
                    if (valid == false)
                        continue;
                       
                }
            }
        }
        
        System.out.println(msgFlightsDublin);
        listener.beforeStart(e);  
        
   }
   
   // print the flights to from dublin before start the program
   public void printFlightsToFromDublin(ArrayList<Flight> flights) {
         
        boolean found = false;
       
        for (Flight flight : flights) {
            
           if (flight.getOrigin().equalsIgnoreCase("Dublin"))
                System.out.println(flight.toString2(flight.getDepartureTime(), "departure time"));
                found = true;
           if (flight.getDestination().equalsIgnoreCase("Dublin"))
                System.out.println(flight.toString2(flight.getArrivalTime(), "arrival time"));
                found = true;
       }
       if (found == false)
           System.out.println(msgNoFlightsDublin);
       System.out.println(System.lineSeparator());
       
   }
   
   
}
