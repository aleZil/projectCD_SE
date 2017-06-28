package aggiungiPartecipanteListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import viewAreaRiservata.aggiungiPartecipanteWnd;

public class closerAddPartecipantListener extends WindowAdapter{
	
	JFrame caller;
	
	public closerAddPartecipantListener(JFrame caller)
	{
		this.caller=caller;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		((aggiungiPartecipanteWnd) this.caller).close();
		super.windowClosing(e);
	}

}
