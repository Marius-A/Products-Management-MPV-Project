package view.productManagementView.operationsView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class OpPanel extends JPanel {

    private  JButton printButton;
    private  JButton restoreButton;
    private  JToggleButton togleButtonExcelLoading;
    private  JButton undoButton;
    private  JButton redoButton;
    private  JToolBar toolBar;
    private  Clock clock;
    
    public OpPanel(){
    	super();
    	initComponents();
    	setButtonIcon();
    	setLayout();
    	setColor();
    	setTipNotification();
    }
    private void initComponents(){
        printButton = new JButton();
        restoreButton = new JButton();
        togleButtonExcelLoading = new  JToggleButton();
        undoButton = new JButton();
        redoButton = new JButton();
        clock = new Clock();
        toolBar = new JToolBar(null, JToolBar.HORIZONTAL);
        
        toolBar.setFloatable(false);
        toolBar.setRollover(true);
    }
    private void setButtonIcon(){
    	Properties props = new Properties();
    	InputStream instr = getClass().getResourceAsStream("../ImgPathConfig.properties");
        try {
			props.load(instr);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    	try {
			instr.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		final String imgPth = props.getProperty("img.path");;		
    	final String printButtonFile = imgPth.concat(props.getProperty("view.opPanel.printButtonIcon.name"));
    	final String refreshButtonFile = imgPth.concat(props.getProperty("view.opPanel.refreshButtonIcon.name"));
    	final String excelLoadingButtonFile = imgPth.concat(props.getProperty("view.opPanel.excelLoadingButtonIcon.name"));
    	final String undoButtonFile = imgPth.concat(props.getProperty("view.opPanel.undoButtonIcon.name"));
    	final String redoButtonFile = imgPth.concat(props.getProperty("view.opPanel.redoButtonIcon.name"));
    	
    	printButton.setIcon(new ImageIcon(printButtonFile));
    	restoreButton.setIcon(new ImageIcon(refreshButtonFile));
    	togleButtonExcelLoading.setIcon(new ImageIcon(excelLoadingButtonFile));
    	undoButton.setIcon(new ImageIcon(undoButtonFile));
    	redoButton.setIcon(new ImageIcon(redoButtonFile));
    }
    private void setTipNotification(){
    	printButton.setToolTipText("Print PDF/HTML file");
    	restoreButton.setToolTipText("Restore entire table");
    	togleButtonExcelLoading.setToolTipText("Load data from xml file");
    	undoButton.setToolTipText("Undo");
    	redoButton.setToolTipText("Redo");
    }
    private void setLayout(){
    	this.setLayout(new GridBagLayout());
    	GridBagConstraints c= new GridBagConstraints();
        
    	toolBar.setLayout(new GridLayout(1,5,5,5));
    	
        toolBar.add(printButton);
        toolBar.add(restoreButton);
        toolBar.add(togleButtonExcelLoading);
        toolBar.add(undoButton);
        toolBar.add(redoButton);
        
        
        c.gridx = 0;
        c.gridy = 0;  
        c.anchor = GridBagConstraints.FIRST_LINE_START;  
        this.add(toolBar,c);
        
        c.gridx = 0;
        c.gridy = 1;  
        c.anchor = GridBagConstraints.FIRST_LINE_START;   
        this.add(clock,c);
    }
    private void setColor(){
    	/*Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    	this.setBackground(Color.GREEN);/*
    	butonPintare.setBackground(new Color(76, 175, 80));
    	butonRestaurare.setBackground(new Color(76, 175, 80));
    	butonPintare.setBorder(raisedbevel);
    	butonRestaurare.setBorder(raisedbevel);
    	 */
	}
	public void printButtonListener(ActionListener print){
		printButton.addActionListener(print);
	}
	public void restoreButtonListener(ActionListener restore){
		restoreButton.addActionListener(restore);
	}
	public void undoButtonListener(ActionListener undo){
		undoButton.addActionListener(undo);
	}
	public void redoButtonListener(ActionListener redo){
		redoButton.addActionListener(redo);
	}
	public void excelLoaderButtonListener(ActionListener load){
		togleButtonExcelLoading.addActionListener(load);
	}
	public void resetButtondoClick(){
		this.restoreButton.doClick();
	}
	public void printButtondoClick(){
		this.printButton.doClick();
	}
	public void undoButtondoClick(){
		this.undoButton.doClick();
	}
	public void redoButtondoClick(){
		this.redoButton.doClick();
	}
	public Boolean getTogleButtonState(){
		return togleButtonExcelLoading.isSelected();
	}
}
