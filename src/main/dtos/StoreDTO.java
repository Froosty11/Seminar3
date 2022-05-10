package main.dtos;

/**
 * Information transference class StoreDTO contains the name, the address and the number of the store.
 */
public class StoreDTO {

    private String storeName;
    private String storeAddress;
    private String telephoneNMR;

    /**
     * Constructor
     * @param name name of the store
     * @param address address of the store
     * @param nmr telephone nmr of the store
     */
    public StoreDTO(String name, String address, String nmr) { //constructor sstoreDTO
        storeName = name;
        storeAddress = address;
        telephoneNMR = nmr;
    }

    /**
     * getter for address
     * @return
     */
    public String getStoreAddress() {
        return storeAddress;
    }

    /**
     * getter for name
     * @return
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * get telephone number
     * @return
     */
    public String getNMR() {
        return telephoneNMR;
    }
}