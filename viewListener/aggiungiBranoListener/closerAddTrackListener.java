package aggiungiBranoListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import viewAreaRiservata.aggiungiBranoWnd;

public class closerAddTrackListener extends WindowAdapter{
	
	JFrame caller;
	
	public closerAddTrackListener(JFrame caller)
	{
		this.caller=caller;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		((aggiungiBranoWnd) this.caller).close();
		super.windowClosing(e);
	}

}
