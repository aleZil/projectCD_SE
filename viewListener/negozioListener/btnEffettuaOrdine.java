package negozioListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import viewNegozio.negozioWnd;

public class btnEffettuaOrdine implements ActionListener{

	negozioWnd caller;
	public btnEffettuaOrdine(negozioWnd caller)
	{
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		caller.effettuaPagamento();
	}
}
