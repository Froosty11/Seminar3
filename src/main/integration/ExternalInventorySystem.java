package main.integration;

import main.model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExternalInventorySystem {
    private List<Item> currentInventory = new ArrayList<>();

    /**
     * Constructor for making a new EXT-system. Loads inventory from ids.txt.
     * Edit txt if you want to add/remove/change items.
     */
    public ExternalInventorySystem() {// makes a new EIS using a txt file that contains the "standard inventory"
        //which is default inventory and contains grocery items for taco tuesday
        try {
            Scanner scnr = new Scanner(new File("src/main/integration/ids.txt"));
            String[] temp;
            Item item;
            int line = 0;
            while (scnr.hasNextLine()) {//while another item exists in txt
                temp = scnr.nextLine().split("#");
                item = new Item(Integer.parseInt(temp[3]), Double.parseDouble(temp[0]), Double.parseDouble(temp[1]), temp[2], line);
                currentInventory.add(item); // foreach item in txt, add that item after parsing info
                line++;
            }
        } catch (FileNotFoundException e) { // quick exception if file is missing
            System.out.println("Error. File not found. ");
            e.printStackTrace();
        }
    }

    /**
     * Gets an item from the inventory.
     * @param id the id of the item ur looking from. Would be the index in the currentInventory list.
     * @return currentInventory[id] item
     */
    public Item getItem(int id) throws ItemNotFoundException, DatabaseNotFoundException{
        if(id == 6) throw new DatabaseNotFoundException("Database not found, try reconnecting", new Exception());
        try {
            return currentInventory.get(id);
        }catch (IndexOutOfBoundsException e){
            throw new ItemNotFoundException(id);
        }
    }

    /**
     * converts the full inventory to a string. Useful if you're printing the full inventory upon updates.
     * @return Long string of all items, their quantity, price etc.
     */
    @Override
    public String toString() {
        String str = "";
        for (Item item : currentInventory) {
            str += "\n" + item.toString();
        }
        return str;
    }

    /**
     * Checks if itemID is in stock.
     * @param id item to look for
     * @return true if theres >=1 copy of item in stock
     */

    public boolean inStock(int id) {
        if (currentInventory.size() >= id) {

            Item it = currentInventory.get(id);
            if (it.getQuantity() > 0)
                return true;
        }

        return false;
    }

    /**
     * Gets length of inventory. Basically amount of indexes used.
     * @return integer with length
     */
    public int getLength(){
        return currentInventory.size();
    }

    /**
     * Looks if an item has more than quantity in stock
     * @param id item to look for
     * @param quantity amount of item to look for
     * @return true if enough items exist
     */
    public boolean inStock(int id, int quantity) {
        //checks if multiple items of the same ID are in stock, for example 4 bananas
        if (currentInventory.size() >= id) {
            Item it = currentInventory.get(id);
            if (it.getQuantity() >= quantity)
                return true;

        }
        return false;
    }

    /**
     * adds an Item to the inventory, useful for returns or new products etc.
     * Item has to be new. Doesn't check if the id already exists. Please double check.
     * ItemID has to be the length of the current inventory. Otherwise you'll have a mismatch between itemIDs and index
     * @param item item to add. Make sure it doesn't exist already in list.
     *
     */
    public void addItem(Item item) {

        currentInventory.add(item);

    }
}
