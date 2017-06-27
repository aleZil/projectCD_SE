package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import viewAreaRiservata.areaRiservataWnd;

public class btnAddMusInsListener implements ActionListener {

	JFrame caller;
	
	public btnAddMusInsListener(JFrame caller)
	{
		this.caller=caller;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((areaRiservataWnd) caller).showAddMusIns();
	}

}
