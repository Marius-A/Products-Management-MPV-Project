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
public class CategoryFilter extends JPanel {
	JLabel filterName;
	Vector<JCheckBox>  category;
	JScrollPane scrollPane;
	
	public CategoryFilter() {
		super();
		initComponents();
	}
	private void initComponents(){
		this.filterName = new JLabel("Category");
		this.category = new Vector<JCheckBox>();
		this.scrollPane = new JScrollPane();
	    this.scrollPane.setMinimumSize(new Dimension(this.getWidth(),120));
	    this.scrollPane.setPreferredSize(new Dimension(this.getWidth(),200));  
	    filterName.setFont(new Font("TimesRoman", Font.BOLD,14));
	}
	public void setModel(final ProductsModel productsModel){
		ListIterator<Product> iter = productsModel.getProductsList().listIterator();
		HashMap<String,Integer> lDenDep = new HashMap<>();
 		category.removeAllElements();
 		int i=0;
		while(iter.hasNext()){
			Product p = new Product(iter.next());	
			if(!lDenDep.containsKey((p.getCategory().getCategoryName()))){
				i=0;
				lDenDep.put(p.getCategory().getCategoryName(),i);
			}else{
				i = lDenDep.get(p.getCategory().getCategoryName());
				lDenDep.replace(p.getCategory().getCategoryName(),i+1);
			}
		}

		Iterator <String> it= lDenDep.keySet().iterator();	
		//se parcurge multimea cheilor:
		while(it.hasNext()){
			String cheie = it.next();	
			Integer valoare= lDenDep.get(cheie)+1;
			
			category.add(new JCheckBox(cheie+" - ("+valoare+")"));
		}
		setLayout();
	}
	public void setLayout(){
		if (category.size() > 0 ){
			 this.setLayout(new GridBagLayout());
			 Box box = Box.createVerticalBox();
		     GridBagConstraints c= new GridBagConstraints();
		     c.gridy = 0;
		     c.gridx = 0;
		     c.anchor = GridBagConstraints.FIRST_LINE_START; 
		     c.insets = new Insets(10,0,10,0);
	         this.add(filterName,c);
		     for (int i=0 ; i< category.size(); i++){
		    	 c.gridy = i+1;
		    	 c.gridx = 0;
		    	 c.weightx = 1;
		    	 c.fill = GridBagConstraints.HORIZONTAL;
		         c.anchor = GridBagConstraints.FIRST_LINE_START;  
		         c.insets = new Insets(0,0,0,0);
		         box.add(category.elementAt(i),c);
		     }
		     scrollPane.setViewportView(box);
		     this.add(scrollPane,c);
		}
	}
	public void addSelectCategoryListener(ActionListener sel){
		for (JCheckBox i : category){
			i.addActionListener(sel);
		}
	}
	public Vector<String> getSelectedCategories(){
		Vector<String> selectedCategories = new Vector<String>();
		for (JCheckBox i : category){
			if(i.isSelected()){	
				selectedCategories.addElement(i.getText().split(" - \\(")[0]);
			}
		}
		if(selectedCategories.isEmpty())
			return null;
		else
			return selectedCategories;
	}
	public void resetCategoryFilters(){
		for (JCheckBox i : category){
			if(i.isSelected()){
				i.setSelected(false);
			}
		}
	}
}
