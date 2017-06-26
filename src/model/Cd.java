package model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import utility.*;


public class Cd{
	
	private Connection db;
	
	private String codice;
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
	 * @param codice
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
	public Cd(	String codice,
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
		
		this.codice = codice;
		this.titolo = titolo;
		this.prezzo = prezzo;
		this.dataInserimento = dataInserimento;
		this.descrizione = descrizione;
		this.pezziVenduti = pezziVenduti;
		this.pezziMagazzino = pezziMagazzino;
		this.genere = genere;
		this.titolare = titolare;
		this.partecipanti = partecipanti;
		
	}
	
	
	public String getCodice() {
		return this.codice;
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
	
	
	
	
	/**
	 * @param codice del cd del quale si vogliono settare le informazioni
	 * @return 
	 */
	public Cd getByCodice(String codice) {

		try {
			String query = "SELECT codice, titolo,  prezzo, data_inserimento, genere_id, G.nome AS genere, descrizione, pezzi_venduti, pezzi_magazzino "
					+ "FROM cd AS C "
					+ "JOIN Genere AS G "
					+ "ON C.genere_id = G.id "
					+ "WHERE C.codice = ?";
			
			PreparedStatement ps = this.db.prepareStatement(query);
			ps.setString(1, codice);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() ) {
				this.codice = rs.getString("codice");
				this.titolo = rs.getString("titolo");
				this.prezzo = rs.getBigDecimal("prezzo");
				this.dataInserimento = rs.getDate("data_inserimento");
				this.descrizione = rs.getString("descrizione");
				this.pezziVenduti = rs.getInt("pezzi_venduti");
				this.pezziMagazzino = rs.getInt("pezzi_magazzino");
				this.genere = new Genere();
				this.genere.getById(rs.getInt("genere_id"));

				this.titolare = new Musicista();
				this.titolare.getTitolareByCodiceCd(this.codice);
				
				this.partecipanti = new Musicista().getPartecipantiByCodiceCd(this.codice);
				
			}
			
			ps.close();
			rs.close();
			
			return this;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	
	}

	
	// TODO da rivedere da qua in poi
	
	public Boolean insert(String titolo,
			String descrizione,
			BigDecimal prezzo,
			Integer pezziMagazzino,
			Integer genereId,
			Hashtable<Integer, String> listaBrani) {

		try {
			String insertCdQuery="INSERT INTO Cd "
					+ "(titolo, prezzo, descrizione, pezzi_magazzino, genere_id) "
					+ "VALUES (?,?,?,?, ?)";
			
			PreparedStatement psIns = this.db.prepareStatement(insertCdQuery);
			
			int i = 1;
			psIns.setString(i++ , titolo);
			psIns.setBigDecimal(i++ , prezzo);
			psIns.setString(i++ , descrizione);
			psIns.setInt(i++ , pezziMagazzino);
			psIns.setInt(i++, genereId);
			
			
			Iterator it = listaBrani.entrySet().iterator();
			
			while(it.hasNext()) {
				
			}
			
			
			

			String insertParecipa="INSERT INTO Partecipazione "
					+ "(cd_codice,musicista_id,is_titolare) "
					+ "VALUES (?,?,?)";
			
			
			
			
			
			
			if( psIns.executeUpdate() != 1 )
				return false;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return true;
		
	}
	
	
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
