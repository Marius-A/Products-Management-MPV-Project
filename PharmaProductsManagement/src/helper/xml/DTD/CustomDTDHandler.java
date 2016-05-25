package helper.xml.DTD;
import org.xml.sax.DTDHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class CustomDTDHandler extends DefaultHandler implements DTDHandler {

	@Override
	public void notationDecl(String name, String id_public, String uri)
			throws SAXException {
		System.out.println("-----DTD Handler-----");
		System.out.println("Notation declaration:\n\t");
		System.out.println("Name ="+ name+ "id_public=" + id_public+ "URI = "+uri);
		System.out.println("----------");
	}

	@Override
	public void unparsedEntityDecl(String name, String id_public, String uri,
									String notationName) throws SAXException {
		System.out.println("-----DTD Handler -------");
		System.out.println("Unparsed Entity Declaration:\n\t ");
		System.out.println("Name = "+name+" id_public ="+id_public+" URI=" +uri+
						   " Notation Name ="+notationName);
		System.out.println("-----------");
	
		
	}
	

}
