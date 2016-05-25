package helper.comparator;

import entities.productEntities.Product;

public class NumarDepartamentComparatorAsc extends ListComparator {

	@Override
	public int compare(Product o1, Product o2) {
		 return (o1.getCategory().getCategoryId().compareTo(o2.getCategory().getCategoryId()));
	}

}
