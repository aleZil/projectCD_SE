package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import viewAggiungiBrano.aggiungiBranoWnd;
import viewAggiungiStrumento.aggiungiStrumentoWnd;

public class btnAddInsListener implements ActionListener {

	JFrame caller;
	public btnAddInsListener(JFrame caller)
	{
		this.caller=caller;
	}


	public void actionPerformed(ActionEvent e)
	{	
			//Visualizzo la finestra di configurazione
		try
		{
			aggiungiStrumentoWnd wnd=new aggiungiStrumentoWnd(caller);
		}
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}

	}
}
