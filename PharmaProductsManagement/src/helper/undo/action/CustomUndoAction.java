package helper.undo.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

@SuppressWarnings("serial")
public class CustomUndoAction extends AbstractAction
{
    protected final UndoManager manager;


    public CustomUndoAction(UndoManager manager)
    {
        this.manager = manager;
    }


    public void actionPerformed(ActionEvent e)
    {
        try
        {
            manager.undo();
        }
        catch (CannotUndoException ex)
        {
            ex.printStackTrace();
        }
    }
}