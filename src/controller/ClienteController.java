package controller;

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
	
	public boolean insert()
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
		   dataValidator.checkString(telefono))

		{
			cliente=new Cliente(username,password,nome,cognome,codiceFiscale,indirizzo,telefono,cellulare);
			return cliente.registra();
		}
		return false;
	}
}