package main.model;

import main.dtos.SaleDTO;
import main.integration.ExternalInventorySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sale {
    private boolean inProgress;
    private double currentTotal;
    private List<Item> listOfItems = new ArrayList<Item>();
    private double totalPrice;
    private int saleID;

    /**
     * Constructs a new sale.
     * Randomly generated ID 0-10k
     * Doesn't check if the ID has been used. (Should be done, but you know, time and assignment doesn't specify it)
     */
    public Sale() {
        Random rndm = new Random();
        listOfItems.clear();
        inProgress = true;
        currentTotal = 0;
        totalPrice = 0;
        saleID = rndm.nextInt(10000);

    }

    /**
     * Adds an item to a sale. Usually called by Scanner.
     * Would want this to be package- only accessible by Scanner.
     * But cant figure out how tests will access it then.
     * @param item item to add.
     * @param ext ext to check storage for
     * @return true if the item exists in inventory- false if any error
     */
    public boolean addItem(Item item, ExternalInventorySystem ext) { // new addItem function taking into account that we now have Scanning
        boolean truth = false;
        if (ext.inStock(item.itemID)) {
            for (Item i :
                    listOfItems) {
                if (i.itemID == item.itemID) {
                    i.quantity += item.quantity;
                    truth = true;
                }
            }
            if (!truth) {
                listOfItems.add(item);
            }
            totalPrice += item.itemPrice * item.quantity;
            return true;
        }
        return false;

    }

    /**
     * Adds multiple items of one type to the sale.
     * Would also want this to have the access package- but that doesn't work since we moved
     * test into its own folder? Solution?
     * @param itemID itemIdentfier to add.
     * @param quantity amount of items to add(ex: 6 oranges)
     * @param ext   InventorySystem to check stock for
     * @return true if all items exist inStock.
     */
    public boolean addItems(int itemID, int quantity, ExternalInventorySystem ext) {
        boolean truth = false;
        if (ext.inStock(itemID, quantity)) {
            for (int i = 0; i < quantity; i++) {
                truth = this.addItems(itemID, 1, ext);
            }
        }


        return truth;
    }

    /**
     * Terminates a sale. Makes it unusuable.
     */
    public void terminateSale() {
        inProgress = false;
    }

    /**
     * Ends a Sale. Inprogress = false, adds more cashier and pos info
     * @param cashier what cashier handled it
     * @param POS where in the store the purchase was handled
     * @return a saleDTO with the ended Sale
     */
    public SaleDTO endSale(String cashier, String POS) { //stores and makes dto from sale
        double totalVAT = 0;
        for (Item i :
                listOfItems) {
            totalVAT += i.VAT * i.itemPrice * i.quantity;
        }
        SaleDTO thisSale = new SaleDTO(totalPrice, totalVAT, listOfItems, POS, saleID);
        this.inProgress = false;
        thisSale.setCashier(cashier);

        return thisSale;
    }

    /**
     * Simple getter for progress.
     * @return
     */
    public boolean getProgress() {
        return inProgress;
    }
@Override
    public String toString() { // converts a sale to string
        StringBuilder str = new StringBuilder();

        for (Item i : listOfItems) {
            str.append(i + "\n");
        }
        return str.toString();
    }

}
