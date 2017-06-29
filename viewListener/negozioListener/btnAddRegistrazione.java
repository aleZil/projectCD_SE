package negozioListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import viewNegozio.negozioWnd;


public class btnAddRegistrazione implements ActionListener {

	negozioWnd caller;
	
	public btnAddRegistrazione(negozioWnd caller)
	{
		this.caller=caller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		caller.registraNuovoUtente();
	}

}
