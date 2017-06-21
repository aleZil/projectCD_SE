package model;

import java.sql.*;

import utility.*;


public class Model {
	
	public Model() throws ClassNotFoundException {
		
		Connection con = null;
		
		try {
			con = Db.getConnection();
		
		} catch (SQLException e) {
			System.out.println("Errore durante la connessione");
		}
		
	}
}