package areaRiservataListener;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class area_riservata_wnd_closer extends WindowAdapter {
	
	JFrame main_wnd;
	
	public area_riservata_wnd_closer(JFrame main_wnd) {
		this.main_wnd=main_wnd;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		// Ri-abilito la main_wnd
		main_wnd.setVisible(true);
		// Chiudo area_riservata_wnd
		super.windowClosing(e);
	}
}
