package entities.productEntities;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Marius Iliescu
 *
 */
public class Product implements Comparable<Product> , Transferable{
	private String prodName;
	private Float prodPrice;
	private Integer prodQuantity;
	private Category category;
	private Date expirationDate;
	
	/**
	 * Product general constructor
	 * @param name
	 * @param price
	 * @param quantitie
	 * @param cat
	 * @param expDate
	 */
	public Product(String name, Float price, Integer quantitie, Category cat , Date expDate) {
		super();
		this.prodName = name;
		this.prodPrice = price;
		this.prodQuantity = quantitie;
		this.category = cat;
		this.expirationDate = expDate;
	}
	/**
	 * Product copy constructor
	 * @param p
	 */
	public Product(Product p){
		this.prodName=p.prodName;
		this.prodPrice = p.prodPrice;
		this.prodQuantity = p.prodQuantity;
		this.category =p.category;
		this.expirationDate = p.expirationDate;
	}
	public Product() {
		this.prodName= new String();
		this.prodPrice = (float) 0;
		this.prodQuantity = 0;
		this.category = new Category();
	}
	/**
	 * Product name getter
	 * @return product name
	 */
	public String getProdName() {
		return prodName;
	}
	/**
	 * Product Category getter
	 * @return product Category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * Product Category setter
	 * @param cat
	 */
	public void setDep(Category cat) {
		this.category = cat;
	}
	/**
	 * Product name setter
	 * @param prodName
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	/**
	 * Product price getter
	 * @return product price
	 */
	public Float getProdPrice() {
		return prodPrice;
	}
	/**
	 * Product price setter
	 * @param price
	 */
	public void setProdPrice(Float price) {
		this.prodPrice = price;
	}
	/**
	 * Product quantity getter
	 * @return product quantity
	 */
	public Integer getQuantity() {
		return prodQuantity;
	}
	/**
	 * Product quantity setter
	 * @param quantity
	 */
	public void setQuantitie(Integer quantity) {
		this.prodQuantity = quantity;
	}
	/**
	 * Product expiration Date getter
	 * @return product expiration Date
	 */
	public Date getExpDate() {
		return expirationDate;
	}
	/**
	 * Product expiration Date setter
	 * @param expirationDate
	 */
	public void setExpDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Product toString method override
	 */
	@Override
	public String toString() {
		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = sm.format(expirationDate);
		return prodName + " / "+ prodPrice + " / "+  prodQuantity + " / "+ category.getCompany().getCompanyName() +" / "+ category.getCategoryName() +" / " + category.getCategoryId() +" / " + category.getCompany().getCompanyAddress()+" / "+strDate+"";
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * Product compareTo method overide
	 */
	@Override
	public int compareTo(Product o) {
        String compareDenProd=((Product)o).getProdName();
        /* For Ascending order*/
        return this.prodName.compareTo(compareDenProd);
	}
	/**
	 * Product contains method
	 * @param string
	 * @return 
	 */
	public boolean contains(String string) {
		return this.toString().contains(string);
	}
	/**
	 * @param s
	 * @return
	 */
	public String[] split(String s){
		return this.toString().split(s);
	}
	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		DataFlavor prodDataFlavor = new DataFlavor(Product.class,Product.class.getSimpleName());
		if (flavor.equals(prodDataFlavor))
			return this;
		else
			throw new UnsupportedFlavorException(flavor);
	}
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor prodDataFlavor = new DataFlavor(Product.class,Product.class.getSimpleName());
		return new DataFlavor[] { prodDataFlavor };
	}
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		DataFlavor prodDataFlavor = new DataFlavor(Product.class,Product.class.getSimpleName());
		return flavor.equals(prodDataFlavor);
	}
}
