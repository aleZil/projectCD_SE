package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import projectCD_SE.area_riservata_wnd;

public class area_riservata_save_updates implements ActionListener{
	
	area_riservata_wnd updatePanel;
	
	public area_riservata_save_updates(JFrame caller)
	{
		updatePanel=(area_riservata_wnd)caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		updatePanel.SaveUpdates();
	}

}
