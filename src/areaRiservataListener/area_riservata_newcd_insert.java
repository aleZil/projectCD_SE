package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import view.areaRiservataWnd;

public class area_riservata_newcd_insert implements ActionListener,KeyListener{

	areaRiservataWnd ar_ref;

	public area_riservata_newcd_insert(areaRiservataWnd caller)
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
				
				String cdCode=ar_ref.getCdCode();
				String cdTitle=ar_ref.getCdTitle();
				String trackList=ar_ref.getTrackList();
				BigDecimal cdPrice=new BigDecimal(ar_ref.getCdPrice());
				Date insertDate=new java.sql.Date(System.currentTimeMillis());
				String cdDesc=ar_ref.getCdDesc();
				int amount=Integer.parseInt(ar_ref.getAmount());
				int genderId=ar_ref.getGenderId();
				int musicianId=ar_ref.getMusicianId();
				
				pst.setString(1,cdCode);
				pst.setString(2, cdTitle);
				pst.setString(3,trackList);
				pst.setBigDecimal(4,cdPrice);
				pst.setDate(5,insertDate);
				pst.setString(6,cdDesc);
				pst.setInt(7,amount);
				pst.setInt(8,genderId);
				pst.setInt(9,musicianId);

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
