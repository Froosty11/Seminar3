package model;
import dtos.SaleDTO;
import java.util.*;
import integration.ExternalInventorySystem;

public class Sale {
    private boolean inProgress;
    private double currentTotal;
    private List<Item> listOfItems = new ArrayList<Item>();
    private double totalPrice;
    private int saleID;
    public Sale(){
        Random rndm = new Random();
        listOfItems.clear();
        inProgress = true;
        currentTotal = 0;
        totalPrice = 0;
        saleID = rndm.nextInt(10000); // slumpmässigt id- borde kolla om det id;t är använt tidigare. 

    }
    public boolean addItem( int itemID, ExternalInventorySystem ext){
        Item gotItem;
        if(ext.inStock(itemID)){
            gotItem = ext.getItem(itemID);
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
    }
    public boolean addItems(int itemID, int quantity, ExternalInventorySystem ext){
        boolean truth = false;
        if (ext.inStock(itemID, quantity)) {
            for(int i = 0; i < quantity; i++){
                truth = this.addItem(itemID, ext);
            }
        }


        return truth;
    }

    public void terminateSale(){
        inProgress = false;

        // ta bort salen från listan av sales

    }
    public SaleDTO endSale(String cashier, String POS){
        SaleDTO thisSale = new SaleDTO(totalPrice, listOfItems, POS, saleID);
        this.inProgress = false;
        thisSale.setCashier(cashier);

        return thisSale;
    }
    public boolean getProgress(){
        return inProgress;
    }

    public String toString(){
        StringBuilder str = new StringBuilder() ;

        for (Item i : listOfItems) {
            str.append(i + "\n");
        }
        return str.toString();
    }
    
}
