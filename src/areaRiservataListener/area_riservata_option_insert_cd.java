package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frame.areaRiservataWnd;


public class area_riservata_option_insert_cd implements ActionListener{
	
	areaRiservataWnd option_wnd;
	public area_riservata_option_insert_cd(areaRiservataWnd caller)
	{
		option_wnd=caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		option_wnd.showInsertCd();
	}

}
