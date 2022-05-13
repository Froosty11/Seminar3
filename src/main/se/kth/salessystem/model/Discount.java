package main.se.kth.salessystem.model;

/**
 * A discount handling class. calculates discounts for individual items or sales
 */
public class Discount {
    String rules;

    /**
     * Makes a new discount. Not necessary for flows in Assignment, so unfinished.
     * @param regulations regulations for a discount. would have to be parsed somehow
     */
    public Discount(String regulations) {
        this.rules = regulations;
    }

    /**
     * Not done. Not needed for flows. Do not use.
     * @return
     */
    public boolean checkEligibility() {
        //discount rules are not required for basic flow- therefore not written
        return true;
    }
    /**
     * Not done. Not needed for flows. Do not use.
     * @return
     */
    private double discountItem() {
        //same thing here. would in theory be used internally when calling discountFullSale
        return 69;
    }
    /**
     * Not done. Not needed for flows. Do not use.
     * @return
     */
    private Sale discountFullSale(Sale s) {
        return s;
    }
}
