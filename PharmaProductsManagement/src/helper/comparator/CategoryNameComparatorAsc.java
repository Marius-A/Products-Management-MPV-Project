package helper.comparator;

import entities.productEntities.Product;

public class CategoryNameComparatorAsc extends ListComparator {

	@Override
	public int compare(Product o1, Product o2) {
		 return (o1.getCategory().getCategoryName().compareTo(o2.getCategory().getCategoryName()));
	}

}
