package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import utility.Db;


public class Carrello {

	private Connection db;
	private Cliente cliente;
	ArrayList<RigaCarrello> righeCarrello;
	
	public Carrello(Cliente cliente) {
		try {
			this.db = Db.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.righeCarrello = new ArrayList<RigaCarrello>();
		this.setCliente(cliente);
	}
	
	public ArrayList<RigaCarrello> getRighe() {
		return this.righeCarrello;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void addRow(RigaCarrello row) {
		this.righeCarrello.add(row);
	}
	
	public void updateQta(int nRiga, int newQta) {
		
		RigaCarrello riga = this.getRighe().get(nRiga);
		riga.setQta(newQta);
		
		this.getRighe().set(nRiga, riga);
	}
	
	public boolean creaOrdine(String modalitaAcquisto, String modalitaConsegna, String ip) {
		
		String clienteUsername = this.getCliente().getUsername();
		BigDecimal prezzoTotale = new BigDecimal(0);
		
		for (int i = 0; i < this.getRighe().size(); i++) {
			
			RigaCarrello riga = this.getRighe().get(i);
			prezzoTotale.add(riga.getPrezzo());
		}
		
		try {
			
			String insertQuery = "INSERT INTO ordine "
					+ "(cliente, prezzo_complessivo, modalita_acquisto, modalita_consegna, ip, prezzo_complessivo) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?) ";
			
			PreparedStatement psIns = this.db.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			psIns.setString(i++, clienteUsername);
			psIns.setBigDecimal(i++, prezzoTotale);
			psIns.setString(i++, modalitaAcquisto);
			psIns.setString(i++, modalitaConsegna);
			psIns.setString(i++, ip);
			psIns.setBigDecimal(i++, prezzoTotale);
			psIns.executeUpdate();

			int ordine_id = 0;
			ResultSet rs = psIns.getGeneratedKeys();

			if (rs.next()){
				ordine_id = rs.getInt("id");   	// recupero l'id della tupla inserita
			} else {
				return false;
			}
			
			// -----------------------------------------------------
			
			// 			INSERIMENTO righe del carrello e MODIFICA magazzino
			
			// -----------------------------------------------------
			
			insertQuery = "INSERT INTO rigaOrdine "
					+ "(ordine_id, cd_id, qta, prezzo) "
					+ "VALUES "
					+ "(?, ?, ?, ?) ";
			psIns = this.db.prepareStatement(insertQuery);
			
			String query = "UPDATE cd SET quantita_magazzino = quantita_magazzino - ? WHERE id = ?";
			PreparedStatement ps = this.db.prepareStatement(query);
			
			
			for (int j = 0; j < this.righeCarrello.size(); j++) {
				
				psIns.clearParameters();
				ps.clearParameters();
				
				// aggiunta riga carrello
				RigaCarrello riga = this.righeCarrello.get(j);
				
				i = 1;
				psIns.setInt(i++, ordine_id);
				psIns.setInt(i++, riga.getCd().getId());
				psIns.setInt(i++, riga.getQta());
				psIns.setBigDecimal(i++, riga.getPrezzo());
				
				psIns.executeUpdate();
				
				// modifica quantita_magazzino
				i = 1;
				ps.setInt(i++, riga.getQta());
				ps.setInt(i++, riga.getCd().getId());
			}
			
			psIns.close();
			ps.close();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
}
