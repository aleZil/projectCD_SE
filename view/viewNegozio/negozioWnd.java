package viewNegozio;
import java.awt.EventQueue;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import negozioListener.btnAddRegistrazione;
import negozioListener.btnEffettuaRicerca;
import negozioListener.btnLoginCliente;
import negozioListener.btnShowAreaRiservata;
import negozioListener.btnShowCarrello;
import negozioListener.btnShowDettagliCd;
import negozioListener.btnShowRegistrazione;
import negozioListener.btnShowHome;
import negozioListener.btnShowLogin;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import sun.java2d.loops.MaskBlit;
import utility.dataValidator;

import javax.swing.border.BevelBorder;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import controller.CdController;
import controller.ClienteController;
import controller.GenereController;
import model.Autenticazione;
import model.Cd;
import model.Cliente;
import model.Genere;
import model.Musicista;

import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class negozioWnd extends JFrame {
	
	private JPanel panelContainer;
	private CardLayout cardLayout;
	
	//Componenti negozio
	JButton btnLogin;
	JButton btnRegistrazione;
	JPanel homePanel;
	
	//Componenti pannello di registrazione
	private JTextField txtUsername;
	private JTextField txtCf;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JPasswordField txtPassword;
	private JTextField txtIndirizzo;
	private JTextField txtTelefono;
	private JTextField txtCellulare;
	
	//Componenti pannello di login cliente
	private JTextField txtUserLogin;
	private JPasswordField txtPassLogin;
	
	//Componenti pannello di ricerca
	private JTextField txtMinP;
	private JTextField txtMaxP;
	private JTextField txtTitolo;
	JComboBox<String> cbGenere;
	JComboBox<String> cbTitolare;
	JComboBox<String> cbPartecipanti;
	
	//Lista cd
	private JList<String> cdList;
	private DefaultListModel<String> cdListModel;
	private ArrayList<Integer> idCdList;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					negozioWnd negozio = new negozioWnd();
					negozio.showHome();
					negozio.setVisible(true);
					//frame.setExtendedState(Frame.MAXIMIZED_BOTH);		//fullscreen
					/*
					//Altro modo per fare fullscreen:
					int ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
					int ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
					frame.setSize(ScreenWidth, ScreenHeight);
					*/
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the frame.
	 */
	
	public negozioWnd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(getLocation().x,getLocation().y, 1000, 700);	//misura uguale a tutte le Wnd principali
		panelContainer = new JPanel();
		panelContainer.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(panelContainer);
		cardLayout=new CardLayout(0, 0);
		panelContainer.setLayout(cardLayout);
		
		this.setTitle("Homepage");
		
		createHomePanel();
		
		createRegistrazionePanel();
		
		createLoginPanel();

		
	}
	
	void createHomePanel()
	{
		idCdList=new ArrayList<Integer>();
		homePanel = new JPanel();
		panelContainer.add(homePanel, "home");
		homePanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[][grow][grow][grow]"));
		
		btnLogin = new JButton("Accedi");
		btnLogin.addActionListener(new btnShowLogin(this));
		
		JLabel lblcdList = new JLabel("Titoli disponibili");
		homePanel.add(lblcdList, "cell 1 0,alignx center,aligny center");
		
		JPanel filterPanel = new JPanel();
		filterPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Filtri disponibili", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		homePanel.add(filterPanel, "cell 0 1,growx,aligny center");
		filterPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][][][]"));
		
		JLabel lblTitolo = new JLabel("Titolo");
		filterPanel.add(lblTitolo, "cell 0 0 2 1,alignx center,aligny bottom");
		
		txtTitolo = new JTextField();
		filterPanel.add(txtTitolo, "cell 0 1 2 1,grow");
		txtTitolo.setColumns(10);
		
		JLabel lblGenere = new JLabel("Genere");
		filterPanel.add(lblGenere, "cell 0 2 2 1,alignx center,aligny bottom");
		
		cbGenere = new JComboBox();
		filterPanel.add(cbGenere, "cell 0 3 2 1,grow");
		
		JLabel lblTitolare = new JLabel("Titolare");
		filterPanel.add(lblTitolare, "cell 0 4 2 1,alignx center,aligny bottom");
		
		cbTitolare = new JComboBox();
		filterPanel.add(cbTitolare, "cell 0 5 2 1,grow");
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		filterPanel.add(lblPrezzo, "cell 0 6 2 1,alignx center,aligny bottom");
		
		JLabel lblMin = new JLabel("Min:");
		filterPanel.add(lblMin, "flowx,cell 0 7,alignx left,aligny center");
		
		txtMinP = new JTextField();
		filterPanel.add(txtMinP, "cell 1 7,grow");
		txtMinP.setColumns(10);
		
		JLabel lblMax = new JLabel("Max:");
		filterPanel.add(lblMax, "flowx,cell 0 8,alignx left,aligny center");
		
		txtMaxP = new JTextField();
		txtMaxP.setColumns(10);
		filterPanel.add(txtMaxP, "cell 1 8,grow");
		
		JLabel lblPartecipanti = new JLabel("Partecipanti");
		filterPanel.add(lblPartecipanti, "cell 0 9 2 1,alignx center,aligny bottom");
		
		cbPartecipanti = new JComboBox();
		filterPanel.add(cbPartecipanti, "cell 0 10 2 1,grow");
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new btnEffettuaRicerca(this));
		filterPanel.add(btnCerca, "cell 0 11 2 1,alignx center,aligny center");
		homePanel.add(btnLogin, "flowx,cell 2 1,alignx right,aligny top");
		
		btnRegistrazione = new JButton("Registrati");
		btnRegistrazione.addActionListener(new btnShowRegistrazione(this));
		homePanel.add(btnRegistrazione, "cell 2 1,alignx right,aligny top");
		
		JButton btnAreaRiservata = new JButton("Area Riservata");
		btnAreaRiservata.addActionListener(new btnShowAreaRiservata(this));
		homePanel.add(btnAreaRiservata, "cell 2 1,alignx right,aligny top");
		
		JScrollPane scrollPaneList = new JScrollPane();
		homePanel.add(scrollPaneList, "cell 1 1,grow");
		
		cdListModel=new DefaultListModel<String>();
		cdList = new JList<String>(cdListModel);
		scrollPaneList.setViewportView(cdList);
		
		JButton btnViewDetail = new JButton("Vedi dettagli prodotto");
		btnViewDetail.addActionListener(new btnShowDettagliCd(this));
		homePanel.add(btnViewDetail, "cell 1 2,growx,aligny top");
	}
	
	void createLoginPanel()
	{
		JPanel loginPanel = new JPanel();
		panelContainer.add(loginPanel, "login");
		loginPanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow]"));
		
		JLabel lblUserLogin = new JLabel("Username:");
		loginPanel.add(lblUserLogin, "cell 0 0,alignx trailing,aligny center");
		
		txtUserLogin = new JTextField();
		loginPanel.add(txtUserLogin, "cell 1 0,growx,aligny center");
		txtUserLogin.setColumns(10);
		
		JLabel lblPassLogin = new JLabel("Password:");
		loginPanel.add(lblPassLogin, "cell 0 1,alignx right,aligny center");
		
		txtPassLogin = new JPasswordField();
		loginPanel.add(txtPassLogin, "cell 1 1,growx");
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new btnLoginCliente(this));
		btnLogin.addKeyListener(new btnLoginCliente(this));
		loginPanel.add(btnLogin, "flowx,cell 1 2,growx,aligny center");
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new btnShowHome(this));
		loginPanel.add(btnAnnulla, "cell 1 2,growx,aligny center");
		loginPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUserLogin, txtPassLogin, btnLogin}));
	}

	void createRegistrazionePanel()
	{
		JPanel registrazionePanel = new JPanel();
		registrazionePanel.setBorder(new TitledBorder(null, "Informazioni utente", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelContainer.add(registrazionePanel, "registrazione");
		registrazionePanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
		
		JLabel lblUsername = new JLabel("Username:");
		registrazionePanel.add(lblUsername, "cell 0 0,alignx trailing,aligny center");
		
		txtUsername = new JTextField();
		registrazionePanel.add(txtUsername, "cell 1 0,growx,aligny center");
		txtUsername.setColumns(10);
		
		JLabel lblCf = new JLabel("Codice Fiscale:");
		registrazionePanel.add(lblCf, "cell 0 1,alignx right,aligny center");
		
		txtCf = new JTextField();
		txtCf.setColumns(10);
		registrazionePanel.add(txtCf, "cell 1 1,growx,aligny center");
		
		JLabel lblNome = new JLabel("Nome:");
		registrazionePanel.add(lblNome, "cell 0 2,alignx right,aligny center");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		registrazionePanel.add(txtNome, "cell 1 2,growx,aligny center");
		
		JLabel lblCognome = new JLabel("Cognome:");
		registrazionePanel.add(lblCognome, "cell 0 3,alignx right,growy");
		
		txtCognome = new JTextField();
		txtCognome.setColumns(10);
		registrazionePanel.add(txtCognome, "cell 1 3,growx,aligny center");
		
		JLabel lblPassword = new JLabel("Password:");
		registrazionePanel.add(lblPassword, "cell 0 4,alignx right,aligny center");
		
		txtPassword = new JPasswordField();
		JButton btnRegistra = new JButton("Registrati");
		registrazionePanel.add(txtPassword, "cell 1 4,growx,aligny center");
		registrazionePanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUsername, txtCf, txtNome, txtCognome, txtPassword, txtIndirizzo, txtTelefono, txtCellulare, btnRegistra}));
		
		JLabel lblIndirizzo = new JLabel("Indirizzo:");
		registrazionePanel.add(lblIndirizzo, "cell 0 5,alignx right,aligny center");
		
		txtIndirizzo = new JTextField();
		txtIndirizzo.setColumns(10);
		registrazionePanel.add(txtIndirizzo, "cell 1 5,growx,aligny center");
		
		JLabel lblTelefono = new JLabel("Telefono:");
		registrazionePanel.add(lblTelefono, "cell 0 6,alignx right,aligny center");
		
		txtTelefono = new JTextField();
		txtTelefono.setText("+39");
		txtTelefono.setColumns(10);
		registrazionePanel.add(txtTelefono, "cell 1 6,growx,aligny center");
		
		JLabel lblCellulare = new JLabel("Cellulare:");
		registrazionePanel.add(lblCellulare, "cell 0 7,alignx right,aligny center");
		
		txtCellulare = new JTextField();
		txtCellulare.setText("+39");
		txtCellulare.setColumns(10);
		registrazionePanel.add(txtCellulare, "cell 1 7,growx,aligny center");
		
		btnRegistra.addActionListener(new btnAddRegistrazione(this));
		btnRegistra.addKeyListener(new btnAddRegistrazione(this));
		registrazionePanel.add(btnRegistra, "flowx,cell 1 8,growx");
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new btnShowHome(this));
		registrazionePanel.add(btnAnnulla, "cell 1 8,growx,aligny center");
	}

	public void showRegistrazione()
	{
		this.setTitle("Registrazione nuovo utente");
		cardLayout.show(panelContainer, "registrazione");
		txtUsername.requestFocus();
	}
	
	public void showHome()
	{
		this.setTitle("Homepage");
		cdListModel.clear();
		Cd cds=new Cd();
		Genere gen=new Genere();
		Musicista mus=new Musicista();
		loadListCd(cds.getAll());
		loadGeneri(gen.getAll());
		loadTitolari(mus.getAllBand());
		loadPartecipanti(mus.getAllNotBand());
		cardLayout.show(panelContainer, "home");
	}
	
	public void showHome(String username)
	{
		btnLogin.setEnabled(false);
		btnRegistrazione.setEnabled(false);
		btnLogin.setVisible(false);
		btnRegistrazione.setVisible(false);
		
		//Creo bottone del carrello
		JButton btnCarrello=new JButton("Carrello");
		homePanel.add(btnCarrello, "cell 2 1,alignx trailing,aligny top");
		btnCarrello.setVisible(true);
		btnCarrello.setEnabled(true);
		btnCarrello.addActionListener(new btnShowCarrello(this));
		cardLayout.show(panelContainer, "home");
	}
	
	public void showCarrello()
	{
		
	}
	
	public void showLogin()
	{
		this.setTitle("Login");
		cardLayout.show(panelContainer, "login");
		txtUserLogin.requestFocus();
	}
	
	public void search()
	{
		String titolo=getTitolo();
		String genere=getGenere();
		BigDecimal minP=getMinPrezzo();
		BigDecimal maxP=getMaxPrezzo();
		String titolare=getTitolare();
		String partecipante=getPartecipante();
		Cd cd=new Cd();
		cdListModel.clear();
		ArrayList<Cd> risultato=cd.getByFilter(titolo, genere, titolare, partecipante, minP, maxP);
		loadListCd(risultato);
		
	}
	
	public void registraNuovoUtente()
	{
		ClienteController clienteC=new ClienteController(this);
		try {
			if(clienteC.insert()) {
				JOptionPane.showMessageDialog(this, "Registrazione effettuata correttamente","Info",JOptionPane.INFORMATION_MESSAGE);
				clearComponents();
				showHome();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Errore!",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void loginCliente()
	{
		String user=getTxtUsernameLogin();
		String pwd=getTxtPassLogin();
		Autenticazione auth = new Autenticazione("cliente", user, pwd);
		
		if (auth.login()) {
			JOptionPane.showMessageDialog(this, "Benvenuto "+user+"!","Info",JOptionPane.INFORMATION_MESSAGE);
			showHome(user);
		} else {
			JOptionPane.showMessageDialog(this, "Username o password non corretti!");
		}
		
	}
	
	//Utility
	
	private void loadListCd(ArrayList<Cd> listaCd)
	{
		for(Cd c:listaCd)
		{
			cdListModel.addElement(c.getTitolo());
			idCdList.add(c.getId());
		}
	}
	
	private void loadGeneri(ArrayList<Genere> listaGeneri)
	{
		cbGenere.addItem("");
		for(Genere g:listaGeneri)
		{
			cbGenere.addItem(g.getNome());
		}
	}
	
	private void loadTitolari(ArrayList<Musicista> listaTitolari)
	{
		
		cbTitolare.addItem("");
		for(Musicista t:listaTitolari)
		{
			cbTitolare.addItem(t.getNomeArte());
		}
	}
	
	private void loadPartecipanti(ArrayList<Musicista> listaMusicisti)
	{
		
		cbPartecipanti.addItem("");
		for(Musicista m:listaMusicisti)
		{
			cbPartecipanti.addItem(m.getNomeArte());
		}
	}
	
	// Metodi get
	
	public int getSelectedCd()
	{
		if(cdList.getSelectedIndex()!=-1)
			return idCdList.get(cdList.getSelectedIndex());
		else
			return -1;
	}
	
	private String getTitolo()
	{
		return txtTitolo.getText();
	}
	
	private String getGenere()
	{
		return (String) cbGenere.getSelectedItem();
	}
	
	private BigDecimal getMinPrezzo()
	{
		if(dataValidator.checkCdPrice(txtMinP.getText()))
		{
			return new BigDecimal(txtMinP.getText());
		}
		else
		{
			return new BigDecimal("-1.0");
		}
	}
	
	private BigDecimal getMaxPrezzo()
	{
		if(dataValidator.checkCdPrice(txtMaxP.getText()))
		{
			return new BigDecimal(txtMaxP.getText());
		}
		else
		{
			return new BigDecimal("-1.0");
		}
	}
	
	private String getTitolare()
	{
		return (String)cbTitolare.getSelectedItem();
	}
	
	private String getPartecipante()
	{
		return (String)cbPartecipanti.getSelectedItem();
	}
	
	private String getTxtUsernameLogin()
	{
		return txtUserLogin.getText();
	}
	
	private String getTxtPassLogin()
	{
		return txtPassLogin.getText();
	}
	
	public String getTxtUsernameReg()
	{
		return txtUsername.getText();
	}
	
	public String getTxtPassReg()
	{
		return txtPassword.getText();
	}
	
	public String getTxtNomeReg()
	{
		return txtNome.getText();
	}
	
	public String getTxtCognomeReg()
	{
		return txtCognome.getText();
	}
	
	public String getTxtCodiceReg()
	{
		return txtCf.getText();
	}
	
	public String getTxtIndirizzoReg()
	{
		return txtIndirizzo.getText();
	}
	
	public String getTxtTelefonoReg()
	{
		return txtTelefono.getText();
	}
	
	public String getTxtCellulareReg()
	{
		return txtCellulare.getText();
	}
	
	private void clearComponents()
	{
		txtUsername.setText("");
		txtPassword.setText("");
		txtNome.setText("");
		txtCognome.setText("");
		txtCf.setText("");
		txtIndirizzo.setText("");
		txtTelefono.setText("");
		txtCellulare.setText("");
		txtUserLogin.setText("");
		txtPassLogin.setText("");
	}

	
}
