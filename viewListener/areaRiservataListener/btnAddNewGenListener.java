package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import viewAreaRiservata.areaRiservataWnd;

public class btnAddNewGenListener implements ActionListener{

	JFrame caller;
	
	public btnAddNewGenListener(JFrame caller) {
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		((areaRiservataWnd) caller).addNewGen();
	}
}
