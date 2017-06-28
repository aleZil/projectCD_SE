package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import viewAreaRiservata.areaRiservataWnd;

public class o4Listener implements ActionListener{
	
	JFrame caller;
	
	public o4Listener(areaRiservataWnd caller) {
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((areaRiservataWnd) caller).showAddNewGen();
	}
}
