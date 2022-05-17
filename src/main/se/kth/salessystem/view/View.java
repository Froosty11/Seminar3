package main.se.kth.salessystem.view;

import main.se.kth.salessystem.controller.Controller;

/**
 * Theoretical class containing hardcoded calls to Controller. Should contain code for a display and buttons
 * on the display, allowing you to put in new items etc.
 */
public class View {
    Controller cont;

    /**
     * Constructor for a view. Makes a controller obj.
     * @param contr
     */
    public View(Controller contr) {
        cont = contr;


    }

    /**
     * Hardcoded calls to Controller.
     * Buys a bunch of taco related items.
     * Only kind of bad choice was not putting the change in the receipt print.
     * It's printed at the top currently, which is ugly.
     */
    public void hardCodedControllerCalls() {


        System.out.println("Starts one new sale-}\n");
        cont.startNewSale(69);
        System.out.println("Adding two tomatoes!");
        cont.addItem(0, 2); //två tomater
        System.out.println("Adding one onion");
        cont.addItem(1); //en gulök
        System.out.println("Alternative flow 3-4b- adding a third tomato");
        cont.addItem(0); //en till tomat- alternative flow 3-4b
        System.out.println("Adding nonexistant item 69");
        cont.addItem(69, 4); //This satisfies 3.4A
        System.out.println("Adding ground meat");
        cont.addItem(3); //köttfärs
        System.out.println("Adding 2x tortilla bread");
        cont.addItem(4, 2);
        System.out.println("Adding hardcoded item that throws databaseException");
        cont.addItem(6);
        System.out.println("Undoing two steps! This will delete the tortillas, but will not delete more because" +
                "it counts the failed DatabaseException as an undo!");
        cont.undo(2);
        System.out.println("Sample Receipt\n");

        if (!cont.endSale(50000, "Edvin", "Kassa 1")) System.out.println("Not enough funds!");
    }
}
