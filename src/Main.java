import controller.Controller;
import view.View;

public class Main {


    /*
    * TODO:
    *       - Controller DONE
    *       - Controller tests DONE
    *       - View that calls to controller, from main, done
    *       -
    *       -
    * */
    public static void main(String[] args) {
        Controller c = new Controller();
        View v = new View(c);
        v.hardCodedControllerCalls();
    }
}
