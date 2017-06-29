package controller;

import java.util.ArrayList;

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
		Genere genere  = new Genere();
		genere.getByNome(wnd.getGenFromMus());
	
		ListModel<String> listaStrumenti = wnd.getInstrumentList();
		ArrayList<Strumento> strumenti = new ArrayList<Strumento>(); 

		//int annoNascita = Integer.parseInt(annoNascitaMusicista);

		/*
		if(!dataValidator.checkInteger(annoNascitaMusicista)) {
			throw new MissingDataException("Inserire l'anno di nascita!");
		}
*/
		
		for(int i=0; i < listaStrumenti.getSize(); i++){
			Strumento s = new Strumento();
			s.getByNome(listaStrumenti.getElementAt(i));
			strumenti.add(s);
		}
	
		model = new Musicista(nomeMusicista, annoNascitaMusicista, genere, strumenti);

		if(model.insert()) {
			return true;
		} else {
			throw new InsertFailedException("Musicista non inserito. Controlla se esiste già.");
		}
	}
}



