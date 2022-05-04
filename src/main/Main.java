package main;

import main.controller.Controller;
import main.view.View;

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
