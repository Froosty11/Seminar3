package model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private Item item;
    private String str;
    @BeforeEach
    void setUp(){ //setups ItemTests
        item = new Item(1, 24.5, 0.25, "\"tomat\"", 0);
        str = "1 \"tomat\" -  24.5kr";
    }
    @AfterEach
    void tearDown(){
        item = null;
        str = null;
    }
    @Test
    void toStringTest(){
        boolean expectedResult = true;
        boolean result = str.equals(item.toString());
        assertEquals(expectedResult, result, "toString test unsuccessful");
        }
}