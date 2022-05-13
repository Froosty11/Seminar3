package se.kth.salessystem.main;

import se.kth.salessystem.main.controller.Controller;
import se.kth.salessystem.main.view.View;

/*
* TODO:
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
