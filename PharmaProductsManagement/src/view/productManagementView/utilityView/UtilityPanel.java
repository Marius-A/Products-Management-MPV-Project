package view.productManagementView.utilityView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Purpose:This class represent the panel that contains the main 
 * utility buttons such as : add,delete and update button
 * 
 * @author Marius Iliescu
 *
 */
@SuppressWarnings("serial")
public class UtilityPanel extends JPanel {
    private  JButton updateButton;
    private  JButton addButton;
	private  JButton deleteButton;

	/**
	 * Utility panel implicit constructor
	 */
	public UtilityPanel() {
		super();
		initComponents();
		setLayout();
    	setColor();
	}
	/**
	 * Initialize the buttons and configure them and the rest of the components
	 */
	private void initComponents(){
		this.updateButton = new JButton("Update");
		this.addButton = new JButton("Add");
		this.deleteButton = new JButton("Delete");
	}
	/**
	 * Set components layout(how appearing on the screen)
	 */
	private void setLayout(){   
		this.setLayout(new GridBagLayout());
        GridBagConstraints cFilter= new GridBagConstraints();
        cFilter.gridx = 0;
        cFilter.gridy = 0; 
        cFilter.ipadx = 55;
        cFilter.insets = new Insets(0,0,10,10);
        this.add(addButton,cFilter);
        
        cFilter.gridx = 1;
        cFilter.gridy = 0; 
        cFilter.ipadx = 35;
        cFilter.insets = new Insets(0,0,10,0);
        this.add(updateButton,cFilter);
        
        cFilter.gridx = 1;
        cFilter.gridy = 1; 
        cFilter.ipadx = 40;
        cFilter.insets = new Insets(0,0,0,0);
        this.add(deleteButton,cFilter);
	}
    /**
     * Set components color , including this panel
     */
    private void setColor(){
    	/*Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    	this.setBackground(new Color(76, 175, 80));
    	buttonActualizare.setBackground(new Color(76, 175, 80));
    	buttonAdaugare.setBackground(new Color(76, 175, 80));
    	buttonStergere.setBackground(new Color(76, 175, 80));
    	buttonActualizare.setBorder(raisedbevel);
    	buttonAdaugare.setBorder(raisedbevel);
    	buttonStergere.setBorder(raisedbevel);*/
	}
	/**
	 * Add listener to "add" button
	 * @param addButtonListener
	 */
	public void addButtonListener(ActionListener addButtonListener){
		addButton.addActionListener(addButtonListener);
	}
	/**
	 * Add listener to "update" button
	 * @param updateButtonListener
	 */
	public void updateButtonListener(ActionListener updateButtonListener){
		updateButton.addActionListener(updateButtonListener);
	}
	/**
	 * Add listener to "delete" button
	 * @param deleteButtonListener
	 */
	public void deleteButtonListener(ActionListener deleteButtonListener){
		deleteButton.addActionListener(deleteButtonListener);
	}
}
