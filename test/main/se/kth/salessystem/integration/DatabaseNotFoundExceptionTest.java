package main.se.kth.salessystem.integration;

import main.se.kth.salessystem.model.Sale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseNotFoundExceptionTest {

    private Sale sale ;
    private ExternalInventorySystem ext;
    @BeforeEach
    void setUp() {
        sale = new Sale();
        ext = ExternalInventorySystem.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAdminMessage() {
        try{
            sale.addItem(ext.getItem(6), ext);
        }
        catch (DatabaseNotFoundException dbExc){
            boolean databaseErrorCorrespondsToError = false;
            String s = dbExc.getAdminMessage().substring(51);
            System.out.println(s);
            if(s.equals("\n" +
                    "Database not found, try reconnecting\n" +
                    dbExc.getStackTrace().toString() +
                    "\n" +
                    "End of Log \n\n")) databaseErrorCorrespondsToError = true;
            assertTrue(databaseErrorCorrespondsToError, "Database error");

        }
        catch (ItemNotFoundException itemNoFound){
            itemNoFound.getStackTrace();
        }
    }
}