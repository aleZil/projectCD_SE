package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import viewAreaRiservata.areaRiservataWnd;

public class btnAddNewGenListener implements ActionListener{

	JFrame caller;
	
	public btnAddNewGenListener(JFrame caller) {
		// TODO Auto-generated constructor stub
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((areaRiservataWnd) caller).addNewGen();
	}
}
