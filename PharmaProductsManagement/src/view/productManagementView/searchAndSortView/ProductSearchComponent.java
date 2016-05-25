package view.productManagementView.searchAndSortView;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ProductSearchComponent extends JPanel {
    private  JButton searchButton;
    private  JTextField textFieldKeyWord; 
    private  JComboBox<String> searchByComboBox;
    
    public ProductSearchComponent() {
		super();
		initComponents();
		setSearchModel();
		setLayout();
		setColor();
	}
	private void initComponents(){
		searchByComboBox = new  JComboBox<String>();
        textFieldKeyWord = new  JTextField(20);
        textFieldKeyWord.setMinimumSize(new Dimension(200,20));
        searchButton = new  JButton("Search");
    }
	private void setSearchModel() {
	  searchByComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Product Name","Company Name",
        	"Category"}));	
	}
	private void setLayout(){
		this.setLayout(new GridBagLayout());
        GridBagConstraints cSearch = new GridBagConstraints();
        
        cSearch.fill = GridBagConstraints.BOTH;
        cSearch.gridx = 0;
        cSearch.gridy = 0; 
        cSearch.anchor =GridBagConstraints.CENTER;
        cSearch.insets  = new Insets(0,0,10,20);
        this.add(searchByComboBox,cSearch);
       
        cSearch.fill = GridBagConstraints.BOTH;
       // cSearch.weighty = 0.5;
        cSearch.gridx = 1;
        cSearch.gridy = 0;  
        cSearch.anchor =GridBagConstraints.LINE_END;  
        cSearch.insets  = new Insets(0,0,10,0);
        this.add(textFieldKeyWord,cSearch);
                
        cSearch.fill = GridBagConstraints.BOTH;
        cSearch.gridx = 1;
        cSearch.gridy = 1;  
        cSearch.insets  = new Insets(0,0,0,0);
        cSearch.anchor = GridBagConstraints.LAST_LINE_END;  
        this.add(searchButton,cSearch);             
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
		textFieldKeyWord.setText("");
	}
	public void searchButtonListener(ActionListener addButtonListener){
		searchButton.addActionListener(addButtonListener);
	}
	public void	textFieldListener(KeyListener keyListener){
		textFieldKeyWord.addKeyListener(keyListener);
	}
	public void searchButtondoClick(){
		this.searchButton.doClick();
	}
	public void searchFocusTextfield(){
        textFieldKeyWord.requestFocusInWindow();
	}
	//GETTERS
	public String getTextFieldKeyWord() {
		return textFieldKeyWord.getText();
	}
	public String getComboBoxSearchBy() {
		return (String)searchByComboBox.getSelectedItem();
	}
}
