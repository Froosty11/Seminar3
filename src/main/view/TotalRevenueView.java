package main.view;

import main.model.Observer;

/**
 * TotalRevenueView sends information to display about the current revenue
 */
public class TotalRevenueView implements Observer {
    private double totalPaid;
    public TotalRevenueView(){
        totalPaid = 0;
    }

    @Override
    public void update(double amount) {
        totalPaid += amount;
        //for each update we're printing total revenue
        System.out.println(totalToString());

    }
    public String totalToString(){
        return "OBSERVER: "+ totalPaid + " SEK since start.";
    }
}
