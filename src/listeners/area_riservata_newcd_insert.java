package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import projectCD_SE.area_riservata_wnd;

public class area_riservata_newcd_insert implements ActionListener,KeyListener{

	area_riservata_wnd ar_ref;

	public area_riservata_newcd_insert(area_riservata_wnd caller)
	{
		ar_ref=caller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		AddNewCd();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		AddNewCd();
	}
	
	private void AddNewCd()
	{
		if(ar_ref.validValues())
		{
			String insertCdQuery="INSERT INTO Cd (codice,titolo,titoli_pezzi,prezzo,data_inserimento,descrizione,pezzi_magazzino,genere_id,musicista_id) VALUES (?,?,?,?,?,?,?,?,?)";
			String insertParecipa="INSERT INTO Partecipazione (cd_codice,musicista_id,is_titolare) VALUES (?,?,?)";
			try
			{
				Connection con=DriverManager.getConnection("jdbc:postgresql://db-cdproject.czz77hrlmvcn.eu-west-1.rds.amazonaws.com/progetto_cd","hanzo","neversurrender");

				//Inserisco il record in cd
				PreparedStatement pst=con.prepareStatement(insertCdQuery);
				pst.clearParameters();
				pst.setString(1,ar_ref.getCdCode());
				pst.setString(2, ar_ref.getCdTitle());
				pst.setString(3, ar_ref.getTrackList());
				pst.setBigDecimal(4,ar_ref.getCdPrice());
				pst.setDate(5,new java.sql.Date(System.currentTimeMillis()));
				pst.setString(6,ar_ref.getCdDesc());
				pst.setInt(7, ar_ref.getAmount());
				pst.setInt(8, ar_ref.getGenderId());
				pst.setInt(9, ar_ref.getMusicianId());

				if(0<pst.executeUpdate())
				{
					//Inserisco in partecipa
					pst=con.prepareStatement(insertParecipa);
					pst.clearParameters();
					pst.setString(1, ar_ref.getCdCode());
					pst.setInt(2, ar_ref.getMusicianId());
					pst.setBoolean(3, ar_ref.isLeader());
					//Sicuramente manca questo record,visto che non esiste il corrispondente cd
					pst.executeUpdate();
					JOptionPane.showMessageDialog(ar_ref,"Cd inserito correttamente","Info",JOptionPane.INFORMATION_MESSAGE);
					ar_ref.clearComponents();
				}
				else
				{
					//Il cd che si prova a inserire esiste già
					JOptionPane.showMessageDialog(ar_ref,"Il cd che stai inserendo esiste già!","Info",JOptionPane.ERROR_MESSAGE);
				}
				pst.close();
				con.close();

			}
			catch (Exception exception)
			{
				JOptionPane.showMessageDialog(ar_ref, exception.getMessage());
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
