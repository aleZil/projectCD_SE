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

import model.Cd;
import view.areaRiservataWnd;

public class area_riservata_newcd_insert implements ActionListener,KeyListener{

	areaRiservataWnd ar_ref;
	private Cd modelCd = new Cd();

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
			try
			{
				//Inserisco il record in cd
				
				String cdTitle=ar_ref.getCdTitle();
				String trackList=ar_ref.getTrackList();
				BigDecimal cdPrice=new BigDecimal(ar_ref.getCdPrice());
				Date insertDate=new java.sql.Date(System.currentTimeMillis());
				String cdDesc=ar_ref.getCdDesc();
				int amount=Integer.parseInt(ar_ref.getAmount());
				int genderId=ar_ref.getGenderId();
				int musicianId=ar_ref.getMusicianId();

				String titolo=ar_ref.getCdTitle();
				String titoloBrani=ar_ref.getTrackList();
				BigDecimal prezzo=new BigDecimal(ar_ref.getCdPrice());
				String descrizione=ar_ref.getCdDesc();
				int pezziMagazzino=Integer.parseInt(ar_ref.getAmount());
				int genereId=ar_ref.getGenderId();
				
				Boolean status = modelCd.insert(titolo, titoloBrani, descrizione, prezzo, pezziMagazzino, genereId);


				if(status == true)
				{
					
					//Inserisco in partecipa
					JOptionPane.showMessageDialog(ar_ref,"Cd inserito correttamente","Info",JOptionPane.INFORMATION_MESSAGE);
					ar_ref.clearComponents();
					
					
				}
				else
				{
					//Il cd che si prova a inserire esiste già
					JOptionPane.showMessageDialog(ar_ref,"Il cd che stai inserendo esiste già!","Info",JOptionPane.ERROR_MESSAGE);
				}

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
