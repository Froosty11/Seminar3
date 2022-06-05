package main.se.kth.salessystem.integration;

import main.se.kth.salessystem.controller.Controller;
import main.se.kth.salessystem.model.Item;
import main.se.kth.salessystem.model.ItemScanner;
import main.se.kth.salessystem.model.Sale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the itemNotFoundException
 */
class ItemNotFoundExceptionTest {
     private ExternalInventorySystem ext;
    @BeforeEach
    void setUp() {
        ext = ExternalInventorySystem.getInstance();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetIncorrectID() {
        boolean isCorrect = false;
        try{
            ext.getItem(29);
        }
        catch (ItemNotFoundException itemNotFoundException){
            if(itemNotFoundException.getIncorrectID() == 29) isCorrect = true;
        }
        catch (DatabaseNotFoundException databaseNotFoundException){
            databaseNotFoundException.getAdminMessage();
        }
        assertTrue(isCorrect, "ID getter from internal exception ItemNotFoundException not working");



    }

    @Test
    void testGetMessage() {
        boolean msgEqualsExpectedMessage = false;

        try{
            ext.getItem(213);
        }catch (ItemNotFoundException exception){
            if(exception.getMessage().equals("Barcode is incorrect, could not find item with identifier 213")) msgEqualsExpectedMessage = true;
        }
        catch (DatabaseNotFoundException dbNotFound){
            dbNotFound.getAdminMessage();
        }
        assertTrue(msgEqualsExpectedMessage, "getMessage for itemNotFoundException not handled correctly");
    }

    @Test
    void testGetAdminMessage() {
        boolean msgEqualsExpectedMessage = false;

        try{
            ext.getItem(2321);
        }catch (ItemNotFoundException exception){
            String s = exception.getAdminMessage();
            System.out.println(s);
            if(s.contains("\n" +
                    "Barcode is incorrect, could not find item with identifier 2321\n" +
                    "\n" +
                    " End of Log \n" +
                    "\n")) msgEqualsExpectedMessage = true;
        }
        catch (DatabaseNotFoundException dbNotFound){
            dbNotFound.getAdminMessage();
        }
        assertTrue(msgEqualsExpectedMessage, "getAdminMessage for itemNotFoundException test failed");
    }
}