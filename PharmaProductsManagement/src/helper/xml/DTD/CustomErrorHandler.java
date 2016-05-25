package helper.xml.DTD;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class CustomErrorHandler implements ErrorHandler{
	boolean myError = false;

	@Override
	public void error(SAXParseException e) throws SAXException {
		System.err.println("Eroare : " + e.getMessage());
		myError = true;
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.err.println("Fatal Error: " + e.getMessage());
		System.exit(1);
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.err.println("Warning : " + e.getMessage());
		myError = true;
	}
	public boolean isError() {
		return myError;
	}
	//public void handleError
}
