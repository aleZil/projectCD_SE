package negozioListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import viewNegozio.negozioWnd;

public class btnShowHome implements ActionListener {

	negozioWnd caller;
	
	public btnShowHome(negozioWnd caller) {
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		caller.showHome();
	}

}
