package listeners;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import projectCD_SE.carrello_wnd;
import sun.swing.SwingLazyValue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.MessageDigest;
import java.sql.*;


public class carrello_intro implements ActionListener,KeyListener {
	
	carrello_wnd carrello_wnd;
	
	public carrello_intro(JFrame caller)
	{
		carrello_wnd=(carrello_wnd) caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Carrello();
	}

	private void Carrello()
	{
		JOptionPane.showMessageDialog(carrello_wnd, "Hai premuto su 'Visualizza'");
	}
	
	
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Carrello();
	}
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
