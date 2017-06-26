package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utility.Db;

public class Catalogo {

	private Connection db;
	
	ArrayList<Cd> listProducts; 
	
	public Catalogo() {
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.listProducts = new ArrayList<Cd>();
	}
	
	private void addCd(Cd cd) {
		this.listProducts.add(cd);
	}
	
	public ArrayList<Cd> getList() {
		return this.listProducts;
	}
	
	
	public void getAll() {
		
		String codice;
		String titolo;
		BigDecimal prezzo;
		Date dataInserimento;
		String descrizione;
		Integer pezziVenduti;
		Integer pezziMagazzino;
		Genere genere;
		Musicista titolare;
		ArrayList<Musicista> partecipanti;
		
		try {
			String query = "SELECT codice, titolo,  prezzo, data_inserimento, genere_id, G.nome AS genere, descrizione, pezzi_venduti, pezzi_magazzino "
					+ "FROM cd AS C "
					+ "JOIN Genere AS G "
					+ "ON C.genere_id = G.id "
					+ "ORDER BY titolo";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while( rs.next() ){
				codice = rs.getString("codice");
				titolo = rs.getString("titolo");
				prezzo = rs.getBigDecimal("prezzo");
				dataInserimento = rs.getDate("data_inserimento");
				descrizione = rs.getString("descrizione");
				pezziVenduti = rs.getInt("pezzi_venduti");
				pezziMagazzino = rs.getInt("pezzi_magazzino");
				
				genere = new Genere();
				genere.getById(rs.getInt("genere_id"));
				
				titolare = new Musicista();
				titolare.getTitolareByCodiceCd(codice);
				
				partecipanti = new Musicista().getPartecipantiByCodiceCd(codice);

				this.addCd(new Cd(codice, titolo, prezzo, dataInserimento, descrizione, pezziVenduti, pezziMagazzino, genere, titolare, partecipanti));
			}
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
