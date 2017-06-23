package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utility.Db;

public class Brano {
	
	private Connection db;
	
	public Brano() {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Boolean insert(String codiceCd, String titoloBrano, int ordine) {
		
		try {
			String insertQuery="INSERT INTO Brano "
				+ "(cd_codice, titolo, ordine) "
				+ "VALUES (?,?,?)";
		
			PreparedStatement psIns = this.db.prepareStatement(insertQuery);
			
			int i=1;
			psIns.setString(i++, codiceCd);
			psIns.setString(i++, titoloBrano);
			psIns.setInt(i++, ordine);
			
			if( psIns.executeUpdate() != 1 ) {
				psIns.close();
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
}
