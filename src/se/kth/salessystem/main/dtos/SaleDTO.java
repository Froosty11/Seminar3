package se.kth.salessystem.main.dtos;

import se.kth.salessystem.main.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * SaleDTO is used for data transference and storage of a sale.
 *
 */
public class SaleDTO {

    private double totalPrice;
    private int saleID;

    private double totalVAT;
    private String cashier;
    private List<Item> listOfItems = new ArrayList<Item>();
    private String pointOfSale;

    /**
     * Constructor for a SaleDTO
     * @param price totalPrice of the sale, not including vat
     * @param vat   vat of the sale
     * @param list  the list of items, which then includes price, quantity, description etc
     * @param pos   point of sale
     * @param i     id of sale- randomly generated between 0-10k
     */
    public SaleDTO(double price, double vat, List<Item> list, String pos, int i) {

        //constructor for making a sale DTO
        this.totalPrice = price;
        this.pointOfSale = pos;
        this.listOfItems = list;
        this.saleID = i;
        this.totalVAT = vat;
    }

    /**
     * Make a deep copy of a saleDTO- same values new address
     * @return a new deep copy
     */
    public SaleDTO copy() {
        //copy of object with new adress, so we can edit it without editing the old copy
        SaleDTO temp = new SaleDTO(this.totalPrice, this.totalVAT, this.listOfItems, this.pointOfSale, this.saleID);
        temp.setCashier(this.cashier);
        return temp;
    }

    /**
     * Conmpares if two SaleDTO are "equal" its not comparing all aspects- only the most important ones
     * @param obj the other SaleDTO to compare to
     * @return true if equal, false in other states
     */
    public boolean equal(SaleDTO obj) {
        //compares two saleDTOs with eachother, to make sure they're the same. it doesn't compare all
        //aspects because that would take time.
        if (obj.getSaleID() == this.saleID && obj.cashier.equals(this.cashier)
                && obj.listOfItems.equals(this.listOfItems)) return true;
        return false;
    }

    /**
     * sets cashier to desired string
     * @param cashier new cashier
     */
    public void setCashier(String cashier) { //sets Cashier
        this.cashier = cashier;
    }

    /**
     * gets cashier
     * @return cashier in form of a string
     */
    public String getCashier() { // gets Cashier as String
        return cashier;
    }

    /**
     * gets total of a sale- not vat
     * @return totalPrice
     */
    public double getTotal() {
        return totalPrice;
    } // gets Total price, not including vat

    /**
     * gets vat
     * @return
     */
    public double getTotalVAT() { // gets VAT
        return totalVAT;
    }

    /**
     * gets full list of items from a saleDTO-
     * @return List<Item> of items</Item>
     */
    public List<Item> getItems() {
        return listOfItems;
    } // gets a list of Items.

    /**
     *
     * @return returns string pos
     */
    public String getPOS() {
        return pointOfSale;
    } // gets POS

    /**
     * gets saleID.
     * @return
     */
    public int getSaleID() {
        return saleID;
    } // gets id


}