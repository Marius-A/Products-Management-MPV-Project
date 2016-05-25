package productManagementModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import helper.sqlDatabase.SqlProductsHandler;
import helper.xml.XmlProductsHandler;
import entities.productEntities.Product;


/**
 * @author Marius Iliescu
 *
 */
public class ProductsModel extends Observable implements Observer{
	private SqlProductsHandler sqlDatabase;
	private XmlProductsHandler xmlDatabase;
	
	private ArrayList<Product> productList;

	/**
	 * ProductsModel copy constructor
	 * @param productsList
	 */
	public ProductsModel(ArrayList<Product> productsList) {
		super();
		this.productList = productsList;
	}
	/**
	 * ProductsModel implicit constructor
	 * -init product list taking data from sql/xml database
	 */
	public ProductsModel(){
		initDatabase();
	}
	/**
	 * Product list initializer
	 */
	private void initDatabase(){
		initDatabaseFromSQL();
		//initDatabaseFromXML();
	}
	/**
	 * Product list initializer - take data from SQL database
	 */
	private void initDatabaseFromSQL(){
		this.sqlDatabase = new SqlProductsHandler();
		this.productList=sqlDatabase.getDatabase();
	}
	/**
	 * Product list initializer - take data from XML database
	 */
	@SuppressWarnings("unused")
	private void initDatabaseFromXML(){
		xmlDatabase = new XmlProductsHandler();
		this.productList = xmlDatabase.getData();
	}
	/**
	 * Write an XML that contains the product list
	 * @param products
	 */
	public void writeDatabaseFromXml(ArrayList<Product> products){
		xmlDatabase.writeXML(products);
	}
	/**
	 * Add a product to the database
	 * @param index
	 * @param p
	 */
	public void addProduct(int index ,Product p){
    	productList.add(index, p);
    	sqlDatabase.addProdToDatabase(p);
		setChanged();
		notifyObservers(productList);
	}
	/**
	 * Delete a product from database
	 * @param indexProd
	 * @param p
	 */
	public void delProduct(int indexProd , Product p){
		productList.remove(indexProd);
		sqlDatabase.delProdFromDatabase(p);
		setChanged();
		notifyObservers(productList);
	}
	/**
	 * Update a product from database
	 * @param indexProd
	 * @param pNew
	 * @param pSel
	 */
	public void updateProdus(int indexProd , Product pNew , Product pSel){
		delProduct(indexProd, pSel);
		addProduct(indexProd,pNew);
		//Collections.sort(listaProduse);
	}
	/**
	 * Product list getter
	 * @return the product list
	 */
	public ArrayList<Product> getProductsList() {
		return productList;
	}
	/**
	 * Product list setter
	 * @param productsList
	 */
	public void setListaProduse(ArrayList<Product> productsList) {
		this.productList = productsList;
	}	
	/**
	 * This method help close the database , when main frame is closed
	 */
	public void closeDatabase(){
		sqlDatabase.closeDatabase();	
	}
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof ArrayList<?>){
			for(int i=0;i< ((ArrayList<Product>)arg).size();i++){
				this.addProduct(0,((ArrayList<Product>)arg).get(i));
			}
		}
		
	}
}
