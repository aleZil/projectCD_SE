package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.areaRiservataWnd;

public class areaRiservataOptionAddGen implements ActionListener{
	
	areaRiservataWnd caller;
	
	public areaRiservataOptionAddGen(areaRiservataWnd caller) {
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		caller.showAddNewGen();
	}
}
