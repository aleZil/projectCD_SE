package areaRiservataListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import viewAggiungiBrano.aggiungiBranoWnd;
import viewAreaRiservata.areaRiservataWnd;


public class returnNegozioListener implements ActionListener {
	
	JFrame caller;
	public returnNegozioListener(areaRiservataWnd caller)
	{
		this.caller=caller;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		caller.show();
		
	}

}
