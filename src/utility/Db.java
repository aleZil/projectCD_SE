package utility;

import java.sql.*;

public class Db {

	public static Connection getConnection() throws SQLException {
		
        String dbName = "progetto_cd";
        String userName = "hanzo";
        String password = "neversurrender";
        
        try{
        	
        	Connection con=DriverManager.getConnection("jdbc:postgresql://db-cdproject.czz77hrlmvcn.eu-west-1.rds.amazonaws.com/" + dbName, userName, password);
            return con;
            
        } catch (SQLException e) {
        	
        	// TODO da sistemare sta cosa
        	return null;
        }
	}
}
