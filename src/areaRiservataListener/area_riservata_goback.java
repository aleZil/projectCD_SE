package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.areaRiservataWnd;


public class area_riservata_goback implements ActionListener{
	
	JFrame option_wnd;
	public area_riservata_goback(JFrame caller)
	{
		option_wnd=caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		((areaRiservataWnd) option_wnd).showOption("asd");
	}

}
