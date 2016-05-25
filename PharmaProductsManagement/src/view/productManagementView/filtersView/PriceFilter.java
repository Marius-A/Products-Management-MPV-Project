package view.productManagementView.filtersView;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PriceFilter extends JPanel {
    private  JLabel priceFilterLabel;
    private  JLabel delimiter;
    private  JTextField priceFilter1TestField;
    private  JTextField priceFilter2TestField;
    private  JButton filterButton;
    
    public PriceFilter(){
    	initComponents();
    	setLayout();
    }  
    private void initComponents(){
        priceFilterLabel = new JLabel("Price between :");
        priceFilter1TestField = new  JTextField(5);
        priceFilter2TestField = new  JTextField(5);
        filterButton = new JButton("Filter");
        delimiter = new JLabel("< - >");
        priceFilter1TestField.setMinimumSize(new Dimension(60, 25));
        priceFilter2TestField.setMinimumSize(new Dimension(60, 25));
        priceFilterLabel.setFont(new Font("TimesRoman", Font.BOLD,14));
    }
    private void setLayout(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0; 
        c.insets = new Insets(0,0,10,0);
        //c.ipadx =10;
        this.add(priceFilterLabel,c);
        
        c.gridx = 0;
        c.gridy = 1; 
        c.ipadx=20;
        c.insets = new Insets(0,0,0,0);
        this.add(priceFilter1TestField,c);
        
        c.gridx = 1;
        c.gridy = 1; 
        c.ipadx=0;
        c.insets = new Insets(0,5,0,0);
        this.add(delimiter,c);
        
        c.gridx = 2;
        c.gridy = 1; 
        c.ipadx= 20;
        this.add(priceFilter2TestField,c);
        
        c.gridx = 2;
        c.gridy = 2; 
        c.ipadx= 30;
        c.insets = new Insets(10,5,0,0);
        this.add(filterButton,c);        
    }
    public void resetTextFields(){
    	priceFilter1TestField.setText("");
    	priceFilter2TestField.setText("");
    }
	public void filterButtonListener(ActionListener filterButtonListener){
		filterButton.addActionListener(filterButtonListener);
	}
	public void	textFieldListener(KeyListener keyListener){
		priceFilter1TestField.addKeyListener(keyListener);
		priceFilter2TestField.addKeyListener(keyListener);
	}
	public void filterButtonDoClick(){
		filterButton.doClick();
	}
	public Float getTextPriceFilter1() {
		return Float.parseFloat(priceFilter1TestField.getText());
	}
	public void setTextPriceFilter1(String s) {
		this.priceFilter1TestField.setText(s);
	}
	public Float getTextPriceFilter2() {
		return Float.parseFloat(priceFilter2TestField.getText());
	}
	public void setTextPriceFilter2(String s) {
		this.priceFilter2TestField.setText(s);
	}

}

