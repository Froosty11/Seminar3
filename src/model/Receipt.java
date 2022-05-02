package model;

import java.util.*;
import dtos.SaleDTO;
import dtos.StoreDTO;
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

    /*

    private double totalPrice; 
    private String cashier; 
    private List<Item> listOfItems = new ArrayList<Item>(); 
    */

    public Receipt(SaleDTO dto, StoreDTO store){
        this.cashier = dto.getCashier();
        this.totalPrice = dto.getTotal();
        this.listOfItems = dto.getItems();
        this.storeInfo = store.getStoreName() + " \n" + store.getStoreAddress() + "\n " + store.getNMR() + "\n";
        //generating the rest of the values
        double vat = 0; 

        //generating total amount of moms/vat
        for (Item item : listOfItems) {
            vat += item.VAT*item.itemPrice*item.quantity;
        }
        this.totalVAT = vat;

        //time n shit
        this.date = java.time.LocalDate.now().toString();
        this.time = java.time.LocalTime.now().toString().substring(0,8);


        //pos, had to add getPOS and pos variable to dtos
        this.pointOfSale = dto.getPOS();
        
        //implement this later- we need externalAccountSystem

        this.saleID = dto.getSaleID();


    }
    public int getSaleID(){
        return this.saleID;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(storeInfo);
        str.append("\nCashier: " + this.cashier + "\n");
        str.append(time + " " + date+ "\n");
        str.append(pointOfSale + "\n");
        for (Item item : listOfItems) {
            str.append(item.quantity + " " + item.itemDesc + " " + item.itemPrice + " \n");
        }
        str.append("Subtotal:" + this.totalPrice + " \n");
        str.append("VAT total: " + this.totalVAT + "\n");
        str.append("TOTAL: " + (this.totalPrice+this.totalVAT) + " \n");

        str.append(saleID);
        

        return str.toString();
    }

    
}

