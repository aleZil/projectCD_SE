package areaRiservataListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import model.Autenticazione;
import model.Cd;
import sun.swing.SwingLazyValue;
import viewAreaRiservata.areaRiservataWnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;


public class btnLoginListener implements ActionListener,KeyListener {
	
	JFrame caller;
	
	public btnLoginListener(JFrame caller)
	{
		this.caller=(areaRiservataWnd) caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Login();
	}

	private void Login()
	{
		String user=((areaRiservataWnd) caller).getUsername();
		String pwd=((areaRiservataWnd) caller).getPassword();
		Autenticazione auth = new Autenticazione("personale", user, pwd);
		
		if (auth.login()) {
			((areaRiservataWnd) caller).showOption();
			//Controllo quantità
			checkAmount();
		} else {
			JOptionPane.showMessageDialog(caller, "Username o password non corretti!");
		}
		
	}
	
	public void checkAmount()
	{
		ArrayList<String> listaInEsaurimento = new Cd().getTitoliInEsaurimento();
		String list="";
		int nProdotti = listaInEsaurimento.size();
		
		for(int i=0; i < nProdotti; i++) {
			list += "\n- " + listaInEsaurimento.get(i)+"\n";
		}
		
		if(nProdotti == 1)
			JOptionPane.showMessageDialog(caller, "Attenzione "+ nProdotti+" prodotto in esaurimento: "+list,"Attenzione!", JOptionPane.WARNING_MESSAGE);
		else if (nProdotti > 1)
			JOptionPane.showMessageDialog(caller, "Attenzione "+ nProdotti+" prodotti in esaurimento: "+list,"Attenzione!", JOptionPane.WARNING_MESSAGE);
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Login();
	}
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
