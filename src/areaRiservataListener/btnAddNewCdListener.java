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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Cd;

public class btnAddNewCdListener implements ActionListener,KeyListener{

	JFrame caller;
	private Cd modelCd = new Cd();

	public btnAddNewCdListener(JFrame caller)
	{
		this.caller=caller;
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
		/*
		if(ar_ref.validValues())
		{
			try
			{
				//Recupero i dati dal form 
				
				String titolo=ar_ref.getCdTitle();
				String titoloBrani=ar_ref.getTrackList();
				BigDecimal prezzo=new BigDecimal(ar_ref.getCdPrice());
				String descrizione=ar_ref.getCdDesc();
				int pezziMagazzino=Integer.parseInt(ar_ref.getAmount());
				int genereId=ar_ref.getGenderId();
				
				// Inserisco il record nel DB 
				
				Boolean status = modelCd.insert(titolo, descrizione, prezzo, pezziMagazzino, genereId);

				if (status == true) {
					//Inserisco in partecipa
					JOptionPane.showMessageDialog(ar_ref,"Cd inserito correttamente","Info",JOptionPane.INFORMATION_MESSAGE);
					ar_ref.clearComponents();
				} else {
					//Il cd che si prova a inserire esiste già
					JOptionPane.showMessageDialog(ar_ref,"Il cd che stai inserendo esiste già!","Info",JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception exception) {
				JOptionPane.showMessageDialog(ar_ref, exception.getMessage());
			}
		}
		*/
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
