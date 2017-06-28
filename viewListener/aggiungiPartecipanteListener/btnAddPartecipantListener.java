package aggiungiPartecipanteListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import viewAreaRiservata.aggiungiPartecipanteWnd;


public class btnAddPartecipantListener implements ActionListener{
	
	JFrame caller;
	public btnAddPartecipantListener(JFrame caller) {
		// TODO Auto-generated constructor stub
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((aggiungiPartecipanteWnd) caller).addPartecipant();
	}
}
