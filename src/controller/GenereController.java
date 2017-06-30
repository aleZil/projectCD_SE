package controller;

import exception.InsertFailedException;
import exception.MissingDataException;
import model.Genere;
import utility.dataValidator;
import viewAreaRiservata.areaRiservataWnd;


public class GenereController {
	
	private areaRiservataWnd wnd;
	private Genere model;

	public GenereController(areaRiservataWnd wnd){
		this.wnd = wnd;
	}

	public Boolean insert() throws MissingDataException, InsertFailedException {

		String nomeGenere = wnd.getGenName();
		
		if(!dataValidator.checkString(nomeGenere) || nomeGenere.equals(""))
		{
			throw new MissingDataException("Inserire nome genere!");
		}

		model = new Genere(nomeGenere);

		if(model.insert()) {
			return true;
		} else {
			throw new InsertFailedException("Genere non inserito. Controlla se questo genere esiste già.");
		}
	}
}



