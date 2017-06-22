package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.areaRiservataWnd;

public class area_riservata_save_updates implements ActionListener{
	
	areaRiservataWnd updatePanel;
	
	public area_riservata_save_updates(JFrame caller)
	{
		updatePanel=(areaRiservataWnd)caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		updatePanel.SaveUpdates();
	}

}
