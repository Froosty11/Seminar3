package test;

import main.controller.Controller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {
    Controller controll1;
    String string;

    /**
     * Before each test- setups some basic controller
     */
    @BeforeEach
    void setUp() {
        controll1 = new Controller();
        controll1.startNewSale(1);

    }

    @AfterEach
    void tearDown() {
        controll1 = null;
        string = null;
    }

    /**
     * tests starting a new sale.
     *
     */
    @Test
    void startNewSale() {
        controll1.startNewSale(1);
        controll1.addItem(1);
        boolean result = controll1.endSale(10000, "TEST", "TEST");
        assertTrue(result, "startNewSale controller error");
    }

    @Test
    void addItem() {
        boolean result = false;
        controll1.addItem(0);
        if (controll1.getString().equals("1x Tomat, Ekologisk -  24.5kr\n")) {
            result = true;
        }
        assertTrue(result, "Controller.addItem is failing test.");

    }

    @Test
    void endSale() {
        boolean result = false;
        controll1.endSale(20, "Edvin", "fortnite");
        if (controll1.getString() == null) {
            result = true;
        }
        assertTrue(result, "Controller.endSale failed junit test");
    }

    @Test
    void terminate() {
        boolean result = false;
        controll1.terminate();
        if (controll1.getString() == null) {
            result = true;
        }
        assertTrue(result, "Controller.terminate failed junit test");
    }

    @Test
    void addItems() {
        boolean result = false;
        controll1.addItem(0, 2);
        if (controll1.getString().equals("2x Tomat, Ekologisk -  24.5kr\n")) {
            result = true;
        }
        assertTrue(result, "Controller.addItems is failing test.");

    }
}