package controller;

import exception.InsertFailedException;
import exception.MissingDataException;
import model.Cliente;
import utility.dataValidator;
import viewNegozio.negozioWnd;

public class ClienteController {
	private negozioWnd negozio;
	private Cliente cliente;
	
	public ClienteController(negozioWnd negozio)
	{
		this.negozio=negozio;
	}
	
	public boolean insert() throws MissingDataException
	{
		String username=negozio.getTxtUsernameReg();
		String password=negozio.getTxtPassReg();
		String codiceFiscale=negozio.getTxtCodiceReg();
		String nome=negozio.getTxtNomeReg();
		String cognome=negozio.getTxtCognomeReg();
		String indirizzo=negozio.getTxtIndirizzoReg();
		String telefono=negozio.getTxtTelefonoReg();
		String cellulare=negozio.getTxtCellulareReg();
		
		//Check sui dati
		if(dataValidator.checkString(username) && dataValidator.checkString(password) && dataValidator.checkString(nome) && 
		   dataValidator.checkString(cognome) && dataValidator.checkString(indirizzo) && 
		   dataValidator.checkNumber(telefono)) {
			
			cliente=new Cliente(username,password,nome,cognome,codiceFiscale,indirizzo,telefono,cellulare);

			if(cliente.registra()) {
				return true;
			} else {
				throw new InsertFailedException("Inserimento fallito.");
			}
		}else{
			throw new MissingDataException("Attenzione: dati mancanti");
		}
	}
}
