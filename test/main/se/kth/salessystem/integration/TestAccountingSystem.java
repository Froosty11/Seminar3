package main.se.kth.salessystem.integration;
import main.se.kth.salessystem.controller.Controller;
import main.se.kth.salessystem.dtos.SaleDTO;
import main.se.kth.salessystem.integration.AccountingSystem;
import main.se.kth.salessystem.integration.ExternalInventorySystem;
import main.se.kth.salessystem.model.Sale;
import main.se.kth.salessystem.view.TotalRevenueView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * AccountingSystems test.
 */
class TestAccountingSystem {
    private Controller ctrl;
    private AccountingSystem ac;
    private SaleDTO dto;

    /**
     * Setups for potential AC-testing
     */
    @BeforeEach
    void setUp() throws DatabaseNotFoundException, ItemNotFoundException { //Happens before each se.kth.salessystem.test
        ctrl = new Controller(new TotalRevenueFileOutput(), new TotalRevenueView());
        ac = new AccountingSystem();
        Sale temp = new Sale();
        ctrl.startNewSale(69);
        ctrl.addItem(3, 2);
        dto = temp.endSale("uwu", "kassa 2");
    }

    /**
     * Tests registerSale
     */
    @Test
    void testRegisterSale() { //makes sure listOfSales contains the correct items after registering sales
        boolean result = false;
        ac.registerSale(dto);
        if (ac.getSaleDTO(dto.getSaleID()) != null) {
            result = true;
        }
        assertTrue(result, "Registering sale failed. ");
    }

    /**
     * Tests getSaleDTO
     */
    @Test
    void testGetSaleDTO() { // makes sure the dto contains all important information after getting
        boolean expected = true;
        ac.registerSale(dto);

        boolean result = dto.equal(ac.getSaleDTO(dto.getSaleID()));
        //for some reason @override doesnt work here idk
        assertEquals(expected, result, "getSaleDTO failed");
    }

    /**
     * Ignore. Not used in current iteration.
     */
    @Test
    void testFindAndGetSaleDTO() {
        //since we're not using it currently- im not testing it either


    }
}