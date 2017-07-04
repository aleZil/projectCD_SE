package controller;

import model.Carrello;
import model.Cd;
import model.RigaCarrello;

public class CarrelloController {
	
	// private wnd;
	private Carrello carrello;
	
	public CarrelloController(Carrello carrello) {
		this.carrello = carrello;
	}
	
	public void addRigaCarrello(int id) {
		
		Cd cd = new Cd();
		cd.getById(id);
		RigaCarrello row = new RigaCarrello(cd, 1);
		this.carrello.addRow(row);
	}
	
	public void incrementaQta(int nRiga) {
		this.carrello.incrementaQta(nRiga);
	}
	
	public void decrementaQta(int nRiga) {
		this.carrello.decrementaQta(nRiga);
	}
	
	public void rimuoviRiga(int nRiga) {
		this.carrello.rimuoviRiga(nRiga);
	}
	
	public boolean creaOrdine(String modalitaAcquisto, String modalitaConsegna, String ip) {
		if (this.carrello.creaOrdine(modalitaAcquisto, modalitaConsegna, ip))
			return true;
		
		return false;
	}
	
	public Carrello getCarrello() {
		return this.carrello;
	}
}
