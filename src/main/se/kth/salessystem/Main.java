package main.se.kth.salessystem;

import main.se.kth.salessystem.view.*;
import main.se.kth.salessystem.controller.*;
/* TODO fixes for sem3:
* - Figure out better tests for controller instead of using toString
* - Package/directory thing? ask
* -
*/
/*
* TODO SEM 4:
*   - Use exceptions for alternative flow 3.4A(handling of nonexistant itemID) ✓
*   - Use exceptions for when database cannot be called, in our case we used calling for itemID 6 ✓
*   -
*   -
*   -
*   -
* */
public class Main {
    /**
     * Start of programming. Literally only calls view. View creates everything else and runs hardcoded
     * calls to make a shopping list of taco items.
     * @param args
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        View v = new View(c);
        v.hardCodedControllerCalls();
    }
}
