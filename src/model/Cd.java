package model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

import javax.swing.JOptionPane;

import utility.*;



public class Cd{
	
	private Connection db;
/*
	private String codice;
	private String titolo;
	private String titoloBrani;W
	private BigDecimal prezzo;
	private Date dataInserimento;
	private Integer pezziVenduti;
	private Integer pezziMagazzino;
	private String genere;
	private List<String> musicisti;
*/
	
	public Cd(	/*String codice, 
				String titolo,
				String titoloBrani,
				BigDecimal prezzo,
				Date dataInserimento,
				Integer pezziVenduti,
				Integer pezziMagazzino,
				String genere,
				List<String> musicisti */) {
		
		/*
		this.codice = codice;
		this.titolo = titolo;
		this.titoloBrani = titoloBrani;
		this.prezzo = prezzo;
		this.dataInserimento = dataInserimento;
		this.pezziVenduti = pezziVenduti;
		this.pezziMagazzino = pezziMagazzino;
		this.genere = genere;
		this.musicisti = musicisti;
		*/
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			// print exception
		}

	}
	
	public ResultSet getByCodice(String codice) {
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM cd WHERE codice = ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setString(1, codice);
			
			rs = ps.executeQuery();
			
			if (!rs.next() ) {
				return null;
			}
		} catch (SQLException e) {
			// print error
		}
		return rs;
	
	}
	
	public ResultSet getAll()  throws SQLException {
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM cd ORDER BY titolo";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			if (!rs.next() ) {
				return null;
			}
		} catch (SQLException e) {
			// print error
		}
		return rs;
	}
	
	public Boolean updateByCodice(String codice, 
				String titolo,
				String titoloBrani,
				String descrizione,
				BigDecimal prezzo,
				Date dataInserimento) {
	
		try {
			String query="UPDATE Cd SET titolo=?,"
					+ "titoloBrani=?,"
					+ "prezzo=?,"   
					+ "data_inserimento=?,"
					+ "descrizione=? "
					+ "WHERE codice=?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			Integer i = 1;
			
			ps.setString(i++, titolo);
			ps.setString(i++, titoloBrani);
			ps.setBigDecimal(i++, prezzo); 
			ps.setDate(i++, dataInserimento);
			ps.setString(i++, descrizione);
			ps.setString(i++, codice);
			
			if( ps.executeUpdate() != 1 )
				return false;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
		
	}
}
