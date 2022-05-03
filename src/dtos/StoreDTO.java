package dtos;

public class StoreDTO{

    private String storeName; 
    private String storeAddress;
    private String telephoneNMR;
     

    public StoreDTO(String name, String address, String nmr){ //constructor sstoreDTO
        storeName = name;
        storeAddress = address;
        telephoneNMR = nmr;
    }    

    public String getStoreAddress(){
        return storeAddress;
    }
    public String getStoreName(){
        return storeName;
    }
    public String getNMR(){
        return telephoneNMR;
    }
}