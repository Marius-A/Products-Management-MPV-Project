package helper.transfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import entities.productEntities.Product;
import view.productManagementView.tableView.CustomListModel;

@SuppressWarnings("serial")
public class CustomListTransferHandler extends TransferHandler{

	JList<Product> list;
	CustomListModel myListModel;

	public CustomListTransferHandler(JList<Product> excelProductsList, CustomListModel myListModel) {
		this.list = excelProductsList;
		this.myListModel = myListModel;
	}

	//This method is called repeatedly during a drag gesture and returns true 
	//if the area below the cursor can accept the transfer, or false if the 
	//transfer will be rejected. 
	public boolean canImport(TransferHandler.TransferSupport info) {
		// we only import Strings
		DataFlavor prodDataFlavor = new DataFlavor(Product.class,Product.class.getSimpleName());
		if (!info.isDataFlavorSupported(prodDataFlavor)) {
			return false;
		}
		//check for a valid location
		JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
		if (dl.getIndex() == -1) {
			return false;
		}
		return false;
	}

	//This method is called on a successful drop (or paste) and initiates the 
	//transfer of data to the target component. 
	public boolean importData(TransferHandler.TransferSupport info) {
		if (!info.isDrop()) {
			return false;
		}
		DataFlavor prodDataFlavor = new DataFlavor(Product.class,Product.class.getSimpleName());
		// Check for String flavor
		if (!info.isDataFlavorSupported(prodDataFlavor)) {
			displayDropLocation("List doesn't accept a drop of this type.");
			return false;
		}

		JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();

		//get the model for the working list
		CustomListModel listModel = myListModel;//(DefaultListModel)list.getModel();

		//get the location for insertion
		int index = dl.getIndex();

		//this variable tells if the drop will insert between lines or override one.
		boolean insert = dl.isInsert();

		// get the current string under the drop.
		//String value = (String)listModel.getElementAt(index);

		// Get the string that is being dropped.
		Transferable t = info.getTransferable();
		Product data;
		try {
			data = (Product) t.getTransferData(prodDataFlavor);
		} 
		catch (Exception e) { return false; }

		// Perform the actual import.  
		if (insert) {//if the drop is between lines, we have an insertion

			listModel.add(index, data); //only one line selected
			//listModel.addUndoableRow(index,data); //save the row so it can be undone
		}
		return true;

	}

	private void displayDropLocation(final String string) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JOptionPane.showMessageDialog(null, string);
			}
		});
	}

	//This method is used to query what actions are supported by 
	//the source component, such as COPY, MOVE, or LINK, in any combination.

	public int getSourceActions(JComponent c) {
		return COPY;
	}

	//This method bundles up the data to be exported into a 
	//Transferable object in preparation for the transfer.

	protected Transferable createTransferable(JComponent c) {
		@SuppressWarnings("unchecked")
		JList<Product> list = (JList<Product>)c;
		List<Product> values = list.getSelectedValuesList();

		Product buff = new Product();
		for (int i = 1; i <= values.size(); ++i) {
			if(i>0 && i<=1)
				buff = values.get(i-1);
		}
		return buff;
	}
}
