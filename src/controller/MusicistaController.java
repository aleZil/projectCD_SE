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

	public Boolean insert() throws MissingDataException {
		
		String nomeMusicista = wnd.getMusName();
		if(!dataValidator.checkString(nomeMusicista) || nomeMusicista.equals("") ) {
			throw new InsertFailedException("Inserire un nome valido.");
		}
				
		String annoNascitaMusicistaString = wnd.getYearMus();
		if(annoNascitaMusicistaString.equals("") || !dataValidator.checkInteger(annoNascitaMusicistaString) ){
			throw new InsertFailedException("Inserire un anno valido.");
		}
		
		Integer annoNascitaMusicistaInteger = Integer.parseInt(annoNascitaMusicistaString);

		Boolean isBand = wnd.getIsBand();	
		
		Genere genere  = new Genere();
		genere.getByNome(wnd.getGenFromMus());
	
		ListModel<String> listaStrumenti = wnd.getInstrumentList();
		ArrayList<Strumento> strumenti = new ArrayList<Strumento>(); 
		
		for(int i=0; i < listaStrumenti.getSize(); i++){
			Strumento s = new Strumento();
			s.getByNome(listaStrumenti.getElementAt(i));
			strumenti.add(s);
		}
		
		//Calcolo l'anno corrente
		Calendar now = Calendar.getInstance(); 
		int year = now.get(Calendar.YEAR);

		if(annoNascitaMusicistaInteger > 1600 && annoNascitaMusicistaInteger <= year){
			
			model = new Musicista(nomeMusicista, annoNascitaMusicistaInteger, genere, strumenti, isBand);
			
			if(model.insert()) {
				return true;
			} else {
				throw new InsertFailedException("Inserimento fallito.");
			}
		}else{
			throw new MissingDataException("Inserire un anno valido! Non siamo ancora nel ", annoNascitaMusicistaString);
		}
	}
}



