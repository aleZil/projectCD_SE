package controller;


import java.math.BigDecimal;
import java.util.ArrayList;

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


public class CdController {

	private areaRiservataWnd wnd;
	private Cd model;

	public CdController(areaRiservataWnd wnd){
		this.wnd = wnd;
	}

	public Boolean insert() throws InsertFailedException {

		if(this.wnd.validValues())
		{
			// Recupero i dati dal form 

			// ---------------------------------------------------- info base
			String titolo = wnd.getCdTitle();
			BigDecimal prezzo = new BigDecimal(wnd.getCdPrice());
			String descrizione = wnd.getCdDesc();

			int pezziMagazzino = Integer.parseInt(wnd.getAmount());
			Genere genere  = new Genere();
			genere.getById(wnd.getGenderId());

			// ---------------------------------------------------- brani
			ListModel<String> titoloBrani = wnd.getTrackList();
			ArrayList<Brano> brani = new ArrayList<Brano>(); 

			for(int i=0; i < titoloBrani.getSize(); i++){
				brani.add(new Brano(titoloBrani.getElementAt(i), i));
			}

			// ---------------------------------------------------- musicista titolare
			Musicista titolare = new Musicista();
			titolare.getById(wnd.getMusicianId());

			// ---------------------------------------------------- partecipanti
			ListModel<String> listaNomiPartecipanti = wnd.getPartecipantList();
			ArrayList<Musicista> partecipanti = new ArrayList<Musicista>(); 

			for(int i=0; i < listaNomiPartecipanti.getSize(); i++){
				Musicista p = new Musicista();
				p.getByNomeArte(listaNomiPartecipanti.getElementAt(i));

				partecipanti.add(p);
			}

			// creazione del Cd
			model = new Cd(titolo, prezzo, descrizione, pezziMagazzino, brani, genere, titolare, partecipanti);

			if(!model.insert()) {
				//JOptionPane.showMessageDialog(this, "Cd Inserito!","Info!",JOptionPane.INFORMATION_MESSAGE);
				throw new InsertFailedException("Cd non inserito.");
				//JOptionPane.showMessageDialog(this, "Errore durante l'inserimento!","Errore!",JOptionPane.ERROR_MESSAGE);
			}
		}
		return true;
	}
	
	public Boolean update() {

		if(this.wnd.validValues())
		{
			// recupero del cd
			Cd cd = new Cd();

			// Recupero i dati dal form 

			if(!cd.update()) {
				throw new InsertFailedException("Cd non moodificato.");
			}
		}
		return true;
	}
}
