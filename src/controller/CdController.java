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

		if(this.arWnd.validValues())
		{
			// Recupero i dati dal form 

			// ---------------------------------------------------- info base
			String titolo = arWnd.getCdTitle();
			BigDecimal prezzo = new BigDecimal(arWnd.getCdPrice());
			String descrizione = arWnd.getCdDesc();

			int pezziMagazzino = Integer.parseInt(arWnd.getAmount());
			Genere genere  = new Genere();
			genere.getById(arWnd.getGenderId());

			// ---------------------------------------------------- brani
			ListModel<String> titoloBrani = arWnd.getTrackList();
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
				//JOptionPane.showMessageDialog(this, "Cd Inserito!","Info!",JOptionPane.INFORMATION_MESSAGE);
				throw new InsertFailedException("Cd non inserito.");
				//JOptionPane.showMessageDialog(this, "Errore durante l'inserimento!","Errore!",JOptionPane.ERROR_MESSAGE);
			}
		}
		return true;
	}
	
	public Boolean update() {

		if(this.modWnd.validValues())
		{
			int id = modWnd.getCdId();
			Cd cd = new Cd();
			cd.getById(id);
			
			cd.setTitolo(modWnd.getCdTitle());
			cd.setPrezzo(new BigDecimal(modWnd.getCdPrice()));
			cd.setDescrizione(modWnd.getCdDesc());
			cd.setPezziMagazzino(Integer.parseInt(modWnd.getAmount()));
			
			Genere genere  = new Genere();
			genere.getByNome(modWnd.getGender());
			cd.setGenere(genere);
			
			// ---------------------------------------------------- brani
			ListModel<String> titoloBrani = modWnd.getTrackList();
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
		}
		return true;
	}
}
