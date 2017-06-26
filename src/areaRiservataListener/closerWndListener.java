package areaRiservataListener;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class closerWndListener extends WindowAdapter {
	
	JFrame caller;
	
	public closerWndListener(JFrame caller) {
		this.caller=caller;

	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		caller.setVisible(true);
		// Chiudo area_riservata_wnd
		super.windowClosing(e);
	}
}
