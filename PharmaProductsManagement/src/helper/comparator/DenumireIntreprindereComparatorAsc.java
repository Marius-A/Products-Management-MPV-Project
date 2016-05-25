package helper.comparator;

import entities.productEntities.Product;

public class DenumireIntreprindereComparatorAsc extends ListComparator {

	@Override
	public int compare(Product o1, Product o2) {
		 return -(o1.getCategory().getCompany().getCompanyName().compareTo(o2.getCategory().getCompany().getCompanyName()));
	}

}
