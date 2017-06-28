package aggiungiStrumentoListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import viewAggiungiStrumento.aggiungiStrumentoWnd;

public class btnRemoveInstrumentListener implements ActionListener{
	
	JFrame caller;
	public btnRemoveInstrumentListener(JFrame caller) {
		// TODO Auto-generated constructor stub
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((aggiungiStrumentoWnd) caller).removeInstrument();
	}
}
