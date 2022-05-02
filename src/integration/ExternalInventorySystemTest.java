package integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Item;

import static org.junit.jupiter.api.Assertions.*;

class ExternalInventorySystemTest {

        private Item item;
        private ExternalInventorySystem ext;


    @BeforeEach
    void setUp() {
        ext = new ExternalInventorySystem();
        item = new Item(4, 6, 0.5, "banana", 4);
        ext.addItem(item);
        item = new Item(10, 16, 0.6, "Nocco pear", 5);
        ext.addItem(item);



    }

    @AfterEach
    void tearDown() {



    }

    @Test
    void inStock() {

        assertTrue(ext.inStock(4), "banana not in stock");
    }

    @Test
    void testInStock() {

        assertTrue(ext.inStock(4, 3), "could not find that many bananas in stock");

    }
    @Test
    void testAddItem(){
        assertTrue(ext.inStock(5), "Nocco pear is in stock");
    }
}