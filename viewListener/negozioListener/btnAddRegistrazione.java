package negozioListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import viewNegozio.negozioWnd;


public class btnAddRegistrazione implements ActionListener,KeyListener {

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
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		caller.registraNuovoUtente();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
