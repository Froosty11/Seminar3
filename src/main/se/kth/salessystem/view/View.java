package main.se.kth.salessystem.view;

import main.se.kth.salessystem.controller.Controller;
import main.se.kth.salessystem.integration.DatabaseNotFoundException;
import main.se.kth.salessystem.integration.ItemNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Theoretical class containing hardcoded calls to Controller. Should contain code for a display and buttons
 * on the display, allowing you to put in new items etc.
 */
public class View {
    Controller cont;

    /**
     * Constructor for a view. Makes a controller obj.
     *
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

        try {

            System.out.println("Starts one new sale-}\n");
            cont.startNewSale(69);
            addItem(2, 2);
            addItem(4, 2);
            cont.endSale(200, "Yas", "Kassa 1");
            System.out.println("Starts a second new sale! \n");
            cont.startNewSale(67);
            addItem(1, 2);
            addItem(2, 1);
            cont.endSale(200, "Edvin", "Kassa 2");
        } catch (IOException i) {
            i.printStackTrace();
        }
        System.out.println("Sample Receipt 1\n");

        if (!cont.endSale(50000, "Edvin", "Kassa 1")) System.out.println("Not enough funds!");
    }


    private void addItem(int id, int count) throws IOException {
        FileWriter logger = null;
        try {
            logger = new FileWriter("src/main/se/kth/salessystem/integration/errorLogs.txt", true);
            cont.addItem(id, count);
        } catch (
                ItemNotFoundException e) {
            System.out.println(e.getMessage());
            logger.close();
        } catch (
                DatabaseNotFoundException dbException) {
            System.out.println(dbException.getMessage());
            logger.write(dbException.getAdminMessage());
            logger.close();
        }
    }
}
