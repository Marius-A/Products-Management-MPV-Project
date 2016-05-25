package productManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import productManagementModel.ProductsModel;
import view.productManagementView.ProductManagementView;

public class LookAndFeelController {
	public LookAndFeelController(final ProductsModel prodModel , final ProductManagementView prodView){
		class LookAndFeelActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String lookAndFeel = prodView.getMenuBarView().getSelectedLookAndFeel();
				String className = ProductManagementView.getLookAndFeelClassName(lookAndFeel);
				if(!ProductManagementView.getLookAndFeelClassName(UIManager.getLookAndFeel().getName()).equals(className)){
					try {
						UIManager.setLookAndFeel(className);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}
		            SwingUtilities.updateComponentTreeUI(prodView);
		            prodView.getEditPanel().setModel(prodModel.getProductsList());
				}
					
			}
		
		}
		prodView.getMenuBarView().addLookAndFeelActionListener(new LookAndFeelActionListener());
	}

}
