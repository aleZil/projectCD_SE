package listeners;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import projectCD_SE.area_riservata_wnd;
import sun.swing.SwingLazyValue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.*;


public class area_riservata_btn_login implements ActionListener {
	
	area_riservata_wnd login_wnd;
	
	public area_riservata_btn_login(JFrame caller)
	{
		login_wnd=(area_riservata_wnd) caller;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String user=login_wnd.getUsername();
		String pssw=login_wnd.getPassword();
		
		String query="SELECT * FROM personale WHERE username=? AND password=MD5('?')";
		try {

			Class.forName("org.postgresql.Driver");
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

			JOptionPane.showMessageDialog(null, e1.getMessage());
			return;
		}

		try
		{

			JOptionPane.showMessageDialog(null, "Mett gay!");
			Connection con=DriverManager.getConnection("db-cdproject.czz77hrlmvcn.eu-west-1.rds.amazonaws.com","hanzo","neversurrender");
			PreparedStatement pst=con.prepareStatement(query);
			pst.clearParameters();
			pst.setString(1, user);
			pst.setString(2, pssw);
			ResultSet rs=pst.executeQuery(query);
			if(rs.first())
			{
				JOptionPane.showMessageDialog(null, "Benvenuto "+user+"!");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Mett gay!");
			}
			pst.close();
			rs.close();
			con.close();
			
		}
		catch (ParseException exc)
		{
			JOptionPane.showMessageDialog(null,exc.getMessage());
		}
		catch(SQLException exc)
		{
			JOptionPane.showMessageDialog(null,exc.getMessage());
		}
	}

}
