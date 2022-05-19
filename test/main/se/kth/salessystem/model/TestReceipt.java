package main.se.kth.salessystem.model;

import main.se.kth.salessystem.dtos.SaleDTO;
import main.se.kth.salessystem.dtos.StoreDTO;
import main.se.kth.salessystem.integration.ExternalInventorySystem;
import main.se.kth.salessystem.model.Item;
import main.se.kth.salessystem.model.Receipt;
import main.se.kth.salessystem.model.Sale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestReceipt {
    private ExternalInventorySystem ext;
    private String str;
    private Receipt rec;

    @BeforeEach
    void setUp() { // sets up a string equal to a DTO
        ext = ExternalInventorySystem.getInstance();
        Item chips = new Item(6, 15.0, 0.25, "chips", 3);
        ext.addItem(chips);
        Sale sale = new Sale();
        sale.addItem(chips, ext);
        SaleDTO temp = sale.endSale("Edvin", "Butik");
        str = "Ica Nära \nBjörkvägen 2\n" + " 037417\n\n" +
                "Cashier: Edvin\n" + java.time.LocalTime.now().toString().substring(0, 8) +
                " " + java.time.LocalDate.now().toString() + "\n" +
                "Butik\n" +
                "6 chips 15.0 \n" +
                "Subtotal:90.0 \n" +
                "VAT total: 22.5\n" +
                "TOTAL: 112.5 \n" +
                "SaleID:" + temp.getSaleID();
        rec = new Receipt(temp, new StoreDTO("Ica Nära", "Björkvägen 2", "037417"),2000);

    }

    @AfterEach
    void tearDown() {
        str = null;
        rec = null;
    }

    /**
     * Compares a string of the dto or not. We could just use contains instead to be lesss certain
     */
    @Test
    void testReceiptToString() { //compares string to dto output
        boolean expectedResult = true;
        String st2 = rec.toString();
        boolean result = str.equals(st2);
        assertEquals(expectedResult, result, "Receipt does not match expected output. ");
    }
}