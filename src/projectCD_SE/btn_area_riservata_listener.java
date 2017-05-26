package projectCD_SE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class btn_area_riservata_listener implements ActionListener {
	
	JFrame main_wnd;
	
	public btn_area_riservata_listener(JFrame caller_wnd)
	{
		main_wnd=caller_wnd;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{	
		//Disabilito la main_wnd
		main_wnd.setVisible(false);
		//Visualizzo la finestra di configurazione
		area_riservata_wnd wnd=new area_riservata_wnd(main_wnd);
	}
}
