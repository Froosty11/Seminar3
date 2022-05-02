package integration;

import controller.Controller;

public class View {
    Controller cont;
    public View(Controller contr){
        cont = contr;

    }
    public void hardCodedControllerCalls(){
        cont.startNewSale(69);
        cont.addItem(0, 2) ; //två tomater
        cont.addItem(1); //en gulök
        cont.addItem(0); //en till tomat- alternative flow 3-4b
        cont.addItem(2, 4); //monster energydrink
        cont.addItem(3); //köttfärs
        cont.addItem(4);
        cont.addItem(5);
        cont.endSale(100, "Edvin","kassa 1");


    }
}
