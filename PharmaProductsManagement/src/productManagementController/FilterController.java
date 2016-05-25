package productManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ListIterator;
import java.util.Vector;

import entities.productEntities.Product;
import productManagementModel.ProductsModel;
import view.productManagementView.ProductManagementView;

public class FilterController {

	public FilterController(final ProductsModel productsModel,final ProductManagementView productManagementView) {
						
		class FilterButtonListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				Float pMin = (float) 0;
				Float pMax = (float) 999999;
				ArrayList<Product> tmpProducts = new ArrayList<Product>();

				try{
					pMin = productManagementView.getFilterPanel().getTextFieldFiltarrePret1();
				}catch(NumberFormatException ex){
					//ex.printStackTrace();
				}
				try{
					pMax = productManagementView.getFilterPanel().getTextFieldFiltarrePret2();
				}catch(NumberFormatException ex){
					//ex.printStackTrace();
				}
				
				ListIterator<Product> pIter = productsModel.getProductsList().listIterator();

				while(pIter.hasNext()){
					Product p = new Product(pIter.next());
					if((p.getProdPrice()>= pMin) &&(p.getProdPrice() <=pMax) ){
						tmpProducts.add(p);
					}
				}			
				ProductsModel tmpModel = new ProductsModel(tmpProducts);
				productManagementView.getTablePanel().setNewTableModel(tmpModel);	

				productManagementView.getFilterPanel().resetCompaniesFilters();
				productManagementView.getFilterPanel().resetCategoryFilters();
				productManagementView.getFilterPanel().resetSoldOutCheckbox();
			}
		
		}
		class SelectCategoryListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				
				Vector<String> selectedCategories = productManagementView.getFilterPanel().getSelectedCategories();
				ArrayList<Product> tmpProducts = new ArrayList<Product>();
				
				if(selectedCategories == null){
					productManagementView.getTablePanel().setNewTableModel(productsModel);
				}else{
					ListIterator<Product> pIter= productsModel.getProductsList().listIterator();
					
					while(pIter.hasNext()){
						Product p = new Product(pIter.next());
						for(String s : selectedCategories){
							if(p.getCategory().getCategoryName().equals(s)){
								tmpProducts.add(p);
							}
						}
					}
					
					ProductsModel tmpModel = new ProductsModel(tmpProducts);
					productManagementView.getTablePanel().setNewTableModel(tmpModel);
					productManagementView.getFilterPanel().resetTextFields();
					productManagementView.getFilterPanel().resetCompaniesFilters();
					productManagementView.getFilterPanel().resetSoldOutCheckbox();
					productManagementView.getFilterPanel().resetExpiredCheckbox();
				}	
			}	
		}
		class SelectCompanyListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				
				Vector<String> selectedCompanies = productManagementView.getFilterPanel().getSelectedCompanies();
				ArrayList<Product> tmpProducts = new ArrayList<Product>();
				
				if(selectedCompanies == null){
					productManagementView.getTablePanel().setNewTableModel(productsModel);
				}else{
					
					ListIterator<Product> pIter= productsModel.getProductsList().listIterator();
	
					
					while(pIter.hasNext()){
						Product p = new Product(pIter.next());
						for(String s : selectedCompanies){
							if(p.getCategory().getCompany().getCompanyName().equals(s)){
								tmpProducts.add(p);
							}
						}
					}	
					ProductsModel tmpModel = new ProductsModel(tmpProducts);
					productManagementView.getTablePanel().setNewTableModel(tmpModel);
					productManagementView.getFilterPanel().resetTextFields();
					productManagementView.getFilterPanel().resetCategoryFilters();
					productManagementView.getFilterPanel().resetSoldOutCheckbox();
					productManagementView.getFilterPanel().resetExpiredCheckbox();
				}			
			}	
		}
		class SelectSoldOutListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Product> tmpProducts = new ArrayList<Product>();
				ListIterator<Product> pIter= productsModel.getProductsList().listIterator();
				if(productManagementView.getFilterPanel().getSoldOutFilter() == false){
					productManagementView.getTablePanel().setNewTableModel(productsModel);
				}else{
					while(pIter.hasNext()){
						Product p = new Product(pIter.next());
						if(p.getQuantity() == 0){
							tmpProducts.add(p);
						}
					}	
					ProductsModel tmpModel = new ProductsModel(tmpProducts);
					productManagementView.getTablePanel().setNewTableModel(tmpModel);
					productManagementView.getFilterPanel().resetTextFields();
					productManagementView.getFilterPanel().resetCategoryFilters();
					productManagementView.getFilterPanel().resetCompaniesFilters();
					productManagementView.getFilterPanel().resetExpiredCheckbox();
				}
			}
			
		}
		class SelectExpProdListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Product> tmpProducts = new ArrayList<Product>();
				Calendar cal = new GregorianCalendar();
				ListIterator<Product> pIter= productsModel.getProductsList().listIterator();
				if(productManagementView.getFilterPanel().getExpiredFilter() == false){
					productManagementView.getTablePanel().setNewTableModel(productsModel);
				}else{
					while(pIter.hasNext()){
						Product p = new Product(pIter.next());
						if(p.getExpDate().compareTo(cal.getTime()) < 0){
							tmpProducts.add(p);
						}
					}	
					ProductsModel tmpModel = new ProductsModel(tmpProducts);
					productManagementView.getTablePanel().setNewTableModel(tmpModel);
					productManagementView.getFilterPanel().resetTextFields();
					productManagementView.getFilterPanel().resetCategoryFilters();
					productManagementView.getFilterPanel().resetCompaniesFilters();
					productManagementView.getFilterPanel().resetSoldOutCheckbox();
				}
			}
			
		}
		class TextFieldListener implements KeyListener{

			 public void keyPressed(java.awt.event.KeyEvent evt) {
        		if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        			productManagementView.getFilterPanel().filterButtonDoClick();
     	        }
			 }

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		productManagementView.getFilterPanel().filterButtonListener(new FilterButtonListener());	
		productManagementView.getFilterPanel().textFieldListener(new TextFieldListener());
		productManagementView.getFilterPanel().setCategoryFilterModel(productsModel);
		productManagementView.getFilterPanel().setCompaniesFilterModel(productsModel);
		productManagementView.getFilterPanel().addSelectCategoryListener(new SelectCategoryListener());
		productManagementView.getFilterPanel().addSelectCompaniesListener(new SelectCompanyListener());
		productManagementView.getFilterPanel().addSoldOutListener(new SelectSoldOutListener());
		productManagementView.getFilterPanel().addExpiredListener(new SelectExpProdListener());
	}
}
