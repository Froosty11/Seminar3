package model;

import dtos.SaleDTO;
import integration.AccountingSystem;
import integration.ExternalInventorySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Sale sale;
    private AccountingSystem ac;

    @BeforeEach
    void setUp(){
        sale = new Sale();
        ac = new AccountingSystem();

    }
    @Test
    void addItem() {
        //We're unsure about the proper usage of junit for this- what probably would be more
        //correct would be to make a new sale object, without items in it- then adding items and comparing all
        // item ids in the two sales. - i dont know if thats viable in the long run though.
        // the logic for returning boolean is found in addItem
        boolean addingItem = sale.addItem(3, new ExternalInventorySystem());
        boolean expectedResult = true;
        assertEquals(expectedResult, addingItem, "Adding item failed" );
    }

    @Test
    void addItems() {
        boolean truthiness = sale.addItems(3, 3, new ExternalInventorySystem());
        //remember to change the externalInventory document to make sure the test fails.
        boolean expectedResult = true;
        assertEquals(expectedResult, truthiness, "AddItems failed- either supply is low or ID is incorrect");
    }

    @Test
    void terminateSale() {
        sale.terminateSale();
        boolean result = sale.getProgress();
        boolean expected = false;
        assertEquals(expected,result,"Sale not terminated properly.");
    }

    @Test
    void endSale() {
        /*
        * endSale testas genom att skicka genom andra metoder- kanske inte en perfekt lösning
        * men det är det enda sättet att veta om salet hamnat i accounting eller inte.
        * */

        sale.addItem(2,new ExternalInventorySystem());
        boolean result = false;
        boolean expectedResult = true;
        SaleDTO t = sale.endSale("Edvin", "Kassa 2");
        ac.registerSale(t);
        if(ac.getSaleDTO(t.getSaleID()) != null && !sale.getProgress()){
            result = true;
        }
        assertEquals(expectedResult, result, "Ended sale could not be found in Accounting. ");
    }
}