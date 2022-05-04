package main.model;

import main.integration.ExternalInventorySystem;

public class Item {
    double itemPrice;
    double VAT; // a percentage 0.25 etc
    int quantity;
    int itemID;
    String itemDesc;

    /**
     * Better solution for getting items. Currently not in use, but theres not much use for the constructor anyways.
     * @param itemID itemID to get
     * @param ext ext, need it to get.
     */
    public Item(int itemID, ExternalInventorySystem ext){
        Item temp = ext.getItem(itemID);
        this.quantity = temp.quantity;
        this.itemPrice = temp.quantity;
        this.itemID = temp.itemID;
        this.VAT = temp.VAT;
        this.itemDesc = temp.itemDesc;

    }
    /**
     * Constructor for item. Honestly, should probably get it from the inventorySystem, but we'd still need this
     * for testing and adding new items.
     * @param quantity quantity of items
     * @param price price in kr
     * @param vat percentage vat ex. 0.25 = 25%
     * @param desc Name/Description of item.
     */
    public Item(int quantity, double price, double vat, String desc, int id) {
        itemPrice = price;
        this.quantity = quantity;
        this.itemDesc = desc;
        this.VAT = vat;
        this.itemID = id; // if i could rewrite now, i'd honestly just check length of inventory.
    }

    /**
     * Gets item item.Quantity property
     * @return quantity of item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Override toString. Makes an item into a readable string.
     * @return string with all information in an item
     */
    @Override
    public String toString() {
        String str = "";
        str += this.quantity;
        str += "x " + this.itemDesc;
        str += " -  " + this.itemPrice + "kr";
        return str;
    }

    /**
     * Deep copy of item.
     * @param item Item to copy.
     */
    public Item(Item item) {
        this.itemDesc = item.itemDesc;
        this.itemID = item.itemID;
        this.itemPrice = item.itemPrice;
        this.quantity = 1;
        this.VAT = item.VAT;
    }
}
