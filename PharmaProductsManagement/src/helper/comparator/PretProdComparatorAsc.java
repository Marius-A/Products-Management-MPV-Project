package helper.comparator;

import entities.productEntities.Product;

public class PretProdComparatorAsc extends ListComparator {

	@Override
	public int compare(Product o1, Product o2) {
		 return (o1.getProdPrice()).compareTo(o2.getProdPrice());
	}

}
