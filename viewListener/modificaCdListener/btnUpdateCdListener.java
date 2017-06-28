package modificaCdListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import viewAreaRiservata.modificaCdWnd;

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
