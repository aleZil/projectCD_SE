package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.aggiungiBranoWnd;

public class areaRiservataWnd_to_aggiungiBranoWnd implements ActionListener {

	JFrame main_wnd;
	public areaRiservataWnd_to_aggiungiBranoWnd(JFrame caller_wnd)
	{
		main_wnd=caller_wnd;
	}


	public void actionPerformed(ActionEvent e)
	{	
			//Visualizzo la finestra di configurazione
		try
		{
			aggiungiBranoWnd wnd=new aggiungiBranoWnd(main_wnd);
		}
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}

	}
}
