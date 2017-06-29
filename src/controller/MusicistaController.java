package controller;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.ListModel;

import exception.InsertFailedException;
import exception.MissingDataException;
import model.Brano;
import model.Genere;
import model.Musicista;
import model.Strumento;
import utility.dataValidator;
import viewAreaRiservata.areaRiservataWnd;


public class MusicistaController {
	
	private areaRiservataWnd wnd;
	private Musicista model;

	public MusicistaController(areaRiservataWnd wnd){
		this.wnd = wnd;
	}

	public Boolean insert() throws MissingDataException, InsertFailedException {
		
		String nomeMusicista = wnd.getMusName();
		Integer annoNascitaMusicista = wnd.getYearMus();
		Boolean isBand = wnd.getIsBand();
		Genere genere  = new Genere();
		genere.getByNome(wnd.getGenFromMus());
	
		ListModel<String> listaStrumenti = wnd.getInstrumentList();
		ArrayList<Strumento> strumenti = new ArrayList<Strumento>(); 

		Calendar now = Calendar.getInstance(); 
		//Calcolo l'anno corrente
		int year = now.get(Calendar.YEAR);
		
		//Controllo validità annoNascitaMusicista
		if(annoNascitaMusicista < 1600 || annoNascitaMusicista > year){
			throw new InsertFailedException("L'anno inserito non è valido");
		}
		
		
		for(int i=0; i < listaStrumenti.getSize(); i++){
			Strumento s = new Strumento();
			s.getByNome(listaStrumenti.getElementAt(i));
			strumenti.add(s);
		}
	
		model = new Musicista(nomeMusicista, annoNascitaMusicista, genere, strumenti, isBand);

		if(model.insert()) {
			return true;
		} else {
			throw new InsertFailedException("Musicista non inserito. Controlla se esiste già.");
		}
	}
}



