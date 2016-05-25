package productManagementController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.undo.UndoManager;

import helper.undo.CustomUndoManager;
import productManagementModel.ProductsModel;
import view.productManagementView.ProductManagementView;

public class TableController {
	UndoManager undoManager;
	public TableController(CustomUndoManager undoManager, ProductsModel productsModel, final ProductManagementView productManagementView) {
		this.undoManager = undoManager;
		
		class tableListener implements MouseListener {

		    public void mouseClicked(MouseEvent evnt) {
		        if (evnt.getClickCount() == 2) {
		        	JTable target = (JTable)evnt.getSource();
		            
		        	int row = target.getSelectedRow();
		            int column = target.getSelectedColumn();
		            if (row >= 0 && column >= 0) {           	
	            		productManagementView.getEditPanel().setTextFieldProdName(productManagementView.getTablePanel().getValueAt(row,0));
	            		productManagementView.getEditPanel().setTextFieldProductQuantity(productManagementView.getTablePanel().getValueAt(row,2));
	            		productManagementView.getEditPanel().setTextFieldProdPrice(productManagementView.getTablePanel().getValueAt(row,1));
	            		productManagementView.getEditPanel().setTextFieldCompanyName(productManagementView.getTablePanel().getValueAt(row,3));
	            		productManagementView.getEditPanel().setTextFieldDepartmentName(productManagementView.getTablePanel().getValueAt(row,4));
	            		productManagementView.getEditPanel().setTextFieldDepNumber(productManagementView.getTablePanel().getValueAt(row,5));
	            		productManagementView.getEditPanel().setTextFieldCompanyAddress(productManagementView.getTablePanel().getValueAt(row,6));
	            		productManagementView.getEditPanel().setExpDate(productManagementView.getTablePanel().getValueAt(row,7));
		            }
		        }
		    }
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	
		}
		initTableContent(productsModel, productManagementView);
		productManagementView.getTablePanel().tableListener(new tableListener() );
		productManagementView.getTablePanel().setTransferHandler(productsModel);
		productManagementView.getTablePanel().getTableModel().addUndoableEditListener(undoManager);
	}

	private void initTableContent(ProductsModel productsModel, ProductManagementView productManagementView){
		try {
			productManagementView.getTablePanel().initTable(productsModel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
