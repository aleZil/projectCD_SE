package listeners;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import projectCD_SE.home_page;
import sun.swing.SwingLazyValue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.MessageDigest;
import java.sql.*;


public class home_page_visualizza_carrello implements ActionListener,KeyListener {
	
	home_page carrello_wnd; // COSA SUCCEDE QUI???????????
	
	public home_page_visualizza_carrello(JFrame caller)
	{
		carrello_wnd=(home_page) caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Login();
	}

	private void Login()
	{
		String user=carrello_wnd.getUsername();
		String pssw=carrello_wnd.getPassword();
		
		
		
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
