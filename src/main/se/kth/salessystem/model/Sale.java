package main.se.kth.salessystem.model;


import main.se.kth.salessystem.dtos.SaleDTO;
import main.se.kth.salessystem.integration.DatabaseNotFoundException;
import main.se.kth.salessystem.integration.ExternalInventorySystem;
import main.se.kth.salessystem.integration.ItemNotFoundException;
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
    boolean addItem(Item item, ExternalInventorySystem ext) { // new addItem function taking into account that we now have Scanning
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
    boolean addItems(int itemID, int quantity, ExternalInventorySystem ext) {
        Item temp = null;
        try{
            temp = ext.getItem(itemID);}
        catch (DatabaseNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ItemNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean addItemWasSuccess = false;
        if (ext.inStock(itemID, quantity) && temp != null) {
            for (int i = 0; i < quantity; i++) {
                addItemWasSuccess = this.addItem(temp, ext);
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

    /**
     * Overridden toString method to convert any sale to string o
     * Only return the items in the sale- noting else.
     * @return
     */
    @Override
    public String toString() { // converts a sale to string
        StringBuilder str = new StringBuilder();

        for (Item i : listOfItems) {
            str.append(i + "\n");
        }
        return str.toString();
    }

    /**
     * Updates all observers in the observerList. Usually called after every ended sale.
     * @param amount amount of money to update observers of
     */
    public void updateObservers(double amount){
        for (Observer o : observerList) {
            o.update(amount);
        }
    }
    /**
     *  Adds an observer to the observerList. According to the obsrever pattern.
     *  Currently, we're just observing one thing in two ways, the revenue
     * @param observer the observer to add.
     */
    public void addObserver(Observer observer){
        observerList.add(observer);
    }

    /**
     * Returns a sales memenento- the storage class used for the MementoPattern.
     * Used after every modification of the itemList.
     * @return a memento containing the listOfItems and the price.
     */
    public SaleMemento getMemento(){
        return new SaleMemento(this.listOfItems, this.totalPrice);
    }

    /**
     * Restores a currently active sale to a previous state- using the salemMementos
     * listOfItems and price- therefore allowing us to undo edits
     * @param memento the memento to restore to- doesn't actually matter if its from this sale or another one
     */
    public void restoreFromMemento(SaleMemento memento){
        this.listOfItems = memento.getOldSaleItems();
        this.totalPrice = memento.getPrice();
    }
    /**
     * Our implementation of the Memento pattern
     * We store the Sale's listOfItems and totalPrice into a memento for further use.
     * <a href="https://en.wikipedia.org/wiki/Memento_pattern#Java_example">https://en.wikipedia.org/wiki/Memento_pattern#Java_example</a>
     *
     * We decided to store our state as the full list.
     */
    public static class SaleMemento {
        private final List<Item> oldSaleItems;
        private final double price;

        /**
         * Construtor for the saleMemento
         * We could've just used the Sale class instance itself- but that's a lot more data
         * then we need.
         * @param items a list of items from the previous sale.
         * @param price the price of the list
         */
        public SaleMemento(List<Item> items, double price){
            oldSaleItems = new ArrayList<>();
            for (Item i :
                    items) {
                oldSaleItems.add(i.clone());
            }
            this.price = price;
        }

        /**
         * Get's a list of items from the saleMemento.
         * @return full list of items in a java.lang.List
         */
        private List<Item> getOldSaleItems(){
            return oldSaleItems;
        }

        /**
         * gets the price of the items in a saleMemento
         * @return price in a double
         */
        private double getPrice(){
            return price;
        }
    }

}


