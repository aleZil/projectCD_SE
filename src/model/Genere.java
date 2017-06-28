package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utility.Db;

public class Genere {
	
	private Connection db;
	
	private Integer id;
	private String nome;
	
	public Genere() {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Genere(Integer id, String nome) {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.id = id;
		this.nome = nome;
	}
	
	public Genere(String nome) {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// this.id = id;
		this.setNome(nome);
	}
	
	
	// ------------------------------------------------ RECUPERO INFO BASE
	
	public Integer getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	// ------------------------------------------------ SETTAGGIO DATI BASE
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	//TODO ho cambiato da private a public
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	// ------------------------------------------------ INTERAZIONE DB
	
	public void getById(int id) {
		
		try {
			String query = "SELECT * FROM genere WHERE id = ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() ) {
				this.setId(rs.getInt("id"));
				this.setNome(rs.getString("nome"));
			}
			
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public ArrayList<Genere> getAll() {
		
		ArrayList<Genere> lista = new ArrayList<Genere>();
		
		try {
			String query = "SELECT * FROM genere ORDER BY nome";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ResultSet rs = rs = ps.executeQuery();
			
			while (rs.next() ) {
				
				Genere genere = new Genere();
				genere.setId(rs.getInt("id"));
				genere.setNome(rs.getString("nome"));
				
				lista.add(genere);
			}
			
			ps.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	

	public Boolean insert() {
		
		try {
			String insertQuery="INSERT INTO Genere (nome) VALUES (?)";
			
			PreparedStatement psIns = this.db.prepareStatement(insertQuery);
			
			int i = 1;
			
			psIns.setString(i++, this.getNome());
			psIns.executeUpdate();
			
			psIns.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
}
