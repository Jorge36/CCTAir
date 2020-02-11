package cctair;

/**
 * Interface which has the handler methods to update the screen with the information
 * provided by setup class
 * @author Jorge and Verônica
 */
public interface LogicInterface {
    
    // declared as public - encapsulated
    // access the methods from the Logic class
    public void mainViewHandler(MainEvent e);
    public void secondViewHandler(MainEvent e);
    public void thirdViewHandler(MainEvent e);
    public void beforeStart(MainEvent e);
}
