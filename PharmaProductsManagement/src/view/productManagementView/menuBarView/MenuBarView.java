package view.productManagementView.menuBarView;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;

/**
 * @author Marius Iliescu
 *
 */
@SuppressWarnings("serial")
public class MenuBarView extends JMenuBar {
	JMenu fileMenu;
	JMenu helpMenu;
	JMenu plafMenu;

	//helpMenuItems
	JMenuItem aboutMenuItem;
	//-----------------

	public MenuBarView(final JFrame f) {
		initComponents(f);
		setMenuModel();
	}
	private void initComponents(final JFrame f){
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		plafMenu = createPlafMenu(f);

		aboutMenuItem = new JMenuItem("About",new ImageIcon("data/img/about.png"));		
	}
	private void setMenuModel(){
		fileMenu.setMnemonic(KeyEvent.VK_F);
		this.add(fileMenu);

		fileMenu.setMnemonic(KeyEvent.VK_H);
		this.add(helpMenu);

		plafMenu.setMnemonic(KeyEvent.VK_D);
		this.add(plafMenu);

		helpMenu.setMnemonic(KeyEvent.VK_A);
		helpMenu.add(aboutMenuItem);

	}
	public static JMenu createPlafMenu(final JFrame frame) {
		JMenu plafmenu = new JMenu("Look and Feel");

		ButtonGroup radiogroup = new ButtonGroup();

		UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();

		for (int i = 0; i < plafs.length; i++) {
			String plafName = plafs[i].getName();
			final String plafClassName = plafs[i].getClassName();
			JMenuItem item = plafmenu.add(new JRadioButtonMenuItem(plafName));
			radiogroup.add(item);
			if(UIManager.getLookAndFeel().getClass().getName() == plafClassName){
				if(item instanceof JRadioButtonMenuItem ){
					((JRadioButtonMenuItem)item).setSelected(true);  
				}
			}
		}
		return plafmenu;
	}
	public String getSelectedLookAndFeel(){
		String lookAndFeel = null;
		for(int i = 0 ; i<this.plafMenu.getMenuComponentCount();i++){
			if(plafMenu.getMenuComponent(i) instanceof JRadioButtonMenuItem){
				JRadioButtonMenuItem menI = ((JRadioButtonMenuItem)plafMenu.getMenuComponent(i));
				if(menI.isSelected()){
					lookAndFeel = menI.getText();
				}
			}
		}
		return lookAndFeel;	
	}
	public void addLookAndFeelActionListener(ActionListener listener){
		for(int i = 0 ; i<this.plafMenu.getMenuComponentCount();i++){
			if(plafMenu.getMenuComponent(i) instanceof JRadioButtonMenuItem){
				((JRadioButtonMenuItem)plafMenu.getMenuComponent(i)).addActionListener(listener);
			}
		}
	}

}
