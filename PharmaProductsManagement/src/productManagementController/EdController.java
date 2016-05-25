package productManagementController;

import productManagementModel.ProductsModel;
import view.productManagementView.ProductManagementView;

public class EdController{

	public EdController(ProductsModel productsModel , ProductManagementView productManagementView){
		productManagementView.getEditPanel().setModel(productsModel.getProductsList());
		productsModel.addObserver(productManagementView.getEditPanel());
	}
}
