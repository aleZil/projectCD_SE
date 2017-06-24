package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import viewAreaRiservata.aggiungiBranoWnd;

public class btnAddTrackListener implements ActionListener {

	JFrame caller;
	public btnAddTrackListener(JFrame caller_wnd)
	{
		this.caller=caller;
	}


	public void actionPerformed(ActionEvent e)
	{	
			//Visualizzo la finestra di configurazione
		try
		{
			aggiungiBranoWnd wnd=new aggiungiBranoWnd(caller);
		}
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}

	}
}
