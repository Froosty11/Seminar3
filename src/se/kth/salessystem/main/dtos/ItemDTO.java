package se.kth.salessystem.main.dtos;
/**
 *ItemDTO felt very unessary when it came to coding. We haven't used it for any information transfers
 *as the minimal information an ItemDTO can contain is just the same as an item. Therefore, we could just send
 * item
 */
public class ItemDTO {

    private double itemPrice;
    private double itemDescription;
    private int itemIdentity;
    private double vat;
    /*
    Not in use
     */
    public ItemDTO(int id) {

    }
}
