package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.Db;

public class Strumento {
		
		private Connection db;
		
		public Strumento() {
			
			try {
				this.db = Db.getConnection();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		
		public ResultSet getById(int id) {
			
			ResultSet rs = null;
			
			try {
				String query = "SELECT * FROM strumento WHERE id = ?";
				
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
		
		public ResultSet getAll() {
			
			ResultSet rs = null;
			
			try {
				String query = "SELECT * FROM strumento ORDER BY nome";
				
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
