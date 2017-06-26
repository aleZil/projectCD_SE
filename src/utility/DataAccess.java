package utility;

import java.sql.*;

public class DataAccess {
	
	Connection db;
	String table;
	String filter;
	
	public DataAccess() {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void setTable(String table) {
		this.table = table;
	}
	
	public void setFilter(String filter) {
		this.table = filter;
	}
	
	
	
	

}
