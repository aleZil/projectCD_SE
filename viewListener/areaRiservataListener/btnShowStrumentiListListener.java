package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import viewAreaRiservata.aggiungiStrumentoWnd;

public class btnShowStrumentiListListener implements ActionListener {

	JFrame caller;
	
	public btnShowStrumentiListListener(JFrame caller)
	{
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		caller.setEnabled(false);
		caller.setFocusable(false);
		caller.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Visualizzo la finestra di configurazione
		aggiungiStrumentoWnd wnd=new aggiungiStrumentoWnd(caller);
	}

}
