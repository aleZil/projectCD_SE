package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utility.Db;




public class Brano {
	
	private Connection db;
	
	private String nome;
	private Integer ordine;
	private Integer cd_id;
	
	//costruttore vuoto
	public Brano() {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//costruttore con attributi
	public Brano(String nome, Integer ordine, Integer cd_id) {
		
		try{
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.setNome(nome);
		this.setOrdine(ordine);
		this.setCdId(cd_id);
	}
	
	public Brano(String nome, Integer ordine) {
		
		try{
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.setNome(nome);
		this.setOrdine(ordine);
	}
	
	
	// ------------------------------------------------ RECUPERO INFO BASE
	
	public String getNome() {
		return this.nome;
	}
	
	public Integer getOrdine() {
		return this.ordine;
	}
	
	public Integer getCd_id(){
		return this.cd_id;
	}
	
	// ------------------------------------------------ SETTAGGIO DATI BASE
	
	private void setNome(String nome){
		this.nome = nome;
	}
	
	private void setOrdine(Integer ordine){
		this.ordine = ordine;
	}
	
	private void setCdId(Integer cd_id){
		this.cd_id = cd_id;
	}
	
	
	// ------------------------------------------------ INTERAZIONE DB
	
	//Inserimento nel DB
	//TODO vedere se funziona correttamente
	public Boolean insert(String nome, Integer ordine, Integer cd_id) {
		
		try {
			String insertQuery="INSERT INTO Brano "
				+ "(nome, ordine, cd_id) "
				+ "VALUES (?,?,?)";
		
			PreparedStatement psIns = this.db.prepareStatement(insertQuery);
			
			int i=1;
			psIns.setString(i++, nome);
			psIns.setInt(i++, ordine);
			psIns.setInt(i++, cd_id);
			
			if( psIns.executeUpdate() != 1 ) {
				psIns.close();
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}

	//Selezione da DB
	public ArrayList<Brano> getAllByIdCd(int id_cd){
		
		ArrayList<Brano> lista = new ArrayList<Brano>();

		try{
			String query = "SELECT * " 
					+ "FROM brano AS B "
					+ "JOIN cd AS C "
					+ "ON C.id = B.cd_id "
					+ "WHERE B.cd_id = ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setInt(1, id_cd);
			
			ResultSet rs = ps.executeQuery();
			
			while( rs.next() ){
				nome = rs.getString("nome");
				ordine = rs.getInt("ordine");
				cd_id = rs.getInt("cd_id");
				lista.add(new Brano(nome, ordine, cd_id));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		return lista;
	}
			

	
	
	
}

