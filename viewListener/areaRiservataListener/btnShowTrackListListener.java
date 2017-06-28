package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import viewAggiungiBrano.aggiungiBranoWnd;

public class btnShowTrackListListener implements ActionListener {

	JFrame caller;
	public btnShowTrackListListener(JFrame caller)
	{
		this.caller=caller;
	}


	public void actionPerformed(ActionEvent e)
	{	
		caller.setEnabled(false);
		caller.setFocusable(false);
		caller.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Visualizzo la finestra di configurazione
		aggiungiBranoWnd wnd=new aggiungiBranoWnd(caller);
	}
}
