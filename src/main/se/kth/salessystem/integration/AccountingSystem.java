package main.se.kth.salessystem.integration;

import main.se.kth.salessystem.dtos.SaleDTO;
import main.se.kth.salessystem.model.Receipt;

import java.util.ArrayList;
import java.util.List;

/**
 * AccountingSystem handles all previously ended purchases. It's currently limited to 10k differnt
 * saleDTOs in a list- w
 *
 * TODO: should be changed to hashmap
 */
public class AccountingSystem {
    List<SaleDTO> allSales;

    /**
     * Constructor for an ACT-system. Fills a list of empty sales 10k long. Really bad- should probably be using a hashmap or similar.
     */
    public AccountingSystem() {
        allSales = new ArrayList<>();
        for (int i = 0; i < 10000; i++) { //yas insists on not fixing this
            allSales.add(null);
        }
    }

    /**
     * Registers a sale. Adds it to the AccountingSystem
     * @param dto the saleDTO to add to the ACT-system. Adds it to the index saleID.
     * @return returns false if that saleID already exists.
     */
    public boolean registerSale(SaleDTO dto) {
        //Registers a new sale onto the SaleList stored in AccountSystem.
            allSales.set(dto.getSaleID(), dto);
            return true;
    }

    /**
     * Gets a saleDTO from the previous sale list.
     * @param saleID the saleID of the dto
     * @return the requested dto
     */
    public SaleDTO getSaleDTO(int saleID) {
        //gets one single DTO using the id of the sale. Since we've stored the sales according to their
        //id as index- we can simply get. An alternative would be a searching algorithm- but this is more efficient
        SaleDTO temp = allSales.get(saleID);
        return temp.copy(); //needs to be a deep copy, otherwise edits to one will yield to other
    }

    /**
     * If you do not have a saleID- but you have a receipt for some reason, takes the receipts information
     * and parses the correct sale.
     * @param receipt receipt to parse id from
     * @return dto requested
     */
    public SaleDTO findAndGetSaleDTO(Receipt receipt) {
        //find and get SaleDTO takes a receipt(or a an id) and returns a DTO using a quick search algorithm
        int temp = receipt.getSaleID();
        for (SaleDTO s :
                allSales) {
            if (s.getSaleID() == temp) {
                return s;
            }

        }
        return null;
    }
}
