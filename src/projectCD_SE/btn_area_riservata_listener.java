package projectCD_SE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class btn_area_riservata_listener implements ActionListener {
	public void actionPerformed(ActionEvent e)
	{
		area_riservata_wnd wnd=new area_riservata_wnd();
		wnd.setVisible(true);
	}
}
