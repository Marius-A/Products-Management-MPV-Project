package view.productManagementView.operationsView;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Clock extends JPanel {
	JLabel clockLabel;
	JLabel dateLabel;
	
	public Clock(){
		initComponents();
		setStyle();
		setLayout();
	}
	private void initComponents(){
		clockLabel = new JLabel();
		dateLabel = new JLabel();
		Thread clock = new Thread(){
			public void run(){
				try {
					while(true){
						Calendar cal = new GregorianCalendar();
						
						int year = 2016;
						int month = 1;
						int day = 23;
						int hour = cal.get(Calendar.HOUR_OF_DAY);
						int minute = cal.get(Calendar.MINUTE);
						int second = cal.get(Calendar.SECOND);
						
						dateLabel.setText(String.format("%02d",day)+"/"+String.format("%02d",month)+"/"+year);
						clockLabel.setText(String.format("%02d",hour)+":"+String.format("%02d",minute)+":"+String.format("%02d",second));
						
						sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		clock.start();
	}
	private void setStyle(){
		clockLabel.setFont(new Font("Thoma", Font.BOLD, 20));
		dateLabel.setFont(new Font("Thoma", Font.BOLD, 20));
	}
	private void setLayout(){
		this.setLayout(new GridLayout(2,1));
		this.add(clockLabel);
		this.add(dateLabel);
	}

}
