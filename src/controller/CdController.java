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

		// ---------------------------------------------------- CONTROLLO TITOLO
		String titolo = arWnd.getCdTitle();
		if(!dataValidator.checkString(titolo) || titolo.equals("") ) {
			throw new InsertFailedException("Inserire un titolo valido.");
		}
		
		// ---------------------------------------------------- CONTROLLO PREZZO

		//Nel caso l'utente inserisca il prezzo con la virgola, la convertiamo in un punto (altrimenti errore)
		String prezzoStr = arWnd.getCdPrice();
		prezzoStr = prezzoStr.replace(',', '.');
		
		if(prezzoStr.equals("") || !dataValidator.checkCdPrice(prezzoStr) ){
			throw new InsertFailedException("Inserire un prezzo valido.");
		}
		
		BigDecimal prezzo = new BigDecimal(prezzoStr);

		if(prezzo.compareTo(BigDecimal.ZERO) == 0){
			throw new InsertFailedException("Inserire un prezzo valido.");
		}
		
		// ---------------------------------------------------- CONTROLLO QUANTITA'

		String pezziMagazzinoStr = arWnd.getAmount();
		if(pezziMagazzinoStr.equals("") || !dataValidator.checkInteger(pezziMagazzinoStr) ){
			throw new InsertFailedException("Inserire una quantità valida.");
		}
		

		
		DefaultListModel<String> titoloBrani = arWnd.getTrackList();

		if(dataValidator.validValues(titolo, titoloBrani, prezzoStr, pezziMagazzinoStr))
		{
			int pezziMagazzino = Integer.parseInt(pezziMagazzinoStr);
			String descrizione = arWnd.getCdDesc();
			
			Genere genere  = new Genere();
			genere.getById(arWnd.getGenderId());
			
			// ---------------------------------------------------- BRANI
			//ListModel<String> titoloBrani = arWnd.getTrackList();
			ArrayList<Brano> brani = new ArrayList<Brano>(); 

			for(int i=0; i < titoloBrani.getSize(); i++){
				brani.add(new Brano(titoloBrani.getElementAt(i), i));
			}

			// ---------------------------------------------------- BAND/MUSICISTA TITOLARE
			Musicista titolare = new Musicista();
			titolare.getById(arWnd.getMusicianId());

			// ---------------------------------------------------- PARTECIPANTI
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
			}else{
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	public Boolean update() {

		// ---------------------------------------------------- INFO BASE
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
			
			// ---------------------------------------------------- BRANI
			ArrayList<Brano> brani = new ArrayList<Brano>(); 

			for(int i=0; i < titoloBrani.getSize(); i++){
				brani.add(new Brano(titoloBrani.getElementAt(i), i));
			}
			
			// ---------------------------------------------------- MUSICISTA TITOLARE
			Musicista titolare = new Musicista();
			titolare.getByNomeArte(modWnd.getMusician());

			// ---------------------------------------------------- PARTECIPANTI
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
