package integration;
import model.Receipt;

public class ReceiptPrinter {
    public ReceiptPrinter(){

    }
    public boolean PrintReceipt(Receipt receipt){//this is for debugging purposes- we dont have a printer- so we're printing to console
        System.out.println(receipt);
        return true;
    }
}
