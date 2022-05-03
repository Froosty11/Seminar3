package model;

import integration.ExternalInventorySystem;
import model.Item;

public class Scanning {
    static ExternalInventorySystem ext;
    public static Item getItemFromBarcode(int barcode){
        Item item = ext.getItem(barcode);
        return item;
    }
}


