package view.productManagementView.tableView;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.util.Date;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import helper.transfer.CustomListTransferHandler;
import helper.transfer.CustomTableTransferHandler;
import entities.productEntities.*;
import productManagementModel.ProductsModel;

import java.util.ListIterator;

@SuppressWarnings("serial")
public class TablePanel extends JPanel{
	private  JPanel leftPanel;
	private  JPanel rightPanel;
	private  JSplitPane spliter;
	
    private  JScrollPane tableView;
    private  JScrollPane listView;
    private  JTable table;
    private  JList<Product> xmlProductsList;
    
    public TablePanel(){
    	super();
    	initComponents();
    	setTableModel();
    	setListModel();
    	setLayout();
    	setColor();
    }
    private void initComponents(){
    	leftPanel = createVerticalBoxPanel();
    	rightPanel = createVerticalBoxPanel();
    	spliter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftPanel,rightPanel);
    	  	
    	tableView = new  JScrollPane();
    	listView = new JScrollPane();
    	xmlProductsList = new JList<Product>();
        table = new  JTable();

        hideLeftComponent();
    }
    private void setTableModel(){
    	 table.setModel(new CustomTableModel(
            new String [] {
                "Product Name", "Product Price", "Quantity", "Company Name", "Category", "Cat. Id", "Company Address","Exp. Date"
            }
        ) {
			Class<?>[] types = new Class [] {
                String.class, Float.class, Integer.class, String.class, String.class, Integer.class, String.class , Date.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

			public Class<?> getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
    	tableView.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(7).setResizable(false);
        }
        
        table.getTableHeader().setReorderingAllowed(false);
       // table.setDragEnabled(true);
    }
    private void setListModel(){
    	xmlProductsList.setModel(new CustomListModel());
    	xmlProductsList.setVisibleRowCount(-1);
    	((CustomListModel) xmlProductsList.getModel()).addValues();

    	xmlProductsList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    	xmlProductsList.setDropMode(DropMode.INSERT);
		
    	xmlProductsList.setTransferHandler(new CustomListTransferHandler(xmlProductsList, (CustomListModel) xmlProductsList.getModel()));
    	xmlProductsList.setDragEnabled(true);
    	listView.setViewportView(xmlProductsList);
    }
	private void setLayout(){
	    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.leftPanel.add(createPanelForComponent(listView, "Products List"));
		this.rightPanel.add(createPanelForComponent(tableView, "Products Table"));
		
		this.add(spliter, BorderLayout.CENTER);
	}
	public void showLeftComponent(){
		spliter.setRightComponent(rightPanel);
		spliter.setLeftComponent(leftPanel);
		spliter.setDividerLocation(this.getWidth()/3);
		spliter.setOneTouchExpandable(true);
	}
	public void hideLeftComponent(){
		spliter.setRightComponent(rightPanel);
		spliter.setLeftComponent(null);
		spliter.setOneTouchExpandable(false);
	}
	private void setColor(){
    	/*this.setBackground(new Color(76, 175, 80));
    	table.setGridColor(new Color(76, 175, 80));*/
	}
	public void initTable(ProductsModel m) throws SQLException{
		/*BeanComparator fieldComparator = new BeanComparator(
                "Deumire Produs");
		Collections.sort(fruits, fieldComparator);*/
		
		ListIterator<Product> pIter = m.getProductsList().listIterator();
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		while(pIter.hasNext()){		
			Product p = new Product(pIter.next());
	        model.addRow(new Object[]{p.getProdName(),p.getProdPrice(),p.getQuantity(),p.getCategory().getCompany().getCompanyName(),p.getCategory().getCategoryName(),p.getCategory().getCategoryId(),p.getCategory().getCompany().getCompanyAddress() ,p.getExpDate()});
		}
		
	}
	public void setNewTableModel(ProductsModel m){
		if(!m.getProductsList().isEmpty())
		{
			ListIterator<Product> pIter = m.getProductsList().listIterator();
			DefaultTableModel model = (DefaultTableModel) this.table.getModel();
			
			model.getDataVector().removeAllElements();
			
			while(pIter.hasNext()){		
				Product p = new Product(pIter.next());
		        model.addRow(new Object[]{p.getProdName(),p.getProdPrice(),p.getQuantity(),p.getCategory().getCompany().getCompanyName(),p.getCategory().getCategoryName(),p.getCategory().getCategoryId(),p.getCategory().getCompany().getCompanyAddress(),p.getExpDate()});
			}
		}else{
			DefaultTableModel model = (DefaultTableModel) this.table.getModel();
			model.getDataVector().removeAllElements();
			model.fireTableDataChanged();
		}
	}
	public void tableListener(MouseListener tableListener){
		table.addMouseListener(tableListener);
	}
	public String getValueAt(int i , int j){
		return table.getValueAt(i, j).toString();
	}
	public int getColumnCount(){
		return table.getColumnCount();
	}
	public CustomTableModel getTableModel(){
		return (CustomTableModel) table.getModel();
	}
	public Product getSelRow(){
		Boolean b= false;
		int noRowSel = 0;
		for(int index = 0 ; index < table.getRowCount();index++){
			if(table.isRowSelected(index)){
				b=true;
				noRowSel++;
				if(noRowSel > 1){
					return null;
				}
			}
		}
		if(b){
			int i =table.getSelectedRow();
			Company intr = new Company(table.getValueAt(i, 3).toString(),table.getValueAt(i, 6).toString());
			Category dep = new Category(Integer.parseInt(table.getValueAt(i, 5).toString()) , table.getValueAt(i, 4).toString() ,intr );
			Product prod = new Product(table.getValueAt(i, 0).toString() , Float.parseFloat(table.getValueAt(i, 1).toString()) , Integer.parseInt(table.getValueAt(i, 2).toString()) , dep , (Date)table.getValueAt(i, 7));
			return prod;
		}else{
			return null;
		}
	}
	public void setTransferHandler(ProductsModel model){
        table.setTransferHandler(new CustomTableTransferHandler((CustomTableModel)this.table.getModel() , model));
		table.setDropMode(DropMode.INSERT);
	}
	//Page_AXIS Specifies that components should be laid out in the direction that lines 
	//flow across a page as determined by the target container's ComponentOrientation
	//property.
	protected JPanel createVerticalBoxPanel() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		return p;
	}	//creates a panel and sets the title for a component 
	public JPanel createPanelForComponent(JComponent comp, String title) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(comp, BorderLayout.CENTER);
		return panel;
	}
}
