package negozioListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import viewNegozio.negozioWnd;

public class btnEffettuaRicerca implements ActionListener,KeyListener{

	negozioWnd caller;
	
	public btnEffettuaRicerca(negozioWnd caller)
	{
		this.caller=caller;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		caller.search();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		caller.search();
	}

	
}
