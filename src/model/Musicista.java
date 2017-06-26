package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utility.Db;

public class Musicista {
	
	private Connection db;
	
	private Integer id;
	private String nomeArte;
	private Integer annoNascita;
	private Integer genereId;
	private String genere;
	
	public Musicista() {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Musicista(	Integer id,
						String nomeArte,
						Integer annoNascita,
						Integer genereId,
						String genere) {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.id = id;
		this.nomeArte = nomeArte;
		this.annoNascita = annoNascita;
		this.genereId = genereId;
		this.genere = genere;
	}
	
	/**
	 * @param id del musicista da cui ottenere le informazioni
	 */
	public void getById(int id) {
		
		try {
			String query = "SELECT M.id AS id, M.nome_arte AS nome_arte, M.anno_nascita AS anno_nascita, M.genere_id AS genere_id, G.nome AS genere "
					+ "FROM musicista AS M "
					+ "JOIN genere AS G "
					+ "ON G.id = M.genere_id "
					+ "WHERE M.id = ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (!rs.next() ) {
				this.id = rs.getInt("id");
				this.nomeArte = rs.getString("nome_arte");
				this.annoNascita = rs.getInt("anno_nascita");
				this.genereId= rs.getInt("genere_id");
				this.genere= rs.getString("genere");
			}
			
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	/**
	 * @param nomeArte del musicista da cui ottenere le informazioni
	 */
	public void getByNomeArte(String nomeArte) {
		
		try {
			String query = "SELECT M.id AS id, M.nome_arte AS nome_arte, M.anno_nascita AS anno_nascita, M.genere_id AS genere_id, G.nome AS genere "
					+ "FROM musicista AS M "
					+ "JOIN genere AS G "
					+ "ON G.id = M.genere_id "
					+ "WHERE M.nome_arte ILIKE ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setString(1, nomeArte);
			
			ResultSet rs = ps.executeQuery();
			
			if (!rs.next() ) {
				this.id = rs.getInt("id");
				this.nomeArte = rs.getString("nome_arte");
				this.annoNascita = rs.getInt("anno_nascita");
				this.genereId= rs.getInt("genere_id");
				this.genere= rs.getString("genere");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void getTitolareByCodiceCd(String codice) {
		
		try {
			String query = "SELECT M.id AS id, M.nome_arte AS nome_arte, M.anno_nascita AS anno_nascita, M.genere_id AS genere_id, G.nome AS genere "
					+ "FROM musicista AS M "
					+ "JOIN genere AS G "
					+ "ON G.id = M.genere_id "
					+ "JOIN Partecipazione AS P "
					+ "ON P.musicista_id = M.id "
					+ "WHERE P.cd_codice = ? "
					+ "AND P.is_titolare = TRUE "
					+ "LIMIT 1";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setString(1, codice);
			
			ResultSet rs = ps.executeQuery();
			
			if (!rs.next() ) {
				this.id = rs.getInt("id");
				this.nomeArte = rs.getString("nome_arte");
				this.annoNascita = rs.getInt("anno_nascita");
				this.genereId= rs.getInt("genere_id");
				this.genere= rs.getString("genere");
				
			}
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Musicista> getPartecipantiByCodiceCd(String codice) {
		
		ArrayList<Musicista> lista = new ArrayList<Musicista>();
		Musicista tmp = null;
		Integer id;
		String nomeArte;
		Integer annoNascita;
		Integer genereId;
		String genere;
		
		try {
			String query = "SELECT M.id AS id, M.nome_arte AS nome_arte, M.anno_nascita AS anno_nascita, M.genere_id AS genere_id, G.nome AS genere "
					+ "FROM musicista AS M "
					+ "JOIN genere AS G "
					+ "ON G.id = M.genere_id "
					+ "JOIN Partecipazione AS P "
					+ "ON P.musicista_id = M.id "
					+ "WHERE P.cd_codice = ? "
					+ "AND P.is_titolare = FALSE ";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setString(1, codice);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next() ) {
				
				id = rs.getInt("id");
				nomeArte = rs.getString("nome_arte");
				annoNascita = rs.getInt("anno_nascita");
				genereId= rs.getInt("genere_id");
				genere= rs.getString("genere");
				
				lista.add(new Musicista(id, nomeArte, annoNascita,genereId, genere));
			}
			
			ps.close();
			rs.close();
			return lista;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	/*
	public ResultSet getAll() {
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT * FROM musicista ORDER BY nome_arte";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			if (!rs.next() ) {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}
	
	public Boolean insert(String nomeArte,
			int genereId,
			int annoNascita,
			int[] strumenti) {
		
		try {
			String insertQuery="INSERT INTO Musicista "
					+ "(nome_arte, genere_id, anno_nascita) "
					+ "VALUES (?,?,?)";
			
			PreparedStatement psIns = this.db.prepareStatement(insertQuery);
			
			int i = 1;
			psIns.setString(i++, nomeArte);
			psIns.setInt(i++, genereId);
			psIns.setInt(i++ ,annoNascita);
			
			// recupero l'id del musicista appena inserito
			int id = this.getIdByNomeArte(nomeArte);

			insertQuery = "INSERT INTO utilizzo (musicista_id, strumenti_id) VALUES (?,?)";
			psIns = this.db.prepareStatement(insertQuery);
			
			// aggiungo per ogni strumento una riga su utilizzo
			for(int j = 0; j < strumenti.length; j++)
			{
				i = 1;
				psIns.setInt(i++, id);
				psIns.setInt(i++, strumenti[j]);
				psIns.executeUpdate();
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
	*/
}
