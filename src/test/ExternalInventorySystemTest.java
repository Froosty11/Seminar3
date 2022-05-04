package test;

import main.integration.ExternalInventorySystem;
import main.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExternalInventorySystemTest {

    private Item item;
    private ExternalInventorySystem ext;


    @BeforeEach
    void setUp() {
        //beforeEach test is ran, we reset add some items to an inventory.
        ext = new ExternalInventorySystem();
        item = new Item(4, 6, 0.5, "banana", 4);
        ext.addItem(item);
        item = new Item(10, 16, 0.6, "Nocco pear", 5);
        ext.addItem(item);


    }

    @AfterEach
    void tearDown() {
        //should null here, but GC can handle it lol


    }

    @Test
    void inStock() {
        boolean inList = false;
        for(int i = 0; i < ext.getLength() ; i++){ // checks all items.
            if(ext.getItem(i).toString().equals(item.toString())) inList = true;
        }

        assertTrue(inList, "banana not in stock");
    }

    @Test
    void testInStock() {
        //same here as above- incorrect junit testing
        assertTrue(ext.inStock(4, 3), "could not find that many bananas in stock");

    }

    @Test
    void testAddItem() {
        //this is fine- since we've tested it previously
        assertTrue(ext.inStock(5), "Nocco pear is in stock");
    }
}