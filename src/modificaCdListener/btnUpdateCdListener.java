package modificaCdListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import viewModificaCd.modificaCdWnd;

import javax.swing.JFrame;

public class btnUpdateCdListener implements ActionListener{
	
	JFrame caller;
	
	public btnUpdateCdListener(JFrame caller) {
		// TODO Auto-generated constructor stub
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		((modificaCdWnd)caller).saveUpdate();
	}

}
