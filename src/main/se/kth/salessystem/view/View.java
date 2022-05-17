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
        cont.addItem(2, 2);
        cont.addItem(4, 2);
        cont.endSale(200, "Yas", "Kassa 1");
        cont.startNewSale(67);
        cont.addItem(1,2);
        cont.addItem(2);
        cont.endSale(200, "Edvin", "Kassa 2");
        System.out.println("Sample Receipt 1\n");

        //if (!cont.endSale(50000, "Edvin", "Kassa 1")) System.out.println("Not enough funds!");
    }
}
