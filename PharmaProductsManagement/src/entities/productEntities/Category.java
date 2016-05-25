package entities.productEntities;

/**
 * @author Marius Iliescu
 *
 */
public class Category {
	private Integer categoryId;
	private String categoryName ;
	private Company company;

	/**
	 * Department general constructor
	 * @param no
	 * @param name
	 * @param comp
	 */
	public Category(Integer no, String name ,Company comp ){
		this.categoryId= no;
		this.categoryName = name;
		this.company = comp;
	}
	/**
	 * Department copy constructor
	 * @param d
	 */
	public Category(Category d){
		this.categoryId = d.categoryId;
		this.categoryName = d.categoryName;
		this.company = d.company;
	}
	public Category() {
		this.categoryId = 0;
		this.categoryName = new String();
		this.company = new Company();
	}
	/**
	 * Category id getter
	 * @return company id
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * Category id setter
	 * @param categoryId
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * Category name getter
	 * @return Category name
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * Category name setter
	 * @param categoryName
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * Company getter
	 * @return company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * Company setter
	 * @param company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
}
