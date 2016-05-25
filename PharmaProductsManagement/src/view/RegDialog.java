package view;
 
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.*;
 
public class RegDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    
    private JPanel introPanel;
    private JLabel introLabel;
    private JLabel imageLabel;
 
    public RegDialog(Frame parent) {
        super(parent, "Login", true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        initComponents();
        setIcons();
        setLayout(parent);
    }
    
    private void initComponents(){       
        lbUsername = new JLabel("Username: ");
        tfUsername = new JTextField(20);
        lbPassword = new JLabel("Password: ");
        pfPassword = new JPasswordField(20);
        btnLogin = new JButton("Login");
        btnCancel = new JButton("Cancel");
        
        introPanel = new JPanel();
        introLabel = new JLabel("Pharma Management");
        imageLabel = new JLabel();
        introLabel.setFont(new Font("Thoma", Font.BOLD+Font.ITALIC, 20));
    }
    private void setLayout(Frame parent){
    	introPanel.add(imageLabel);
    	introPanel.add(introLabel);
    	
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        cs.fill = GridBagConstraints.HORIZONTAL;  
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
 
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
 
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
 
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
        
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(introPanel, BorderLayout.PAGE_START);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
        
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
    private void setIcons(){
    	Properties props = new Properties();
    	InputStream instr = getClass().getResourceAsStream("productManagementView/ImgPathConfig.properties");
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
    	final String introIconFile = imgPth.concat(props.getProperty("view.opPanel.introIcon.name"));
    	final String tipTextHelp = props.getProperty("view.help.contactData");
    	
    	
    	introLabel.setIcon(new ImageIcon(introIconFile));
    	introLabel.setToolTipText("<html><p width=\"500\">" +tipTextHelp+"</p></html>");
    }
    public void addCancelButtonListener(ActionListener lis){
    	btnCancel.addActionListener(lis);
    }
    public void addLoginButtonListener(ActionListener lis){
    	btnLogin.addActionListener(lis);
    }
    public void addFrameListener(WindowListener lis){
    	this.addWindowListener(lis);
    }
    public void addPassKeyListener(KeyListener lis){
    	pfPassword.addKeyListener(lis);
    }
    public void loginButtonDoClick(){
    	btnLogin.doClick();
    }
    public String getUsername() {
        return tfUsername.getText().trim();
    }
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
    public void resetTextFields(){
    	tfUsername.setText("");
    	pfPassword.setText("");
    }
}