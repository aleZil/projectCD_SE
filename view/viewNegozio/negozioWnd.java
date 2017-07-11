package viewNegozio;
import java.awt.EventQueue;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import negozioListener.btnAddRegistrazione;
import negozioListener.btnEffettuaOrdine;
import negozioListener.btnEffettuaRicerca;
import negozioListener.btnLoginCliente;
import negozioListener.btnShowAreaRiservata;
import negozioListener.btnShowCarrello;
import negozioListener.btnShowDettagliCd;
import negozioListener.btnShowRegistrazione;
import negozioListener.btnShowHome;
import negozioListener.btnShowLogin;
import negozioListener.btnShowPagamento;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.BevelBorder;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import controller.CarrelloController;
import controller.ClienteController;
import model.Autenticazione;
import model.Carrello;
import model.Cd;
import model.Cliente;
import model.Genere;
import model.Musicista;
import model.RigaCarrello;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import utility.*;
import java.net.*;
import java.io.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.UIManager.*;

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
	private boolean isLogged=false;

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

	//Carrello
	private JTable carrelloTb;
	private TableModelCarrello carrelloModel;
	private ArrayList<Integer> idCdCarrello;

	// Carrello Model
	private CarrelloController cCarrello;
	private Carrello carrello;

	//Pagamento
	JRadioButton rbBonifico;
	JRadioButton rbCarta;
	JRadioButton rbPayPal;
	JRadioButton rbCorriere;
	JRadioButton rbPosta;

	private JLabel lblWelcome;
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public negozioWnd() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}

		setBounds(getLocation().x,getLocation().y, 680, 650);	//Misura uguale a tutte le Wnd principali
		setLocationRelativeTo(null);
		panelContainer = new JPanel();
		panelContainer.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(panelContainer);
		cardLayout=new CardLayout(0, 0);
		panelContainer.setLayout(cardLayout);
		this.setTitle("Homepage");

		createHomePanel();

		createRegistrazionePanel();

		createLoginPanel();

		createCarrelloPanel();

		createPagamentoPanel();
	}

	void createHomePanel()
	{

		idCdList=new ArrayList<Integer>();
		homePanel = new JPanel();
		panelContainer.add(homePanel, "home");
		homePanel.setLayout(new MigLayout("", "[300][grow]", "[][][][60][grow,fill][]"));

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new btnShowLogin(this));

		btnRegistrazione = new JButton("Registrati");
		btnRegistrazione.addActionListener(new btnShowRegistrazione(this));

		JButton btnAreaRiservata = new JButton("Area Riservata");
		btnAreaRiservata.addActionListener(new btnShowAreaRiservata(this));

		JLabel lblLogo = new JLabel(new ImageIcon("/Users/Andy/Documents/Università_mac/Ing_software/projectCD_SE/view/viewNegozio/icons/logo.png"));
		lblLogo.setBounds(0,0,32,32);
		lblLogo.setVisible(true);

		homePanel.add(lblLogo, "cell 0 0,alignx center,aligny center");

		homePanel.add(btnAreaRiservata, "flowx,cell 1 0,alignx center,aligny center");
		homePanel.add(btnRegistrazione, "cell 1 0,alignx center,aligny center");
		homePanel.add(btnLogin, "cell 1 0,alignx center,aligny center");

		JLabel lblFiltriDisponibili = new JLabel("Cerca Cd");
		lblFiltriDisponibili.setFont(new Font("Dialog", Font.BOLD, 18));
		homePanel.add(lblFiltriDisponibili, "cell 0 1,alignx center,aligny bottom");

		JLabel lblcdList = new JLabel("Titoli disponibili");
		lblcdList.setFont(new Font("Dialog", Font.BOLD, 18));
		homePanel.add(lblcdList, "cell 1 1,alignx center,aligny bottom");

		lblWelcome = new JLabel("");
		lblWelcome.setFont(new Font("Dialog", Font.PLAIN, 14));
		homePanel.add(lblWelcome, "cell 1 2,alignx center,aligny bottom");

		JPanel filterPanel = new JPanel();
		filterPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		homePanel.add(filterPanel, "cell 0 3 1 3,grow");
		filterPanel.setLayout(new MigLayout("", "[][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));

		JLabel lblTitolo = new JLabel("Titolo");
		lblTitolo.setFont(new Font("Dialog", Font.BOLD, 14));
		filterPanel.add(lblTitolo, "cell 0 0 2 1,alignx center,aligny center");

		txtTitolo = new JTextField();
		txtTitolo.setFont(new Font("Dialog", Font.PLAIN, 14));
		filterPanel.add(txtTitolo, "cell 0 1 2 1,grow");
		txtTitolo.setColumns(10);

		JLabel lblGenere = new JLabel("Genere");
		lblGenere.setFont(new Font("Dialog", Font.BOLD, 14));
		filterPanel.add(lblGenere, "cell 0 2 2 1,alignx center,aligny center");

		cbGenere = new JComboBox();
		cbGenere.setFont(new Font("Dialog", Font.BOLD, 14));
		filterPanel.add(cbGenere, "cell 0 3 2 1,grow");

		JLabel lblTitolare = new JLabel("Titolare");
		lblTitolare.setFont(new Font("Dialog", Font.BOLD, 14));
		filterPanel.add(lblTitolare, "cell 0 4 2 1,alignx center,aligny center");

		cbTitolare = new JComboBox();
		cbTitolare.setFont(new Font("Dialog", Font.BOLD, 14));
		filterPanel.add(cbTitolare, "cell 0 5 2 1,grow");

		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setFont(new Font("Dialog", Font.BOLD, 14));
		filterPanel.add(lblPrezzo, "cell 0 6 2 1,alignx center,aligny center");

		JLabel lblMin = new JLabel("Min:");
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		filterPanel.add(lblMin, "flowx,cell 0 7,alignx left,aligny center");

		txtMinP = new JTextField();
		txtMinP.setFont(new Font("Dialog", Font.PLAIN, 14));
		filterPanel.add(txtMinP, "cell 1 7,grow");
		txtMinP.setColumns(10);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		filterPanel.add(lblMax, "flowx,cell 0 8,alignx left,aligny center");

		txtMaxP = new JTextField();
		txtMaxP.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtMaxP.setColumns(10);
		filterPanel.add(txtMaxP, "cell 1 8,grow");

		JLabel lblPartecipanti = new JLabel("Partecipanti");
		lblPartecipanti.setFont(new Font("Dialog", Font.BOLD, 14));
		filterPanel.add(lblPartecipanti, "cell 0 9 2 1,alignx center,aligny center");

		cbPartecipanti = new JComboBox();
		cbPartecipanti.setFont(new Font("Dialog", Font.BOLD, 14));
		filterPanel.add(cbPartecipanti, "cell 0 10 2 1,grow");

		JButton btnCerca = new JButton("Cerca");
		btnCerca.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCerca.addActionListener(new btnEffettuaRicerca(this));
		filterPanel.add(btnCerca, "cell 0 11 2 1,grow");
		filterPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtTitolo, cbGenere, cbTitolare, txtMinP, txtMaxP, cbPartecipanti, btnCerca}));

		JScrollPane scrollPaneList = new JScrollPane();
		homePanel.add(scrollPaneList, "cell 1 3 1 2,grow");

		cdListModel=new DefaultListModel<String>();
		cdList = new JList<String>(cdListModel);
		cdList.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPaneList.setViewportView(cdList);

		JButton btnViewDetail = new JButton("Vedi dettagli prodotto");
		btnViewDetail.setFont(new Font("Dialog", Font.BOLD, 16));
		btnViewDetail.addActionListener(new btnShowDettagliCd(this));
		homePanel.add(btnViewDetail, "cell 1 5,growx,aligny bottom");
	}

	void createLoginPanel()
	{
		JPanel loginPanel = new JPanel();
		panelContainer.add(loginPanel, "login");
		loginPanel.setLayout(new MigLayout("", "[100][grow][100]", "[grow][][grow][][grow][][]"));

		JLabel lblUserLogin = new JLabel("Username");
		lblUserLogin.setFont(new Font("Dialog", Font.BOLD, 15));
		loginPanel.add(lblUserLogin, "cell 1 0,alignx center,aligny bottom");

		txtUserLogin = new JTextField();
		txtUserLogin.setFont(new Font("Dialog", Font.PLAIN, 14));
		loginPanel.add(txtUserLogin, "cell 1 1,growx,aligny center");
		txtUserLogin.setColumns(10);

		JLabel lblPassLogin = new JLabel("Password");
		lblPassLogin.setFont(new Font("Dialog", Font.BOLD, 15));
		loginPanel.add(lblPassLogin, "cell 1 2,alignx center,aligny bottom");

		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new btnShowHome(this));

		JButton btnLogin_1 = new JButton("Login");
		btnLogin_1.setFont(new Font("Dialog", Font.BOLD, 18));
		btnLogin_1.addActionListener(new btnLoginCliente(this));
		btnLogin_1.addKeyListener(new btnLoginCliente(this));
		loginPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUserLogin, txtPassLogin, btnLogin_1}));

		txtPassLogin = new JPasswordField();
		txtPassLogin.setFont(new Font("Dialog", Font.PLAIN, 14));
		loginPanel.add(txtPassLogin, "flowy,cell 1 3,growx,aligny center");
		loginPanel.add(btnLogin_1, "cell 1 5,growx,aligny bottom");
		loginPanel.add(btnAnnulla, "cell 1 6,growx,aligny top");
	}

	void createCarrelloPanel()
	{
		JPanel carrelloPanel = new JPanel();
		panelContainer.add(carrelloPanel, "carrello");
		carrelloPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));

		idCdCarrello=new ArrayList<Integer>();
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Carrello", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		carrelloPanel.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill][]"));

		JScrollPane elderScroll = new JScrollPane();
		panel.add(elderScroll, "cell 0 0,grow");

		carrelloTb = new JTable();
		carrelloTb.setFont(new Font("Dialog", Font.PLAIN, 14));
		elderScroll.setViewportView(carrelloTb);

		JButton btnCompra = new JButton("Acquista");
		btnCompra.setFont(new Font("Dialog", Font.BOLD, 18));
		btnCompra.addActionListener(new btnShowPagamento(this));
		panel.add(btnCompra, "flowx,cell 0 1,grow");

		JButton btnNegozio = new JButton("Torna a negozio");
		btnNegozio.addActionListener(new btnShowHome(this));
		panel.add(btnNegozio, "cell 0 1,alignx right,growy");

	}

	void createRegistrazionePanel()
	{
		JPanel registrazionePanel = new JPanel();
		registrazionePanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Informazioni utente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelContainer.add(registrazionePanel, "registrazione");
		registrazionePanel.setLayout(new MigLayout("", "[100][grow][100]", "[][50][][50][][50][][50][][50][][50][][50][][50][][]"));

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 14));
		registrazionePanel.add(lblUsername, "cell 0 1,alignx right,aligny center");

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Dialog", Font.PLAIN, 14));
		registrazionePanel.add(txtUsername, "cell 1 1,growx,aligny center");
		txtUsername.setColumns(10);

		JLabel lblCf = new JLabel("Codice Fiscale");
		lblCf.setFont(new Font("Dialog", Font.BOLD, 14));
		registrazionePanel.add(lblCf, "cell 0 3,alignx right,aligny center");

		txtCf = new JTextField();
		txtCf.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtCf.setColumns(10);
		registrazionePanel.add(txtCf, "cell 1 3,growx,aligny center");

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		registrazionePanel.add(lblNome, "cell 0 5,alignx right,aligny center");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtNome.setColumns(10);
		registrazionePanel.add(txtNome, "cell 1 5,growx,aligny center");

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Dialog", Font.BOLD, 14));
		registrazionePanel.add(lblCognome, "cell 0 7,alignx right,aligny center");

		txtCognome = new JTextField();
		txtCognome.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtCognome.setColumns(10);
		registrazionePanel.add(txtCognome, "cell 1 7,growx,aligny center");

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 14));
		registrazionePanel.add(lblPassword, "cell 0 9,alignx right,aligny center");

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Dialog", Font.PLAIN, 14));
		registrazionePanel.add(txtPassword, "cell 1 9,growx,aligny center");

		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setFont(new Font("Dialog", Font.BOLD, 14));
		registrazionePanel.add(lblIndirizzo, "cell 0 11,alignx right,aligny center");

		txtIndirizzo = new JTextField();
		txtIndirizzo.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtIndirizzo.setColumns(10);
		registrazionePanel.add(txtIndirizzo, "cell 1 11,growx,aligny center");

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Dialog", Font.BOLD, 14));
		registrazionePanel.add(lblTelefono, "cell 0 13,alignx right,aligny center");

		txtTelefono = new JTextField("+39");
		txtTelefono.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTelefono.setText("+39");
		txtTelefono.setColumns(10);
		registrazionePanel.add(txtTelefono, "cell 1 13,growx,aligny center");

		JLabel lblCellulare = new JLabel("Cellulare");
		lblCellulare.setFont(new Font("Dialog", Font.BOLD, 14));
		registrazionePanel.add(lblCellulare, "cell 0 15,alignx right,aligny center");

		txtCellulare = new JTextField("+39");
		txtCellulare.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtCellulare.setText("+39");
		txtCellulare.setColumns(10);
		registrazionePanel.add(txtCellulare, "cell 1 15,growx,aligny center");

		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new btnShowHome(this));
		JButton btnRegistra = new JButton("Registrati");
		btnRegistra.setFont(new Font("Dialog", Font.BOLD, 16));
		registrazionePanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUsername, txtCf, txtNome, txtCognome, txtPassword, txtIndirizzo, txtTelefono, txtCellulare, btnRegistra}));

		btnRegistra.addActionListener(new btnAddRegistrazione(this));
		btnRegistra.addKeyListener(new btnAddRegistrazione(this));
		registrazionePanel.add(btnRegistra, "cell 1 16,grow");
		registrazionePanel.add(btnAnnulla, "cell 1 17,grow");
	}

	void createPagamentoPanel()
	{
		JPanel pagamentoPanel = new JPanel();
		panelContainer.add(pagamentoPanel, "pagamento");
		pagamentoPanel.setLayout(new MigLayout("", "[100][grow][100]", "[grow][grow][][]"));

		JPanel pagamentoP = new JPanel();
		pagamentoP.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Metodo di pagamento", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		pagamentoPanel.add(pagamentoP, "flowx,cell 1 0,grow");
		pagamentoP.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));

		ButtonGroup pagamentoGroup=new ButtonGroup();
		rbBonifico = new JRadioButton("Bonifico");
		rbBonifico.setFont(new Font("Dialog", Font.BOLD, 14));
		pagamentoP.add(rbBonifico, "cell 0 0,alignx left");

		rbCarta = new JRadioButton("Carta di credito");
		rbCarta.setFont(new Font("Dialog", Font.BOLD, 14));
		pagamentoP.add(rbCarta, "cell 0 1,alignx left");

		rbPayPal = new JRadioButton("Pay Pal");
		rbPayPal.setFont(new Font("Dialog", Font.BOLD, 14));
		pagamentoP.add(rbPayPal, "cell 0 2,alignx left");

		pagamentoGroup.add(rbBonifico);
		pagamentoGroup.add(rbCarta);
		pagamentoGroup.add(rbPayPal);


		ButtonGroup consegnaGroup=new ButtonGroup();
		JPanel consegnaPanel = new JPanel();
		consegnaPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Metodo Consegna", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		pagamentoPanel.add(consegnaPanel, "flowx,cell 1 1,grow");
		consegnaPanel.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));

		rbCorriere = new JRadioButton("Corriere");
		rbCorriere.setFont(new Font("Dialog", Font.BOLD, 14));
		consegnaPanel.add(rbCorriere, "cell 0 0,alignx left,growy");

		rbPosta = new JRadioButton("Posta");
		rbPosta.setFont(new Font("Dialog", Font.BOLD, 14));
		consegnaPanel.add(rbPosta, "cell 0 1,alignx left,growy");

		JButton btnConfermaOrdine = new JButton("Conferma ordine");
		btnConfermaOrdine.setFont(new Font("Dialog", Font.BOLD, 18));
		btnConfermaOrdine.addActionListener(new btnEffettuaOrdine(this));
		pagamentoPanel.add(btnConfermaOrdine, "flowy,cell 1 2,grow");
		consegnaGroup.add(rbCorriere);
		consegnaGroup.add(rbPosta);

		JButton btnBack = new JButton("Indietro");
		btnBack.setFont(new Font("Dialog", Font.BOLD, 14));
		pagamentoPanel.add(btnBack, "cell 1 3,grow");
		btnBack.addActionListener(new btnShowHome(this));
	}

	public void showRegistrazione()
	{
		this.setTitle("Registrazione nuovo utente");
		cardLayout.show(panelContainer, "registrazione");
		txtUsername.requestFocus();
	}

	public void showHome()
	{
		this.setTitle("Home");
		cdListModel.clear();
		clearComponents();

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
		this.setTitle("Home");
		// Carrello Model
		lblWelcome.setText("Benvenuto "+username+"!");
		Cliente cliente = new Cliente();
		cliente.getByUsername(username);
		carrello = new Carrello(cliente);
		cCarrello = new CarrelloController(carrello);
		
		//Creo bottone del carrello
		JButton btnCarrello=new JButton("Carrello");
		homePanel.remove(btnRegistrazione);
		homePanel.remove(btnLogin);
		homePanel.add(btnCarrello, "flowx,cell 1 0,alignx center,aligny center");
		btnCarrello.setVisible(true);
		btnCarrello.setEnabled(true);
		btnCarrello.addActionListener(new btnShowCarrello(this));
		cardLayout.show(panelContainer, "home");
	}

	public void showCarrello()
	{
		this.setTitle("Carrello");
		String[] colNames={"Titolo","Prezzo","Quantità","Aggiungi","Rimuovi"};

		carrelloModel=new TableModelCarrello();		//crea la tabella
		carrelloModel.setColumnIdentifiers(colNames);

		Carrello cart = cCarrello.getCarrello();

		for(int i = 0; i < cart.getRighe().size(); i++) {

			RigaCarrello rowCart = cart.getRighe().get(i);
			Cd cd = rowCart.getCd();
			BigDecimal prezzo = rowCart.getPrezzo();
			int qta = rowCart.getQta();

			Object[] row={cd.getTitolo(), prezzo, qta, "+", "-"};
			carrelloModel.addRow(row);
		}

		carrelloTb.setModel(carrelloModel);
		carrelloTb.getColumn("Aggiungi").setCellRenderer(new ButtonRenderer());
		carrelloTb.getColumn("Aggiungi").setCellEditor(new ButtonEditor(new JCheckBox(),this));
		carrelloTb.getColumn("Rimuovi").setCellRenderer(new ButtonRenderer());
		carrelloTb.getColumn("Rimuovi").setCellEditor(new ButtonEditor(new JCheckBox(),this));
		cardLayout.show(panelContainer, "carrello");
	}

	public void showLogin()
	{
		this.setTitle("Login");
		cardLayout.show(panelContainer, "login");
		txtUserLogin.requestFocus();
	}

	public void showPagamento()
	{
		int totAmount=0;
		for(int i=0;i<carrello.getRighe().size();i++)
		{
			totAmount+=Integer.parseInt(carrelloModel.getValueAt(i, 2).toString());
		}
		
		if(carrello.getRighe().size()>0 && totAmount>0)
		{
			this.setTitle("Modalità ordine");
			cardLayout.show(panelContainer, "pagamento");
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Carrello vuoto","Attenzione!",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void effettuaPagamento()
	{
		String pagamento;
		String consegna;
		if(rbBonifico.isSelected())
		{
			pagamento="BONIFICO";
		}
		else if(rbCarta.isSelected())
		{
			pagamento="CARTA_CREDITO";
		}
		else if(rbPayPal.isSelected())
		{
			pagamento="PAYPAL";
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Selezionare metodo di pagamento!","Info",JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if(rbCorriere.isSelected())
		{
			consegna="CORRIERE";
		}
		else if(rbPosta.isSelected())
		{
			consegna="POSTA";
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Selezionare metodo di consegna!","Info",JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		try
		{
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine();
			if(cCarrello.creaOrdine(pagamento, consegna, ip))
			{
				JOptionPane.showMessageDialog(this, "Ordine effettuato!","Info",JOptionPane.INFORMATION_MESSAGE);
				carrelloTb.removeAll();
				carrello.svuotaCarrello();
				idCdCarrello.clear();
			} else {
				JOptionPane.showMessageDialog(this,"Si è verificato un errore","Errore",JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}

		showHome();
	}

	public void search()
	{
		String titolo=getTitolo();
		String genere=getGenere();
		BigDecimal minP=getMinPrezzo();
		BigDecimal maxP=getMaxPrezzo();
		String titolare=getTitolare();
		String partecipante=getPartecipante();
		Cd cd = new Cd();
		cdListModel.clear();
		ArrayList<Cd> risultato = cd.getByFilter(titolo, genere, titolare, partecipante, minP, maxP);
		loadListCd(risultato);
	}

	public boolean controlloCarrello(Integer cdId)
	{
		return !idCdCarrello.contains(cdId);
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
			isLogged=true;
			showHome(user);
		} else {
			JOptionPane.showMessageDialog(this, "Username o password non corretti!","Errore",JOptionPane.ERROR_MESSAGE);
		}

	}

	//Utility

	public void aggiungiAlCarrello(Integer id)
	{
		if(!idCdCarrello.contains(id))
		{
			cCarrello.addRigaCarrello(id);
			idCdCarrello.add(id);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Cd già nel carrello","Info",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void incrementaTitolo(int row)
	{
		int q=Integer.parseInt(carrelloTb.getValueAt(row, 2).toString());
		carrelloTb.setValueAt(++q, row, 2);
		cCarrello.incrementaQta(row);
	}


	public void decrementaTitolo(int row)
	{
		int q=Integer.parseInt(carrelloTb.getValueAt(row, 2).toString())-1;
		if(q==0)
		{
			carrelloTb.setValueAt(q, row, 2);
			cCarrello.decrementaQta(row);
		}

		if (q > 0) 
		{
			carrelloTb.setValueAt(q, row, 2);
			cCarrello.decrementaQta(row);
		}
	}

	private void loadListCd(ArrayList<Cd> listaCd)
	{
		cdListModel.clear();
		idCdList.clear();

		for(Cd c:listaCd)
		{
			cdListModel.addElement(c.getTitolo());
			idCdList.add(c.getId());
		}
	}

	private void loadGeneri(ArrayList<Genere> listaGeneri)
	{
		cbGenere.removeAllItems();
		cbGenere.addItem("");
		for(Genere g:listaGeneri)
		{
			cbGenere.addItem(g.getNome());
		}
	}

	private void loadTitolari(ArrayList<Musicista> listaTitolari)
	{
		cbTitolare.removeAllItems();
		cbTitolare.addItem("");
		for(Musicista t:listaTitolari)
		{
			cbTitolare.addItem(t.getNomeArte());
		}
	}

	private void loadPartecipanti(ArrayList<Musicista> listaMusicisti)
	{
		cbPartecipanti.removeAllItems();
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

	public boolean isLogged()
	{
		return isLogged;
	}

	private void clearComponents()
	{
		txtUsername.setText("");
		txtPassword.setText("");
		txtNome.setText("");
		txtCognome.setText("");
		txtCf.setText("");
		txtIndirizzo.setText("");
		txtTelefono.setText("+39");
		txtCellulare.setText("+39");
		txtUserLogin.setText("");
		txtPassLogin.setText("");
	}
}
