package utility;

import java.sql.*;

public class Db {

	private static Connection con = null;
	
	public static Connection getConnection() {
		
        String dbName = "progetto_cd";
        String userName = "hanzo";
        String password = "neversurrender";
        
        if (con != null) {
        	return con;
        } else {
        	
            try{
            	
            	Class.forName("org.postgresql.Driver");
            	con = DriverManager.getConnection("jdbc:postgresql://db-cdproject.czz77hrlmvcn.eu-west-1.rds.amazonaws.com/" + dbName, userName, password);
                
            } catch (Exception e) {
            	
            	e.printStackTrace();
            	
            }
        }
        
        return con;
	}
	/*
	public static void closeConnection() {
		con.close();
	}
	*/
}
