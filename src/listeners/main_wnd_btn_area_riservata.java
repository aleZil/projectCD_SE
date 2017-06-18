package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import projectCD_SE.area_riservata_wnd;

public class main_wnd_btn_area_riservata implements ActionListener {

	JFrame main_wnd;
	public main_wnd_btn_area_riservata(JFrame caller_wnd)
	{
		main_wnd=caller_wnd;
	}


	public void actionPerformed(ActionEvent e)
	{	
		//Disabilito la main_wnd
		main_wnd.setVisible(false);
		//Visualizzo la finestra di configurazione
		try
		{
			area_riservata_wnd wnd=new area_riservata_wnd(main_wnd);
		}
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}

	}
}
