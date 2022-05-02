package integration;
import model.Item;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class ExternalInventorySystem {
    private List<Item> currentInventory = new ArrayList<>();
    public ExternalInventorySystem(){
        try{
            Scanner scnr = new Scanner(new File("src/integration/ids.txt"));
            String[] temp;
            Item item;
            //randomized quantities because we have no actual external inventory
            int line = 0;
            while(scnr.hasNextLine()){
                temp = scnr.nextLine().split("#");
                item = new Item(Integer.parseInt(temp[3]), Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), temp[2], line);
                currentInventory.add(item);
                line++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Error. File not found. ");
            e.printStackTrace();
        }
    }

    public Item getItem(int id){
        return currentInventory.get(id);
    }
    public String toString(){
        String str = "";
        for (Item item : currentInventory) {
            str += "\n" + item.toString();
        }
        return str;
    }
    public boolean inStock(int id){
        if(currentInventory.size() >= id) {

            Item it = currentInventory.get(id);
            if (it.getQuantity() > 0)
                return true;
        }

        return false;
    }
    public boolean inStock(int id, int quantity){
        if(currentInventory.size() >= id){
            Item it = currentInventory.get(id);
            if(it.getQuantity() >= quantity)
                return true;

        }
        return false;
    }
    public void addItem(Item item){

        currentInventory.add(item);

    }
}
