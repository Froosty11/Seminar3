package main.model;

import main.integration.ExternalInventorySystem;

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
    public boolean addItemFromBarcode(int barcode, Sale saleToAddTo, int count) {
        Item temp = ext.getItem(barcode);
        Item ret = new Item(count, temp.itemPrice, temp.VAT, temp.itemDesc, temp.itemID);

        return saleToAddTo.addItem(ret, ext);

    }
}


