package listeners;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import projectCD_SE.area_riservata_wnd;
import sun.swing.SwingLazyValue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.MessageDigest;
import java.sql.*;


public class area_riservata_login implements ActionListener,KeyListener {
	
	area_riservata_wnd login_wnd;
	
	public area_riservata_login(JFrame caller)
	{
		login_wnd=(area_riservata_wnd) caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Login();
	}

	private void Login()
	{
		String user=login_wnd.getUsername();
		String pssw=login_wnd.getPassword();
		
		String query="SELECT * FROM personale WHERE username=? AND password=MD5(?)";
		try {

			Class.forName("org.postgresql.Driver");
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

			JOptionPane.showMessageDialog(login_wnd, e1.getMessage());
			return;
		}

		try
		{
			Connection con=DriverManager.getConnection("jdbc:postgresql://db-cdproject.czz77hrlmvcn.eu-west-1.rds.amazonaws.com/progetto_cd","hanzo","neversurrender");
			PreparedStatement pst=con.prepareStatement(query);
			pst.clearParameters();
			pst.setString(1, user);
			pst.setString(2, pssw);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				login_wnd.showOption(user);
				//Controllo quantità
				login_wnd.checkAmount();
			}
			else
			{
				JOptionPane.showMessageDialog(login_wnd, "Username o password non corretti!");
			}
			pst.close();
			rs.close();
			con.close();
			
		}
		catch (Exception exc)
		{
			JOptionPane.showMessageDialog(login_wnd,exc.getMessage());
		}
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
