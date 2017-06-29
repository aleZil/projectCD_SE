package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utility.Db;

public class Cliente {

	private Connection db;
	
	private String username;
	private String password;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private String cellulare;
	
	public Cliente()
	{
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Cliente(String username,String password,String codiceFiscale,String nome,String cognome,String indirizzo,String telefono,String cellulare)
	{
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.setCodiceFiscale(codiceFiscale);
		this.setUsername(username);
		this.setPassword(password);
		this.setNome(nome);
		this.setCognome(cognome);
		this.setIndirizzo(indirizzo);
		this.setTelefono(telefono);
		this.setCellulare(cellulare);	
	}
	
	//Metodi set
	
	public void setUsername(String username)
	{
		this.username=username;
	}
	
	private void setPassword(String password)
	{
		this.password=password;
	}
	
	public void setCodiceFiscale(String codiceFiscale)
	{
		this.codiceFiscale=codiceFiscale;
	}
	
	public void setNome(String nome)
	{
		this.nome=nome;
	}
	
	public void setCognome(String cognome)
	{
		this.cognome=cognome;
	}

	public void setIndirizzo(String indirizzo)
	{
		this.indirizzo=indirizzo;
	}
	
	public void setTelefono(String telefono)
	{
		this.telefono=telefono;
	}
	
	public void setCellulare(String cellulare)
	{
		this.cellulare=cellulare;
	}

	//Metodi get
	
	public String getUsername()
	{
		return this.username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public String getNome()
	{
		return this.nome;
	}
	
	public String getCognome()
	{
		return this.cognome;
	}
	
	public String getCodiceFiscale()
	{
		return this.codiceFiscale;
	}
	
	public String getIndirizzo()
	{
		return this.indirizzo;
	}
	
	public String getTelefono()
	{
		return this.telefono;
	}

	public String getCellulare()
	{
		return this.cellulare;
	}
	
	
	
	public boolean registra()
	{
		try {
			String insertQuery="INSERT INTO Cliente "
					+ "(username, codice_fiscale, nome, cognome, password, indirizzo, telefono, cellulare) "
					+ "VALUES (?,?,?,?,MD5(?),?,?,?)";

			PreparedStatement psIns = this.db.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			
			int i=1;
			
			psIns.setString(i++, this.getUsername());
			psIns.setString(i++, this.getCodiceFiscale());
			psIns.setString(i++, this.getNome());
			psIns.setString(i++, this.getCognome());
			psIns.setString(i++, this.getPassword());
			psIns.setString(i++, this.getIndirizzo());
			psIns.setString(i++, this.getTelefono());
			psIns.setString(i++, this.getCellulare());
			
			if(psIns.executeUpdate()>0)
				return true;
			else
				return false;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
}
