package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import viewAreaRiservata.areaRiservataWnd;
import viewNegozio.*;

public class o3Listener implements ActionListener {
	
	JFrame caller;
	public o3Listener(JFrame caller)
	{
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((areaRiservataWnd) caller).showAddMusPanel();
	}

}
