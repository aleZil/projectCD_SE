package model;

public class RigaCarrello {
	
	int nRiga;
	Cd cd;
	int qta;
	
	public RigaCarrello(Cd cd, int qta) {
		this.setCd(cd);
		this.setQta(qta);
	}
	
	public void setCd(Cd cd) {
		this.cd = cd;
	}
	
	public void setQta(int qta) {
		this.qta = qta;
	}

	public Cd getCd() {
		return this.cd;
	}
	
	public int getQta() {
		return this.qta;
	}
}
