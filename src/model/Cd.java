package model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;


public class Cd {

	private String codice;
	private String titolo;
	private String titoloBrani;
	private BigDecimal prezzo;
	private Date dataInserimento;
	private Integer pezziVenduti;
	private Integer pezziMagazzino;
	private String genere;
	private List<String> musicisti;
	
	
	public Cd() {
		

	}
	
}
