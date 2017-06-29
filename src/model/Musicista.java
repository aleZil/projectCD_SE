package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utility.Db;

public class Musicista {
	
	private Connection db;
	
	private Integer id;
	private String nomeArte;
	private Integer annoNascita;
	private Genere genere;
	private ArrayList<Strumento> strumenti;
	
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
			Genere genere) {

		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		this.setId(id);
		this.setNomeArte(nomeArte);
		this.setAnnoNascita(annoNascita);
		this.setGenere(genere);
	}
	
	public Musicista(	Integer id,
						String nomeArte,
						Integer annoNascita,
						Genere genere,
						ArrayList<Strumento> strumenti) {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.setId(id);
		this.setNomeArte(nomeArte);
		this.setAnnoNascita(annoNascita);
		this.setGenere(genere);
		this.setStrumenti(strumenti);
	}
	
	//TODO
	public Musicista(	String nomeArte,
						Integer annoNascita,
						Genere genere) {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.setNomeArte(nomeArte);
		this.setAnnoNascita(annoNascita);
		this.setGenere(genere);
	}
	
	//TODO 2
	public Musicista(	String nomeArte,
						Integer annoNascita,
						Genere genere,
						ArrayList<Strumento> strumenti) {
		
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.setNomeArte(nomeArte);
		this.setAnnoNascita(annoNascita);
		this.setGenere(genere);
		this.setStrumenti(strumenti);
	}
	
	
	
	// ------------------------------------------------ RECUPERO INFO BASE
	
	public Integer getId() {
		return this.id;
	}
	
	public String getNomeArte() {
		return this.nomeArte;
	}
	
	public Integer getAnnoNascita() {
		return this.annoNascita;
	}
	
	public Genere getGenere() {
		return this.genere;
	}
	
	public ArrayList<Strumento> getStrumenti() {
		return this.strumenti;
	}
	
	// ------------------------------------------------ SETTAGGIO DATI BASE
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setNomeArte(String nomeArte) {
		this.nomeArte = nomeArte;
	}
	
	public void setAnnoNascita(Integer annoNascita) {
		this.annoNascita = annoNascita;
	}
	
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
	
	public void setStrumenti(ArrayList<Strumento> strumenti) {
		this.strumenti = strumenti;
	}
	
	// ------------------------------------------------ INTERAZIONE DB
	
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
			
			if (rs.next() ) {
				this.setId(rs.getInt("id"));
				this.setNomeArte(rs.getString("nome_arte"));
				this.setAnnoNascita(rs.getInt("anno_nascita"));
				
				this.setGenere(new Genere(rs.getInt("genere_id"), rs.getString("genere")));
			}
			ps.close();
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
			
			if (rs.next() ) {
				this.setId(rs.getInt("id"));
				this.setNomeArte(rs.getString("nome_arte"));
				this.setAnnoNascita(rs.getInt("anno_nascita"));
				
				this.setGenere(new Genere(rs.getInt("genere_id"), rs.getString("genere")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void getTitolareByIdCd(Integer cdId) {
		
		try {
			String query = "SELECT M.id AS id, M.nome_arte AS nome_arte, M.anno_nascita AS anno_nascita, M.genere_id AS genere_id, G.nome AS genere "
					+ "FROM musicista AS M "
					+ "JOIN genere AS G "
					+ "ON G.id = M.genere_id "
					+ "JOIN Partecipazione AS P "
					+ "ON P.musicista_id = M.id "
					+ "WHERE P.cd_id = ? "
					+ "AND P.is_titolare = TRUE "
					+ "LIMIT 1";
			
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setInt(1, cdId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() ) {
				this.setId(rs.getInt("id"));
				this.setNomeArte(rs.getString("nome_arte"));
				this.setAnnoNascita(rs.getInt("anno_nascita"));
				
				this.setGenere(new Genere(rs.getInt("genere_id"), rs.getString("genere")));
				
			}
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Musicista> getPartecipantiByIdCd(Integer cdId) {
		
		ArrayList<Musicista> lista = new ArrayList<Musicista>();
		Integer id;
		String nomeArte;
		Integer annoNascita;
		Genere genere;
		
		try {
			String query = "SELECT M.id AS id, M.nome_arte AS nome_arte, M.anno_nascita AS anno_nascita, M.genere_id AS genere_id, G.nome AS genere "
					+ "FROM musicista AS M "
					+ "JOIN genere AS G "
					+ "ON G.id = M.genere_id "
					+ "JOIN Partecipazione AS P "
					+ "ON P.musicista_id = M.id "
					+ "WHERE P.cd_id = ? "
					+ "AND P.is_titolare = FALSE ";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setInt(1, cdId);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next() ) {
				
				id = rs.getInt("id");
				nomeArte = rs.getString("nome_arte");
				annoNascita = rs.getInt("anno_nascita");
				genere = new Genere(rs.getInt("genere_id"), rs.getString("genere"));
				
				lista.add(new Musicista(id, nomeArte, annoNascita, genere));
			}
			
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return lista;
	}
	
	public ArrayList<Musicista> genericGet(String filter) {
		
		ArrayList<Musicista> lista = new ArrayList<Musicista>();
		String queryFilter = "";
		
		switch(filter) {
			case "isband":
				queryFilter = "WHERE isband = TRUE ";
				break;
			case "notband":
				queryFilter = "WHERE isband = FALSE ";
				break;	
			default:
				break;
		}
		
		try {
			String query = "SELECT M.id AS id, M.nome_arte AS nome_arte, M.anno_nascita AS anno_nascita, G.id AS genere_id, G.nome AS genere "
					+ "FROM musicista AS M "
					+ "JOIN Genere AS G "
					+ "ON M.genere_id = G.id "
					+ queryFilter 
					+ "ORDER BY nome_arte";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next() ) {
				Musicista m = new Musicista();
				
				m.setId(rs.getInt("id"));
				m.setNomeArte(rs.getString("nome_arte"));
				m.setAnnoNascita(rs.getInt("anno_nascita"));
				m.setGenere(new Genere(rs.getInt("genere_id"), rs.getString("genere")));
			
				lista.add(m);
			}
			
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	
	public ArrayList<Musicista> getAll() {
		
		return this.genericGet("All");
	}
	
	public ArrayList<Musicista> getAllBand() {
		
		return this.genericGet("isband");
	}
	
	public ArrayList<Musicista> getAllNotBand() {
		
		return this.genericGet("notband");
	}
	
	public Boolean insert() {
		
		Genere genere = new Genere();
		genere = this.getGenere();
		ArrayList<Strumento> strumenti = this.getStrumenti();

		try {
			String insertQuery="INSERT INTO Musicista "
					+ "(nome_arte, genere_id, anno_nascita) "
					+ "VALUES (?,?,?)";

			PreparedStatement psIns = this.db.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

			int i = 1;

			psIns.setString(i++, this.getNomeArte());

			//TODO
			psIns.setInt(i++, genere.getId());

			psIns.setInt(i++,this.getAnnoNascita());

			psIns.executeUpdate();

			int id = 0;
			ResultSet rs = psIns.getGeneratedKeys();

			if (rs.next()){
				// recupero l'id della tupla inserita
			    id = rs.getInt("id");
			} else {
				return false;
			}
			
			// ---------------------------------------------------- AGGIUNTA STRUMENTI

			insertQuery = "INSERT INTO utilizzo (musicista_id, strumento_id) VALUES (?,?)";
			psIns = this.db.prepareStatement(insertQuery);
			
			// aggiungo per ogni strumento una riga su utilizzo
			for(int j=0; j < strumenti.size(); j++)
			{
				// recupero l'oggetto strumento dalla lista
				Strumento s = strumenti.get(j);
				
				i = 1;
				psIns.setInt(i++, id);
				psIns.setInt(i++, s.getId());
				psIns.executeUpdate();
			}
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}
}
