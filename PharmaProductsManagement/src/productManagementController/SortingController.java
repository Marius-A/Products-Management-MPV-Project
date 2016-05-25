package productManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import helper.comparator.*;
import entities.productEntities.Product;
import productManagementModel.ProductsModel;
import view.productManagementView.ProductManagementView;

public class SortingController {
	public SortingController(final ProductsModel productsModel,final ProductManagementView productManagementView){
		class OrderBySelectListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String orderBy = productManagementView.getSearchPanel().getSelectedSortingOrder();
				ArrayList<Product> productsList = new ArrayList<Product>(productsModel.getProductsList());
				
				ListComparator comp = null;
				
				ProductsModel tmpModel = new ProductsModel(productsList);
				productManagementView.getTablePanel().setNewTableModel(tmpModel);	
				
				if(orderBy.equals("Order by ...")){
					//DO NOTHING
					return;
				}else if(orderBy.equals("Product Name - Asc")){
					comp = new NumeProdusComparatorAsc();
					Collections.sort(productsList, comp);	
					productManagementView.getTablePanel().setNewTableModel(tmpModel);	
				}else if(orderBy.equals("Product Name - Desc")){
					comp = new  NumeProdusComparatorDesc();
					Collections.sort(productsList, comp);
					productManagementView.getTablePanel().setNewTableModel(tmpModel);	
				}else if(orderBy.equals("Company Name - Asc")){
					comp = new DenumireIntreprindereComparatorAsc();
					Collections.sort(productsList, comp);	
					productManagementView.getTablePanel().setNewTableModel(tmpModel);	
				}else if(orderBy.equals("Company Name - Desc")){
					comp = new DenumireIntreprindereComparatorDesc();
					Collections.sort(productsList, comp);	
					productManagementView.getTablePanel().setNewTableModel(tmpModel);	
				}else if(orderBy.equals("Category id - Asc")){
					comp = new NumarDepartamentComparatorAsc();
					Collections.sort(productsList, comp);	
					productManagementView.getTablePanel().setNewTableModel(tmpModel);	
				}else if(orderBy.equals("Category id - Desc")){
					comp = new NumarDepartamentComparatorDesc();
					Collections.sort(productsList, comp);	
					productManagementView.getTablePanel().setNewTableModel(tmpModel);	
				}else if(orderBy.equals("Category Name - Asc")){
					comp = new CategoryNameComparatorAsc();
					Collections.sort(productsList, comp);	
					productManagementView.getTablePanel().setNewTableModel(tmpModel);	
				}else if(orderBy.equals("Category Name - Desc")){
					comp = new CategoryNameComparatorDesc();
					Collections.sort(productsList, comp);	
					productManagementView.getTablePanel().setNewTableModel(tmpModel);	
				}else if(orderBy.equals("Product Price - Asc")){
					comp = new PretProdComparatorAsc();
					Collections.sort(productsList, comp);	
					productManagementView.getTablePanel().setNewTableModel(tmpModel);	
				}else if(orderBy.equals("Product Price - Desc")){
					comp = new PretProdComparatorDesc();
					Collections.sort(productsList, comp);	
					productManagementView.getTablePanel().setNewTableModel(tmpModel);	
				}
				productManagementView.getFilterPanel().resetCompaniesFilters();
				productManagementView.getFilterPanel().resetCategoryFilters();
				productManagementView.getFilterPanel().resetTextFields();
				productManagementView.getFilterPanel().resetSoldOutCheckbox();
				productManagementView.getFilterPanel().resetExpiredCheckbox();
				
			}
			
		}
		productManagementView.getSearchPanel().selectOrderListener(new OrderBySelectListener());
	}
}
