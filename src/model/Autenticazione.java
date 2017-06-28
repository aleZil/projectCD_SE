package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utility.Db;

public class Autenticazione {
	
	private Connection db;
	private String tipoUtente;
	private String username;
	private String password;
	
	public Autenticazione(String tipoUtente, String username, String password) {
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.tipoUtente = tipoUtente;
		this.username = username;
		this.password = password;
	}

	public Boolean login() {

		if(this.tipoUtente.equals("personale") || this.tipoUtente.equals("cliente")) {

			try {
				String query="SELECT * FROM "+this.tipoUtente+" WHERE username=? AND password=MD5(?)";
	
				PreparedStatement ps=this.db.prepareStatement(query);
				
				int i = 1;
				ps.clearParameters();
				ps.setString(i++, this.username);
				ps.setString(i++,  this.password);
				
				ResultSet rs=ps.executeQuery();
				
				if(rs.next()) {
					ps.close();
					rs.close();
					return true;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}
}
