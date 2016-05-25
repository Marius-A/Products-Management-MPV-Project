package productManagementController;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import helper.Login;
import view.RegDialog;

public class LoginController {
	public  LoginController(Frame f){
		RegDialog dial = new RegDialog(f);
		
		class CancelButtonListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Close"); 
				int n = JOptionPane.showConfirmDialog(
					    dial,
					    "Do you really want to exit?",
					    "Exit confirmation.",
					    JOptionPane.YES_NO_OPTION);
				switch(n){
				case 0:
					System.out.println("Close confirmed"); 
					dial.dispose();
					//productsModel.closeDatabase();
					System.exit(0);
					//theModel.writeDatabaseFromXml();
					break;
				case 1:
					System.out.println("Accidental exit attempt"); 
				default:
					return;
				}	
			}
		}
		class LoginButtonListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dial.getUsername().equals("") || dial.getPassword().equals("")){	
					JOptionPane.showMessageDialog(dial,
                            "Complete username/password fields",
                            "Login",
                            JOptionPane.CANCEL_OPTION);
					return;
				}
				if (Login.authenticate(dial.getUsername(),dial.getPassword())) {
					f.setVisible(true);
                    dial.dispose();
                } else {
                    JOptionPane.showMessageDialog(dial,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                }
			}
		}
		class PassKeyListener implements KeyListener{

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					dial.loginButtonDoClick();	
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
		class FrameListener implements WindowListener{
			@Override
			public void windowActivated(WindowEvent arg0) {}
			@Override
			public void windowClosed(WindowEvent arg0){}
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.out.println("Close"); 
				int n = JOptionPane.showConfirmDialog(
					    dial,
					    "Do you really want to exit?",
					    "Exit confirmation.",
					    JOptionPane.YES_NO_OPTION);
				switch(n){
				case 0:
					System.out.println("Close confirmed"); 
					arg0.getWindow().dispose();
					//productsModel.closeDatabase();
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
			public void windowDeactivated(WindowEvent arg0) {}
			@Override
			public void windowDeiconified(WindowEvent arg0) {}
			@Override
			public void windowIconified(WindowEvent arg0) {}
			@Override
			public void windowOpened(WindowEvent arg0) {}
		}
		
		dial.addCancelButtonListener(new CancelButtonListener());
		dial.addLoginButtonListener(new LoginButtonListener());
		dial.addPassKeyListener(new PassKeyListener());
		dial.addWindowListener(new FrameListener());

		dial.setVisible(true);
	}
}
