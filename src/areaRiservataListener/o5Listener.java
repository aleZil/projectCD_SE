package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import viewAreaRiservata.areaRiservataWnd;

public class o5Listener implements ActionListener {
	
	JFrame caller;
	
	public o5Listener(JFrame caller)
	{
		this.caller=caller;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		((areaRiservataWnd) caller).showAddMusIns();
	}

}
