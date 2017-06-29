package negozioListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Autenticazione;
import viewAreaRiservata.areaRiservataWnd;
import viewNegozio.negozioWnd;

public class btnLoginCliente implements ActionListener {

	negozioWnd caller;
	
	public btnLoginCliente(negozioWnd caller)
	{
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		caller.loginCliente();
	}
	
	private void Login()
	{
		String user=caller.getTxtUsernameLogin();
		String pwd=caller.getTxtPassLogin();
		Autenticazione auth = new Autenticazione("cliente", user, pwd);
		
		if (auth.login()) {
			JOptionPane.showMessageDialog(caller, "Benvenuto "+user+"!","Info",JOptionPane.INFORMATION_MESSAGE);
			caller.showHome();
		} else {
			JOptionPane.showMessageDialog(caller, "Username o password non corretti!");
		}
		
	}

}
