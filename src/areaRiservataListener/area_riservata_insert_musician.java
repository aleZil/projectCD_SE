package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frame.areaRiservataWnd;
import frame.*;

public class area_riservata_insert_musician implements ActionListener {
	
	areaRiservataWnd caller;
	public area_riservata_insert_musician(areaRiservataWnd caller)
	{
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		caller.showAddMusPanel();
	}

}
