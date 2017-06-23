package model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

import javax.swing.JOptionPane;

import utility.*;


public class Cd{
	
	private Connection db;
	
	public Cd() {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
		}
		return rs;
	
	}
	
	public ResultSet getAll() {
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM cd ORDER BY titolo";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			if (!rs.next() ) {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}
	
	
	public Boolean insert(String titolo,
			String titoloBrani,
			String descrizione,
			BigDecimal prezzo,
			Integer pezziMagazzino,
			Integer genereId) {

		try {
			String insertCdQuery="INSERT INTO Cd "
					+ "(titolo, titoli_pezzi, prezzo, descrizione, pezzi_magazzino, genere_id) "
					+ "VALUES (?,?,?,?, ?)";
			
			PreparedStatement psIns = this.db.prepareStatement(insertCdQuery);
			
			String insertParecipa="INSERT INTO Partecipazione "
					+ "(cd_codice,musicista_id,is_titolare) "
					+ "VALUES (?,?,?)";
			
			int i = 1;
			psIns.setString(i++, titolo);
			psIns.setString(i++, titoloBrani);
			psIns.setBigDecimal(i++ ,prezzo);
			psIns.setString(i++, descrizione);
			psIns.setInt(i++ , pezziMagazzino);
			psIns.setInt(i++, genereId);
			
			/*
			PreparedStatement ps = this.db.prepareStatement(query);
			Integer i = 1;
			
			ps.setString(i++, titolo);
			ps.setString(i++, titoloBrani);
			ps.setBigDecimal(i++, prezzo); 
			ps.setDate(i++, dataInserimento);
			ps.setString(i++, descrizione);
			ps.setString(i++, codice);
			*/
			if( psIns.executeUpdate() != 1 )
				return false;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
		
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
	
	public ResultSet getAllInfo() {
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT C.id AS id,  "
					+ "FROM cd AS C "
					+ "JOIN genere AS G"
					+ "		ON C.genere_id = G.id"
					+ "JOIN partecipazione AS P"
					+ "		ON P.cd_id = C.id"
					+ "JOIN Musicista AS M"
					+ "		ON M.id = P.musicista_id"
					+ ""
					+ "ORDER BY nome";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			if (!rs.next() ) {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}
}
