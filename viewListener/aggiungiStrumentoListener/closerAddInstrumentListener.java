package aggiungiStrumentoListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import viewAggiungiStrumento.aggiungiStrumentoWnd;

public class closerAddInstrumentListener extends WindowAdapter{
	
	JFrame caller;
	
	public closerAddInstrumentListener(JFrame caller)
	{
		this.caller=caller;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		((aggiungiStrumentoWnd) this.caller).close();
		super.windowClosing(e);
	}

}
