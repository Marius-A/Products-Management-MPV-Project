package entities.productEntities;

/**
 * @author Marius Iliescu
 *
 */
public class Company {
	private String companyName ;
	private String companyAddress;
	
	
	/**
	 * General constructor
	 * @param denIntreprindere
	 * @param adresa
	 */
	public Company(String denIntreprindere, String adresa) {
		super();
		this.companyName = denIntreprindere;
		this.companyAddress = adresa;
	}
	/**
	 * Copy constructor
	 * @param company
	 */
	public Company(Company company){
		this.companyName = company.companyName;
		this.companyAddress= company.companyAddress;	
	}
	public Company() {
		this.companyName = new String();
		this.companyAddress= new String();
	}
	/**
	 * Company name getter
	 * @return company Name
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * Company name setter
	 * @param companyName 
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * Company address getter
	 * @return company address
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}
	/**
	 * Company address setter
	 * @param companyAdress
	 */
	public void setCompanyAddress(String companyAdress) {
		this.companyAddress = companyAdress;
	}

	
}
