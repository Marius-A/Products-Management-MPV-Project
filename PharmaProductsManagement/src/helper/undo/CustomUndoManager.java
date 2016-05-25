package helper.undo;
import javax.swing.Action;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

import helper.undo.action.CustomRedoAction;
import helper.undo.action.CustomUndoAction;


@SuppressWarnings("serial")
public class CustomUndoManager extends UndoManager
{
    protected Action undoAction;
    protected Action redoAction;


    public CustomUndoManager()
    {
        this.undoAction = new CustomUndoAction(this);
        this.redoAction = new CustomRedoAction(this);

        synchronizeActions();           // to set initial names
    }

    public Action getUndoAction()
    {
        return undoAction;
    }


    public Action getRedoAction()
    {
        return redoAction;
    }

// Adds an UndoableEdit to this UndoManager, if it's possible.
    @Override
    public boolean addEdit(UndoableEdit anEdit)
    {
        try
        {
            return super.addEdit(anEdit);
        }
        finally
        {
            synchronizeActions();
        }
    }

    //Undoes all changes from the index of the next edit to edit,
    //updating the index of the next edit appropriately.
    
    @Override
    protected void undoTo(UndoableEdit edit) throws CannotUndoException
    {
        try
        {
            super.undoTo(edit);
        }
        finally
        {
            synchronizeActions();
        }
    }

    // Redoes all changes from the index of the next edit to edit, updating 
    //the index of the next edit appropriately.
    @Override
    protected void redoTo(UndoableEdit edit) throws CannotRedoException
    {
        try
        {
            super.redoTo(edit);
        }
        finally
        {
            synchronizeActions();
        }
    }


    protected void synchronizeActions()
    {
        undoAction.setEnabled(canUndo());
        undoAction.putValue(Action.NAME, getUndoPresentationName());

        redoAction.setEnabled(canRedo());
        redoAction.putValue(Action.NAME, getRedoPresentationName());
    }
}
