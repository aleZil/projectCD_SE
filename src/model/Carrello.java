package model;

import java.util.ArrayList;


public class Carrello {

	ArrayList<RigaCarrello> righeCarrello;
	
	public Carrello() {
		this.righeCarrello = new ArrayList<RigaCarrello>();
	}
	
	public ArrayList<RigaCarrello> getCarrello() {
		return this.righeCarrello;
	}
	
	public void addRow(RigaCarrello row) {
		this.righeCarrello.add(row);
	}
	
	public void updateQta(Cd cd, int newQta) {
		
		for (int i = 0; i < this.righeCarrello.size(); i++) {
			
			RigaCarrello riga = this.righeCarrello.get(i);
			
			if (riga.getCd().getId() == cd.getId()) {
				
			}
			
		}
	}
	
}
