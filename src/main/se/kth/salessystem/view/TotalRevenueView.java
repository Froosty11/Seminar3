package main.se.kth.salessystem.view;

import main.se.kth.salessystem.model.Observer;

/**
 * TotalRevenueView sends information to display about the current revenue
 */
public class TotalRevenueView implements Observer{
    private double totalPaid;

    /**
     *
     */
    public TotalRevenueView(){
        totalPaid = 0;
    }
/**
 * Updates THIS to what amount of money it should contain
 * @param amount
 */
    @Override
    public void update(double amount) {
        totalPaid += amount;
        //for each update we're printing total revenue
        System.out.println(totalToString());

    }

    /**
     * Not the full class to a string- therefor omitted classic override tostring.
     * Just sends the totalPid to a string- usually printed in view
     * @return
     */
    public String totalToString(){
        return "OBSERVER: "+ totalPaid + " SEK since start.";
    }
}
