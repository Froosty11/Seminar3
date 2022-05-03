import controller.Controller;
import view.View;

public class Main {
    /*
    * TODO: Fix "Paid:" input in endSale.
    * TODO: 1 comment per public declaration*/

    public static void main(String[] args) {
        Controller c = new Controller();
        View v = new View(c);
        v.hardCodedControllerCalls();
    }
}
