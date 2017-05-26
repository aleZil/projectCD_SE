package projectCD_SE;

import javax.swing.JFrame;

import sun.awt.WindowClosingListener;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class area_riservata_wnd extends JFrame {
	
	JFrame main_wnd;
	
	public area_riservata_wnd(JFrame caller) {
		
		
		main_wnd=caller;
		this.addWindowListener(new area_riservata_wnd_closer(main_wnd));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setBounds(100, 100, 450, 300);
		JButton btnNewButton = new JButton("asd");
		btnNewButton.setBounds(149, 135, 117, 25);
		getContentPane().add(btnNewButton);
		this.setVisible(true);
	}
	
	
	
	
	

}
