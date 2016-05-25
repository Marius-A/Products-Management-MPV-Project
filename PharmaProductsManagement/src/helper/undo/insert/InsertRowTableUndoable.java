package helper.undo.insert;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotUndoException;

import entities.productEntities.Product;
import productManagementModel.ProductsModel;
import view.productManagementView.tableView.CustomTableModel;


//implemented only to undo/redo an insertion of one row!

@SuppressWarnings("serial")
public class InsertRowTableUndoable extends AbstractUndoableEdit
{
    protected CustomTableModel tableModel;
    protected ProductsModel model;
    protected Product rowValue;
    protected int row;

    public InsertRowTableUndoable(final ProductsModel model, CustomTableModel tableModel,int row, Product rowData){
        this.tableModel = tableModel;
        this.row = row;
        this.rowValue = rowData;
        this.model = model;
    }


    @Override
    public String getPresentationName(){
        return "One Row Inserted in JTable";
    }


    @Override
    public void undo() throws CannotUndoException{
        super.undo();
        tableModel.removeRow(row);
        model.delProduct(row, rowValue);
    }


    @Override
    public void redo() throws CannotUndoException{
        super.redo();
        tableModel.insertRow(row, rowValue);
        model.addProduct(row, rowValue);
    }
}