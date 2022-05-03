package model;

import dtos.SaleDTO;
import dtos.StoreDTO;
import integration.ExternalInventorySystem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class ReceiptTest {
    private String str;
    private Receipt rec;
    @BeforeEach
    void setUp() { // sets up a string equal to a DTO
        str = "Ica Nära \nBjörkvägen 2\n"+ " 037417\n\n" +
                "Cashier: Edvin\n" + java.time.LocalTime.now().toString().substring(0, 8) +
                 " " + java.time.LocalDate.now().toString() + "\n" +
                "Butik\n" +
                "6 chips 15.0 \n" +
                "Subtotal:90.0 \n" +
                "VAT total: 22.5\n" +
                "TOTAL: 112.5 \n" +
                "1337";
        Item chips = new Item(6, 15.0, 0.25, "\"chips\"", 3);
        List<Item> itemList = new ArrayList<>();
        itemList.add(chips);
        Sale sale = new Sale();
        sale.addItems(3, 6, new ExternalInventorySystem());
        SaleDTO s = sale.endSale("Edvin", "Butik");
        rec = new Receipt(s, new StoreDTO("Ica Nära", "Björkvägen 2", "037417"));

    }

    @AfterEach
    void tearDown() {
        str = null;
        rec = null;
    }
    @Test
    void receiptToStringTest(){ //compares string to dto output
        boolean expectedResult = true;
        String st2 = rec.toString();
        boolean result = str.equals(st2);
        assertEquals(expectedResult, result, "Receipt does not match expected output. ");
    }
}