package view.productManagementView.tableView;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import helper.undo.insert.InsertRowTableUndoable;
import entities.productEntities.*;
import productManagementModel.ProductsModel;
import java.util.Date;


@SuppressWarnings("serial")
public class CustomTableModel extends DefaultTableModel{

	public CustomTableModel(String[] s) {
		for(int i = 0 ; i < s.length ; i++){
			addColumn(s[i]);
		}
	}
	public void addUndoableRow(final ProductsModel model , int row,Product rowData) {
		//Obtaining an array of all the change listeners registered on this 
    	//DefaultTableModel.
		
		UndoableEditListener listeners[] = getListeners(UndoableEditListener.class);
		
		//if there are no change listeners, this method has done it's work.
		if(listeners == null)
			return;
		
    	//the row is added in an InsertRowTableUndoable so the change can be undone later
		InsertRowTableUndoable rowEdit = new InsertRowTableUndoable(model,this, row, rowData);
		
		//An event indicating that an operation which can be undone has occurred.
		UndoableEditEvent editEvent = new UndoableEditEvent(this, rowEdit);
		
		//iterate events
		for (UndoableEditListener listener : listeners)
			listener.undoableEditHappened(editEvent);
	}

	public void addUndoableEditListener(UndoableEditListener listener)
	{
		listenerList.add(UndoableEditListener.class, listener);
	}

	 //converts multiple strings(table row) into one String (list row)
	public Product getRowForList(int row) {
		Product rowValue = getRow(row);
		
		return rowValue;		
	}
	public void insertRow(int row, Product p) {
		insertRow(row, new Object[]{p.getProdName(),p.getProdPrice(),p.getQuantity(),p.getCategory().getCompany().getCompanyName(),p.getCategory().getCategoryName(),p.getCategory().getCategoryId(),p.getCategory().getCompany().getCompanyAddress(),p.getExpDate()});	
	}
	public Product getRow(int i){
		Company intr = new Company(getValueAt(i, 3).toString(),getValueAt(i, 6).toString());
		Category cat = new Category(Integer.parseInt(getValueAt(i, 5).toString()) ,getValueAt(i, 4).toString() ,intr );
		Product prod = new Product(getValueAt(i, 0).toString() , Float.parseFloat(getValueAt(i, 1).toString()) , Integer.parseInt(getValueAt(i, 2).toString()) , cat ,((Date)getValueAt(i, 7)));
		return prod;
	}
}
