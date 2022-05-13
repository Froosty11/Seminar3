package se.kth.salessystem.main.model;

import se.kth.salessystem.main.dtos.SaleDTO;
import se.kth.salessystem.main.dtos.StoreDTO;

import java.util.List;

/**
 * Receipt is a class to simulate the receipt that you would give a customer to be able to prove their purchase
 *
 */
public class Receipt {
    private final double totalPrice;      // totalprice we have
    private final double totalVAT;        // vat we generate from the list of Items. we assume they dont change
    private final String time;
    private final String date;            // we check the current time and date of system
    private final List<Item> listOfItems; // included
    private final String pointOfSale;     // this needs to be included in saleDTO
    private final String storeInfo;
    private final String cashier;         // we need this one, a getCashier
    private final int saleID;             // this one we can generate- it's the index in externalAccountingSystem.
    private double paid;

    /**
     * Generates a receipt using the SaleDTO. Time and Date are grabbed through System.
     * VAT is calculated itemwise.
     * @param dto SaleDTO to make the Receipt from. Obviously needs to be a completed purchase
     * @param store Contains store information.
     */
    public Receipt(SaleDTO dto, StoreDTO store, double paid) {
        this.cashier = dto.getCashier();
        this.totalPrice = dto.getTotal();
        this.listOfItems = dto.getItems();
        this.storeInfo = store.getStoreName() + " \n" + store.getStoreAddress() + "\n " + store.getNMR() + "\n";
        double vat = 0;
        this.paid = paid;

        //generating total amount of moms/vat
        for (Item item : listOfItems) {
            vat += item.VAT * item.itemPrice * item.quantity;
        }
        this.totalVAT = vat;
        this.date = java.time.LocalDate.now().toString();
        this.time = java.time.LocalTime.now().toString().substring(0, 8);
        this.pointOfSale = dto.getPOS();
        this.saleID = dto.getSaleID();


    }

    /**
     *
     * @return returns id of a receipt
     */
    public int getSaleID() {
        return this.saleID;
    }

    /**
     * Override object.toString().
     * Makes a full receipt into string using multiple toString() methods
     * @return long string, intended to be printed.
     */
    @Override
    public String toString() { // converts a receipt to a string
        StringBuilder str = new StringBuilder();
        str.append(storeInfo);
        str.append("\nCashier: " + this.cashier + "\n");
        str.append(time + " " + date + "\n");
        str.append(pointOfSale + "\n");
        for (Item item : listOfItems) {
            str.append(item.quantity + " " + item.itemDesc + " " + item.itemPrice + " \n");
        }
        str.append("Change: " + (this.paid - this.totalPrice) + "\n");
        str.append("Subtotal:" + this.totalPrice + " \n");
        str.append("VAT total: " + this.totalVAT + "\n");
        str.append("TOTAL: " + (this.totalPrice + this.totalVAT) + " \n");

        str.append("SaleID:"+saleID);


        return str.toString();
    }


}

