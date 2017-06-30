package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import viewAreaRiservata.areaRiservataWnd;


public class btnBackListener implements ActionListener{
	
	JFrame caller;
	public btnBackListener(JFrame caller)
	{
		this.caller=caller;
	}
	
	public void actionPerformed(ActionEvent e) {
		((areaRiservataWnd) caller).showOption();
	}

}
