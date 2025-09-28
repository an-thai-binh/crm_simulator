import view.CRMFrame;
import view2.CRMView;
import view2.ICRMView;

public class CRMSimulatorApp {
    public static void main(String[] args) {
        ICRMView view = new CRMView();
        view.display();
    }
}
