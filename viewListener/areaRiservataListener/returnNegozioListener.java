package areaRiservataListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import viewAreaRiservata.aggiungiBranoWnd;
import viewAreaRiservata.areaRiservataWnd;


public class returnNegozioListener extends WindowAdapter implements ActionListener {
	
	JFrame negozio;
	JFrame caller;
	public returnNegozioListener(JFrame negozio,JFrame caller)
	{
		this.caller=caller;
		this.negozio=negozio;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		caller.dispose();
		negozio.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
		//Se la finestra di area riservata è disabled
		//non devo poter chiudere
		if(caller.isEnabled())
		{
			negozio.setVisible(true);
			negozio.setLocation(caller.getLocation().x+110,caller.getLocation().y+50);
			// Chiudo area_riservata_wnd
			super.windowClosing(e);
		}
	}
}
