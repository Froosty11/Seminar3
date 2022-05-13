package main.se.kth.salessystem.model;

import main.se.kth.salessystem.integration.DatabaseNotFoundException;
import main.se.kth.salessystem.integration.ExternalInventorySystem;
import main.se.kth.salessystem.integration.ItemNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

/**
 * ItemScanner works like handling class for adding items.
 * Instead of adding an item using the item class we can use the external inventory system
 */
public class ItemScanner {
    private ExternalInventorySystem ext;

    /**
     * Constructor for a Scanner. Bad choice of Identifier- but idk what to call it.
     * Scanner already exists. Renaming -Edvin
     * @param ext
     */
    public ItemScanner(ExternalInventorySystem ext) {
        this.ext = ext;
    }

    /**
     * Adds an item to a sale using barcode(Barcode in this case is the same as itemID- but in theory could be
     * different.
     * @param barcode the itemID/Barcode. We don't have a physical barcode scanner so its just an int
     * @param saleToAddTo sale to add item to
     * @param count amount of items to add
     * @return
     */
    public boolean addItemFromBarcode(int barcode, Sale saleToAddTo, int count) throws IOException {
        Item ret;
        FileWriter logger = new FileWriter("src/se.kth.salessystem.main/integration/errorLogs.txt", true);
        Item temp = new Item(0, 0,0 ,"null", 0);
        try{
            temp = ext.getItem(barcode);
        }
        catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
            logger.write(e.getAdminMessage());

        }
        catch (DatabaseNotFoundException dbException){
            //print message for user
            System.out.println(dbException.getMessage());
            //log message for admin
            logger.write(dbException.getAdminMessage());
        }
        logger.close();
        ret = new Item(count, temp.itemPrice, temp.VAT, temp.itemDesc, temp.itemID);

        return saleToAddTo.addItem(ret, ext);

    }
}


