package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.Db;

public class Musicista {
	
	private Connection db;
	
	public Musicista() {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public ResultSet getById(int id) {
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM musicista WHERE id = ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (!rs.next() ) {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	
	}
	
	public int getIdByNomeArte(String nomeArte) {
		
		try {
			String query = "SELECT id FROM musicista WHERE nome_arte ILIKE ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setString(1, nomeArte);
			
			ResultSet res = ps.executeQuery();
			
			if (res.next()) {
				ps.close();
				return res.getInt("id");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}
	
	
	public ResultSet getAll() {
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM musicista ORDER BY nome_arte";
			
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
	
	public Boolean insert(String nomeArte,
			int genereId,
			int annoNascita,
			int[] strumenti) {
		
		try {
			String insertQuery="INSERT INTO Musicista "
					+ "(nome_arte, genere_id, anno_nascita) "
					+ "VALUES (?,?,?)";
			
			PreparedStatement psIns = this.db.prepareStatement(insertQuery);
			
			int i = 1;
			psIns.setString(i++, nomeArte);
			psIns.setInt(i++, genereId);
			psIns.setInt(i++ ,annoNascita);
			
			// recupero l'id del musicista appena inserito
			int id = this.getIdByNomeArte(nomeArte);

			insertQuery = "INSERT INTO utilizzo (musicista_id, strumenti_id) VALUES (?,?)";
			psIns = this.db.prepareStatement(insertQuery);
			
			// aggiungo per ogni strumento una riga su utilizzo
			for(int j = 0; j < strumenti.length; j++)
			{
				i = 1;
				psIns.setInt(i++, id);
				psIns.setInt(i++, strumenti[j]);
				psIns.executeUpdate();
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
}
