package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import viewAreaRiservata.areaRiservataWnd;

public class o2Listener implements ActionListener{
	
	areaRiservataWnd caller;
	public o2Listener(areaRiservataWnd caller)
	{
		this.caller=caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		caller.showWarehouse();
	}

}
