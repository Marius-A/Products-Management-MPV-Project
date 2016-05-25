package helper.comparator;

import entities.productEntities.Product;

public class NumeProdusComparatorAsc extends ListComparator {

	@Override
	public int compare(Product o1, Product o2) {
		 return (o1.getProdName().compareTo(o2.getProdName()));
	}

}
