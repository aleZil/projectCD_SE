package model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListModel;

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
	
	
	public Boolean insert(String titolo,String descrizione,Date dataIns,BigDecimal prezzo,Integer pezziMagazzino,Integer genereId,ListModel<String> listaBrani)
	{

		try {
			String insertCdQuery="INSERT INTO Cd "
					+ "(titolo, prezzo,data_inserimento,descrizione,pezzi_magazzino,genere_id) "
					+ "VALUES (?,?,?,?,?,?)";
			
			PreparedStatement psIns = this.db.prepareStatement(insertCdQuery);
			
			int i = 1;
			psIns.setString(1 , titolo);
			psIns.setBigDecimal(2, prezzo);
			psIns.setDate(3,dataIns);
			psIns.setString(4,descrizione);
			psIns.setInt(5,pezziMagazzino);
			psIns.setInt(6,genereId);
		
			if(psIns.executeUpdate()!=1)
			{
				return false;
			}

			psIns.clearParameters();

			/*String insertParecipa="INSERT INTO Partecipazione "
					+ "(cd_codice,musicista_id,is_titolare) "
					+ "VALUES (?,?,?)";
			
			
			if( psIns.executeUpdate() != 1 )
				return false;*/
			
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
			String query = "SELECT codice, titolo,  prezzo, data_inserimento, nome AS genere, descrizione, pezzi_venduti, pezzi_magazzino "
					+ "FROM cd AS C "
					+ "JOIN genere AS G "
					+ "ON C.genere_id = G.id "
					+ "ORDER BY nome";

			PreparedStatement ps = this.db.prepareStatement(query);
			System.out.println(query);
			
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
