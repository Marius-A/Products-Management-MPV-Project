import java.awt.EventQueue;

import productManagementController.ProductManagementFrameController;
import productManagementController.Startup;
import productManagementModel.ProductsModel;
import view.productManagementView.*;

public class MainAplication {

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new Startup(new ProductManagementFrameController(new ProductsModel(), new ProductManagementView()));
            }
        });
	}
}
