package model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListModel;

import utility.*;


public class Cd{
	
	private Connection db;
	
	private Integer id;
	private String titolo;
	private BigDecimal prezzo;
	private Date dataInserimento;
	private String descrizione;
	private Integer pezziVenduti;
	private Integer pezziMagazzino;
	private ArrayList<Brano> brani;
	private Genere genere;
	private Musicista titolare;
	private ArrayList<Musicista> partecipanti;
	

	public Cd() {
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// costruttore usato dall'utente per la creazione di un cd di cui non conosce id e data inserimento
	public Cd(
				String titolo,
				BigDecimal prezzo,
				String descrizione,
				Integer pezziMagazzino,
				ArrayList<Brano> brani,
				Genere genere,
				Musicista titolare,
				ArrayList<Musicista> partecipanti) {
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.setTitolo(titolo);
		this.setPrezzo(prezzo);
		this.setDescrizione(descrizione);
		this.setPezziMagazzino(pezziMagazzino);
		this.setBrani(brani);
		this.setGenere(genere);
		this.setTitolare(titolare);
		this.setPartecipanti(partecipanti);
	}
	
	// costruttore del cd ottenuto dal database, con tutte le informazioni
	public Cd(
			Integer id,
			String titolo,
			BigDecimal prezzo,
			String descrizione,
			Integer pezziVenduti,
			Integer pezziMagazzino,
			Date dataInserimento,
			ArrayList<Brano> brani,
			Genere genere,
			Musicista titolare,
			ArrayList<Musicista> partecipanti) {
	try {
		this.db = Db.getConnection();
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	
	this.setId(id);
	this.setTitolo(titolo);
	this.setPrezzo(prezzo);
	this.setDescrizione(descrizione);
	this.setPezziVenduti(pezziVenduti);
	this.setPezziMagazzino(pezziMagazzino);
	this.setDataInserimento(dataInserimento);
	this.setBrani(brani);
	this.setGenere(genere);
	this.setTitolare(titolare);
	this.setPartecipanti(partecipanti);
}
	
	// ------------------------------------------------ RECUPERO INFO BASE
	
	public Integer getId() {
		return this.id;
	}
	
	public String getTitolo() {
		return this.titolo;
	}
	
	public BigDecimal getPrezzo() {
		return this.prezzo;
	}
	
	public Date getDataInserimento() {
		return this.dataInserimento;
	}
	
	public String getDescrizione() {
		return this.descrizione;
	}
	
	public Integer getPezziVenduti() {
		return this.pezziVenduti;
	}
	
	public Integer getPezziMagazzino() {
		return this.pezziMagazzino;
	}
	
	public ArrayList<Brano> getBrani() {
		return this.brani;
	}
	
	public Genere getGenere() {
		return this.genere;
	}
	
	public Musicista getTitolare() {
		return this.titolare;
	}
	
	public ArrayList<Musicista> getPartecipanti() {
		return this.partecipanti;
	}
	// ------------------------------------------------ SETTAGGIO INFO BASE
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	
	public void setDataInserimento(Date data) {
		this.dataInserimento = data;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public void setPezziVenduti(Integer pezziVenduti) {
		this.pezziVenduti = pezziVenduti;
	}
	
	public void setPezziMagazzino(Integer pezziMagazzino) {
		this.pezziMagazzino = pezziMagazzino;
	}
	
	public void setBrani(ArrayList<Brano> brani) {
		this.brani = brani;
	}
	
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
	
	public void setTitolare(Musicista titolare) {
		this.titolare = titolare;
	}
	
	public void setPartecipanti(ArrayList<Musicista> partecipanti) {
		this.partecipanti = partecipanti;
	}
	
	
	// ------------------------------------------------ INTERAZIONE DB
	
	
	/**
	 * @param codice del cd del quale si vogliono settare le informazioni
	 * @return 
	 */
	public void getById(int id) {

		try {
			String query = "SELECT id, titolo,  prezzo, data_inserimento, genere_id, descrizione, pezzi_venduti, pezzi_magazzino "
					+ "FROM cd AS C "
					+ "WHERE C.id = ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() ) {
				this.setId(rs.getInt("id"));
				this.setTitolo(rs.getString("titolo"));
				this.setPrezzo(rs.getBigDecimal("prezzo"));
				this.setDataInserimento(rs.getDate("data_inserimento"));
				this.setDescrizione(rs.getString("descrizione"));
				this.setPezziVenduti(rs.getInt("pezzi_venduti"));
				this.setPezziMagazzino(rs.getInt("pezzi_magazzino"));
				
				Genere genere = new Genere();
				genere.getById(rs.getInt("genere_id"));
				
				this.setGenere(genere);
				
				Musicista titolare = new Musicista();
				titolare.getTitolareByIdCd(this.id);
				this.setTitolare(titolare);
				this.setPartecipanti(new Musicista().getPartecipantiByIdCd(this.id));
				
				this.setBrani(new Brano().getAllByIdCd(rs.getInt("id")));
			}
			
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * @return un ArrayList<Cd> con le informazioni solo della tabella Cd del database
	 */
	public ArrayList<Cd> getAllBase() {
		
		ArrayList<Cd> lista = new ArrayList<Cd>();
		
		Integer id;
		String titolo;
		BigDecimal prezzo;
		Date dataInserimento;
		String descrizione;
		Integer pezziVenduti;
		Integer pezziMagazzino;
		
		ArrayList<Brano> brani = new ArrayList<Brano>();
		Genere genere = null;
		Musicista titolare = null;
		ArrayList<Musicista> partecipanti = new ArrayList<Musicista>();
		
		try { 
			String query = "SELECT id, titolo,  prezzo, data_inserimento, descrizione, pezzi_venduti, pezzi_magazzino "
					+ "FROM cd AS C "
					+ "ORDER BY titolo";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while( rs.next() ){
				
				id = rs.getInt("id");
				titolo = rs.getString("titolo");
				prezzo = rs.getBigDecimal("prezzo");
				dataInserimento = rs.getDate("data_inserimento");
				descrizione = rs.getString("descrizione");
				pezziVenduti = rs.getInt("pezzi_venduti");
				pezziMagazzino = rs.getInt("pezzi_magazzino");

				lista.add(new Cd(id, titolo, prezzo, descrizione, pezziVenduti, pezziMagazzino, dataInserimento, brani, genere,	titolare, partecipanti));
			}
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return lista;
	}
	
	/**
	 * 
	 * @return una lista dei titoli in esaurimento
	 */
	public ArrayList<String> getTitoliInEsaurimento() {
		ArrayList<String> lista = new ArrayList<String>();
		
		try { 
			String query = "SELECT titolo "
					+ "FROM cd AS C "
					+ "WHERE pezzi_magazzino < 2"
					+ "ORDER BY titolo";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while( rs.next() ){

				lista.add(rs.getString("titolo"));
			}
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return lista;
	}
	
	
	public ArrayList<Cd> getByFilter(String titolo,
										String nomeGenere, 
										String nomeTitolare, 
										String nomePartecipante,
										BigDecimal prezzoMin,
										BigDecimal prezzoMax) {
		
		ArrayList<Cd> lista = new ArrayList<Cd>();
		
		try {
			String query = "SELECT id FROM Cd AS C WHERE 1=1 "; // trucchetto per usare direttamente l'AND dopo
			
			if (!titolo.equals("")) {
				query += " AND titolo ILIKE ? ";
			}
			
			if (prezzoMin.compareTo(BigDecimal.ZERO) > 0) {
				query += " AND prezzo >=  ? ";
			}
			
			if (prezzoMax.compareTo(BigDecimal.ZERO) > 0) {
				query += " AND prezzo <= ? ";
			}
			
			if(!nomeGenere.equals("")) {
				Genere genere = new Genere();
				genere.getByNome(nomeGenere);
				
				query += " AND genere_id = "+ genere.getId();
			}
			
			if(!nomeTitolare.equals("")) {
				Musicista musicista = new Musicista();
				musicista.getByNomeArte(nomeTitolare);
				
				query += " INTERSECT SELECT id "
						+ "FROM partecipazione "
						+ "WHERE is_titolare = TRUE "
						+ "AND musicista_id = "+ musicista.getId();
			}
			
			if(!nomePartecipante.equals("")) {
				Musicista partecipante = new Musicista();
				partecipante.getByNomeArte(nomeTitolare);
				
				query += " INTERSECT SELECT id "
						+ "FROM partecipazione "
						+ "WHERE is_titolare = FALSE "
						+ "AND musicista_id = "+ partecipante.getId();
			}
			
			PreparedStatement ps = this.db.prepareStatement(query);
			
			int i = 1;
			if (!titolo.equals("")) {
				ps.setString(i++, titolo);
			}
			
			if (prezzoMin.compareTo(BigDecimal.ZERO) > 0) {
				ps.setBigDecimal(i++, prezzoMin);
			}
			
			if (prezzoMax.compareTo(BigDecimal.ZERO) > 0) {
				ps.setBigDecimal(i++, prezzoMax);
			}
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				Cd cd = new Cd();
				cd.getById(id);
				
				lista.add(cd);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

		
		
		
		return lista;
		
		
	}
	
	
	
	/**
	 * @return ArrayList<Cd> con tutte le informazioni relative al Cd, anche quelle in relazione ad altre tabelle
	 */
	public ArrayList<Cd> getAll() {
		
		ArrayList<Cd> lista = new ArrayList<Cd>();
		
		Integer id;
		String titolo;
		BigDecimal prezzo;
		Date dataInserimento;
		String descrizione;
		Integer pezziVenduti;
		Integer pezziMagazzino;
		
		ArrayList<Brano> brani = new ArrayList<Brano>();
		Genere genere;
		Musicista titolare;
		ArrayList<Musicista> partecipanti = new ArrayList<Musicista>();
		
		try { 
			String query = "SELECT id, titolo,  prezzo, data_inserimento, genere_id, descrizione, pezzi_venduti, pezzi_magazzino "
					+ "FROM cd AS C "
					+ "ORDER BY titolo";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while( rs.next() ){
				
				Cd cdTmp = new Cd();
				
				id = rs.getInt("id");
				titolo = rs.getString("titolo");
				prezzo = rs.getBigDecimal("prezzo");
				dataInserimento = rs.getDate("data_inserimento");
				descrizione = rs.getString("descrizione");
				pezziVenduti = rs.getInt("pezzi_venduti");
				pezziMagazzino = rs.getInt("pezzi_magazzino");
				
				genere = new Genere();
				genere.getById(rs.getInt("genere_id"));
				
				titolare = new Musicista();
				titolare.getTitolareByIdCd(id);
				
				partecipanti = new Musicista().getPartecipantiByIdCd(id);
				//brani = new Brano().getBraniByIdCd();
				

				lista.add(new Cd(id, titolo, prezzo, descrizione, pezziVenduti, pezziMagazzino, dataInserimento, brani, genere,	titolare, partecipanti));
			}
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return lista;
	}
	
	
	/**
	 *  Inserisce nel db l'oggetto Cd creato
	 */
	public Boolean insert()
	{

		ArrayList<Brano> brani = this.getBrani();
		Genere genere = this.getGenere();
		Musicista titolare = this.getTitolare();
		ArrayList<Musicista> partecipanti = this.getPartecipanti();
		
		try {
			String query= "INSERT INTO Cd "
					+ "(titolo, prezzo, descrizione, pezzi_magazzino, genere_id) "
					+ "VALUES (?,?,?,?,?)";
			
			// Statement.RETURN_GENERATED_KEYS permette di recuperare subito la chiave della riga appena inserita
			PreparedStatement psIns = this.db.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			
			psIns.setString(i++ , this.getTitolo());
			psIns.setBigDecimal(i++, this.getPrezzo());
			psIns.setString(i++, this.getDescrizione());
			psIns.setInt(i++, this.getPezziMagazzino());
			psIns.setInt(i++, genere.getId());
			
			psIns.executeUpdate();
			
			int id = 0;
			ResultSet rs = psIns.getGeneratedKeys();
			
			if (rs.next()){
				// recupero l'id della tupla inserita
			    id = rs.getInt("id");
			} else {
				return false;
			}
			
			// ---------------------------------------------------- AGGIUNTA BRANI
			query = "INSERT INTO Brano "
					+ "(nome, ordine, cd_id) "
					+ "VALUES (?,?,?)";
			
			psIns = this.db.prepareStatement(query);
			
			for (int j = 0; j < brani.size(); j++) 
			{
				psIns.clearParameters();
				i = 1;
				
				// recupero il brano j-esimo
				Brano b = brani.get(j);
				
				psIns.setString(i++, b.getNome());
				psIns.setInt(i++, b.getOrdine());
				psIns.setInt(i++, id);
				
				if( psIns.executeUpdate() != 1 )
					return false;
			}

			// ---------------------------------------------------- AGGIUNTA TITOLARE
			query ="INSERT INTO Partecipazione "
					+ "(cd_id, musicista_id, is_titolare) "
					+ "VALUES (?,?,?)";
			
			psIns = this.db.prepareStatement(query);
			
			i = 1;
			psIns.setInt(i++, id);
			psIns.setInt(i++, titolare.getId());
			psIns.setBoolean(i++, true);
			
			if( psIns.executeUpdate() != 1 )
				return false;

			// ---------------------------------------------------- AGGIUNTA PARTECIPANTI
			for (int j = 0; j < partecipanti.size(); j++) 
			{
				psIns.clearParameters();
				i = 1;
				
				psIns.setInt(i++, id);
				// recupero l'id del musicista partecipante j-esimo
				psIns.setInt(i++, partecipanti.get(j).getId());
				psIns.setBoolean(i++, false);
				
				if( psIns.executeUpdate() != 1 )
					return false;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}

	public Boolean update() {
	
		ArrayList<Brano> brani = this.getBrani();
		Genere genere = this.getGenere();
		Musicista titolare = this.getTitolare();
		ArrayList<Musicista> partecipanti = this.getPartecipanti();
		
		try {
			String query="UPDATE Cd SET titolo=?,"
					+ "prezzo= ?, "   
					+ "descrizione = ?, "
					+ "pezzi_magazzino = ?, "
					+ "genere_id = ? "
					+ "WHERE id = ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			Integer i = 1;
			
			ps.setString(i++, this.getTitolo());
			ps.setBigDecimal(i++, this.getPrezzo()); 
			ps.setString(i++, this.getDescrizione());
			ps.setInt(i++, this.getPezziMagazzino());
			ps.setInt(i++, genere.getId());
			ps.setInt(i++, this.getId());
			
			if( ps.executeUpdate() != 1 )
				return false;
			
			// ---------------------------------------------------- ELIMINO INFORMAZIONI VECCHIE
			query = "DELETE FROM Brano WHERE cd_id = ?";
			ps = this.db.prepareStatement(query);
			ps.setInt(1, this.getId()); 
			ps.executeUpdate();
			
			query = "DELETE FROM Partecipazione WHERE cd_id = ?";
			ps = this.db.prepareStatement(query);
			ps.setInt(1, this.getId()); 
			ps.executeUpdate();
			
			// ---------------------------------------------------- AGGIUNTA BRANO
			
			query = "INSERT INTO Brano "
					+ "(nome, ordine, cd_id) "
					+ "VALUES (?,?,?)";
			
			ps = this.db.prepareStatement(query);
			
			for (int j = 0; j < brani.size(); j++) 
			{
				ps.clearParameters();
				i = 1;
				
				// recupero il brano j-esimo
				Brano b = brani.get(j);
				
				ps.setString(i++, b.getNome());
				ps.setInt(i++, b.getOrdine());
				ps.setInt(i++, this.getId());
				
				if( ps.executeUpdate() != 1 )
					return false;
			}
			
			// ---------------------------------------------------- AGGIUNTA TITOLARE
			ps.clearParameters();
			
			query = "INSERT INTO Partecipazione "
					+ "(cd_id, musicista_id, is_titolare) "
					+ "VALUES ( ?, ?, ? )";
			
			ps = this.db.prepareStatement(query);
			i = 1;
			ps.setInt(i++, id);
			ps.setInt(i++, titolare.getId());
			ps.setBoolean(i++, true);
				
			if( ps.executeUpdate() != 1 )
				return false;

			// ---------------------------------------------------- AGGIUNTA PARTECIPANTI
			for (int j = 0; j < partecipanti.size(); j++) 
			{
				ps.clearParameters();
				i = 1;
				
				ps.setInt(i++, this.getId());
				// recupero l'id del musicista partecipante j-esimo
				ps.setInt(i++, partecipanti.get(j).getId());
				ps.setBoolean(i++, false);
				
				if( ps.executeUpdate() != 1 )
					return false;
			}
			
			ps.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
}
