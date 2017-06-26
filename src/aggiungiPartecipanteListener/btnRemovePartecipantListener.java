package aggiungiPartecipanteListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import viewAggiungiBrano.aggiungiPartecipanteWnd;

public class btnRemovePartecipantListener implements ActionListener{
	
	JFrame caller;
	public btnRemovePartecipantListener(JFrame caller) {
		// TODO Auto-generated constructor stub
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((aggiungiPartecipanteWnd) caller).removePartecipant();
	}
}
