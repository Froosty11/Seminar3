package main.se.kth.salessystem.model;


import main.se.kth.salessystem.dtos.SaleDTO;
import main.se.kth.salessystem.integration.ExternalInventorySystem;
import main.se.kth.salessystem.view.TotalRevenueView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Sale contains the necessary information and objects to start, end and modify a sale that is currently happening.
 */
public class Sale {
    private boolean inProgress;
    private double currentTotal;
    private List<Item> listOfItems = new ArrayList<Item>();
    private double totalPrice;
    private int saleID;
    private List<Observer> observerList = new ArrayList<Observer>();

    /**
     * Constructs a new sale.
     * Randomly generated ID 0-10k
     * Doesn't check if the ID has been used. (Should be done, but you know, time and assignment doesn't specify it)
     */
    public Sale() {
        Random rndm = new Random();
        observerList.add(new TotalRevenueView());
        observerList.add(new TotalRevenueFileOutput());
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
        boolean addedItemSuccesfully = false;
        if (ext.inStock(item.itemID)) {
            for (Item i :
                    listOfItems) {
                if (i.itemID == item.itemID) {
                    i.quantity += item.quantity;
                    addedItemSuccesfully = true;
                }
            }
            if (!addedItemSuccesfully) {
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
     * se.kth.salessystem.test into its own folder? Solution?
     * @param itemID itemIdentfier to add.
     * @param quantity amount of items to add(ex: 6 oranges)
     * @param ext   InventorySystem to check stock for
     * @return true if all items exist inStock.
     */
    public boolean addItems(int itemID, int quantity, ExternalInventorySystem ext) {
        boolean addItemWasSuccess = false;
        if (ext.inStock(itemID, quantity)) {
            for (int i = 0; i < quantity; i++) {
                addItemWasSuccess = this.addItems(itemID, 1, ext);
            }
        }


        return addItemWasSuccess;
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
        updateObservers(this.totalPrice + totalVAT);

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

    public void updateObservers(double amount){
        for (Observer o : observerList) {
            o.update(amount);
        }
    }
    public void addObserver(Observer observer){
        addObserver(observer);
    }
    public SaleMemento getMemento(){
        return new SaleMemento(this.listOfItems, this.totalPrice);
    }
    public void restoreFromMemento(SaleMemento memento){
        this.listOfItems = memento.getOldSaleItems();
        this.totalPrice = memento.getPrice();
    }
    /**
     * Our implementation of the Memento pattern
     * <a href="https://en.wikipedia.org/wiki/Memento_pattern#Java_example">https://en.wikipedia.org/wiki/Memento_pattern#Java_example</a>
     *
     * We decided to store our state as the full list.
     */
    public static class SaleMemento {
        private final List<Item> oldSaleItems;
        private final double price;
        public SaleMemento(List<Item> items, double price){
            oldSaleItems = new ArrayList<>();
            for (Item i :
                    items) {
                oldSaleItems.add(i.clone());
            }
            this.price = price;
        }
        private List<Item> getOldSaleItems(){
            return oldSaleItems;
        }
        private double getPrice(){
            return price;
        }
    }

}


