package helper.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import entities.productEntities.Company;
import entities.productEntities.Category;
import entities.productEntities.Product;

public class ProductsXMLReader  extends DefaultHandler {
	private ArrayList<Product> products;
	private Company company;
	private Category dep;
	private String prodName,catName, compName , compAddress;
	private Integer quantity, catId;
	private Float price;
	private Date expDate;
	private int flag;
	
	public  ProductsXMLReader(){
		this.products = new ArrayList<Product>();
		this.flag=0;
		this.prodName = new String();
		this.catName = new String();
		this.compName = new String();
		this.compAddress = new String();
		this.quantity=0;
		this.catId = 0;
		this.expDate = new Date();
		this.price = new Float(0);
	}
	public void startDocument() throws SAXException {
		System.out.println("Begin Parsing");
	}
	// Receive notification of the start of an element.
	public void startElement(String ns,
			String local,
			String name,
			org.xml.sax.Attributes attributes)
					throws SAXException {
		if(name.equals("ProductName")) 
			flag = 1; 
		else
			if(name.equals("ProductPrice"))
				flag = 2;
			else
				if(name.equals("ProductQuantity"))
					flag = 3;
				else
					if(name.equals("ExpDate"))
						flag = 4;
					else
						if(name.equals("CategoryId"))
							flag = 5;
						else
							if(name.equals("CategoryName"))
								flag = 6;
							else
								if(name.equals("CompanyName"))
									flag = 7;
								else
									if(name.equals("CompanyAddress"))
										flag = 8;

	}
	//  Receive notification of character data inside an element.
	public void characters(char[] ch, int start, int length) throws SAXException {
		try{	
			switch (flag) {
			case 1: {
				prodName = new String(ch,start,length);
				flag=0;
				}break;
				
			case 2: {
				String tmpP = new String(ch,start,length);
				price = Float.parseFloat(tmpP);
				flag=0;
				}break;

			case 3: {
				String tmpC = new String(ch,start,length);
				quantity = Integer.parseInt(tmpC);
				flag=0;
			}break;
			
			case 4: {
				//
				String tmpNd = new String(ch,start,length);
				SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					expDate = newDateFormat.parse(tmpNd);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				flag=0;
			}break;
			
			case 5: {
				String tmpNd = new String(ch,start,length);
				catId = Integer.parseInt(tmpNd); 
				flag=0;
			}break;
			
			case 6: {
				catName = new String(ch,start,length);
				flag=0;
			}break;
			
			case 7: {
				compName = new String(ch,start,length);
				flag=0;
			}break;
			
			case 8: {
				compAddress = new String(ch,start,length);
				flag=0;
			}break;
			}

		} catch(NumberFormatException e) {
			System.err.println(e.getMessage());

		} catch(NullPointerException e) {
			System.err.println(e.getMessage());
		} 

	}
	//  Receive notification of the end of an element.
	public void endElement(String ns, String local, String name) throws SAXException {
		if(name.equals("Company"))
			company = new Company(compName, compAddress);
		if(name.equals("Category"))
			dep = new Category(catId, catName, company);
		if (name.equals("Product")){
			products.add(new Product(prodName, price, quantity, dep ,expDate) );
		}
	}
	public ArrayList<Product> GetArray() {
		return this.products;
	}
}
