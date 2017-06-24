package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import viewAreaRiservata.areaRiservataWnd;


public class o1Listener implements ActionListener{
	
	JFrame caller;
	public o1Listener(areaRiservataWnd caller)
	{
		this.caller=caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		((areaRiservataWnd) caller).showInsertCd();
	}

}
