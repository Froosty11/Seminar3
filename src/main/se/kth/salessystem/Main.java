package main.se.kth.salessystem;

import main.se.kth.salessystem.view.*;
import main.se.kth.salessystem.controller.*;

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
