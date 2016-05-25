package helper.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Properties;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import entities.productEntities.Product;

public class ProductsXMLWriter {
	
	public ProductsXMLWriter(){
		//
	}
	public void writeXML(ArrayList<Product> produse){
		XMLOutputFactory XMLof = null;
		XMLStreamWriter XMLsw = null;
		
	 	Properties props = new Properties();
    	InputStream instr = getClass().getResourceAsStream("XmlDBConfig.properties");
        try {
			props.load(instr);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	try {
			instr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	final String databaseName = props.getProperty("xml.name");
    	final String fileXML = props.getProperty("xml.Path").concat(databaseName);
    	
		//instantiate class XMLOutputFactory
		XMLof = XMLOutputFactory.newInstance();
		try{
			XMLsw = XMLof.createXMLStreamWriter(new FileWriter(fileXML));
			
			XMLsw.writeDTD(props.getProperty("xml.version"));
			XMLsw.writeCharacters("\n");
			XMLsw.writeDTD(props.getProperty("xml.doctype"));
			XMLsw.writeCharacters("\n");
			XMLsw.writeStartElement("Products");
			XMLsw.writeCharacters("\n ");
			
			ListIterator<Product> iter = produse.listIterator();
			Integer i = 0;
			while (iter.hasNext())
			{
				Product p = new Product(iter.next());
				
				XMLsw.writeStartElement("Product");
				XMLsw.writeAttribute("id", i.toString());
				XMLsw.writeCharacters("\n	");
				
				XMLsw.writeStartElement("ProductName");
				XMLsw.writeCharacters(p.getProdName());
				XMLsw.writeEndElement();
				XMLsw.writeCharacters("\n	");
				
				XMLsw.writeStartElement("ProductPrice");
				XMLsw.writeCharacters(p.getProdPrice().toString());
				XMLsw.writeEndElement();
				XMLsw.writeCharacters("\n	");
				
				XMLsw.writeStartElement("ProductQuantity");
				XMLsw.writeCharacters(p.getQuantity().toString());
				XMLsw.writeEndElement();
				XMLsw.writeCharacters("\n	");
				
				XMLsw.writeStartElement("ExpDate");
				XMLsw.writeCharacters(p.getExpDate().toString());
				XMLsw.writeEndElement();
				XMLsw.writeCharacters("\n	");
				
				XMLsw.writeStartElement("Category");
				XMLsw.writeCharacters("\n		");
				
				XMLsw.writeStartElement("CategoryId");
				XMLsw.writeCharacters(p.getCategory().getCategoryId().toString());
				XMLsw.writeEndElement();
				XMLsw.writeCharacters("\n		");
				
				XMLsw.writeStartElement("CategoryName");
				XMLsw.writeCharacters(p.getCategory().getCategoryName());
				XMLsw.writeEndElement();
				XMLsw.writeCharacters("\n		");
				
				XMLsw.writeStartElement("Company");
				XMLsw.writeCharacters("\n			");
				
				XMLsw.writeStartElement("CompanyName");
				XMLsw.writeCharacters(p.getCategory().getCompany().getCompanyName());
				XMLsw.writeEndElement();
				XMLsw.writeCharacters("\n			");
				
				XMLsw.writeStartElement("CompanyAddress");
				XMLsw.writeCharacters(p.getCategory().getCompany().getCompanyAddress());
				XMLsw.writeEndElement();
				XMLsw.writeCharacters("\n		");
				
				XMLsw.writeEndElement();//end company
				XMLsw.writeCharacters("\n	");
				
				XMLsw.writeEndElement();//end department
				XMLsw.writeCharacters("\n ");
				
				XMLsw.writeEndElement();//end product
				XMLsw.writeCharacters("\n ");
				
				i++;
				
			}
			//end writing
			XMLsw.writeEndElement();
			XMLsw.writeEndDocument();
			XMLsw.close();
		} catch(java.io.IOException e) {
			System.out.println(e.getMessage());
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

}
