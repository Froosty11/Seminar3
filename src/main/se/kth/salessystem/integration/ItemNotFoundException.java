package main.se.kth.salessystem.integration;

/**
 * ItemNotFoundException handles an item missing when getting it. Usually this is just an indication of indexOutOfBounds
 * in the big array- but this will be expanded when we change to a hashmap
 */
public class ItemNotFoundException extends Exception {
    private int itemIdentifier;
    /**
     * Constructor for INFException
     */
    public ItemNotFoundException(int id){
        this.itemIdentifier = id;
    }

    /**
     * Returns the id of the error
     * @return
     */
    public int getIncorrectID(){
        return this.itemIdentifier;
    }

    /**
     * Returns a string containing the proper information to print the error to user.
     * @return
     */
    public String getMessage(){
        String s = ("Barcode is incorrect, could not find item with identifier " +
                this.itemIdentifier +".  " +
                "Please try again.");
        return s;
    }

    /**
     * Admin message for writing to errorLogs
     * @return
     */
    public String getAdminMessage() {
        StringBuilder strBu = new StringBuilder();
        strBu.append("Item Exception -" + java.time.LocalDate.now() + java.time.LocalTime.now() + "\n");
        strBu.append(this.getMessage() + "\n");
        strBu.append("\n End of Log \n\n");
        return strBu.toString();
    }
}
