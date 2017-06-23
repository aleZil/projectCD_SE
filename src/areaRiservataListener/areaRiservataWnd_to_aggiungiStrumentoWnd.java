package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.aggiungiBranoWnd;
import view.aggiungiStrumentoWnd;

public class areaRiservataWnd_to_aggiungiStrumentoWnd implements ActionListener {

	JFrame main_wnd;
	public areaRiservataWnd_to_aggiungiStrumentoWnd(JFrame caller_wnd)
	{
		main_wnd=caller_wnd;
	}


	public void actionPerformed(ActionEvent e)
	{	
			//Visualizzo la finestra di configurazione
		try
		{
			aggiungiStrumentoWnd wnd=new aggiungiStrumentoWnd(main_wnd);
		}
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}

	}
}
