
package cctair;

/**
 * this class is used by menu and logic to interact between them
 * @author Jorge and Verônica
 */
public class MainEvent {
    
    int id; // Choice that the user choose
        
    public MainEvent(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MainEvent() {
    }
    
}
