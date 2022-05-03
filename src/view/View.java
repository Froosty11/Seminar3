package view;

import controller.Controller;

public class View {
    Controller cont;
    public View(Controller contr){
        cont = contr;

    }
    public void hardCodedControllerCalls(){
        /*

         */
        System.out.println("Making new purchase, customerID 69");
        cont.startNewSale(69);
        System.out.println("Adding two tomatoes!");
        cont.addItem(0, 2) ; //två tomater
        System.out.println("Adding one onion");
        cont.addItem(1); //en gulök
        System.out.println("Alternative flow 3-4b- adding a third tomato");
        cont.addItem(0); //en till tomat- alternative flow 3-4b
        System.out.println("Adding Monster Energy");
        cont.addItem(2, 4); //monster energydrink
        System.out.println("Adding ground meat");
        cont.addItem(3); //köttfärs
        System.out.println("Adding more taco items");
        cont.addItem(4);
        cont.addItem(5);
        System.out.println("Ending purchase.");
        System.out.println("RECEIPT \n\n");
        if(!cont.endSale(50000, "Edvin","Kassa 1")) System.out.println("Not enough funds!");
    }
}
