package view.productManagementView.filtersView;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entities.productEntities.Product;
import productManagementModel.ProductsModel;

@SuppressWarnings("serial")
public class CompanyFilter extends JPanel {
	JLabel filterName;
	Vector<JCheckBox> companies;
	JScrollPane scrollPane;
	
	public CompanyFilter(){
		super();
		initComponents();
	}
	private void initComponents(){
		this.filterName = new JLabel("Company");
		this.companies = new Vector<JCheckBox>();
		this.scrollPane = new JScrollPane();
	    this.scrollPane.setMinimumSize(new Dimension(this.getWidth(),120));
	    this.scrollPane.setPreferredSize(new Dimension(this.getWidth(),200));  
	    
	    filterName.setFont(new Font("TimesRoman", Font.BOLD,14));
	}
	public void setModel(final ProductsModel productsModel){
		ListIterator<Product> iter = productsModel.getProductsList().listIterator();
		HashMap<String,Integer> lDenIntr = new HashMap<>();	
		companies.removeAllElements();
 		int i=0;
		while(iter.hasNext()){
			Product p = new Product(iter.next());	
			if(!lDenIntr.containsKey(p.getCategory().getCompany().getCompanyName())){
				i=0;
				lDenIntr.put(p.getCategory().getCompany().getCompanyName(),i);
			}else{
				i = lDenIntr.get(p.getCategory().getCompany().getCompanyName());
				lDenIntr.replace(p.getCategory().getCompany().getCompanyName(),i+1);
			}
		}
		
		Iterator <String> it= lDenIntr.keySet().iterator();	
		//se parcurge multimea cheilor:
		while(it.hasNext()){
			String cheie = it.next();	
			Integer valoare= lDenIntr.get(cheie)+1;
			
			companies.add(new JCheckBox(cheie+" - ("+valoare+")"));
		}
		setLayout();
	}
	public void setLayout(){
		if (companies.size() > 0 ){
			 Box box = Box.createVerticalBox();
			 this.setLayout(new GridBagLayout());
		     GridBagConstraints c= new GridBagConstraints();
			 
		     c.gridy = 0;
		     c.gridx = 0;
		     c.anchor = GridBagConstraints.FIRST_LINE_START; 
		     c.insets = new Insets(10,0,10,0);
	         this.add(filterName,c);
	         
		     for (int i=0 ; i< companies.size(); i++){
		    	 c.gridy = i+1;
		    	 c.gridx = 0;
		    	 c.weightx = 1;
		    	 c.fill = GridBagConstraints.HORIZONTAL;
		         c.anchor = GridBagConstraints.FIRST_LINE_START;  
		         c.insets = new Insets(0,0,0,0);
		         box.add(companies.elementAt(i),c);
		     }
		     scrollPane.setViewportView(box);
		     this.add(scrollPane,c);
		}
	}
	public void addSelectCompaniesListener(ActionListener sel){
		for (JCheckBox i : companies){
			i.addActionListener(sel);
		}
	}
	public Vector<String> getSelectedCompanies(){
		Vector<String> selectedCompanies = new Vector<String>();
		for (JCheckBox i : companies){
			if(i.isSelected()){
				selectedCompanies.addElement(i.getText().split(" - \\(")[0]);
			}
		}
		if(selectedCompanies.isEmpty())
			return null;
		else
			return selectedCompanies;
	}
	public void resetCompaniesFilters(){
		for (JCheckBox i : companies){
			if(i.isSelected()){
				i.setSelected(false);
			}
		}
	}
}
