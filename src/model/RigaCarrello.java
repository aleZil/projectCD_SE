package model;

import java.math.BigDecimal;

public class RigaCarrello {
	
	Cd cd;
	int qta;
	BigDecimal prezzo;
	
	public RigaCarrello(Cd cd, int qta) {
		this.setCd(cd);
		this.setQta(qta);
		this.setPrezzo(cd.getPrezzo());
	}
	
	public void setCd(Cd cd) {
		this.cd = cd;
	}
	
	public void setQta(int qta) {
		this.qta = qta;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	
	public Cd getCd() {
		return this.cd;
	}
	
	public int getQta() {
		return this.qta;
	}
	
	public BigDecimal getPrezzo() {
		return this.prezzo;
	}
}
