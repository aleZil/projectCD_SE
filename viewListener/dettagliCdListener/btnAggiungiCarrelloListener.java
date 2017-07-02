package dettagliCdListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Cd;
import viewNegozio.dettagliCdWnd;
import viewNegozio.negozioWnd;

public class btnAggiungiCarrelloListener implements ActionListener {

	negozioWnd negozio;
	dettagliCdWnd caller;
	
	public btnAggiungiCarrelloListener(negozioWnd negozio,dettagliCdWnd caller) {
		this.negozio=negozio;
		this.caller=caller;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Cd cd=caller.getCd();
		if(negozio.isLogged())
		{
			if(negozio.controlloCarrello(cd.getId()))
			{
				JOptionPane.showMessageDialog(negozio, "Prodotto aggiunto al carrello","Info",JOptionPane.INFORMATION_MESSAGE);
				caller.dispose();
				negozio.aggiungiAlCarrello(cd.getTitolo(), cd.getId());
			}
			else
			{
				JOptionPane.showMessageDialog(negozio, "Cd già nel carrello","Info",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(negozio, "Devi eseguire il login prima di qualsiasi acquisto","Info",JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
