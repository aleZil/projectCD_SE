package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.areaRiservataWnd;

public class areaRiservataInsertNewGen implements ActionListener{

	areaRiservataWnd caller;
	
	public areaRiservataInsertNewGen(areaRiservataWnd caller) {
		// TODO Auto-generated constructor stub
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		caller.AddNewGen();
	}
}
