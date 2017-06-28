package aggiungiStrumentoListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import viewAreaRiservata.aggiungiStrumentoWnd;


public class btnAddInstrumentListener implements ActionListener{
	
	JFrame caller;
	public btnAddInstrumentListener(JFrame caller) {
		// TODO Auto-generated constructor stub
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((aggiungiStrumentoWnd) caller).addInstrument();
	}
}
