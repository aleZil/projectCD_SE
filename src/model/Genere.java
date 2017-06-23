package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.Db;

public class Genere {
	
	private Connection db;
	
	public Genere() {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ResultSet getById(int id) {
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM genere WHERE id = ?";
			
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
	
	public int getIdByNome(String nome) {
		
		try {
			String query = "SELECT id FROM genere WHERE nome ILIKE ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setString(1, nome);
			
			ResultSet res = ps.executeQuery();
			
			if (res.next()) {
				ps.close();
				return res.getInt("id");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1; //trovato nulla
	}
	
	public ResultSet getAll() {
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM genere ORDER BY nome";
			
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
	
	public Boolean insert(String nome) {
		
		try {
			String insertQuery="INSERT INTO Genere (nome) VALUES (?)";
			
			PreparedStatement psIns = this.db.prepareStatement(insertQuery);
			
			int i = 1;
			psIns.setString(i++, nome);
			psIns.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
}
