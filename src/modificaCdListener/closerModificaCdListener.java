package modificaCdListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import viewAggiungiBrano.aggiungiBranoWnd;

public class closerModificaCdListener extends WindowAdapter{
	
	JFrame caller;
	
	public closerModificaCdListener(JFrame caller)
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
