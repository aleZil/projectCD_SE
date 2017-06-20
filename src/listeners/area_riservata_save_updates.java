package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class area_riservata_save_updates implements ActionListener{
	
	JFrame updatePanel;
	
	public area_riservata_save_updates(JFrame caller)
	{
		updatePanel=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//updatePanel.SaveUpdates();
	}

}
