package main.se.kth.salessystem.model;


import main.se.kth.salessystem.dtos.SaleDTO;
import main.se.kth.salessystem.integration.AccountingSystem;
import main.se.kth.salessystem.integration.ExternalInventorySystem;
import main.se.kth.salessystem.model.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.ExecutionRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSale {
    private Sale sale;
    private AccountingSystem ac;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        ac = new AccountingSystem();

    }

    @Test
    void addItem() {
        //We're unsure about the proper usage of junit for this- what probably would be more
        //correct would be to make a new sale object, without items in it- then adding items and comparing all
        // item ids in the two sales. - i dont know if thats viable in the long run though.
        // the logic for returning boolean is found in addItem
        boolean addingItem = sale.addItems(3, 1, ExternalInventorySystem.getInstance());
        boolean expectedResult = true;
        assertEquals(expectedResult, addingItem, "Adding item failed");
    }

    @Test
    void addItems() {
        boolean addedItemSuccess = sale.addItems(3, 3, ExternalInventorySystem.getInstance());
        //remember to change the externalInventory document to make sure the se.kth.salessystem.test fails.
        boolean expectedResult = true;
        assertEquals(expectedResult, addedItemSuccess, "AddItems failed- either supply is low or ID is incorrect");
    }

    @Test
    void terminateSale() {
        sale.terminateSale();
        boolean result = sale.getProgress();
        boolean expected = false;
        assertEquals(expected, result, "Sale not terminated properly.");
    }

    @Test
    void endSale() {
        /*
         * endSale testas genom att skicka genom andra metoder- kanske inte en perfekt lösning
         * men det är det enda sättet att veta om salet hamnat i accounting eller inte.
         * */

        sale.addItems(2, 1, ExternalInventorySystem.getInstance());
        boolean result = false;
        boolean expectedResult = true;
        SaleDTO t = sale.endSale("Edvin", "Kassa 2");
        ac.registerSale(t);
        if (ac.getSaleDTO(t.getSaleID()) != null && !sale.getProgress()) {
            result = true;
        }
        assertEquals(expectedResult, result, "Ended sale could not be found in Accounting. ");
    }
}