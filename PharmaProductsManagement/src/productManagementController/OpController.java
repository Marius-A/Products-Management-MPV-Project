package productManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.JOptionPane;

import helper.comparator.ListComparator;
import helper.comparator.NumeProdusComparatorAsc;
import helper.transformer.XmlTransformer;
import helper.undo.CustomUndoManager;
import helper.xml.XmlProductsHandler;
import productManagementModel.ProductsModel;
import view.productManagementView.ProductManagementView;

public class OpController {
	CustomUndoManager undoManager;
	public OpController(CustomUndoManager um , final ProductsModel productsModel,final ProductManagementView productManagementView) {
		undoManager = um;
		
		class RestoreButtonListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				productManagementView.getTablePanel().setNewTableModel(productsModel);	
				productManagementView.getFilterPanel().resetTextFields();
				productManagementView.getFilterPanel().resetCategoryFilters();
				productManagementView.getFilterPanel().resetCompaniesFilters();
				productManagementView.getSearchPanel().resetTextFields();
				productManagementView.getSearchPanel().setDefaultOrder();
			}
		
		}
		class LoadExcelButtonListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(productManagementView.getOpPanel().getTogleButtonState()){
					productManagementView.getTablePanel().showLeftComponent();
				}else{
					productManagementView.getTablePanel().hideLeftComponent();
					productManagementView.getTablePanel().revalidate();
				}		
			}
		
		}
		class PrintButtonListener implements ActionListener{
			
			XmlProductsHandler xmlH = new XmlProductsHandler();
			XmlTransformer xmlT = new XmlTransformer();
			public void actionPerformed(ActionEvent arg0) {
				Object[] options = {"PDF",
				                    "HTML",
				                    "Cancel"};
				int n = JOptionPane.showOptionDialog(productManagementView,
				    "Would you like to generate a Pdf or a HTML file? ",
				    "Question",
				    JOptionPane.YES_NO_CANCEL_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[2]);
				
				//if chose cancel ->return
				if(n==2){
					return;
				}else{
					ListComparator comp = new NumeProdusComparatorAsc();
					Collections.sort(productsModel.getProductsList(), comp);	
					xmlH.writeXML(productsModel.getProductsList());	
					if(n==0){
						xmlT.makePdf();
					} else if(n==1){
						xmlT.makeHTML();
					}
				}
			}
		}
		class UndoButtonListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(undoManager.canUndo())
		        	undoManager.undo();
			}
		}
		class RedoButtonListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(undoManager.canRedo())
		        	undoManager.redo();
			}
		}
		productManagementView.getOpPanel().excelLoaderButtonListener(new LoadExcelButtonListener());
		productManagementView.getOpPanel().restoreButtonListener(new RestoreButtonListener());
		productManagementView.getOpPanel().printButtonListener(new PrintButtonListener());
		productManagementView.getOpPanel().undoButtonListener(new UndoButtonListener());
		productManagementView.getOpPanel().redoButtonListener(new RedoButtonListener());
	}
}
