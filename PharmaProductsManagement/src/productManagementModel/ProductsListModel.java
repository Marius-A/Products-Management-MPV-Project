package productManagementModel;

import java.util.ArrayList;

import helper.xml.XmlProductsHandler;
import entities.productEntities.Product;

public class ProductsListModel {
	private XmlProductsHandler xmlDatabase;	
	private ArrayList<Product> productList;

	public ProductsListModel() {
		super();
		this.xmlDatabase = new XmlProductsHandler();
		this.productList = new ArrayList<Product>();
	}
	public void initList(){
		productList=xmlDatabase.getData();
	}
	public ArrayList<Product> getProductsList() {
		return productList;
	}
	
}
