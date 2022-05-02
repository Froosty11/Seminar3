package integration;

import dtos.SaleDTO;
import model.Receipt;

import java.util.ArrayList;
import java.util.List;

public class AccountingSystem {
    List<SaleDTO> allSales;

    public AccountingSystem(){
        allSales = new ArrayList<>();
        for(int i = 0; i < 10000 ; i ++){
            allSales.add(null);
        }
    }
    public boolean registerSale(SaleDTO dto){
        if(allSales.get(dto.getSaleID()) != null){
            allSales.set(dto.getSaleID(),dto);
            return true;
        }
        return false;
    }
    public SaleDTO getSaleDTO( int saleID){
        SaleDTO temp = allSales.get(saleID);
        return temp.copy(); //needs to be a deep copy, otherwise edits to one will yield to other
    }
    public SaleDTO findAndGetSaleDTO(Receipt receipt){
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
