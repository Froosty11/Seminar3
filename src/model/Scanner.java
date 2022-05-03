package model;

import integration.ExternalInventorySystem;

public class Scanner {
    private ExternalInventorySystem ext;
    public Scanner (ExternalInventorySystem ext){
        this.ext = ext;
    }
    public boolean addItemFromBarcode(int barcode, Sale s, int count){ //upgraded add items
        // adds one or multiple items only using an int.
        Item temp = ext.getItem(barcode);
        Item ret = new Item(count, temp.itemPrice, temp.VAT, temp.itemDesc, temp.itemID);

        return   s.addItem(ret, ext);

    }
}


