package productManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ListIterator;

import entities.productEntities.*;
import productManagementModel.ProductsModel;
import view.productManagementView.ProductManagementView;

public class ButtonsController{

	
	public ButtonsController(final ProductsModel productsModel, final ProductManagementView productManagementView) {
		class addButtonListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				
				Product p = retProd(productManagementView);
				
				if(p.equals(null)){
					return;
				}

				ListIterator<Product> produse = productsModel.getProductsList().listIterator();
				while(produse.hasNext()){
					Product prod = new Product(produse.next());
					
					String insProd = new String(p.toString());
					String bdProd = new String(prod.toString());
					if(bdProd.equals(insProd)){
						productManagementView.displayWarningMessage("The product is already in the database!");
						return;
					}
				}
				productsModel.addProduct(0,p);
				productManagementView.getOpPanel().resetButtondoClick();
				productManagementView.getEditPanel().resetTextFields();
			}
			
		}
		class delButtonListener implements ActionListener{
			
			public void actionPerformed(ActionEvent arg0) {
				Product p = productManagementView.getTablePanel().getSelRow();
				
				if(p == null){
					productManagementView.displayWarningMessage("Please select one row from the main table.");
					return;
				}

				ListIterator<Product> produse = productsModel.getProductsList().listIterator();
				int index = 0;
				while(produse.hasNext()){
					Product prod = new Product(produse.next());
					
					String insProd = new String(p.toString());
					String bdProd = new String(prod.toString());
					
					if(bdProd.equals(insProd)){	
						productsModel.delProduct(index , p);
						break;
					}
					index++;
				}

				productManagementView.getOpPanel().resetButtondoClick();
				productManagementView.getEditPanel().resetTextFields();
			}
		}
		class updateButtonListener implements ActionListener{
			
			public void actionPerformed(ActionEvent arg0) {
				Product pNew = null;
				Product pSel = productManagementView.getTablePanel().getSelRow();
				
				if(pSel == null){
					productManagementView.displayWarningMessage("Please select one row from the main table.");
					return;
				}else{
					pNew = retProd(productManagementView);
					if(pNew == null){
						//productManagementView.displayErrorMessage("Please complete all fields.");
						return;
					}
				}
				
				ListIterator<Product> products = productsModel.getProductsList().listIterator();
				int index = 0;
				while(products.hasNext()){
					Product prod = new Product(products.next());
					
					String selProd = new String(pSel.toString());
					String bdProd = new String(prod.toString());
					if(bdProd.equals(selProd)){	
						productsModel.updateProdus(index , pNew , pSel);
						break;
					}
					index++;
				}

				productManagementView.getOpPanel().resetButtondoClick();
				productManagementView.getEditPanel().resetTextFields();	
			}
		}
		
		productManagementView.getUtilityPanel().addButtonListener(new  addButtonListener());
		productManagementView.getUtilityPanel().deleteButtonListener(new  delButtonListener());
		productManagementView.getUtilityPanel().updateButtonListener(new updateButtonListener());
	}
	private Product retProd(ProductManagementView productManagementView){
		Product p = null;
		Category d;
		Company c;
		Date expDate = new Date();
		String companyName = new String();
		String departmentName = new String();
		String productName = new String();
		String companyAddress = new String();
		int departmentNumber=0;
		int prodQuantitie=0;
		float prodPrice=0;
		
		companyName = productManagementView.getEditPanel().getCompanyName();
		departmentName =productManagementView.getEditPanel().getDepName();
		productName = productManagementView.getEditPanel().getProdName();
		companyAddress =productManagementView.getEditPanel().getCompanyAddress();
		
		if(companyName.equals("") || departmentName.equals("") || companyAddress.equals("") ||productName.equals("")) {
			productManagementView.displayWarningMessage("Fill in all fields");
			return null;
		}
		try{
			departmentNumber =productManagementView.getEditPanel().getDepNumber();
			prodQuantitie = productManagementView.getEditPanel().getProdQuantities();
			prodPrice = productManagementView.getEditPanel().getProdPrice();
		}catch(NumberFormatException nfe){
			productManagementView.displayWarningMessage("Enter an integer/real value in fields 'Department Number', 'Product quantity' or 'Product Price'!");
			return null;
		}
		expDate = productManagementView.getEditPanel().getExpDate();
		
		c = new Company(companyName, companyAddress);
		d= new Category(departmentNumber, departmentName,c);
		p = new Product(productName, prodPrice, prodQuantitie, d, expDate);
		
		return p;
		
	}
}
