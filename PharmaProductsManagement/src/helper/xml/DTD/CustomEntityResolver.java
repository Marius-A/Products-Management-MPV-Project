package helper.xml.DTD;
import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class CustomEntityResolver implements EntityResolver{

	@Override
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		// TODO Auto-generated method stub

		//publicId - The public identifier of the external entity being referenced, or null if none was supplied.
		//systemId - The system identifier of the external entity being referenced.
		System.out.println("-----Resolving Entity--------");
		
		System.out.println("\n publicId = "+publicId+"\n systemId = "+systemId);
		System.out.println("----------------------");
		return new InputSource(systemId);
		
		//the InputSource can be changed. Eg:
		//return new InputSource("str2.DTD");
		
	}

}
