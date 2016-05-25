package view.productManagementView.searchAndSortView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TableListOrder extends JPanel {
	private JComboBox<String> orderBy;
	
	public TableListOrder(){
		initComponents();
		setOrderBModel();
		setLayout();
	}
	private void initComponents(){
		orderBy = new JComboBox<String>();
	}
	private void setOrderBModel(){
		 orderBy.setModel(new DefaultComboBoxModel<String>(new String[] { "Order by ...",
				 "Product Name - Asc",
				 "Product Name - Desc",
				 "Company Name - Asc",
				 "Company Name - Desc",
	        	 "Category id - Asc" ,
	        	 "Category id - Desc" ,
	        	 "Category Name - Asc",
	        	 "Category Name - Desc",
	        	 "Product Price - Asc",
	        	 "Product Price - Desc"
		 }));	
	}
    private void setLayout(){
    	this.setLayout(new GridBagLayout());
    	GridBagConstraints c= new GridBagConstraints();

    	c.gridx = 0;
        c.gridy = 0;  
        c.anchor = GridBagConstraints.FIRST_LINE_START; 
        c.ipadx = 55;    
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(orderBy,c);
    }
	public String getSelectedOrder(){
		return (String)orderBy.getSelectedItem();	
	}
	public void orderSelectionListener(ActionListener sel){
		this.orderBy.addActionListener(sel);
	}
	public void setDefaultOrder(){
		orderBy.setSelectedIndex(0);
	}
}
