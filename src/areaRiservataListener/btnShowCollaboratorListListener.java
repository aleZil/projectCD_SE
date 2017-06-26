package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import viewAggiungiPartecipante.aggiungiPartecipanteWnd;

public class btnShowCollaboratorListListener implements ActionListener {

	JFrame caller;
	public btnShowCollaboratorListListener(JFrame caller)
	{
		this.caller=caller;
	}


	public void actionPerformed(ActionEvent e)
	{	
		caller.setEnabled(false);
		caller.setFocusable(false);
		//Visualizzo la finestra di configurazione
		aggiungiPartecipanteWnd wnd=new aggiungiPartecipanteWnd(caller);
	}
}
