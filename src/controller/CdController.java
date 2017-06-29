package controller;


import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import exception.InsertFailedException;
import exception.MissingDataException;
import model.Brano;
import model.Cd;
import model.Genere;
import model.Musicista;
import utility.dataValidator;
import viewAreaRiservata.areaRiservataWnd;
import viewAreaRiservata.modificaCdWnd;


public class CdController {

	private areaRiservataWnd arWnd;
	private modificaCdWnd modWnd;
	private Cd model;

	public CdController(areaRiservataWnd wnd){
		this.arWnd = wnd;
	}

	public CdController(modificaCdWnd modificaCdWnd) {
		this.modWnd = modificaCdWnd;
	}

	public Boolean insert() throws InsertFailedException {

		// ---------------------------------------------------- info base
		String titolo = arWnd.getCdTitle();
		
		//Nel caso l'utente inserisca il prezzo con la virgola, la convertiamo in un punto (altrimenti errore)
		String prezzoStr = arWnd.getCdPrice();
		prezzoStr = prezzoStr.replace(',', '.');
		
		
		String pezziMagazzinoStr = arWnd.getAmount();
		DefaultListModel<String> titoloBrani = arWnd.getTrackList();
		
		if(dataValidator.validValues(titolo, titoloBrani, prezzoStr, pezziMagazzinoStr))
		{
			BigDecimal prezzo = new BigDecimal(prezzoStr);
			int pezziMagazzino = Integer.parseInt(pezziMagazzinoStr);
			String descrizione = arWnd.getCdDesc();
			
			Genere genere  = new Genere();
			genere.getById(arWnd.getGenderId());
			
			// ---------------------------------------------------- brani
			//ListModel<String> titoloBrani = arWnd.getTrackList();
			ArrayList<Brano> brani = new ArrayList<Brano>(); 

			for(int i=0; i < titoloBrani.getSize(); i++){
				brani.add(new Brano(titoloBrani.getElementAt(i), i));
			}

			// ---------------------------------------------------- musicista titolare
			Musicista titolare = new Musicista();
			titolare.getById(arWnd.getMusicianId());

			// ---------------------------------------------------- partecipanti
			ListModel<String> listaNomiPartecipanti = arWnd.getPartecipantList();
			ArrayList<Musicista> partecipanti = new ArrayList<Musicista>(); 

			for(int i=0; i < listaNomiPartecipanti.getSize(); i++){
				Musicista p = new Musicista();
				p.getByNomeArte(listaNomiPartecipanti.getElementAt(i));

				partecipanti.add(p);
			}

			// creazione del Cd
			model = new Cd(titolo, prezzo, descrizione, pezziMagazzino, brani, genere, titolare, partecipanti);

			if(!model.insert()) {
				throw new InsertFailedException("Cd non inserito.");
			}
		}
		return true;
	}
	
	public Boolean update() {

		// ---------------------------------------------------- info base
		String titolo = modWnd.getCdTitle();
		
		String prezzoStr = modWnd.getCdPrice();
		String pezziMagazzinoStr = modWnd.getAmount();
		DefaultListModel<String> titoloBrani = modWnd.getTrackList();
		
		if(dataValidator.validValues(titolo, titoloBrani, prezzoStr, pezziMagazzinoStr))
		{
			int id = modWnd.getCdId();
			Cd cd = new Cd();
			cd.getById(id);
			
			cd.setTitolo(modWnd.getCdTitle());
			cd.setPrezzo(new BigDecimal(prezzoStr));
			cd.setDescrizione(modWnd.getCdDesc());
			cd.setPezziMagazzino(Integer.parseInt(pezziMagazzinoStr));
			
			Genere genere  = new Genere();
			genere.getByNome(modWnd.getGender());
			cd.setGenere(genere);
			
			// ---------------------------------------------------- brani
			ArrayList<Brano> brani = new ArrayList<Brano>(); 

			for(int i=0; i < titoloBrani.getSize(); i++){
				brani.add(new Brano(titoloBrani.getElementAt(i), i));
			}
			
			// ---------------------------------------------------- musicista titolare
			Musicista titolare = new Musicista();
			titolare.getByNomeArte(modWnd.getMusician());

			// ---------------------------------------------------- partecipanti
			ListModel<String> listaNomiPartecipanti = modWnd.getPartecipantList();
			ArrayList<Musicista> partecipanti = new ArrayList<Musicista>(); 

			for(int i=0; i < listaNomiPartecipanti.getSize(); i++){
				Musicista p = new Musicista();
				p.getByNomeArte(listaNomiPartecipanti.getElementAt(i));

				partecipanti.add(p);
			}
			
			cd.setTitolare(titolare);
			cd.setPartecipanti(partecipanti);
			cd.setBrani(brani);
			
			if(!cd.update()) {
				throw new InsertFailedException("Cd non modificato.");
			}
			return true;
		}
		return false;
	}
}
