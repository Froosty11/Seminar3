package integration;

import dtos.SaleDTO;
import model.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountingSystemTest {
    private AccountingSystem ac;
    private SaleDTO dto;
    @BeforeEach
    void setUp() {
        ac = new AccountingSystem();
        Sale temp = new Sale();
        temp.addItems(3,2 ,new ExternalInventorySystem());
        dto = temp.endSale("uwu", "kassa 2");
    }

    @Test
    void registerSale() {
        boolean result = false;
        ac.registerSale(dto);
        if(ac.getSaleDTO(dto.getSaleID()) != null){
            result = true;
        }
        assertTrue(result, "Registering sale failed. ");
    }

    @Test
    void getSaleDTO() {
        boolean expected = true;
        ac.registerSale(dto);
        boolean result = dto.equal(ac.getSaleDTO(dto.getSaleID()));
        //for some reason @override doesnt work here idk
        assertEquals(expected,result,"getSaleDTO failed");
    }


    @Test
    void findAndGetSaleDTO() {


    }
}