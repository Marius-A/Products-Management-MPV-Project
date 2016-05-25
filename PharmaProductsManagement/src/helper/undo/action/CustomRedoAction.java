package helper.undo.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

@SuppressWarnings("serial")
public class CustomRedoAction extends AbstractAction
{
    protected final UndoManager manager;


    public CustomRedoAction(UndoManager manager)
    {
        this.manager = manager;
    }


    public void actionPerformed(ActionEvent e)
    {
        try
        {
            manager.redo();
        }
        catch (CannotRedoException ex)
        {
            ex.printStackTrace();
        }
    }

}