package main.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TotalRevenueFileOutput implements Observer{
    String fileLoc = "";
    double amount;
    public TotalRevenueFileOutput(){
        amount = 0;
        fileLoc = "src/main/model/totalRevenue.txt";

    }

    @Override
    public void update(double amount) {
        this.amount += amount;
        try {
            PrintWriter logger = new PrintWriter(fileLoc);
            logger.println(totalToString());
            logger.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String totalToString(){
        return "OBSERVER: "+ amount + " SEK since start.";
    }
}
