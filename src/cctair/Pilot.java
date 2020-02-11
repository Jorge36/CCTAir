
package cctair;

/**
  * class which contain attributes and behaviour related to a Pilot
 * @author Jorge and Verônica
 */
public class Pilot {
    
    // declared as private - encapsulated
    private String name;
    private int rating;
    private int id;
    private static int idCount = 1;

    // constructor to initalise Pilot variables
    public Pilot(String name, int rating) {
        this.name = name;
        this.rating = rating;
        this.id = this.idCount;
        this.idCount++;
    }

    // constructor to initalise Pilot variables
    public Pilot(String name, int rating, int id) {
        
       this.name = name;
       this.rating = rating;
       this.id = id;
        
    }

    // constructor to initalise Pilot variables
    public Pilot(String name) {
       this.name = name;
       this.id = this.idCount;
    }
    
    // public getters and setters to allow access to variables - encapsulation
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    // method to allow printing of pilot variables by construction of a string
    @Override
    public String toString() {
        return "Pilot{" + "name=" + this.name + ", rating=" + this.rating + '}';
    }

    // method to allow printing of pilot variables with id by construction of a string
    public String toStringWithID() {
        return "Pilot{ID=" + this.id + ", name=" + this.name + ", rating=" + this.rating + '}';
    }    
    
    // method to allow printing of pilot variables by construction of a string
    public String headerPilotToString() {
        
        return "Pilot ID: " + "Number: " + this.id + System.lineSeparator();    
        
    }
    
    // method to allow printing of pilot variables by construction of a string
    public String fullInfoPilotToString() {
        
       return "Pilot{" + "id=" + this.id + ",  name=" + this.name + ", rating=" + this.rating + '}';
 
    }
    
}
