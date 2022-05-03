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
        this.totalPrice = price;
        this.pointOfSale = pos;
        this.listOfItems = list;
        this.saleID = i;
        this.totalVAT = vat;
    }
    public SaleDTO copy(){
        SaleDTO temp = new SaleDTO(this.totalPrice,this.totalVAT, this.listOfItems, this.pointOfSale, this.saleID);
        temp.setCashier(this.cashier);
        return temp;
    }

    public boolean equal(SaleDTO obj) {
        if(obj.getSaleID() == this.saleID){
            if(obj.cashier.equals(this.cashier)){
                if(obj.listOfItems.equals(this.listOfItems)) return true;
            }
        }
        return false;
    }

    // getters and setters
    public void setCashier(String cashier){
        this.cashier = cashier;
    }
    public String getCashier(){
        return cashier;
    }
    public double getTotal(){
        return totalPrice;
    }
    public double getTotalVAT(){
        return totalVAT;
    }
    public List<Item> getItems(){
        return listOfItems;
    }
    public String getPOS(){
        return pointOfSale;
    }
    public int getSaleID(){ return saleID ; }


}