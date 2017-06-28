package areaRiservataListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import sun.swing.SwingLazyValue;
import viewAreaRiservata.areaRiservataWnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.MessageDigest;
import java.sql.*;


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
		String pssw=((areaRiservataWnd) caller).getPassword();
		
		String query="SELECT * FROM personale WHERE username=? AND password=MD5(?)";
		try {

			Class.forName("org.postgresql.Driver");
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

			JOptionPane.showMessageDialog(caller, e1.getMessage());
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
				((areaRiservataWnd) caller).showOption();
				//Controllo quantità
				checkAmount();
			}
			else
			{
				JOptionPane.showMessageDialog(caller, "Username o password non corretti!");
			}
			pst.close();
			rs.close();
			con.close();
			
		}
		catch (Exception exc)
		{
			JOptionPane.showMessageDialog(caller,exc.getMessage());
		}
	}
	
	public void checkAmount()
	{
		String query="SELECT * FROM CD";

		try
		{
			Connection con=DriverManager.getConnection("jdbc:postgresql://db-cdproject.czz77hrlmvcn.eu-west-1.rds.amazonaws.com/progetto_cd","hanzo","neversurrender");
			
			 Statement stm=con.createStatement();
			 
			 ResultSet res=stm.executeQuery(query);
			 
			 boolean showmessage=false;
			 int amount=0;
			 String list="";
			 while(res.next())
			 {
				 amount=res.getInt("pezzi_magazzino");
				 if(amount==1)
				 {
					 showmessage=true;
					 list+=res.getString("titolo")+"\n";
				 }
			 }
			 
			 if(showmessage)
			 {
				 JOptionPane.showMessageDialog(caller, "Attenzione i seguenti titoli sono in esaurimento: "+list,"Attenzione!", JOptionPane.WARNING_MESSAGE);
			 }
		}
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(caller, exception.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
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
