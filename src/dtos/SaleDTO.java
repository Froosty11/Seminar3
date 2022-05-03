package dtos;
import java.util.ArrayList;
import java.util.List; 
import model.Item;

public class SaleDTO{

    private double totalPrice;
    private int saleID;

    private double totalVAT;
    private String cashier; 
    private List<Item> listOfItems = new ArrayList<Item>(); 
    private String pointOfSale;
    public SaleDTO(double price, double vat, List<Item>list, String pos, int i){

        //constructor for making a sale DTO
        this.totalPrice = price;
        this.pointOfSale = pos;
        this.listOfItems = list;
        this.saleID = i;
        this.totalVAT = vat;
    }
    public SaleDTO copy(){
        //copy of object with new adress, so we can edit it without editing the old copy
        SaleDTO temp = new SaleDTO(this.totalPrice,this.totalVAT, this.listOfItems, this.pointOfSale, this.saleID);
        temp.setCashier(this.cashier);
        return temp;
    }

    public boolean equal(SaleDTO obj) {
        //compares two saleDTOs with eachother, to make sure they're the same. it doesn't compare all
        //aspects because that would take time.
        if(obj.getSaleID() == this.saleID && obj.cashier.equals(this.cashier)
        && obj.listOfItems.equals(this.listOfItems)) return true;
        return false;
    }

    // getters and setters
    public void setCashier(String cashier){ //sets Cashier
        this.cashier = cashier;
    }
    public String getCashier(){ // gets Cashier as String
        return cashier;
    }
    public double getTotal(){
        return totalPrice;
    } // gets Total price, not including vat
    public double getTotalVAT(){ // gets VAT
        return totalVAT;
    }
    public List<Item> getItems(){
        return listOfItems;
    } // gets a list of Items.
    public String getPOS(){
        return pointOfSale;
    } // gets POS
    public int getSaleID(){ return saleID ; } // gets id


}