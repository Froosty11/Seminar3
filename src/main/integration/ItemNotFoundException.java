package main.integration;

public class ItemNotFoundException extends Exception {
    private int itemIdentifier;
    /**
     * Constructor for FNFException
     */
    public ItemNotFoundException(int id){
        this.itemIdentifier = id;
    }
    public int getIncorrectID(){
        return this.itemIdentifier;
    }

    public String getMessage(){
        String s = ("Barcode is incorrect, could not find item \nwith identifier " +
                this.itemIdentifier +".  " +
                "Please try again.");
        return s;
    }
    public String getAdminMessage() {
        StringBuilder strBu = new StringBuilder();
        strBu.append("Item Exception -" + java.time.LocalDate.now() + java.time.LocalTime.now() + "\n");
        strBu.append(this.getMessage() + "\n");
        strBu.append(this.getStackTrace().toString());
        strBu.append("\n End of Log \n\n");
        return strBu.toString();
    }
}
