package main.view;

import main.controller.Controller;

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

        System.out.println("Making new purchase, customerID 69");
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
        System.out.println("Adding more taco items");
        cont.addItem(4);
        System.out.println("Adding hardcoded item that throws databaseException");
        cont.addItem(6);
        System.out.println("Ending purchase.");
        System.out.println("RECEIPT \n\n");
        if (!cont.endSale(50000, "Edvin", "Kassa 1")) System.out.println("Not enough funds!");
    }
}
