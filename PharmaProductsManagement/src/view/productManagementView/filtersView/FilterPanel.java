package view.productManagementView.filtersView;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import productManagementModel.ProductsModel;

@SuppressWarnings("serial")
public class FilterPanel extends JPanel{

    private  PriceFilter priceFilterPanel;
    private  CategoryFilter categoryFilterPanel;
    private  CompanyFilter companiesFiltetPanel;
    private  JCheckBox zeroStockCheckBox;
    private  JCheckBox isExpiredCheckBox;
    public FilterPanel(){
    	super();
    	initComponents();
    	setButtonIcon();
    	setLayout();
    	setColor();
    }
    private void initComponents(){ 
        priceFilterPanel = new PriceFilter();
        categoryFilterPanel = new CategoryFilter();
        companiesFiltetPanel = new CompanyFilter();
        zeroStockCheckBox = new JCheckBox("Sold out");
        isExpiredCheckBox = new JCheckBox("Expired products");
    }
    private void setButtonIcon(){
    	//butonFiltrare.setIcon(new ImageIcon("res/Img/filterButton.png"));
    }
    private void setLayout(){
    	this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(priceFilterPanel);  
        this.add(categoryFilterPanel);
        this.add(zeroStockCheckBox);
        this.add(isExpiredCheckBox);
        this.add(companiesFiltetPanel);
    }
    private void setColor(){
    /*	Border raisedbevel = BorderFactory.createRaisedBevelBorder();*/
    	//this.setBackground(new Color(76, 175, 80));
    	/*textFieldFiltarrePret1.setBackground(new Color(76, 175, 80));
    	textFieldFiltarrePret2.setBackground(new Color(76, 175, 80));
    	butonFiltrare.setBackground(new Color(76, 175, 80));
    	butonFiltrare.setBorder(raisedbevel);*/
	}
    public void resetTextFields(){
    	priceFilterPanel.resetTextFields();
    }
    public void resetSoldOutCheckbox(){
    	zeroStockCheckBox.setSelected(false);
    }
    public void resetExpiredCheckbox(){
    	isExpiredCheckBox.setSelected(false);
    }
	public void filterButtonListener(ActionListener filterButtonListener){
		priceFilterPanel.filterButtonListener(filterButtonListener);
	}
	public void	textFieldListener(KeyListener keyListener){
		priceFilterPanel.textFieldListener(keyListener);
	}
	public void addSoldOutListener(ActionListener sel){
		zeroStockCheckBox.addActionListener(sel);
	}
	public void addExpiredListener(ActionListener sel){
		isExpiredCheckBox.addActionListener(sel);
	}
	public void filterButtonDoClick(){
		priceFilterPanel.filterButtonDoClick();
	}
	
	public Float getTextFieldFiltarrePret1() {
		return priceFilterPanel.getTextPriceFilter1();
	}
	public void setTextFieldFiltarrePret1(String s) {
		priceFilterPanel.setTextPriceFilter1(s);
	}
	public Float getTextFieldFiltarrePret2() {
		return priceFilterPanel.getTextPriceFilter2();
	}
	public Boolean getSoldOutFilter() {
		return zeroStockCheckBox.isSelected();
	}
	public Boolean getExpiredFilter() {
		return isExpiredCheckBox.isSelected();
	}
	public void setTextFieldFiltarrePret2(String s) {
		priceFilterPanel.setTextPriceFilter2(s);
	}
	//---------------------------
	public void setCategoryFilterModel(final ProductsModel productsModel){
		categoryFilterPanel.setModel(productsModel);
	}
	public void addSelectCategoryListener(ActionListener sel){
		categoryFilterPanel.addSelectCategoryListener(sel);
	}
	public Vector<String> getSelectedCategories(){
		return categoryFilterPanel.getSelectedCategories();
	}
	public void resetCompaniesFilters(){
		companiesFiltetPanel.resetCompaniesFilters();
	}
	//--------------------------
	public void setCompaniesFilterModel(final ProductsModel productsModel){
		companiesFiltetPanel.setModel(productsModel);
	}
	public void addSelectCompaniesListener(ActionListener sel){
		companiesFiltetPanel.addSelectCompaniesListener(sel);
	}
	public Vector<String> getSelectedCompanies(){
		return companiesFiltetPanel.getSelectedCompanies();
	}
	public void resetCategoryFilters(){
		categoryFilterPanel.resetCategoryFilters();
	}
	
}
