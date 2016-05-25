package helper.transformer;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import net.sf.saxon.TransformerFactoryImpl;


public class XmlTransformer {


	private static Transformer getTransformer(StreamSource streamSource)
	{
		// setting xslt transformer
		TransformerFactoryImpl implementation = new TransformerFactoryImpl();
		try {
			return implementation.newTransformer(streamSource);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void makePdf() {
		
		Properties propXsl = new Properties();
		Properties propXml = new Properties();
    	InputStream inStrXsl = getClass().getResourceAsStream("XmlTransformConfig.properties");
    	InputStream inStrXml = getClass().getResourceAsStream("../xml/XmlDBConfig.properties");
        try {
			propXsl.load(inStrXsl);
			propXml.load(inStrXml);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	try {
    		inStrXsl.close();
    		inStrXml.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		final String xslPdfPath = propXsl.getProperty("xsl.Path");
		final String xmlPath = propXml.getProperty("xml.Path");
		final String pdfPath = propXsl.getProperty("out.Pdf.path");
		
    	final String xslFileName = xslPdfPath.concat(propXsl.getProperty("xsl.Pdf.name"));
    	final String xmlFileName = xmlPath.concat(propXml.getProperty("xml.name"));
    	final String pdfFileName = pdfPath.concat(propXsl.getProperty("Pdf.name")); 
    	
		//the files we will use
		File xsltFile = new File(xslFileName);
		File xmlFile = new File(xmlFileName);

		//the stream that will be transformed and the stream that will transform it.
		StreamSource source = new StreamSource(xmlFile);
		StreamSource transformSource = new StreamSource(xsltFile);

		FopFactory fopFactory = FopFactory.newInstance();

		//outStream will contain the xml formated data that will be used to create the pdf file
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Transformer xslfoTransformer = getTransformer(transformSource);

		try
		{

			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF,outStream);
			Result res = new SAXResult( fop.getDefaultHandler());

			xslfoTransformer.transform(source, res);

			//output file that will contain the result of the transformation
			File pdfFile = new File(pdfFileName);

			//writing the data
			FileOutputStream str = new FileOutputStream(pdfFile);
			str.write(outStream.toByteArray());

			//closing stream
			str.close();

		}catch (TransformerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (FOPException e) {
			e.printStackTrace();
		}
		try {
			Desktop.getDesktop().open(new File(pdfFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void makeHTML() {
		
		Properties propXsl = new Properties();
		Properties propXml = new Properties();
    	InputStream inStrXsl = getClass().getResourceAsStream("XmlTransformConfig.properties");
    	InputStream inStrXml = getClass().getResourceAsStream("../xml/XmlDBConfig.properties");
        try {
			propXsl.load(inStrXsl);
			propXml.load(inStrXml);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	try {
    		inStrXsl.close();
    		inStrXml.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		final String xslHtmlPath = propXsl.getProperty("xsl.Path");
		final String xmlPath = propXml.getProperty("xml.Path");
		final String htmlPath = propXsl.getProperty("out.Html.path");
		
    	final String xslFileName = xslHtmlPath.concat(propXsl.getProperty("xsl.HTML.name"));
    	final String xmlFileName = xmlPath.concat(propXml.getProperty("xml.name"));
    	final String htmlFileName = htmlPath.concat(propXsl.getProperty("HTML.name")); 
    	
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer trans = factory.newTransformer(new StreamSource(xslFileName));
			StreamSource sursa = new StreamSource(xmlFileName);

			//the result
			StreamResult result = new StreamResult(htmlFileName);

			//the transformation
			trans.transform(sursa, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		try {
			Desktop.getDesktop().open(new File(htmlFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
