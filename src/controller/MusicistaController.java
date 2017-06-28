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
		

		System.out.println(genere.getNome());


		
	//	genere.getById(wnd.getGenderId());
		/*//TODO
		ListModel<String> listaStrumenti = wnd.getInstrumentList();
		ArrayList<Strumento> strumenti = new ArrayList<Strumento>(); 

		for(int i=0; i < listaStrumenti.getSize(); i++){
			strumenti.add(new Brano(listaStrumenti.getElementAt(i), i));
		}
		 */
		
		if(!dataValidator.checkString(nomeMusicista)) {
			throw new MissingDataException("Inserire nome del musicista!");
		}
		/*
		if(!dataValidator.checkString(genere)) {
			throw new MissingDataException("Inserire il genere!");
		}
		*/
		/*
		//TODO sistema tutti i controlli
		if(!dataValidator.checkInteger(annoNascitaMusicista)) {
			throw new MissingDataException("Inserire l'anno di nascita!");
		}
		*/
	
		model = new Musicista(nomeMusicista, annoNascitaMusicista, genere);

		if(model.insert()) {
			System.out.println("tutoben");
			return true;
		} else {
			System.out.println("erroredc");
			throw new InsertFailedException("Musicista non inserito. Controlla se esiste già.");
		}
	}
}



