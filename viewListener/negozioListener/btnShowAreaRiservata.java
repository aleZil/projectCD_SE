package negozioListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import viewAreaRiservata.areaRiservataWnd;
import viewNegozio.negozioWnd;

public class btnShowAreaRiservata implements ActionListener {

	negozioWnd caller;
	
	public btnShowAreaRiservata(negozioWnd caller)
	{
		this.caller=caller;
	}


	public void actionPerformed(ActionEvent e)
	{	
		//Disabilito la main_wnd
		caller.setVisible(false);
		//Visualizzo la finestra di configurazione
		try
		{
			areaRiservataWnd wnd = new areaRiservataWnd(caller);
		}
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(caller, exception.getMessage());
		}

	}
}
