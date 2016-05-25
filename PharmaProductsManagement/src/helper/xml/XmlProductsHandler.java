package helper.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import helper.xml.DTD.CustomDTDHandler;
import helper.xml.DTD.CustomEntityResolver;
import helper.xml.DTD.CustomErrorHandler;
import entities.productEntities.Product;

public class XmlProductsHandler{
	ProductsXMLReader saxReader;
	ProductsXMLWriter saxWriter;
	
	static CustomErrorHandler errorHandler;
	static CustomEntityResolver entityResolver;
	static CustomDTDHandler dtdHandler;
	
	public XmlProductsHandler(){
		saxReader=new ProductsXMLReader();
		saxWriter = new ProductsXMLWriter();
		
		errorHandler= new CustomErrorHandler();
		entityResolver = new CustomEntityResolver();
		dtdHandler = new CustomDTDHandler();
	}
	public void writeXML(ArrayList<Product> products){
		saxWriter.writeXML(products);
	}
	public ArrayList<Product> getData(){
		ArrayList<Product> productsDB = null;
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		
		
		//validate the XML document using the specified DTD
		factory.setValidating(true);
		
		factory.setNamespaceAware(true);
		
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
		
    	final String databaseName = props.getProperty("xml.name.forList");
    	final String fileXML = props.getProperty("xml.Path").concat(databaseName);
		
		//parse the xml file
		try {
			parser = factory.newSAXParser();
			//obtaining an XMLReader object from a SAXParser object
			XMLReader xmlReader = parser.getXMLReader();
			
			xmlReader.setErrorHandler(errorHandler);
			xmlReader.setEntityResolver(entityResolver);
			xmlReader.setDTDHandler(dtdHandler);
			
			//xmlReader.parse(fileXML);
			xmlReader.parse(fileXML);
			
			if(!errorHandler.isError()){
				parser.parse(fileXML,saxReader);
				productsDB = saxReader.GetArray();
				System.out.println("\n\tThe parsing went OK!");
			}

				
			
		} catch(SAXException se) {
			System.err.println("SAXException:\n" + se.getMessage());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return productsDB;
	}
}
