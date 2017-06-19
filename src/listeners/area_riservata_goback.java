package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import projectCD_SE.area_riservata_wnd;


public class area_riservata_goback implements ActionListener{
	
	area_riservata_wnd option_wnd;
	public area_riservata_goback(area_riservata_wnd caller)
	{
		option_wnd=caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		option_wnd.showOption("asd");
	}

}
