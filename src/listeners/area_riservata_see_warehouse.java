package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import projectCD_SE.area_riservata_wnd;

public class area_riservata_see_warehouse implements ActionListener{
	
	area_riservata_wnd option_wnd;
	public area_riservata_see_warehouse(area_riservata_wnd caller)
	{
		option_wnd=caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		option_wnd.showWarehouse();
	}

}
