/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cctair;

/**
 * Class of main method
 * @author Jorge and  Verônica
 * @version 1.0
 */ 
public class CCTAir {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SetUp setUp = new SetUp(); // create an instance of the SetUp class
        setUp.BuildThem(); //fill the objects using a method from SetUp class
        
        Menu view = new Menu(); // create an instance of the Menu class
        
        Logic logic = new Logic(view, setUp); // create an instance of the Logic class
        
        view.addListener((LogicInterface)logic); // add the listener (Logic) to the view/menu
                
        view.mainMenu(); // show the menu using a mehotd from Menu class
    }
    
}
