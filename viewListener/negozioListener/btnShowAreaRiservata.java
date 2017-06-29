package negozioListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import viewAreaRiservata.areaRiservataWnd;

public class btnShowAreaRiservata implements ActionListener {

	JFrame main_wnd;
	
	public btnShowAreaRiservata(JFrame caller_wnd)
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
			areaRiservataWnd wnd = new areaRiservataWnd(main_wnd);
		}
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}

	}
}
