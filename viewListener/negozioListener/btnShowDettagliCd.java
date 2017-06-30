package negozioListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import viewNegozio.dettagliCdWnd;
import viewNegozio.negozioWnd;

public class btnShowDettagliCd implements ActionListener {

	negozioWnd caller;
	
	public btnShowDettagliCd(negozioWnd caller) {
		// TODO Auto-generated constructor stub
		this.caller=caller;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(caller.getSelectedCd()!=-1)
			new dettagliCdWnd(caller.getSelectedCd());
		else
			JOptionPane.showMessageDialog(caller, "Seleziona un cd","Info",JOptionPane.WARNING_MESSAGE);
	}

}
