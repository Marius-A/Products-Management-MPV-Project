package view.productManagementView.inputView;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import helper.AutocompletePack;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import entities.productEntities.Product;

/**
 * Purpose:This class represent the panel that contains all labels and input text fields for a product
 * @see Product
 * @author Marius Iliescu
 *
 */
@SuppressWarnings("serial")
public class EditPanel extends JPanel implements Observer{

	private  JLabel companyAddressLabel;
    private  JLabel productQuantitiesLabel;
    private  JLabel departmentNameLabel;
    private  JLabel companyNameLabel;
    private  JLabel productNameLabel;
    private  JLabel departmentNumberLabel;
    private  JLabel productPriceLabel;
    private  JLabel productExpDate;
    private  JComboBox<String> companyAddressComboBox;
    private  JComboBox<String> productQuantitiesComboBox;
    private  JComboBox<String> categoryNameComboBox;
    private  JComboBox<String> companyNameComboBox;
    private  JComboBox<String> productNameComboBox;
    private  JComboBox<String> companyIdComboBox;
    private  JComboBox<String> productPriceComboBox;
	private  JDatePickerImpl datePicker;
    
	/**
	 * Edit Panel implicit constructor
	 */
	public EditPanel(){
		super();
		initComponents();
		setComboBoxesEditable();
		setLabelsFont();
		setLayout();
    	setColor();
	}
	private void initComponents(){
		this.companyAddressLabel = new  JLabel("Company Address");
		this.productQuantitiesLabel = new  JLabel("Quantities");
		this.departmentNameLabel = new  JLabel("Category");
		this.companyNameLabel = new  JLabel("Company Name");
		this.productNameLabel = new  JLabel("Product Name");
		this.departmentNumberLabel = new  JLabel("Category Id");
		this.productPriceLabel = new  JLabel("Product Price");
		this.productExpDate = new JLabel("Exp. Date");
		
		this.companyAddressComboBox = new  JComboBox<String>();
		this.productQuantitiesComboBox = new  JComboBox<String>();
		this.categoryNameComboBox = new  JComboBox<String>();
		this.companyNameComboBox = new  JComboBox<String>();
		this.productNameComboBox = new  JComboBox<String>();
		this.companyIdComboBox = new  JComboBox<String>();
		this.productPriceComboBox = new  JComboBox<String>();
		
		//-----------
		UtilDateModel model = new UtilDateModel();
		model.setDate(2016,00,01);
		model.setSelected(true);
		
//		UtilCalendarModel model = new UtilCalendarModel();
//		SqlDateModel model = new SqlDateModel();
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	}
	/**
	 * Set components layout(how appearing on the screen)
	 */
	private void setLayout(){
		this.setLayout(new GridLayout(16,1));
        this.add(productNameLabel);
        this.add(productNameComboBox);     
        this.add(productQuantitiesLabel);
        this.add(productQuantitiesComboBox);    
        this.add(productPriceLabel);
        this.add(productPriceComboBox);     
        this.add(companyNameLabel);
        this.add(companyNameComboBox);    
        this.add(departmentNameLabel);
        this.add(categoryNameComboBox);
        this.add(departmentNumberLabel);
        this.add(companyIdComboBox);     
        this.add(companyAddressLabel);
        this.add(companyAddressComboBox);
        this.add(productExpDate);
        this.add(datePicker);		
	}
	/**
	 * Set labels font from this panel
	 */
	private void setLabelsFont(){
		final int fontSize=13;
		productNameLabel.setFont(new Font("TimesRoman", Font.BOLD,fontSize));
		productQuantitiesLabel.setFont(new Font("TimesRoman", Font.BOLD,fontSize));
		productPriceLabel.setFont(new Font("TimesRoman", Font.BOLD,fontSize));
		companyNameLabel.setFont(new Font("TimesRoman", Font.BOLD,fontSize));
		departmentNameLabel.setFont(new Font("TimesRoman", Font.BOLD,fontSize));
		departmentNumberLabel.setFont(new Font("TimesRoman", Font.BOLD,fontSize));
		companyAddressLabel.setFont(new Font("TimesRoman", Font.BOLD,fontSize));
		productExpDate.setFont(new Font("TimesRoman", Font.BOLD,fontSize));
	}
	/**
	 * Set all input fields editable
	 */
	private void setComboBoxesEditable(){
		companyAddressComboBox.setEditable(true);
		productQuantitiesComboBox.setEditable(true);
		categoryNameComboBox.setEditable(true);
		companyNameComboBox.setEditable(true);
		productNameComboBox.setEditable(true);
		companyIdComboBox.setEditable(true);
		productPriceComboBox.setEditable(true);
	}
	/**
	 * Set components color , including this panel
	 */
	private void setColor(){
    /*	this.setBackground(new Color(76, 175, 80));
		textFieldAdrIntr.getEditor().getEditorComponent().setBackground(new Color(76, 175, 80));
	    textFieldCantitate.getEditor().getEditorComponent().setBackground(new Color(76, 175, 80));
	    textFieldDenDep.getEditor().getEditorComponent().setBackground(new Color(76, 175, 80));
	    textFieldDenIntr.getEditor().getEditorComponent().setBackground(new Color(76, 175, 80));
	    textFieldDenProd.getEditor().getEditorComponent().setBackground(new Color(76, 175, 80));
	    textFieldNrDep.getEditor().getEditorComponent().setBackground(new Color(76, 175, 80));
	    textFieldPretProd.getEditor().getEditorComponent().setBackground(new Color(76, 175, 80));
	   */ 
	}
	/**
	 * This method is used to reset all input fields for a product
	 */
	public void resetTextFields()
	{
		companyAddressComboBox.setSelectedIndex(-1);
	    productQuantitiesComboBox.setSelectedIndex(-1);
	    categoryNameComboBox.setSelectedIndex(-1);
	    companyNameComboBox.setSelectedIndex(-1);
	    productNameComboBox.setSelectedIndex(-1);
	    companyIdComboBox.setSelectedIndex(-1);
	    productPriceComboBox.setSelectedIndex(-1);
	}
	/**
	 * Set a model for the company address combo box
	 * @param v -  a Vector of company addresses
	 */
	private void setModelCompanyAddress(final Vector<String> v){
		companyAddressComboBox.setModel(new DefaultComboBoxModel<String>(v));
        new AutocompletePack(companyAddressComboBox);
	}
	/**
	 * Set a model for the category combo box
	 * @param v -  a Vector of category names
	 */
	private void setModelCategoryName(final Vector<String> v){
		categoryNameComboBox.setModel(new DefaultComboBoxModel<String>(v));
        new AutocompletePack(categoryNameComboBox);
	}
	/**
	 * Set a model for the company name combo box
	 * @param v -  a Vector of company names
	 */
	private void setModelCompanyName(final Vector<String> v){
		companyNameComboBox.setModel(new DefaultComboBoxModel<String>(v));
        new AutocompletePack(companyNameComboBox);
	}
	/**
	 * Set a model for the product name combo box
	 * @param v -  a Vector of product names
	 */
	private void setModelProductName(final Vector<String> v){
		productNameComboBox.setModel(new DefaultComboBoxModel<String>(v));
        new AutocompletePack(productNameComboBox);
	}
	/**
	 * Set a model for the  department number combo box
	 * @param v -  a Vector of department numbers
	 */
	private void setModelCategoryId(final Vector<String> v){
		companyIdComboBox.setModel(new DefaultComboBoxModel<String>(v));
        new AutocompletePack(companyIdComboBox);
	}
	/**
	 * Set model for all input combo boxes
	 * @param products - product list
	 */
	public void setModel(ArrayList<Product> products){
		ListIterator<Product> iter = products.listIterator();
		Vector<String> lCompAddress = new Vector<String>();
		Vector<String> lDepName = new Vector<String>();
		Vector<String> lCompName = new Vector<String>();
		Vector<String> lProdName = new Vector<String>();
		Vector<String> lDepNr = new Vector<String>();
		
		lProdName.add("");
		lDepName.add("");
		lDepNr.add("");
		lCompName.add("");
		lCompAddress.add("");
		
		while(iter.hasNext()){
			Product p = new Product(iter.next());
			if(!lProdName.contains(p.getProdName()))
				lProdName.add(p.getProdName());
			if(!lDepName.contains(p.getCategory().getCategoryName()))
				lDepName.add(p.getCategory().getCategoryName());
			if(!lDepNr.contains(p.getCategory().getCategoryId().toString()))
				lDepNr.add(p.getCategory().getCategoryId().toString());
			if(!lCompName.contains(p.getCategory().getCompany().getCompanyName()))
				lCompName.add(p.getCategory().getCompany().getCompanyName());
			if(!lCompAddress.contains(p.getCategory().getCompany().getCompanyAddress()))
				lCompAddress.add(p.getCategory().getCompany().getCompanyAddress());
		}

		setModelCompanyAddress(lCompAddress);
		setModelCompanyName(lCompName);
		setModelProductName(lProdName);
		setModelCategoryName(lDepName);
		setModelCategoryId(lDepNr);
	}
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * Update editor combo boxes model when product list has change
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1) {

		if (arg1 instanceof ArrayList<?>) {
			ArrayList<Product> nullList= new ArrayList<Product>();
			this.setModel(nullList);
			this.setModel((ArrayList<Product>) arg1);
		}else {
			System.out.println("NameObserver: Some other change to subject!");
		}
		
	}
	/**
	 * Company address combo box SETTER
	 * @param companyAdress
	 */
	public void setTextFieldCompanyAddress(String companyAdress) {
		this.companyAddressComboBox.setSelectedItem(companyAdress);
	}
	/**
	 * Product quantity combo box SETTER
	 * @param productQuantities
	 */
	public void setTextFieldProductQuantity(String productQuantities) {
		this.productQuantitiesComboBox.setSelectedItem(productQuantities);
	}
	/**
	 * Department name combo box SETTER
	 * @param departmentName
	 */
	public void setTextFieldDepartmentName(String departmentName) {
		this.categoryNameComboBox.setSelectedItem(departmentName);
	}
	/**
	 * Company name combo box SETTER
	 * @param companyName
	 */
	public void setTextFieldCompanyName(String companyName) {
		this.companyNameComboBox.setSelectedItem(companyName);
	}
	/**
	 * Product name combo box SETTER
	 * @param prodName
	 */
	public void setTextFieldProdName(String prodName) {
		this.productNameComboBox.setSelectedItem(prodName);
	}
	/**
	 * Department number combo box SETTER
	 * @param depNumber
	 */
	public void setTextFieldDepNumber(String depNumber) {
		this.companyIdComboBox.setSelectedItem(depNumber);
	}
	/**
	 * Product price combo box SETTER
	 * @param textFieldPretProd
	 */
	public void setTextFieldProdPrice(String textFieldPretProd) {
		this.productPriceComboBox.setSelectedItem(textFieldPretProd);
	}
	/**
	 * Product exp. date combo box SETTER
	 * @param expDate
	 */
	public void setExpDate(String expDate) {
		String [] vect = expDate.split("-");
		((UtilDateModel)datePicker.getModel()).setDate(Integer.parseInt(vect[0]),Integer.parseInt(vect[1])-1,Integer.parseInt(vect[2]));
	}
	/**
	 * Company address combo box text GETTER
	 * @return company address combo box text
	 */
	public String getCompanyAddress(){
		return companyAddressComboBox.getEditor().getItem().toString();
	}
	/**
	 * Product price combo box text GETTER
	 * @return product price combo box text
	 */
	public int getProdQuantities(){
		return Integer.parseInt(productQuantitiesComboBox.getEditor().getItem().toString());
	}
	/**
	 * Department name combo box text GETTER
	 * @return department name combo box text
	 */
	public String getDepName(){
		return categoryNameComboBox.getEditor().getItem().toString();
	}
	/**
	 * Company name combo box text GETTER
	 * @return company name combo box text
	 */
	public String getCompanyName(){
		return companyNameComboBox.getEditor().getItem().toString();
	}
	/**
	 * Product name combo box text GETTER
	 * @return product name name combo box text
	 */
	public String getProdName(){
		return productNameComboBox.getEditor().getItem().toString();
	}
	/**
	 * Department number combo box text GETTER
	 * @return department number combo box text
	 */
	public int getDepNumber(){
		return Integer.parseInt(companyIdComboBox.getEditor().getItem().toString());
	}
	/**
	 * Product price combo box text GETTER
	 * @return product price combo box text
	 */
	public Float getProdPrice(){
		return Float.parseFloat(productPriceComboBox.getEditor().getItem().toString());
	}
	/**
	 * Category number combo box text GETTER
	 * @return Category number combo box text
	 */
	@SuppressWarnings("deprecation")
	public Date getExpDate(){
		int day = datePicker.getModel().getDay();
		int month = datePicker.getModel().getMonth();
		int year = datePicker.getModel().getYear();	
		return new Date(year-1900,month,day);
	}
}
