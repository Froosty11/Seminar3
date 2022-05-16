package main.se.kth.salessystem.integration;

import main.se.kth.salessystem.controller.Controller;
import main.se.kth.salessystem.model.Item;
import main.se.kth.salessystem.model.Sale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class ItemNotFoundExceptionTest {
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
    void getIncorrectID() {
        boolean isCorrect = false;
        try{
            sale.addItem(ext.getItem(29), ext);
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
    void getMessage() {
        boolean msgEqualsExpectedMessage = false;

        try{
            sale.addItem(ext.getItem(29), ext);
        }catch (ItemNotFoundException exception){
            if(exception.getMessage().equals("Barcode is incorrect, could not find item with identifier 29.  Please try again.")) msgEqualsExpectedMessage = true;
        }
        catch (DatabaseNotFoundException dbNotFound){
            dbNotFound.getAdminMessage();
        }
        assertTrue(msgEqualsExpectedMessage, "getMessage for itemNotFoundException not handled correctly");
    }

    @Test
    void getAdminMessage() {
        boolean msgEqualsExpectedMessage = false;

        try{
            sale.addItem(ext.getItem(29), ext);
        }catch (ItemNotFoundException exception){
            String s = exception.getAdminMessage().substring(44);
            System.out.println(s);
            if(s.equals(
                    "\n" +
                            "Barcode is incorrect, could not find item with identifier 29.  Please try again.\n" +
                            "\n End of Log " +
                            "\n" +
                            "\n")) msgEqualsExpectedMessage = true;
        }
        catch (DatabaseNotFoundException dbNotFound){
            dbNotFound.getAdminMessage();
        }
        assertTrue(msgEqualsExpectedMessage, "getAdminMessage for itemNotFoundException test failed");
    }
}