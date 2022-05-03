package dtos;
import model.Item;
public class ItemDTO{
    
    private double itemPrice; 
    private double itemDescription; 
    private int itemIdentity; 
    private double vat; 

    public ItemDTO(int id){
        //ItemDTO felt very unessary when it came to coding. We haven't used it for any information transfers
        //as the minimal information an ItemDTO can contain is just the same as an item. Therefore, we could just send
        // an item. For Sale & SaleDTO, theres a clear contrast to what is required. We could make the ItemDTO only contain
        //quantity and ID- and then get the item from that.
    }
}
