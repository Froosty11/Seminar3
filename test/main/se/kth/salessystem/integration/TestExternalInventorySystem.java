package main.se.kth.salessystem.integration;

import main.se.kth.salessystem.integration.DatabaseNotFoundException;
import main.se.kth.salessystem.integration.ExternalInventorySystem;
import main.se.kth.salessystem.integration.ItemNotFoundException;
import main.se.kth.salessystem.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestExternalInventorySystem {

    private Item item;
    private ExternalInventorySystem ext;

    /**
     * Before all tests- we're adding two trandom items to storage.
     */

    @BeforeEach
    void setUp() {
        ext = ExternalInventorySystem.getInstance();
        item = new Item(4, 6, 0.5, "banana", 4);
        ext.addItem(item);
        item = new Item(10, 16, 0.6, "Nocco pear", 5);
        ext.addItem(item);


    }

    /**
     * Teardown to null information.
     */
    @AfterEach
    void tearDown() {
        item = null;
        ext = null;


    }

    /**
     * Tests if in stock is correct about being inStock about something.   We do this by looping
     * through the ext for the correct item, then comparing it to the answer from inStock itself.
     */
    @Test
    void testInStock() {
        boolean inList = false;
        for(int i = 0; i < ext.getLength() ; i++){ // checks all items.
            try{
                if(ext.getItem(i).toString().equals(item.toString())) inList = true;
            }
            catch (DatabaseNotFoundException e){
                e.printStackTrace();
            }
            catch (ItemNotFoundException itemNotFoundException){
                itemNotFoundException.getMessage();
            }
        }

        assertTrue(inList, "banana not in stock");
    }


    /**
     * Test's adding an item.
     * We use instock here since it's already tested- but in reality we shouldnn't
     */
    @Test
    void testAddItem() {
        assertTrue(ext.inStock(5), "Nocco pear is in stock");
    }
}