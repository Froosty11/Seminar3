package model;

import dtos.SaleDTO;
import integration.ExternalInventorySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sale {
    private boolean inProgress;
    private double currentTotal;
    private List<Item> listOfItems = new ArrayList<Item>();
    private double totalPrice;
    private int saleID;

    public Sale() {
        Random rndm = new Random();
        listOfItems.clear();
        inProgress = true;
        currentTotal = 0;
        totalPrice = 0;
        saleID = rndm.nextInt(10000); // slumpmässigt id- borde kolla om det id;t är använt tidigare. 

    }

    boolean addItem(Item item, ExternalInventorySystem ext) { // new addItem function taking into account that we now have Scanning
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

    /*boolean addItems(Item item, ExternalInventorySystem ext, int count){
        boolean truth = false;
        boolean ret = false;
        if(ext.inStock(item.itemID, count)){
            ret = true;
            for(int j = 0; j < count; j ++){
                for (Item i:
                        listOfItems) {
                    if(i.itemID == item.itemID){
                        i.quantity++;
                        truth = true;
                    }
                }
                if(!truth){
                    listOfItems.add(item);
                }
                totalPrice+=item.itemPrice;
            }
        }
        return ret;
    }
    public boolean addItem( int barcode, ExternalInventorySystem ext){
        //DEPRECATED BY SCANNER
        //could also be called scanItem
        //takes identifier in form of barCode
        Item gotItem;
        //addItem can be replaced with Scanning.getItemFromBarcode(barcode);
        if(ext.inStock(barcode)){
            gotItem = ext.getItem(barcode);
            gotItem = new Item(gotItem);
            //if it's already an item in the current sale
            int nmr = 0;
            boolean truth = false;
            for (Item i :
                    listOfItems) {
                if(i.itemID == gotItem.itemID){
                    truth = true;
                    nmr = listOfItems.indexOf(i);
                }
            }
            if(truth){
                int temp = nmr;
                this.listOfItems.get(temp).quantity++;
            }
            else{
                listOfItems.add(gotItem);
            }
            totalPrice += gotItem.itemPrice;
            return true;
        }
        else{
            System.out.println("ELSE");
            return false;
        }
    }*/
    public boolean addItems(int itemID, int quantity, ExternalInventorySystem ext) {
        boolean truth = false;
        if (ext.inStock(itemID, quantity)) {
            for (int i = 0; i < quantity; i++) {
                truth = this.addItems(itemID, 1, ext);
            }
        }


        return truth;
    }

    public void terminateSale() { // makes currentSale invalid.
        inProgress = false;


    }

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

    public boolean getProgress() {
        return inProgress;
    }

    public String toString() { // converts a sale to string
        StringBuilder str = new StringBuilder();

        for (Item i : listOfItems) {
            str.append(i + "\n");
        }
        return str.toString();
    }

}
