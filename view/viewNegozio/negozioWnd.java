package viewNegozio;
import java.awt.EventQueue;
import java.math.BigDecimal;
import java.text.ParseException;
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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import net.miginfocom.swing.MigLayout;
import sun.java2d.loops.MaskBlit;
import javax.swing.border.BevelBorder;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPasswordField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import controller.CdController;
import controller.ClienteController;
import controller.GenereController;
import jdk.nashorn.internal.runtime.regexp.joni.ast.QuantifierNode;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import utility.*;
import java.awt.GridLayout;
import java.net.*;
import java.io.*;

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
	private Carrello carrello;

	//Pagamento
	JRadioButton rbBonifico;
	JRadioButton rbCarta;
	JRadioButton rbPayPal;
	JRadioButton rbCorriere;
	JRadioButton rbPosta;

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

		createCarrelloPanel();

		createPagamentoPanel();
	}

	void createHomePanel()
	{
		// Carrello model
		//carrello = new Carrello();

		idCdList=new ArrayList<Integer>();
		homePanel = new JPanel();
		panelContainer.add(homePanel, "home");
		homePanel.setLayout(new MigLayout("", "[300][350][]", "[][60][grow][grow]"));

		btnLogin = new JButton("Accedi");
		btnLogin.addActionListener(new btnShowLogin(this));

		JLabel lblFiltriDisponibili = new JLabel("Filtri disponibili");
		homePanel.add(lblFiltriDisponibili, "cell 0 1,alignx center,aligny bottom");

		JLabel lblcdList = new JLabel("Titoli disponibili");
		homePanel.add(lblcdList, "cell 1 1,alignx center,aligny bottom");
		homePanel.add(btnLogin, "flowx,cell 2 0,alignx right,aligny top");

		btnRegistrazione = new JButton("Registrati");
		btnRegistrazione.addActionListener(new btnShowRegistrazione(this));
		homePanel.add(btnRegistrazione, "cell 2 0,alignx right,aligny top");

		JButton btnAreaRiservata = new JButton("Area Riservata");
		btnAreaRiservata.addActionListener(new btnShowAreaRiservata(this));
		homePanel.add(btnAreaRiservata, "cell 2 0,alignx right,aligny top");

		cdListModel=new DefaultListModel<String>();

		JButton btnViewDetail = new JButton("Vedi dettagli prodotto");
		btnViewDetail.addActionListener(new btnShowDettagliCd(this));

		JPanel filterPanel = new JPanel();
		filterPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		homePanel.add(filterPanel, "cell 0 2,grow");
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
		cdList = new JList<String>(cdListModel);
		homePanel.add(cdList, "flowx,cell 1 2");
		homePanel.add(btnViewDetail, "cell 1 3,growx,aligny top");

		JScrollPane scrollPaneList = new JScrollPane();
		homePanel.add(scrollPaneList, "cell 1 2,grow");
	}

	void createLoginPanel()
	{
		JPanel loginPanel = new JPanel();
		panelContainer.add(loginPanel, "login");
		loginPanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[200][50][50][50]"));

		JLabel lblUserLogin = new JLabel("Username:");
		loginPanel.add(lblUserLogin, "cell 0 1,alignx trailing,aligny center");

		txtUserLogin = new JTextField("aleZil");
		loginPanel.add(txtUserLogin, "cell 1 1,growx,aligny center");
		txtUserLogin.setColumns(10);

		JLabel lblPassLogin = new JLabel("Password:");
		loginPanel.add(lblPassLogin, "cell 0 2,alignx right,aligny center");

		txtPassLogin = new JPasswordField("pwdzil");
		loginPanel.add(txtPassLogin, "cell 1 2,growx");

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new btnLoginCliente(this));
		btnLogin.addKeyListener(new btnLoginCliente(this));
		loginPanel.add(btnLogin, "flowx,cell 1 3,growx,aligny center");

		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.addActionListener(new btnShowHome(this));
		loginPanel.add(btnAnnulla, "cell 1 3,growx,aligny center");
		loginPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUserLogin, txtPassLogin, btnLogin}));

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
		elderScroll.setViewportView(carrelloTb);

		JButton btnCompra = new JButton("Vai a pagamento");
		btnCompra.addActionListener(new btnShowPagamento(this));
		panel.add(btnCompra, "flowx,cell 0 1,grow");

		JButton btnNegozio = new JButton("Torna a negozio");
		btnNegozio.addActionListener(new btnShowHome(this));
		panel.add(btnNegozio, "cell 0 1,growy");

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

		txtTelefono = new JTextField("+39");
		txtTelefono.setText("+39");
		txtTelefono.setColumns(10);
		registrazionePanel.add(txtTelefono, "cell 1 6,growx,aligny center");

		JLabel lblCellulare = new JLabel("Cellulare:");
		registrazionePanel.add(lblCellulare, "cell 0 7,alignx right,aligny center");

		txtCellulare = new JTextField("+39");
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

	void createPagamentoPanel()
	{
		JPanel pagamentoPanel = new JPanel();
		panelContainer.add(pagamentoPanel, "pagamento");
		pagamentoPanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][]"));

		JPanel pagamentoP = new JPanel();
		pagamentoP.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Metodo di pagamento", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		pagamentoPanel.add(pagamentoP, "flowx,cell 1 0,grow");
		pagamentoP.setLayout(new MigLayout("", "[grow]", "[grow][grow][grow]"));

		ButtonGroup pagamentoGroup=new ButtonGroup();
		rbBonifico = new JRadioButton("Bonifico");
		pagamentoP.add(rbBonifico, "cell 0 0,alignx center");

		rbCarta = new JRadioButton("Carta di credito");
		pagamentoP.add(rbCarta, "cell 0 1,alignx center");

		rbPayPal = new JRadioButton("Pay Pal");
		pagamentoP.add(rbPayPal, "cell 0 2,alignx center");

		pagamentoGroup.add(rbBonifico);
		pagamentoGroup.add(rbCarta);
		pagamentoGroup.add(rbPayPal);


		ButtonGroup consegnaGroup=new ButtonGroup();
		JPanel consegnaPanel = new JPanel();
		consegnaPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Metodo Consegna", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		pagamentoPanel.add(consegnaPanel, "flowx,cell 1 1,grow");
		consegnaPanel.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));

		rbCorriere = new JRadioButton("Corriere");
		consegnaPanel.add(rbCorriere, "cell 0 0,alignx center,growy");

		rbPosta = new JRadioButton("Posta");
		consegnaPanel.add(rbPosta, "cell 0 1,alignx center,growy");

		JButton btnConfermaOrdine = new JButton("Esegui pagamento");
		btnConfermaOrdine.addActionListener(new btnEffettuaOrdine(this));
		pagamentoPanel.add(btnConfermaOrdine, "flowy,cell 1 2,growx");
		consegnaGroup.add(rbCorriere);
		consegnaGroup.add(rbPosta);

		JButton btnBack = new JButton("Indietro");
		pagamentoPanel.add(btnBack, "cell 1 2,grow");
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
		this.setTitle("Homepage");
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
		this.setTitle("Homepage");
		// Carrello Model
		Cliente cliente = new Cliente();
		cliente.getByUsername(username);
		carrello = new Carrello(cliente);

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
		this.setTitle("Carrello");
		String[] colNames={"Titolo","Prezzo","Quantità","Aggiungi","Togli"};

		carrelloModel=new TableModelCarrello();		//crea la tabella
		carrelloModel.setColumnIdentifiers(colNames);

		for(int i = 0; i < carrello.getRighe().size(); i++) {

			RigaCarrello rowCart = carrello.getRighe().get(i);
			Cd cd = rowCart.getCd();
			BigDecimal prezzo = rowCart.getPrezzo();
			int qta = rowCart.getQta();

			Object[] row={cd.getTitolo(), prezzo, qta, "+", "-"};
			carrelloModel.addRow(row);	
		}

		carrelloTb.setModel(carrelloModel);
		carrelloTb.getColumn("Aggiungi").setCellRenderer(new ButtonRenderer());
		carrelloTb.getColumn("Aggiungi").setCellEditor(new ButtonEditor(new JCheckBox(),this));
		carrelloTb.getColumn("Togli").setCellRenderer(new ButtonRenderer());
		carrelloTb.getColumn("Togli").setCellEditor(new ButtonEditor(new JCheckBox(),this));
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
		if(carrello.getRighe().size()>0)
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
			BufferedReader in = new BufferedReader(new InputStreamReader(
					whatismyip.openStream()));
			String ip = in.readLine();
			if(carrello.creaOrdine(pagamento, consegna, ip))
			{
				JOptionPane.showMessageDialog(this, "Ordine effettuato!","Info",JOptionPane.INFORMATION_MESSAGE);
				carrelloTb.removeAll();
				carrello.svuotaCarrello();
				idCdCarrello.clear();
			}
			else
			{
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

	public void aggiungiAlCarrello(String titolo, Integer id)
	{
		if(!idCdCarrello.contains(id))
		{
			Cd cd = new Cd();
			cd.getById(id);
			RigaCarrello row = new RigaCarrello(cd, 1);
			carrello.addRow(row);
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
		carrello.incrementaQta(row);
	}

	public void decrementaTitolo(int row)
	{
		int q=Integer.parseInt(carrelloTb.getValueAt(row, 2).toString())-1;
		if(q==0)
		{
			carrelloTb.setValueAt(q, row, 2);
			idCdCarrello.remove(row);
			carrelloTb.remove(row);
			carrello.rimuoviRiga(row);
		}

		if(q<0)
		{
			return;
		}
		else 
		{
			carrelloTb.setValueAt(q, row, 2);
			carrello.decrementaQta(row);
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
		//TODO scommentare questi commenti del clearComponents
		//txtUsername.setText("");
		//txtPassword.setText("");
		txtNome.setText("");
		txtCognome.setText("");
		txtCf.setText("");
		txtIndirizzo.setText("");
		txtTelefono.setText("");
		txtCellulare.setText("");
		//txtUserLogin.setText("");
		//txtPassLogin.setText("");
	}
}
