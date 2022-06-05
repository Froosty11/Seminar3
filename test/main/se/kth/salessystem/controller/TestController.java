package main.se.kth.salessystem.controller;


import main.se.kth.salessystem.controller.Controller;
import main.se.kth.salessystem.integration.DatabaseNotFoundException;
import main.se.kth.salessystem.integration.ItemNotFoundException;
import main.se.kth.salessystem.integration.TotalRevenueFileOutput;
import main.se.kth.salessystem.view.TotalRevenueView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * So, I do know that it is not perfectly ok to do testing with the string- but to compare two sales
 * in our current state would require us to addItems to both sales- which wouldn't make a difference since they're both
 * running the exact same code. We'd have to implement a makeSaleFromTxt or hardcode it.
 *
 *  After further delibiration i've decided to keep it the same- Edvin
 *  We'd like to change this in the future by implementing a package private way to get the currentActive sale
 *  then compare that.
 */
class TestController {
    Controller controll1;
    String string;

    /**
     * Before each se.kth.salessystem.test- setups some basic controller
     */
    @BeforeEach
    void setUp() {
        controll1 = new Controller(new TotalRevenueFileOutput(), new TotalRevenueView());
        controll1.startNewSale(1);

    }

    @AfterEach
    void tearDown() {
        controll1 = null;
        string = null;
    }

    /**
     * tests starting a new sale.
     * @throws DatabaseNotFoundException never
     * @throws ItemNotFoundException never
     */
    @Test
    void testStartNewSale() throws DatabaseNotFoundException, ItemNotFoundException {
        controll1.startNewSale(1);
        controll1.addItem(1);
        boolean result = controll1.endSale(10000, "TEST", "TEST");
        assertTrue(result, "startNewSale controller error");
    }

    /**
     * tests addItem by adding an item- then checks if the string contains that information
     * @throws DatabaseNotFoundException never
     * @throws ItemNotFoundException never
     */
    @Test
    void testAddItem() throws DatabaseNotFoundException, ItemNotFoundException {
        boolean result = false;
        controll1.addItem(0);
        if (controll1.getString().equals("1x Tomat, Ekologisk -  24.5kr\n")) {
            result = true;
        }
        assertTrue(result, "Controller.addItem is failing se.kth.salessystem.test.");

    }

    /**
     * Checks if the sale is ended by starting an empty sale, shoud therefor
     * contain the empty string of items. We can't check for prograess- as that outside the controlls
     * scope.
     */
    @Test
    void testEndSale() {
        boolean result = false;
        controll1.endSale(20, "Edvin", "fortnite");
        if ((controll1.getString() == null || controll1.getString() == "")) {
            result = true;
        }
        assertTrue(result, "Controller.endSale failed junit se.kth.salessystem.test");
    }

    /**
     * Tests terminate
     */
    @Test
    void testTerminate() {
        boolean result = false;
        controll1.terminate();
        if (controll1.getString() == null) {
            result = true;
        }
        assertTrue(result, "Controller.terminate failed junit se.kth.salessystem.test");
    }

    /**
     * Tests adding multiple items.
     * We do this by a simple string comparison.
     *@throws DatabaseNotFoundException never
     *@throws ItemNotFoundException never
     */
    @Test
    void testAddItems() throws DatabaseNotFoundException, ItemNotFoundException {
        boolean result = false;
        controll1.addItem(0, 2);
        if (controll1.getString().equals("2x Tomat, Ekologisk -  24.5kr\n")) {
            result = true;
        }
        assertTrue(result, "Controller.addItems is failing se.kth.salessystem.test.");

    }

    /**
     * Checks if undo works by adding item, undoing it and then comparing it to an empty shopping cart.
     * @throws DatabaseNotFoundException never
     * @throws ItemNotFoundException never
     */
    @Test
    void testUndo() throws DatabaseNotFoundException, ItemNotFoundException {
        boolean isCorrect = false;
        controll1.addItem(1);
        controll1.undo();
        if(controll1.getString().equals("")) isCorrect = true;
        assertTrue(isCorrect, "Error! Undo not reseting shopping cart to 0");
    }

    /**
     *Checks undo by adding two items then undoing both steps- then checking if its equal
     * to an empty string.
     *@throws DatabaseNotFoundException never
     * @throws ItemNotFoundException never
     */
    @Test
    void testUndos() throws DatabaseNotFoundException, ItemNotFoundException {
        boolean isCorrect  = false;
        controll1.addItem(1, 2);
        controll1.addItem(2, 2);
        controll1.undo(2);
        if(controll1.getString().equals("")) isCorrect = true;
        assertTrue(isCorrect, "Undo not undoing multiple actions properly");


    }


}