package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utility.Db;

public class Strumento {
		
		private Connection db;
		
		private Integer id;
		private String nome;
		
		public Strumento() {
			
			try {
				this.db = Db.getConnection();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		public Strumento(	Integer id,
							String nome) {
			
			try {
				this.db = Db.getConnection();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			this.setId(id);
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
		
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		// ------------------------------------------------ INTERAZIONE DB
		
		
		public void getById(int id) {
			
			try {
				String query = "SELECT * FROM strumento WHERE id = ?";
				
				PreparedStatement ps = this.db.prepareStatement(query);
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				if (rs.next() ) {
					this.setId(rs.getInt("id"));
					this.setNome(rs.getString("nome"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		public void getByNome(String nome) {

			try {
				String query = "SELECT * FROM strumento WHERE nome = ?";

				PreparedStatement ps = this.db.prepareStatement(query);
				ps.setString(1,nome);

				ResultSet rs = ps.executeQuery();

				if (rs.next() ) {
					this.setId(rs.getInt("id"));
					this.setNome(rs.getString("nome"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}



		
		public ArrayList<Strumento> getAll() {
			
			ArrayList<Strumento> lista = new ArrayList<Strumento>();
			
			try {
				String query = "SELECT * FROM strumento ORDER BY nome";
				
				PreparedStatement ps = this.db.prepareStatement(query);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next() ) {
					
					Strumento s = new Strumento();
					s.setId(rs.getInt("id"));
					s.setNome(rs.getString("nome"));
					
					lista.add(s);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return lista;
		}
}
