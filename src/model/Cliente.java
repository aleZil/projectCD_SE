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
		setCodiceFiscale(codiceFiscale);
		setUsername(username);
		setPassword(password);
		setNome(nome);
		setCognome(cognome);
		setIndirizzo(indirizzo);
		setTelefono(telefono);
		setCellulare(cellulare);	
	}
	
	public boolean registra()
	{
		try {
			String insertQuery="INSERT INTO Cliente "
					+ "(username, codice_fiscale, nome, cognome, password, indirizzo, telefono, cellulare) "
					+ "VALUES (?,?,?,?,?,?,?,?)";

			PreparedStatement psIns = this.db.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			
			int i=1;
			
			psIns.setString(i++, getUsername());
			psIns.setString(i++, getCodiceFiscale());
			psIns.setString(i++, getNome());
			psIns.setString(i++, getCognome());
			psIns.setString(i++, getPassword());
			psIns.setString(i++, getIndirizzo());
			psIns.setString(i++, getTelefono());
			psIns.setString(i++, getCellulare());
			
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
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public String getCognome()
	{
		return cognome;
	}
	
	public String getCodiceFiscale()
	{
		return codiceFiscale;
	}
	
	public String getIndirizzo()
	{
		return indirizzo;
	}
	
	public String getTelefono()
	{
		return telefono;
	}

	public String getCellulare()
	{
		return cellulare;
	}
	
}
