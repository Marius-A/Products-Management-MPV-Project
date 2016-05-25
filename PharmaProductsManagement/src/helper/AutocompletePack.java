package helper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class AutocompletePack extends PlainDocument {
    JComboBox<?> comboBox;
    ComboBoxModel<?> model;
    JTextComponent editor;
    boolean selecting=false;
    boolean hidePopupOnFocusLoss;
    boolean hitBackspace=false;
    boolean hitBackspaceOnSelection;
    boolean listContainsSelectedItem;
    
    public AutocompletePack(final JComboBox<?> comboBox) {
        this.comboBox = comboBox;
        model = comboBox.getModel();
        editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
        editor.setDocument(this);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!selecting) highlightCompletedText(0);
            }
        });
        editor.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (comboBox.isDisplayable() && (e.getKeyCode() == KeyEvent.CTRL_MASK)) 
                	comboBox.setPopupVisible(true);
                hitBackspace=false;
                switch (e.getKeyCode()) {
                    // determine if the pressed key is backspace (needed by the remove method)
                    case KeyEvent.VK_BACK_SPACE : hitBackspace=true;
                    hitBackspaceOnSelection=editor.getSelectionStart()!=editor.getSelectionEnd();
                    break;
                }
            }
        });
        editor.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                highlightCompletedText(0);
            }
            public void focusLost(FocusEvent e) {
                if (hidePopupOnFocusLoss) comboBox.setPopupVisible(false);
            }
        });
        Object selected = comboBox.getSelectedItem();
        if (selected!=null) setText(selected.toString());
        highlightCompletedText(0);
    }
    public void remove(int offs, int len) throws BadLocationException {
        if (selecting) return;
        if (hitBackspace) {
            if (listContainsSelectedItem) {
                if (offs>0) {
                    if (hitBackspaceOnSelection) offs--;
                } else {
                    comboBox.getToolkit().beep();
                }
                highlightCompletedText(offs);
                return;
            } else {
                super.remove(offs, len);
                String currentText = getText(0, getLength());
                Object item = lookupItem(currentText);
                if (item != null) {
                    setSelectedItem(item);
                    listContainsSelectedItem=true;
                } else {
                    item=currentText;
                    setSelectedItem(item);
                    listContainsSelectedItem=false;
                }
                String itemString=item.toString();
                setText(itemString);
                if (listContainsSelectedItem) highlightCompletedText(offs);
            }
        } else {
            super.remove(offs, len);
            setSelectedItem(getText(0, getLength()));
            listContainsSelectedItem=false;
        }
    }   
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (selecting) return;
        super.insertString(offs, str, a);
        Object item = lookupItem(getText(0, getLength()));
        
        listContainsSelectedItem = true;
        if (item == null) {
            item=getText(0, getLength());
            listContainsSelectedItem=false;
        }
        setSelectedItem(item);
        setText(item.toString());
        if (listContainsSelectedItem) highlightCompletedText(offs+str.length());
    } 
    private void setText(String text) {
        try {
            super.remove(0, getLength());
            super.insertString(0, text, null);
        } catch (BadLocationException e) {
            throw new RuntimeException(e.toString());
        }
    }  
    private void highlightCompletedText(int start) {
        editor.setCaretPosition(getLength());
        editor.moveCaretPosition(start);
    } 
    private void setSelectedItem(Object item) {
        selecting = true;
        model.setSelectedItem(item);
        selecting = false;
    }  
    private Object lookupItem(String pattern) {
        Object selectedItem = model.getSelectedItem();
        if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern) && listContainsSelectedItem) {
            return selectedItem;
        } else {
            for (int i=0, n=model.getSize(); i < n; i++) {
                Object currentItem = model.getElementAt(i);
                if (startsWithIgnoreCase(currentItem.toString(), pattern)) {
                    return currentItem;
                }
            }
        }
        return null;
    }
    private boolean startsWithIgnoreCase(String str1, String str2) {
        return str1.toUpperCase().startsWith(str2.toUpperCase());
    }       

}