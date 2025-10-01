import controller.CRMController;
import view.CRMView;

public class CRMSimulatorApp {
    public static void main(String[] args) {
        CRMController controller = new CRMController(new CRMView());
        controller.start();
    }
}
