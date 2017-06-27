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
	
	/**
	 * 
	 * @param id
	 * @param titolo
	 * @param prezzo
	 * @param dataInserimento
	 * @param descrizione
	 * @param pezziVenduti
	 * @param pezziMagazzino
	 * @param genere
	 * @param musicista
	 * @param partecipanti
	 */
	public Cd(	Integer id,
				String titolo,
				BigDecimal prezzo,
				Date dataInserimento,
				String descrizione,
				Integer pezziVenduti,
				Integer pezziMagazzino,
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
		this.setDataInserimento(dataInserimento);
		this.setDescrizione(descrizione);
		this.setPezziVenduti(pezziVenduti);
		this.setPezziMagazzino(pezziMagazzino);
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
	public Cd getByCodice(String codice) {

		try {
			String query = "SELECT id, titolo,  prezzo, data_inserimento, genere_id, G.nome AS genere, descrizione, pezzi_venduti, pezzi_magazzino "
					+ "FROM cd AS C "
					+ "JOIN Genere AS G "
					+ "ON C.genere_id = G.id "
					+ "WHERE C.id = ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setString(1, codice);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() ) {
				this.id = rs.getInt("id");
				this.titolo = rs.getString("titolo");
				this.prezzo = rs.getBigDecimal("prezzo");
				this.dataInserimento = rs.getDate("data_inserimento");
				this.descrizione = rs.getString("descrizione");
				this.pezziVenduti = rs.getInt("pezzi_venduti");
				this.pezziMagazzino = rs.getInt("pezzi_magazzino");
				this.genere = new Genere();
				this.genere.getById(rs.getInt("genere_id"));

				this.titolare = new Musicista();
				this.titolare.getTitolareByIdCd(this.id);
				
				this.partecipanti = new Musicista().getPartecipantiByIdCd(this.id);
				
			}
			
			ps.close();
			rs.close();
			
			return this;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	
	}
	
	
	
	public ArrayList<Cd> getAll() {
		
		ArrayList<Cd> lista = new ArrayList<Cd>();
		
		Integer id;
		String titolo;
		BigDecimal prezzo;
		Date dataInserimento;
		String descrizione;
		Integer pezziVenduti;
		Integer pezziMagazzino;
		Genere genere;
		Musicista titolare;
		ArrayList<Musicista> partecipanti;
		
		try { 
			String query = "SELECT C.id AS id, titolo,  prezzo, data_inserimento, genere_id, G.nome AS genere, descrizione, pezzi_venduti, pezzi_magazzino "
					+ "FROM cd AS C "
					+ "JOIN Genere AS G "
					+ "ON C.genere_id = G.id "
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
				
				genere = new Genere();
				genere.getById(rs.getInt("genere_id"));
				
				titolare = new Musicista();
				titolare.getTitolareByIdCd(id);
				
				partecipanti = new Musicista().getPartecipantiByIdCd(id);

				lista.add(new Cd(id, titolo, prezzo, dataInserimento, descrizione, pezziVenduti, pezziMagazzino, genere, titolare, partecipanti));
			}
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return lista;
	}

	/*
	 * ----------------------------------------------------------------
	 * 					DA SISTEMARE
	 * ----------------------------------------------------------------
	 */
	
	
	
	// TODO da sistemare
	public Boolean insert(String titolo,String descrizione,Date dataIns,BigDecimal prezzo,Integer pezziMagazzino,Integer genereId,ListModel<String> listaBrani)
	{

		try {
			String insertCdQuery="INSERT INTO Cd "
					+ "(titolo, prezzo,data_inserimento,descrizione,pezzi_magazzino,genere_id) "
					+ "VALUES (?,?,?,?,?,?)";
			
			PreparedStatement psIns = this.db.prepareStatement(insertCdQuery);
			
			int i = 1;
			psIns.setString(1 , titolo);
			psIns.setBigDecimal(2, prezzo);
			psIns.setDate(3, dataIns);
			psIns.setString(4, descrizione);
			psIns.setInt(5, pezziMagazzino);
			psIns.setInt(6, genereId);
		
			if(psIns.executeUpdate()!=1)
			{
				return false;
			}

			psIns.clearParameters();

			/*String insertParecipa="INSERT INTO Partecipazione "
					+ "(cd_codice,musicista_id,is_titolare) "
					+ "VALUES (?,?,?)";
			
			
			if( psIns.executeUpdate() != 1 )
				return false;*/
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
		
	}
	
	// TODO da sistemare
	public Boolean updateByCodice(String codice, 
				String titolo,
				String titoloBrani,
				String descrizione,
				BigDecimal prezzo,
				Date dataInserimento) {
	
		try {
			String query="UPDATE Cd SET titolo=?,"
					+ "titoloBrani=?,"
					+ "prezzo=?,"   
					+ "data_inserimento=?,"
					+ "descrizione=? "
					+ "WHERE codice=?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			Integer i = 1;
			
			ps.setString(i++, titolo);
			ps.setString(i++, titoloBrani);
			ps.setBigDecimal(i++, prezzo); 
			ps.setDate(i++, dataInserimento);
			ps.setString(i++, descrizione);
			ps.setString(i++, codice);
			
			if( ps.executeUpdate() != 1 )
				return false;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
	}
	
	// TODO da sistemare
	public ResultSet getAllInfo() {
		
		ResultSet rs = null;
		
		try {
			String query = "SELECT codice, titolo,  prezzo, data_inserimento, nome AS genere, descrizione, pezzi_venduti, pezzi_magazzino "
					+ "FROM cd AS C "
					+ "JOIN genere AS G "
					+ "ON C.genere_id = G.id "
					+ "ORDER BY nome";

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
}
