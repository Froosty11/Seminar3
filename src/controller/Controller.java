package controller;

import dtos.SaleDTO;
import dtos.StoreDTO;
import integration.AccountingSystem;
import integration.ExternalInventorySystem;
import integration.ReceiptPrinter;
import model.Receipt;
import model.Sale;

public class Controller {
    private Sale currentActive;
    private ExternalInventorySystem ext;
    private AccountingSystem act;
    private ReceiptPrinter rp;
    private StoreDTO store;

    public Controller(){
        store = new StoreDTO("ICA NÄRA", "Björkvägen 22", "0733533596");
        ext = new ExternalInventorySystem();
        act = new AccountingSystem();
        rp = new ReceiptPrinter();

    }
    public boolean startNewSale(int customerID){
        currentActive = new Sale();
        return true;
    }
    public boolean addItem(int itemID){
        return currentActive.addItem(itemID, ext);
    }
    public boolean endSale(double paid, String s, String pos){
        SaleDTO dto = currentActive.endSale(s, pos);
        Receipt r = new Receipt(dto, store);
        rp.PrintReceipt(r);
        return act.registerSale(dto);
    }
    public void terminate(){
        currentActive.terminateSale();
    }
    public boolean addItem(int itemID, int count){
        return currentActive.addItems(itemID,count, ext);
    }
    public String getString(){
        if(currentActive.getProgress()){
            String str;
            str = currentActive.toString();
            return str;
        }
        return null;

    }
}
