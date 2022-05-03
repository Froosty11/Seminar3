package integration;

import dtos.SaleDTO;
import model.Receipt;

import java.util.ArrayList;
import java.util.List;

public class AccountingSystem {
    List<SaleDTO> allSales;

    public AccountingSystem(){
        //Adds a new accountSystem- constructor
        allSales = new ArrayList<>();
        for(int i = 0; i < 10000 ; i ++){
            allSales.add(null);
        }
    }
    public boolean registerSale(SaleDTO dto){
        //Registers a new sale onto the SaleList stored in AccountSystem.
        if(allSales.get(dto.getSaleID()) != null){
            allSales.set(dto.getSaleID(),dto);
            return true;
        }
        return false;
    }
    public SaleDTO getSaleDTO( int saleID){
        //gets one single DTO using the id of the sale. Since we've stored the sales according to their
        //id as index- we can simply get. An alternative would be a searching algorithm- but this is more efficient
        SaleDTO temp = allSales.get(saleID);
        return temp.copy(); //needs to be a deep copy, otherwise edits to one will yield to other
    }
    public SaleDTO findAndGetSaleDTO(Receipt receipt){
        //find and get SaleDTO takes a receipt(or a an id) and returns a DTO using a quick search algorithm
        int temp = receipt.getSaleID();
        for (SaleDTO s :
                allSales) {
            if(s.getSaleID() == temp){
                return s;
            }

        }
        return null;
    }
}
