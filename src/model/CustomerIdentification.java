package model;

public class CustomerIdentification {
    public boolean checkID(int id){
        return true;
    }
    //we decided this was the best course of action- because we dont want to write a person database.
    // we assume everyone is valid, but this would connect to an external database of people
}
