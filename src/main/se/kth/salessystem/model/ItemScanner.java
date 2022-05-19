package main.se.kth.salessystem.model;

import main.se.kth.salessystem.integration.DatabaseNotFoundException;
import main.se.kth.salessystem.integration.ExternalInventorySystem;
import main.se.kth.salessystem.integration.ItemNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ItemScanner works like handling class for adding items.
 * Instead of adding an item using the item class we can use the external inventory system
 *
 * The reason we have it set up like this is to keep good encapsulation, handling all edits to the ItemList of sale
 * using ItemScanner. Should maybe be renamed to "ItemHandler" or something similar
 */
public class ItemScanner {
    private ExternalInventorySystem ext;
    private List<Sale.SaleMemento> undoList;

    /**
     * Constructor for a Scanner. Bad choice of Identifier- but idk what to call it.
     * Scanner already exists. Renaming -Edvin
     * @param ext
     */
    public ItemScanner(ExternalInventorySystem ext) {
        this.ext = ext;
        this.undoList = new ArrayList<>();
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
        undoList.add(saleToAddTo.getMemento());
        Item ret;
        FileWriter logger = new FileWriter("src/main/se/kth/salessystem/integration/errorLogs.txt", true);
        Item temp;
        try{
            temp = ext.getItem(barcode);
        }
        catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
            logger.write(e.getAdminMessage());
            logger.close();
            return false;
        }
        catch (DatabaseNotFoundException dbException){
            System.out.println(dbException.getMessage());
            logger.write(dbException.getAdminMessage());
            logger.close();
            return false;
        }
        logger.close();
        ret = new Item(count, temp.itemPrice, temp.VAT, temp.itemDesc, temp.itemID);
        return saleToAddTo.addItem(ret, ext);

    }

    /**
     * As our ItemScanner acts as the caretaker for our memento pattern, this also has to include an undo function.
     * @param saleToUse usually, this is simply currentSale.
     */
    public void undo(Sale saleToUse){
        saleToUse.restoreFromMemento(undoList.get(undoList.size()-1));
        undoList.remove(undoList.size()-1);
    }

    /**
     * Undo's an i amount of actions to the itemList
     * As of current implementation, this also "tries" to undo failed addItems
     * TODO: Probably should be fixed asap - Edvin
     * @param saleToUse usually currentSale- other sale's shouldn't really be undone
     * @param i         amount of steps to "undo". Has to be less than steps done.
     */
    public void undo(Sale saleToUse, int i){
        int temp = undoList.size() - i;
        saleToUse.restoreFromMemento(undoList.get(undoList.size()-i));
        for(int j = undoList.size()-1; j >= temp   ; j--){
            undoList.remove(undoList.size()-1);
        }
    }
}


