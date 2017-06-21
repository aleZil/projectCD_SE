package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import projectCD_SE.area_riservata_wnd;


public class carrello_goback implements ActionListener{
	
	carrello_wnd boh_wnd;
	public carrello_goback(carrello_wnd caller)
	{
		boh_wnd=caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//boh_wnd.showOption("asd");
		//JOptionPane.showMessageDialog(boh_wnd, "Tornato indietro!");
		boh_wnd.showCarrello("asd");
		
	}

}
