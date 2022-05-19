package main.se.kth.salessystem.model;

import main.se.kth.salessystem.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestItem {
    private Item item;
    private String str;

    @BeforeEach
    void setUp() { //setups ItemTests
        item = new Item(1, 24.5, 0.25, "\"tomat\"", 0);
        str = "1 \"tomat\" -  24.5kr";
    }

    @AfterEach
    void tearDown() {
        item = null;
        str = null;
    }

    /**
     * tests if the string is correct. not needed apparently - edvin
     */
    @Test
    void testToString() {
        boolean expectedResult = true;
        boolean result = str.equals(item.toString());
        assertEquals(expectedResult, result, "toString se.kth.salessystem.test unsuccessful");
    }
}