package productManagementController;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import productManagementModel.ProductsModel;
import view.productManagementView.ProductManagementView;

public class FrameController {

	@SuppressWarnings("serial")
	public FrameController(final ProductsModel productsModel  , final ProductManagementView productManagementView){
		class FrameListener implements WindowListener{

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Close"); 
				int n = JOptionPane.showConfirmDialog(
					    productManagementView,
					    "Do you really want to exit?",
					    "Exit confirmation.",
					    JOptionPane.YES_NO_OPTION);
				switch(n){
				case 0:
					System.out.println("Close confirmed"); 
					e.getWindow().dispose();
					productsModel.closeDatabase();
					System.exit(0);
					//theModel.writeDatabaseFromXml();
					break;
				case 1:
					System.out.println("Accidental exit attempt"); 
				default:
					return;
				}
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				productManagementView.setFocusable(true);
			}
		
		}
		
		//setup aplication shortcuts:
		Action actionPrint = new AbstractAction("Print it") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				productManagementView.getOpPanel().printButtondoClick();	
			}
		};
		Action actionReset = new AbstractAction("Reset it") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				productManagementView.getTablePanel().setNewTableModel(productsModel);
				productManagementView.getEditPanel().resetTextFields();
				productManagementView.getSearchPanel().resetTextFields();
				productManagementView.getFilterPanel().resetTextFields();
				productManagementView.getFilterPanel().resetCategoryFilters();
				productManagementView.getFilterPanel().resetCompaniesFilters();
				productManagementView.getSearchPanel().setDefaultOrder();
				
			}
		};
		Action actionUndo = new AbstractAction("Print it") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				productManagementView.getOpPanel().undoButtondoClick();	
			}
		};
		Action actionRedo = new AbstractAction("Reset it") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				productManagementView.getOpPanel().redoButtondoClick();			
			}
		};
		Action actionFind = new AbstractAction("Find it") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				productManagementView.getSearchPanel().searchFocusTextfield();		
			}
		};

		KeyStroke keyStrokePrint = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK);
		KeyStroke keyStrokeReset = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
		KeyStroke keyStrokeUndo = KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK);
		KeyStroke keyStrokeRedo = KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK);
		KeyStroke keyStrokeFind = KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK);

		productManagementView.getTablePanel().getActionMap().put("Print it", actionPrint);
		productManagementView.getTablePanel().getActionMap().put("Reset it", actionReset);
		productManagementView.getTablePanel().getActionMap().put("Undo it", actionUndo);
		productManagementView.getTablePanel().getActionMap().put("Redo it", actionRedo);
		productManagementView.getTablePanel().getActionMap().put("Find it", actionFind);

		productManagementView.getTablePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokePrint, "Print it");
		productManagementView.getTablePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeReset, "Reset it");
		productManagementView.getTablePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeUndo, "Undo it");
		productManagementView.getTablePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeRedo, "Redo it");
		productManagementView.getTablePanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeFind, "Find it");
		
		productManagementView.addFrameListener(new FrameListener());
	}

}
