package viewNegozio;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import negozioListener.btnAddRegistrazione;
import negozioListener.btnLoginCliente;
import negozioListener.btnShowAreaRiservata;
import negozioListener.btnShowRegistrazione;
import negozioListener.btnShowHome;
import negozioListener.btnShowLogin;

import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.BevelBorder;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class negozioWnd extends JFrame {
	
	private JPanel panelContainer;
	private CardLayout cardLayout;
	
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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					negozioWnd negozio = new negozioWnd();
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
		
		this.setTitle("Home");
		
		createHomePanel();
		
		createRegistrazionePanel();
		
		createLoginPanel();

		
	}
	
	void createHomePanel()
	{
		JPanel homePanel = new JPanel();
		panelContainer.add(homePanel, "home");
		homePanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow]"));
		
		JButton btnLogin = new JButton("Accedi");
		btnLogin.addActionListener(new btnShowLogin(this));
		homePanel.add(btnLogin, "flowx,cell 2 0,alignx right,aligny top");
		
		JButton btnRegistrazione = new JButton("Registrati");
		btnRegistrazione.addActionListener(new btnShowRegistrazione(this));
		homePanel.add(btnRegistrazione, "cell 2 0,alignx right,aligny top");
		
		JButton btnAreaRiservata = new JButton("Area Riservata");
		btnAreaRiservata.addActionListener(new btnShowAreaRiservata(this));
		homePanel.add(btnAreaRiservata, "cell 2 0,alignx right,aligny top");
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
		registrazionePanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow]\n"));
		
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
		registrazionePanel.add(txtPassword, "cell 1 4,growx,aligny center");
		
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
		
		JButton btnRegistra = new JButton("Registrati");
		btnRegistra.addActionListener(new btnAddRegistrazione(this));
		registrazionePanel.add(btnRegistra, "flowx,cell 1 8,growx");
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new btnShowHome(this));
		registrazionePanel.add(btnAnnulla, "cell 1 8,growx,aligny center");
		registrazionePanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUsername, txtCf, txtNome, txtCognome, txtPassword, txtIndirizzo, txtTelefono, txtCellulare, btnRegistra}));
	}

	public void showRegistrazione()
	{
		this.setTitle("Registrazione nuovo utente");
		cardLayout.show(panelContainer, "registrazione");
	}
	
	public void showHome()
	{
		this.setTitle("Home");
		cardLayout.show(panelContainer, "home");
	}
	
	public void showLogin()
	{
		this.setTitle("Login");
		cardLayout.show(panelContainer, "login");
	}
	
	
	public void registraNuovoUtente()
	{
		
	}
	
	public void loginCliente()
	{
		
	}
	
	// Metodi get
	
	public String getTxtUsernameLogin()
	{
		return txtUserLogin.getText();
	}
	
	public String getTxtPassLogin()
	{
		return txtPassLogin.getText();
	}
}
