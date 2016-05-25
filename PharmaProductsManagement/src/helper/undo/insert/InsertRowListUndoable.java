package helper.undo.insert;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotUndoException;

import entities.productEntities.Product;
import view.productManagementView.tableView.CustomListModel;


//implemented only to undo/redo an insertion of one row!

@SuppressWarnings("serial")
public class InsertRowListUndoable extends AbstractUndoableEdit
{
    protected CustomListModel listModel;
    protected Product rowValue;
    protected int row;

    public InsertRowListUndoable(CustomListModel listModel,int row, Product data)
    {
        this.listModel = listModel;
        this.row = row;
        this.rowValue = data;
    }


    @Override
    public String getPresentationName()
    {
        return "One Row Inserted in JList";
    }


    @Override
    public void undo() throws CannotUndoException
    {
        super.undo();
        listModel.remove(row);
    }


    @Override
    public void redo() throws CannotUndoException
    {
        super.redo();
        listModel.add(row,rowValue);
    }
}