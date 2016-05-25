package view.productManagementView;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import view.productManagementView.filtersView.FilterPanel;
import view.productManagementView.inputView.EditPanel;
import view.productManagementView.menuBarView.MenuBarView;
import view.productManagementView.operationsView.OpPanel;
import view.productManagementView.searchAndSortView.SearchPanel;
import view.productManagementView.tableView.TablePanel;
import view.productManagementView.utilityView.UtilityPanel;

import java.awt.GridBagLayout;


/**
 * Purpose:This class represent the main frame that contains all panels 
 * such as : Edit Panel , Table Panel , Search panel , Utility Panel  , Filter Panel , Option Panel and the menu bar
 * @see EditPanel
 * @see TablePanel
 * @see SearchPanel
 * @see UtilityPanel
 * @see FilterPanel
 * @see OpPanel
 * 
 * @author Marius Iliescu
 *
 */
@SuppressWarnings("serial")
public class ProductManagementView extends JFrame {
	
    private  EditPanel editPanel;
    private  TablePanel tablePanel;
    private  SearchPanel searchPanel;
    private  UtilityPanel utilityPanel;
    private  FilterPanel filterPanel;
    private  OpPanel opPanel;
    private  MenuBarView menuBarView;
 
    /**
     * ProductManagementView implicit constructor
     */
    public ProductManagementView() {
    	super();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setSize(new Dimension(1300,600));
        this.setMinimumSize(new Dimension(1200,600));
        this.setName("Products Management");
        this.setCurrentUILookAndFeel();  	
        initComponents();
        setLayout();
        this.getContentPane().setBackground(this.getBackground().brighter());
    }
    /**
     * Main frame component initializer
     */
    private void initComponents(){
        editPanel = new EditPanel();
        tablePanel = new  TablePanel();
        searchPanel = new  SearchPanel();
        utilityPanel = new  UtilityPanel();
        filterPanel = new  FilterPanel();
        opPanel = new OpPanel();
        menuBarView = new MenuBarView(this);
       
    } 
	/**
	 * Set components layout(how appearing on the screen)
	 */
	private void setLayout() {
		this.setJMenuBar(menuBarView);
		
        this.setLayout(new GridBagLayout());
        GridBagConstraints c= new GridBagConstraints();
                
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty=1.0;
        c.insets.bottom = 5;
        this.add(editPanel,c);
       
        c.gridx = 1;
        c.gridy = 0;
        c.ipady=0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.BOTH;
        c.weighty=0;
        c.weightx = 1.0;
        c.insets.bottom = 0;
        c.insets.left = 5;
        this.add(tablePanel,c);
    
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 0;
        c.weightx=0;
        c.insets.left = 5;
        c.anchor = GridBagConstraints.LINE_END;
        this.add(filterPanel,c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.insets.left = 0;
        c.insets.top = 5;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        this.add(utilityPanel,c);
        
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.insets.top=5;
        c.insets.left =5;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        this.add(searchPanel,c);
        
        c.gridx = 2;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets.top=5;
        c.insets.left=5;
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(opPanel,c);
   	}
	/**
	 * Returns the class name of the installed LookAndFeel with a name
	 * containing the name snippet or null if none found.
	 * 
	 * @param nameSnippet a snippet contained in the Laf's name
	 * @return the class name if installed, or null
	 */
	public static String getLookAndFeelClassName(String nameSnippet) {
	    LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
	    for (LookAndFeelInfo info : plafs) {
	        if (info.getName().contains(nameSnippet)) {
	            return info.getClassName();
	        }
	    }
	    return null;
	}
	/**
	 * Set main Frame initial look and feel
	 */
	private void setCurrentUILookAndFeel(){
		UIManager.LookAndFeelInfo[] uiLF = UIManager.getInstalledLookAndFeels();
        try {
			UIManager.setLookAndFeel(uiLF[1].getClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Add listener to "frame" itself
	 * When the "x" button is pressed , do something
	 * @see FrameListener
	 * @param wl
	 */
	public void addFrameListener(WindowListener wl){
		this.addWindowListener(wl);
	}
	/**
	 * Messages dispatcher
	 * @param errorMessage
	 */
	public void displayWarningMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	/**
	 * Edit panel getter
	 * @return Edit Panel
	 * @see EditPanel
	 */
	public EditPanel getEditPanel() {
		return editPanel;
	}
	/**
	 * Table panel getter
	 * @return table panel
	 * @see TablePanel
	 */
	public TablePanel getTablePanel() {
		return tablePanel;
	}
	/**
	 * Search panel getter
	 * @return search panel
	 * @see SearchPanel
	 */
	public SearchPanel getSearchPanel() {
		return searchPanel;
	}
	/**
	 * Utility panel getter
	 * @return utility panel
	 * @see UtilityPanel
	 */
	public UtilityPanel getUtilityPanel() {
		return utilityPanel;
	}
	/**
	 * Filter panel getter
	 * @return filter panel 
	 * @see FilterPanel
	 */
	public FilterPanel getFilterPanel() {
		return filterPanel;
	}
	/**
	 * Option panel getter
	 * @return Option Panel
	 * @see OpPanel
	 */
	public OpPanel getOpPanel() {
		return opPanel;
	}
	public MenuBarView getMenuBarView() {
		return this.menuBarView;
	}
	
}