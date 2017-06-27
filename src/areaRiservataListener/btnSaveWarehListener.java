package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import viewAreaRiservata.areaRiservataWnd;

public class btnSaveWarehListener implements ActionListener{
	
	JFrame caller;
	
	public btnSaveWarehListener(JFrame caller)
	{
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
