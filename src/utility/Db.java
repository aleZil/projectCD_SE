package utility;

import java.sql.*;

public class Db {

	private static Connection con = null;
	
	
	public static Connection getConnection() throws SQLException {
		
        String dbName = "progetto_cd";
        String userName = "hanzo";
        String password = "neversurrender";
        
        if (con != null) {
        	return con;
        } else {
        	
            try{
            	
            	con = DriverManager.getConnection("jdbc:postgresql://db-cdproject.czz77hrlmvcn.eu-west-1.rds.amazonaws.com/" + dbName, userName, password);
                
            } catch (SQLException e) {
            	
            	e.printStackTrace();
            	
            }
        }
        
        return con;
	}
}
