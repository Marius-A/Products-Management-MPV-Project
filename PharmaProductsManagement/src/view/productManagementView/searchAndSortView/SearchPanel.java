package view.productManagementView.searchAndSortView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SearchPanel extends JPanel{
    private  ProductSearchComponent searchComponent;
    private  TableListOrder orderByCombobox;

	public SearchPanel() {
		super();
		initComponents();
		setLayout();
		setColor();
	}
	private void initComponents(){
        searchComponent = new ProductSearchComponent();
        orderByCombobox = new TableListOrder();
    }
	private void setLayout(){
		this.setLayout(new GridBagLayout());
	    GridBagConstraints c= new GridBagConstraints();
		
       // c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;  
        //c.weightx = 1;
        c.insets  = new Insets(0,5,0,0);
        c.anchor = GridBagConstraints.FIRST_LINE_START;  
        this.add(orderByCombobox,c);
        
        //c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;  
        c.ipadx = 0;
        c.weightx = 1;
        c.insets  = new Insets(0,0,0,5);
        c.anchor = GridBagConstraints.LAST_LINE_END;  
        this.add(searchComponent,c);
        
	}
    private void setColor(){
    	/*Border raisedbevel = BorderFactory.createRaisedBevelBorder();*/
    	//this.setBackground(new Color(76, 175, 80));
    	/*buttonCautare.setBackground(new Color(76, 175, 80));
    	textFieldCautare.setBackground(new Color(76, 175, 80));
    	jComboBoxCautareDupa.setEditable(true);
    	jComboBoxCautareDupa.getEditor().getEditorComponent().setBackground(new Color(76, 175, 80));
    	jComboBoxCautareDupa.setEditable(false);

    	buttonCautare.setBorder(raisedbevel);*/
	}
	public void resetTextFields(){
		searchComponent.resetTextFields();
	}
	public void searchButtonListener(ActionListener addButtonListener){
		searchComponent.searchButtonListener(addButtonListener);
	}
	public void	textFieldListener(KeyListener keyListener){
		searchComponent.textFieldListener(keyListener);
	}
	public void selectOrderListener(ActionListener sel){
		orderByCombobox.orderSelectionListener(sel);
	}
	public void searchButtondoClick(){
		searchComponent.searchButtondoClick();
	}
	public void searchFocusTextfield(){
        searchComponent.searchFocusTextfield();
	}
	public void orderBySelectListener(ActionListener sel){
		orderByCombobox.orderSelectionListener(sel);
	}
	public void setDefaultOrder(){
		orderByCombobox.setDefaultOrder();
	}
	//GETTERS
	public String getTextFieldKeyWord() {
		return searchComponent.getTextFieldKeyWord();
	}
	public String getComboBoxSearchBy() {
		return (String)searchComponent.getComboBoxSearchBy();
	}
	public String getSelectedSortingOrder(){
		return orderByCombobox.getSelectedOrder();
	}
}
