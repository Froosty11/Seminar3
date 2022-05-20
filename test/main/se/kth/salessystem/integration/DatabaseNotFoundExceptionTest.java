package main.se.kth.salessystem.integration;

import main.se.kth.salessystem.model.Sale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseNotFoundExceptionTest {

    private ExternalInventorySystem ext;
    @BeforeEach
    void setUp() {
        ext = ExternalInventorySystem.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAdminMessage() {
        try{
            ext.getItem(6);
        }
        catch (DatabaseNotFoundException dbExc){
            boolean databaseErrorCorrespondsToError = false;
            String s = dbExc.getAdminMessage().substring(dbExc.getAdminMessage().indexOf('\n'));
            System.out.println(s);
            if(s.equals("\n" +
                    "Database not found, try reconnecting\n" +
                    dbExc.getStackTrace()[0].toString()+
                    "\n" +
                    "End of Log \n\n")) databaseErrorCorrespondsToError = true;
            assertTrue(databaseErrorCorrespondsToError, "Database error");

        }
        catch (ItemNotFoundException itemNoFound){
            itemNoFound.getStackTrace();
        }
    }
}