package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

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
		caller.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Visualizzo la finestra di configurazione
		aggiungiPartecipanteWnd wnd=new aggiungiPartecipanteWnd(caller);
	}
}
