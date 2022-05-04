package test;

import controller.Controller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {
    Controller contr;

    @BeforeEach
    void setUp() {
        contr = new Controller();
        contr.startNewSale(1);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void startNewSale() {
        boolean result = contr.startNewSale(1);
        assertTrue(result, "startNewSale controller error"); // this can never happen because
        // our startNewSale always returns true- the sale can't be tested for validity without using hashcodes
    }

    @Test
    void addItem() {
        boolean result = false;
        contr.addItem(0);
        Controller ctr2 = new Controller();
        ctr2.startNewSale(1);
        ctr2.addItem(0);
        if (ctr2.getString().equals(contr.getString())) {
            result = true;
        }
        assertTrue(result, "Controller.addItem is failing test.");

    }

    @Test
    void endSale() {
        boolean result = false;
        contr.endSale(20, "Edvin", "fortnite");
        if (contr.getString() == null) {
            result = true;
        }
        assertTrue(result, "Controller.endSale failed junit test");
    }

    @Test
    void terminate() {
        boolean result = false;
        contr.terminate();
        if (contr.getString() == null) {
            result = true;
        }
        assertTrue(result, "Controller.terminate failed junit test");
    }

    @Test
    void addItems() {
        boolean result = false;
        contr.addItem(0, 2);
        Controller ctr2 = new Controller();
        ctr2.startNewSale(1);
        ctr2.addItem(0, 2);
        if (ctr2.getString().equals(contr.getString())) {
            result = true;
        }
        assertTrue(result, "Controller.addItems is failing test.");

    }
}