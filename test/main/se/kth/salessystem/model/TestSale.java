package main.se.kth.salessystem.model;


import main.se.kth.salessystem.dtos.SaleDTO;
import main.se.kth.salessystem.integration.AccountingSystem;
import main.se.kth.salessystem.integration.ExternalInventorySystem;
import main.se.kth.salessystem.model.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.ExecutionRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * tests sale class
 */
class TestSale {
    private Sale sale;
    private AccountingSystem ac;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        ac = new AccountingSystem();
        sale.addItems(1,1, ExternalInventorySystem.getInstance());

    }

    @Test
    void testAddItem() { // not working atm due to ItemScanner implementation
        boolean result = false;
        Sale newSale = new Sale();
        newSale.addItems(1, 1, ExternalInventorySystem.getInstance());
        SaleDTO sDto = sale.endSale("","");
        SaleDTO nDTO = newSale.endSale("","");
        if(nDTO.getItems().get(0).itemID == sDto.getItems().get(0).itemID){
            result = true;
        }



        boolean expectedResult = true;
        assertEquals(expectedResult, result, "Adding item failed");
    }

    @Test
    void testAddItems() {
        boolean addedItemSuccess = sale.addItems(3, 3, ExternalInventorySystem.getInstance());
        //remember to change the externalInventory document to make sure the se.kth.salessystem.test fails.
        boolean expectedResult = true;
        assertEquals(expectedResult, addedItemSuccess, "AddItems failed- either supply is low or ID is incorrect");
    }

    @Test
    void testTerminateSale() {
        sale.terminateSale();
        boolean result = sale.getProgress();
        boolean expected = false;
        assertEquals(expected, result, "Sale not terminated properly.");
    }

    /**
     * tests ending the sale by sending it through other methods, which is not perfect.
     * We check getprogress, the move to accounting etc.
     */
    @Test
    void testEndSale() {
        /*
         * endSale testas genom att skicka genom andra metoder- kanske inte en perfekt lösning
         * men det är det enda sättet att veta om salet hamnat i accounting eller inte.
         * */
        sale.addItem(new Item(1, 69, 1, "fortnite", 1),  ExternalInventorySystem.getInstance());
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