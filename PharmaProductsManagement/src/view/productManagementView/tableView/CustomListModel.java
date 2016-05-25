package view.productManagementView.tableView;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.ListIterator;

import javax.swing.DefaultListModel;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

import helper.undo.insert.InsertRowListUndoable;
import entities.productEntities.Product;
import productManagementModel.ProductsListModel;

@SuppressWarnings("serial")
public class CustomListModel extends DefaultListModel<Product>{
	
	public void addValues() {
		ProductsListModel prod = new ProductsListModel();
		prod.initList();
		//ArrayList<Product> listProd = new ArrayList<>();
		ArrayList<Product> listProd = prod.getProductsList();
		ListIterator<Product> iter = listProd.listIterator();
		while(iter.hasNext()){
			Product p = new Product(iter.next());
			addElement(p);
		}
		
		/*addElement(new Product("Aspacardin",(float)2, 3 ,new Department(207,"Medicamente" , new Company("Sc Med","Bucuresti"))));
		addElement(new Product("Inhalator",(float)2, 3 ,new Department(207,"Aparat Medical" , new Company("Sc Med","Bucuresti"))));
		addElement(new Product("Vata",(float)2, 3 ,new Department(207,"Medicamente" , new Company("Sc Med","Bucuresti"))));*/
	}

    public void addUndoableRow(int row,Product data) {
    	
    	//Obtaining an array of all the change listeners registered on this 
    	//DefaultListModel.
    	EventListener[] listeners = getListeners(UndoableEditListener.class);
    	
    	//if there are no change listeners, this method has done it's work.
    	if(listeners == null)
    		return;
    	
    	//the row is added in an InsertRowListUndoable so the change can be undone later
    	InsertRowListUndoable undoInsert = new InsertRowListUndoable(this, row, data);
    	
    	//An event indicating that an operation which can be undone has occurred.
    	UndoableEditEvent editEvent = new UndoableEditEvent(this, undoInsert);
    	
    	//iterate events
        for (EventListener listener : listeners)
            ((UndoableEditListener) listener).undoableEditHappened(editEvent);
    }
    
    //set UndoableEditListener
    public void addUndoableEditListener(UndoableEditListener listener)
    {
        listenerList.add(UndoableEditListener.class, listener);
    }
    
    //converts a String(list row) into String[] (table row)
    public Object getRowForTable(int index, boolean cut) {
    	Product rowForTable;
    	
    	Product rowToFormat =get(index);
    	rowForTable = rowToFormat;
    	
    	return rowForTable;
    }
}
