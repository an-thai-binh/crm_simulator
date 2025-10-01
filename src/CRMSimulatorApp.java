import controller.CRMController;
import controller.ICRMController;
import model.CRM;
import model.ICRM;
import view.CRMView;
import view.ICRMView;

public class CRMSimulatorApp {
    public static void main(String[] args) {
        ICRM model = new CRM();
        ICRMView view = new CRMView();
        ICRMController controller = new CRMController(view, model);
        controller.start();
    }
}
