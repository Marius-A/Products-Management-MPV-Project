package productManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.ListIterator;

import entities.productEntities.Product;
import productManagementModel.ProductsModel;
import view.productManagementView.ProductManagementView;

public class SearchController {
	public SearchController(final ProductsModel productsModel,final ProductManagementView productManagementView) {
		class searchButtonListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				final String searchBy = new String(productManagementView.getSearchPanel().getComboBoxSearchBy());
				final String info = new String(productManagementView.getSearchPanel().getTextFieldKeyWord());
				ArrayList<Product> products = new ArrayList<Product>();
							
				final ListIterator<Product> pIter = productsModel.getProductsList().listIterator();
				
				if(info.equals("")){
					productManagementView.displayWarningMessage("Insert a key word!");
					return;
				}
				if(info.contains("*")){
					productManagementView.displayWarningMessage(" The key word can not contain '*' character !");
					return;
				}
				
				while(pIter.hasNext()){
					Product p = new Product(pIter.next());
					if(searchBy.equals("Product Name")){
						//Pattern.compile(Pattern.quote(p.getDenProdus()), Pattern.CASE_INSENSITIVE).matcher(info).find()
						if(p.getProdName().toLowerCase().contains(info.toLowerCase())){
							products.add(p);
						}
					}
					else if(searchBy.equals("Company Name")){
						if(p.getCategory().getCompany().getCompanyName().toLowerCase().contains(info.toLowerCase())){
							products.add(p);
						}
					}
					else if(searchBy.equals("Category")){
						if(p.getCategory().getCategoryName().toLowerCase().contains(info.toLowerCase())){
							products.add(p);
						}
					}
				}
				
				ProductsModel tmpModel = new ProductsModel( products );
				if(tmpModel.getProductsList().isEmpty()){
					productManagementView.displayWarningMessage("Not matches were found!!");
				}else{
					productManagementView.getTablePanel().setNewTableModel(tmpModel);
				}
	
				
			}
		
		}
		class textFieldListener implements KeyListener{

			 public void keyPressed(java.awt.event.KeyEvent evt) {
        		if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        			productManagementView.getSearchPanel().searchButtondoClick();
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
		
		productManagementView.getSearchPanel().textFieldListener(new textFieldListener());
		productManagementView.getSearchPanel().searchButtonListener(new  searchButtonListener());
		
	}
}
