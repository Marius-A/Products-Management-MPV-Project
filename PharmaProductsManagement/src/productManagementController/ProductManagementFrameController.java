package productManagementController;

import java.awt.Frame;

import helper.undo.CustomUndoManager;
import productManagementModel.ProductsModel;
import view.productManagementView.ProductManagementView;

public class ProductManagementFrameController {
	private CustomUndoManager undoManager;
	private ProductManagementView view;
	public ProductManagementFrameController( ProductsModel productsModel,ProductManagementView productManagementView) {
		this.view = productManagementView;
		undoManager = new CustomUndoManager();
		new ButtonsController(productsModel, productManagementView);
		new TableController(undoManager,productsModel, productManagementView);
		new SearchController(productsModel, productManagementView);
		new FilterController(productsModel, productManagementView);
		new EdController(productsModel, productManagementView);
		new FrameController(productsModel, productManagementView);
		new OpController(undoManager,productsModel, productManagementView);
		new SortingController(productsModel, productManagementView);
		new LookAndFeelController(productsModel, productManagementView);
	}
	public Frame getFrame(){
		return view;
	}
}
