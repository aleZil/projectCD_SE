package controller;

import javax.swing.JOptionPane;

import model.Genere;
import utility.dataValidator;
import viewAreaRiservata.areaRiservataWnd;


public class GenereController {
	
	private areaRiservataWnd wnd;
	private Genere model;

	public GenereController(areaRiservataWnd wnd){
		this.wnd = wnd;
	}

	public Boolean insert() {
		
		String nomeGenere = wnd.getGenName();
		if(!dataValidator.checkString(nomeGenere))
		{
			//JOptionPane.showMessageDialog(this, "Inserire nome genere!","Attenzione!",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		Genere newGen = new Genere(nomeGenere);

		if(newGen.insert()) {
			//JOptionPane.showMessageDialog(this, "Nuovo genere inserito!","Info!",JOptionPane.INFORMATION_MESSAGE);
			//wnd.txtGen.setText("");
			return false;
		} else {
			//JOptionPane.showMessageDialog(this, "Genere già esistente!","Errore!",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
	}

}



