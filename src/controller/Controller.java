package controller;

import dtos.SaleDTO;
import dtos.StoreDTO;
import integration.AccountingSystem;
import integration.ExternalInventorySystem;
import integration.ReceiptPrinter;
import model.Receipt;
import model.Sale;
import model.Scanner;

public class Controller {
    private Sale currentActive;
    private ExternalInventorySystem ext;
    private AccountingSystem act;
    private ReceiptPrinter rp;
    private StoreDTO store;

    private Scanner scnr;

    public Controller(){
        store = new StoreDTO("ICA NÄRA", "Björkvägen 22", "0733533596");
        ext = new ExternalInventorySystem();
        act = new AccountingSystem();
        rp = new ReceiptPrinter();
        scnr = new Scanner(ext);

    }
    public boolean startNewSale(int customerID){
        currentActive = new Sale();
        return true;
    }
    public boolean addItem(int itemID){
        return scnr.addItemFromBarcode(itemID, currentActive, 1);
    }

    public boolean endSale(double paid, String s, String pos){
        SaleDTO dto = currentActive.endSale(s, pos);
        if(paid > dto.getTotal() + dto.getTotalVAT()) {
            Receipt r = new Receipt(dto, store);
            System.out.print(paid-dto.getTotal()- dto.getTotalVAT() + " change\n ");
            rp.PrintReceipt(r);
            act.registerSale(dto);
            return true;
        }
        return false;

    }
    public void terminate(){
        currentActive.terminateSale();
    }
    public boolean addItem(int itemID, int count){
        scnr.addItemFromBarcode(itemID, currentActive, count);

        return true;
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
