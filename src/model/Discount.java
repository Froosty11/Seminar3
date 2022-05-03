package model;

public class Discount {
    String rules;
    public Discount (String regulations){
        this.rules = regulations;
    }
    public boolean checkEligibility(){
        //discount rules are not required for basic flow- therefore not written
        return true;
    }
    private double discountItem(){
        return 69;
    }
    private Sale discountFullSale(Sale s){
        return s;
    }
}
