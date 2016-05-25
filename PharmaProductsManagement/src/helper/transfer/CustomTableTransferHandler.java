package helper.transfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ListIterator;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import entities.productEntities.Product;
import productManagementModel.ProductsModel;
import view.productManagementView.tableView.CustomTableModel;


@SuppressWarnings("serial")
public class CustomTableTransferHandler extends TransferHandler{
	private CustomTableModel tableModel;
	private ProductsModel arrayListModel;
	


	public CustomTableTransferHandler(CustomTableModel tableModel, ProductsModel model) {
		this.tableModel = tableModel;
		this.arrayListModel = model;
	}

	//This method is called repeatedly during a drag gesture and returns true 
	//if the area below the cursor can accept the transfer, or false if the 
	//transfer will be rejected. 

	public boolean canImport(TransferSupport support) {
		// we'll only support drops (not clipboard paste)
		if (!support.isDrop()) {
			return false;
		}

		// we only import Strings
		DataFlavor prodDataFlavor = new DataFlavor(Product.class,Product.class.getSimpleName());
		if (!support.isDataFlavorSupported(prodDataFlavor)) {
			return false;
		}

		return true;
	}

	//This method is called on a successful drop (or paste) and initiates the 
	//transfer of data to the target component. 

	public boolean importData(TransferSupport support) {
		// if we can't handle the import, say so
		if (!canImport(support)) {
			return false;
		}

		// fetch the drop location
		JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
		
		int row = dl.getRow();
		boolean insert = dl.isInsertRow();
		
		// fetch the data and bail if this fails
		Product data;
		DataFlavor prodDataFlavor = new DataFlavor(Product.class,Product.class.getSimpleName());
		try {
			data = (Product) support.getTransferable().getTransferData(prodDataFlavor);
		} catch (UnsupportedFlavorException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		Boolean contains = false;
		ListIterator<Product> produse = arrayListModel.getProductsList().listIterator();
		while(produse.hasNext()){
			Product prod = new Product(produse.next());
			
			String insProd = new String(data.toString());
			String bdProd = new String(prod.toString());
			if(bdProd.equals(insProd)){
				contains = true;
			}
		}
		if(insert) {//insert between lines
			if(!contains){
				tableModel.insertRow(row, data);
				arrayListModel.addProduct(row, data);
				tableModel.addUndoableRow(arrayListModel, row,data);
			}else{
				JOptionPane.showMessageDialog(new JFrame(),
						"The product is already in the database!",
					    "",
					    JOptionPane.WARNING_MESSAGE);
			}
		}

		return true;
	}

	public int getSourceActions(JComponent c) {
		return COPY;
	}
	//This method bundles up the data to be exported into a 
	//Transferable object in preparation for the transfer.

	protected Transferable createTransferable(JComponent c) {
		JTable table =(JTable)c;
		int[] rows = table.getSelectedRows();
		
		CustomTableModel mtm= (CustomTableModel) table.getModel();

		Product buff = new Product();
			
		for(int i=1; i<=rows.length;++i) {//iterate all selected lines
			if(i > 0 && i <= 1 )
				buff = mtm.getRow(rows[i-1]);
		}
		return buff;
	}

}
