package se.kth.salessystem.test;

import se.kth.salessystem.main.dtos.SaleDTO;
import se.kth.salessystem.main.integration.AccountingSystem;
import se.kth.salessystem.main.integration.ExternalInventorySystem;
import se.kth.salessystem.main.model.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestAccountingSystem {
    private AccountingSystem ac;
    private SaleDTO dto;

    /**
     * Setups for potential AC-testing
     */
    @BeforeEach
    void setUp() { //Happens before each se.kth.salessystem.test

        ac = new AccountingSystem();
        Sale temp = new Sale();
        temp.addItems(3, 2, new ExternalInventorySystem());
        dto = temp.endSale("uwu", "kassa 2");
    }

    /**
     * Tests registerSale
     */
    @Test
    void registerSale() { //makes sure listOfSales contains the correct items after registering sales
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
    void getSaleDTO() { // makes sure the dto contains all important information after getting
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
    void findAndGetSaleDTO() {
        //since we're not using it currently- im not testing it either


    }
}