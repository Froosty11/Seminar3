package integration;

import model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExternalInventorySystem {
    private List<Item> currentInventory = new ArrayList<>();

    public ExternalInventorySystem() {// makes a new EIS using a txt file that contains the "standard inventory"
        //which is default inventory and contains grocery items for taco tuesday
        try {
            Scanner scnr = new Scanner(new File("src/integration/ids.txt"));
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

    public Item getItem(int id) {
        return currentInventory.get(id);
    }

    public String toString() {
        //converts it to String
        String str = "";
        for (Item item : currentInventory) {
            str += "\n" + item.toString();
        }
        return str;
    }

    public boolean inStock(int id) {
        //checks if a single item is in stock using barcode/ID
        if (currentInventory.size() >= id) {

            Item it = currentInventory.get(id);
            if (it.getQuantity() > 0)
                return true;
        }

        return false;
    }

    public boolean inStock(int id, int quantity) {
        //checks if multiple items of the same ID are in stock, for example 4 bananas
        if (currentInventory.size() >= id) {
            Item it = currentInventory.get(id);
            if (it.getQuantity() >= quantity)
                return true;

        }
        return false;
    }

    public void addItem(Item item) {
        //adds an item to the externalInventorySystem, incase a new product or a return

        currentInventory.add(item);

    }
}
