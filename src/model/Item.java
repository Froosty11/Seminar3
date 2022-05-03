package model;
public class Item {
    double itemPrice;
    double VAT; // a percentage 0.25 etc
    int quantity;
    int itemID;
    String itemDesc;
    public Item(int ID){
        /*
        Item item = getItem(ID);
        itemPrice = item.itemPrice;
        VAT = item.VAT;
        quantity = 1;
        itemID = ID;
        itemDesc = item.itemDesc;*/

    }
    public Item(int quantiy, double price, double vat, String desc, int id){
        itemPrice = price;
        quantity = quantiy;
        this.itemDesc = desc;
        this.VAT = vat;
        this.itemID = id;
    }
    public int getQuantity(){
        return quantity;
    }

    public String toString(){ // converts Item to a readable string
        String str = "";
        str += this.quantity;
        str += "x " + this.itemDesc;
        str += " -  " + this.itemPrice + "kr";
        return str;
    }
    //Because we found out due to testing, we need to make a deep copy of the item that we're adding to cart
    public Item(Item item){
        this.itemDesc = item.itemDesc;
        this.itemID = item.itemID;
        this.itemPrice = item.itemPrice;
        this.quantity = 1;
        this.VAT = item.VAT;
    }
}
