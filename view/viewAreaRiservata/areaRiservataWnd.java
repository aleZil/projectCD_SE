package viewAreaRiservata;

import utility.*;
import model.*;
import controller.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import java.text.ParseException;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import areaRiservataListener.btnAddNewGenListener;
import areaRiservataListener.btnAddNewMusListener;
import areaRiservataListener.o4Listener;
import areaRiservataListener.returnNegozioListener;
import areaRiservataListener.btnBackListener;
import areaRiservataListener.o3Listener;
import areaRiservataListener.btnLoginListener;
import areaRiservataListener.btnAddNewCdListener;
import areaRiservataListener.o1Listener;
import areaRiservataListener.btnShowCollaboratorListListener;
import areaRiservataListener.o2Listener;
import areaRiservataListener.btnShowTrackListListener;
import areaRiservataListener.btnShowStrumentiListListener;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import java.awt.Font;


public class areaRiservataWnd extends JFrame {

	JFrame negozio;
	private CardLayout clPanel=new CardLayout();

	//Panel container
	private JPanel panelContainer=new JPanel();

	//Componenti di rilievo


	//Pannello inserimento cd
	private JTextField txtTitle;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JTextField txtPrice;
	private JTextField txtAmo;
	private JTextPane txtDesc;
	private JComboBox<String> cbGen;
	private JComboBox<String> cbMus;

	private JList<String> listTrackList;					
	private DefaultListModel<String> listMTrack;		//lista dei brani

	private JList<String> listPartecipantList;				
	private DefaultListModel<String> listMPartecipanti; 	//lista dei musicisti partecipanti

	private JList<String> listInstrumentList;
	private DefaultListModel<String> listMStrumenti;	//lista degli strumenti

	//Pannello magazzino
	private JTable tbCd;
	private ArrayList<Cd> supListCd;

	//Pannello nuovo musicista
	private JComboBox<String> cbGeneri;
	private JTextField txtArtName;

	//Pannello nuovo genere
	private JTextField txtGen;

	//Pannello strumenti per musicista
	private JComboBox<String> cbMusIns;
	private JList<String> listInst;
	private JComboBox<String> cbIns;
	private JRadioButton rdbtnBand;
	private JRadioButton rdbtnMusicista;

	private JTextField txtYearMus;

	//Variabili usate per il fullscreen
	private int ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 70;
	private int ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width - 100;

	//Utility
	Map<String,Integer> kGen;
	Map<String,Integer> kMus;
	private JButton btnLogin;
	private JButton btnO1;
	private JButton btnO2;
	private JButton btnO3;
	private JButton btnO4;
	private JPanel loginPanel;
	private JPanel option1Panel;
	private JLabel lblUser;
	private JPanel newCdPanel;
	private JLabel lblTitle;
	private JLabel lblPass;
	private JPanel buttonPanel;
	private JPanel optionPanel;
	private JLabel lblAddTrackList;
	private JButton btnAddTrack;
	private JLabel lblTrackList;
	private JScrollPane scrollTrackList;
	private JLabel lblPrice;
	private JLabel lblDesc;
	private JScrollPane scrollDesc;
	private JLabel lblGen_1;
	private JLabel lblMus;
	private JLabel lblCollaboratore;
	private JButton btnAggiungiCollaboratore;
	private JLabel lblListaMusicisti;
	private JScrollPane scrollPartecipantList;
	private JLabel lblAmo;
	private JButton btnAddNewCd;
	private JButton btnBack_1;
	private JPanel option2Panel;
	private JPanel warehPanel;
	private JScrollPane headerPanel;
	private JButton btnBack_2;
	private JPanel option3Panel;
	private JLabel lblArtName;
	private JLabel lblSelezionaTipo;
	private JLabel lblAnnoDiNascita;
	private JLabel lblGen_2;
	private JLabel lblStrumenti;
	private JButton btnAggiungirimuovi;
	private JLabel lblListaStrumenti;
	private JScrollPane scrollInstrumentList;
	private JButton btnAddNewMus;
	private JButton btnBack_3;
	private JPanel option4Panel;
	private JLabel lblGen;
	private JButton btnAddNewGen;
	private JButton btnBack;

	public areaRiservataWnd(JFrame caller) throws ParseException {
		setResizable(false);
		//Tengo il riferimento al main form
		negozio=caller;
		panelContainer.setLayout(clPanel);	//card_layout (contenitore di tutti i panel, ogni panel è un Mig layout)

		this.addWindowListener(new returnNegozioListener(negozio,this));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Dimensioni finestra	
		//this.setSize(ScreenWidth, ScreenHeight);
		setBounds(negozio.getLocation().x,negozio.getLocation().y, 1000, 700);
		//setSize(tk.getScreenSize());
		setUndecorated(false);
		//Creo panel di login
		createLoginPanel();
		//Creo panel delle opzioni
		createOptionPanel();
		//Creo pannello di insert
		createInsertPanel();
		//Creo pannello di gestione magazzino
		createWarehousePanel();
		//Creo pannello di aggiunta musicista
		createOptionAddMusPanel();
		//Creo pannello di aggiunta genere
		createOptionAddGenPanel();


		//Aggiungo il container che contiene tutti i panel
		getContentPane().add(panelContainer);
		clPanel.show(panelContainer, "login");
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtTitle, txtUser, txtPass, btnLogin, btnO1, btnO2, btnO3, btnO4, panelContainer, loginPanel, option1Panel, lblUser, newCdPanel, lblTitle, lblPass, buttonPanel, optionPanel, lblAddTrackList, btnAddTrack, lblTrackList, scrollTrackList, listTrackList, lblPrice, txtPrice, lblDesc, scrollDesc, txtDesc, lblGen_1, cbGen, lblMus, cbMus, lblCollaboratore, btnAggiungiCollaboratore, lblListaMusicisti, scrollPartecipantList, listPartecipantList, lblAmo, txtAmo, btnAddNewCd, btnBack_1, option2Panel, warehPanel, headerPanel, tbCd, btnBack_2, option3Panel, lblArtName, txtArtName, lblSelezionaTipo, lblAnnoDiNascita, txtYearMus, lblGen_2, cbGeneri, lblStrumenti, btnAggiungirimuovi, lblListaStrumenti, scrollInstrumentList, listInstrumentList, btnAddNewMus, btnBack_3, rdbtnMusicista, rdbtnBand, option4Panel, lblGen, txtGen, btnAddNewGen, btnBack}));

	}

	// *********************************************************************************************

	//								SHOW

	// *********************************************************************************************


	public void showAddMusPanel()
	{
		this.setTitle("Aggiungi musicista/band");
		txtArtName.requestFocus();

		//da qui in poi per mostrare la lista di tutti i musicisti nella ComboBox
		ArrayList<Genere> listaGeneri = new Genere().getAll();	

		//Rimuovo gli elementi che eventualmente ci sono
		cbGeneri.removeAllItems();

		for (int i=0; i<listaGeneri.size(); i++) {						
			Genere genere = listaGeneri.get(i);
			cbGeneri.addItem(genere.getNome());
		}

		//pulisco appena viene premuto il bottone riferito all'azione (op3)
		clearAddNewMus();	
		clPanel.show(panelContainer,"optionAddMus");
	}

	public void showOption()
	{
		txtGen.setText("");	//per l'inserimento del nuovo genere
		this.setTitle("Pannello area riservata");
		cbGen.removeAllItems();
		cbMus.removeAllItems();	
		clPanel.show(panelContainer, "options");
	}

	public void showWarehouse()
	{
		try
		{
			this.setTitle("Gestione magazzino");	
			//Variabili supporto 
			String title;
			BigDecimal price;
			int amountCd;

			//Prendo la lista dei cd
			ArrayList<Cd> cdList;
			Cd cdTmp = new Cd();
			cdList=cdTmp.getAll(); 
			supListCd=new ArrayList<Cd>();

			// definizione della tabella
			String[] colNames={"Titolo","Prezzo","Pezzi rimasti","Dettagli"};
			TableModelMagazzino model=new TableModelMagazzino();
			model.setColumnIdentifiers(colNames);
			for (int i= 0; i < cdList.size(); i++) 
			{
				supListCd.add(cdList.get(i));
				title = cdList.get(i).getTitolo();
				price = cdList.get(i).getPrezzo();
				amountCd = cdList.get(i).getPezziMagazzino();
				Object[] rowCd={title,price,amountCd,"Info Cd"};
				model.addRow(rowCd);
			}
			tbCd.setModel(model);
			tbCd.getColumn("Dettagli").setCellRenderer(new ButtonRenderer());
			tbCd.getColumn("Dettagli").setCellEditor(new ButtonEditor(new JCheckBox(),this));
			clPanel.show(panelContainer, "warehouse");
		}
		catch(Exception exception)
		{
			JOptionPane.showMessageDialog(this, exception.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}

	}

	public void showAddNewGen()
	{
		this.setTitle("Aggiungi nuovo genere");
		clPanel.show(panelContainer, "optionAddGen");
	}

	public void showInsertCd()
	{
		//Se l'utente aveva scritto prima, pulisco
		this.setTitle("Inserisci un nuovo cd");
		txtTitle.requestFocus();

		//Recupero lista generi e lista musicisti per le combobox
		ArrayList<Genere> listaGeneri = new Genere().getAll();
		ArrayList<Musicista> listaMusicisti = new Musicista().getAllBand();
		kMus=new HashMap<String,Integer>();
		kGen=new HashMap<String,Integer>();

		//Rimuovo gli elementi che eventualmente ci sono
		cbMus.removeAllItems();
		cbGen.removeAllItems();

		for (int i = 0; i < listaGeneri.size(); i++) {
			Genere genere = listaGeneri.get(i);
			kGen.put(genere.getNome(), genere.getId());
			cbGen.addItem(genere.getNome());
		}

		for (int i = 0; i < listaMusicisti.size(); i++) {
			Musicista musicista = listaMusicisti.get(i);
			kMus.put(musicista.getNomeArte(), musicista.getId());
			cbMus.addItem(musicista.getNomeArte());
		}

		clearComponents();
		clPanel.show(panelContainer, "insert");
	}

	public void showMain()
	{
		negozio.setVisible(true);
		this.setVisible(false);
	}


	// *********************************************************************************************

	//								METODI PER CREAZIONE

	// *********************************************************************************************



	private void createOptionAddGenPanel()
	{
		option4Panel = new JPanel();
		panelContainer.add(option4Panel, "optionAddGen");
		option4Panel.setLayout(new MigLayout("", "[][60.00][100.00][135][]", "[50.00][50.00][50.00][50.00]"));

		lblGen = new JLabel("Nome nuovo genere:");
		option4Panel.add(lblGen, "cell 1 0,alignx right,aligny center");

		txtGen = new JTextField();
		option4Panel.add(txtGen, "flowx,cell 2 0,alignx center,aligny center");
		txtGen.setColumns(10);
		txtGen.requestFocus();	//non funziona

		btnAddNewGen = new JButton("Aggiungi Genere");
		btnAddNewGen.addActionListener(new btnAddNewGenListener(this));
		option4Panel.add(btnAddNewGen, "cell 2 1,growx,aligny center");

		btnBack= new JButton("Annulla");
		btnBack.addActionListener(new btnBackListener(this));
		option4Panel.add(btnBack, "cell 3 1,growx,aligny center");
		option4Panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtGen, btnAddNewGen}));
	}



	private void createOptionAddMusPanel()
	{
		option3Panel = new JPanel();
		panelContainer.add(option3Panel, "optionAddMus");
		option3Panel.setLayout(new MigLayout("", "[][60.00][300][300][grow]", "[40][][40][40][40][100][40][40]"));

		lblArtName = new JLabel("Nome arte:");
		option3Panel.add(lblArtName, "cell 1 0,alignx right,aligny center");

		txtArtName = new JTextField();
		option3Panel.add(txtArtName, "cell 2 0,growx,aligny center");
		txtArtName.setColumns(10);

		lblSelezionaTipo = new JLabel("Seleziona tipo:");
		option3Panel.add(lblSelezionaTipo, "cell 1 1,alignx right,aligny center");

		lblAnnoDiNascita = new JLabel("Anno di nascita:");
		option3Panel.add(lblAnnoDiNascita, "cell 1 2,alignx trailing");

		txtYearMus = new JTextField();
		txtYearMus.setColumns(10);
		option3Panel.add(txtYearMus, "cell 2 2,growx,aligny center");

		lblGen_2 = new JLabel("Genere:");
		option3Panel.add(lblGen_2, "cell 1 3,alignx right,aligny center");

		//ComboBox dei Generi
		cbGeneri = new JComboBox();
		option3Panel.add(cbGeneri, "cell 2 3,growx,aligny center");

		lblStrumenti = new JLabel("Strumenti suonati:");
		option3Panel.add(lblStrumenti, "cell 1 4,alignx right,aligny center");

		btnAggiungirimuovi = new JButton("Aggiungi/Rimuovi");
		option3Panel.add(btnAggiungirimuovi, "cell 2 4,growx,aligny center");
		btnAggiungirimuovi.addActionListener(new btnShowStrumentiListListener(this));

		lblListaStrumenti = new JLabel("Lista strumenti:");
		option3Panel.add(lblListaStrumenti, "cell 1 5");

		scrollInstrumentList = new JScrollPane();
		option3Panel.add(scrollInstrumentList, "cell 2 5,grow");
		listMStrumenti=new DefaultListModel<String>();	
		listInstrumentList = new JList(listMStrumenti);
		scrollInstrumentList.setViewportView(listInstrumentList);

		btnAddNewMus = new JButton("Aggiungi musicista/band");
		option3Panel.add(btnAddNewMus, "cell 2 7,growx,aligny center");
		btnAddNewMus.addActionListener(new btnAddNewMusListener(this));

		btnBack_3 = new JButton("Annulla");
		option3Panel.add(btnBack_3, "cell 3 7,growx,aligny center");
		btnBack_3.addActionListener(new btnBackListener(this));

		rdbtnMusicista = new JRadioButton("Musicista");
		option3Panel.add(rdbtnMusicista, "cell 2 1,alignx left,aligny center");
		rdbtnMusicista.setSelected(true);

		rdbtnBand = new JRadioButton("Band");
		option3Panel.add(rdbtnBand, "flowx,cell 2 1,alignx right,aligny center");

		ButtonGroup groupRadioButtons = new ButtonGroup();
		groupRadioButtons.add(rdbtnMusicista);
		groupRadioButtons.add(rdbtnBand);
		option3Panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtArtName, cbGeneri, btnAddNewMus}));

		this.setVisible(true);
	}

	private void createInsertPanel() throws ParseException
	{
		option1Panel = new JPanel();
		panelContainer.add(option1Panel, "insert");

		newCdPanel = new JPanel();
		newCdPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Dettagli nuovo prodotto", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		newCdPanel.setLayout(new MigLayout("", "[175][600px,grow,fill][]", "[grow][grow][grow][grow][65][grow][grow][grow][grow][grow][grow]"));

		lblTitle = new JLabel("Titolo Cd:");
		newCdPanel.add(lblTitle, "cell 0 0,alignx right,aligny center");

		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		newCdPanel.add(txtTitle, "cell 1 0,alignx center,growy");

		lblAddTrackList = new JLabel("Gestione brani:");
		newCdPanel.add(lblAddTrackList, "cell 0 1,alignx right,aligny center");

		btnAddTrack = new JButton("Aggiungi/Rimuovi");
		newCdPanel.add(btnAddTrack, "cell 1 1,grow");
		btnAddTrack.addActionListener(new btnShowTrackListListener(this));	//apro nuovo frame

		lblTrackList = new JLabel("Lista brani:");
		newCdPanel.add(lblTrackList, "cell 0 2,alignx right,aligny center");

		//Pannello di visualizzazione brani
		scrollTrackList = new JScrollPane();
		newCdPanel.add(scrollTrackList, "cell 1 2,grow");
		listMTrack=new DefaultListModel<String>();
		listTrackList = new JList(listMTrack);
		scrollTrackList.setViewportView(listTrackList);

		lblPrice = new JLabel("Prezzo:");
		newCdPanel.add(lblPrice, "cell 0 3,alignx right,aligny center");

		txtPrice = new JTextField();
		newCdPanel.add(txtPrice, "cell 1 3,alignx center,aligny center");
		txtPrice.setColumns(10);

		lblDesc = new JLabel("Descrizione:");
		newCdPanel.add(lblDesc, "cell 0 4,alignx right,aligny center");

		scrollDesc = new JScrollPane();
		newCdPanel.add(scrollDesc, "cell 1 4,grow");

		txtDesc = new JTextPane();
		scrollDesc.setViewportView(txtDesc);

		//Custom event che ignora il tab
		txtDesc.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {				
			}

			@Override
			public void keyReleased(KeyEvent e) {				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB)
				{
					e.consume();
					KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
				}

			}
		});

		lblGen_1 = new JLabel("Genere:");
		newCdPanel.add(lblGen_1, "cell 0 5,alignx right,aligny center");

		cbGen = new JComboBox();
		newCdPanel.add(cbGen, "cell 1 5,alignx center,aligny center");

		lblMus = new JLabel("Musicista/band titolare:");
		newCdPanel.add(lblMus, "cell 0 6,alignx right,aligny center");

		cbMus = new JComboBox();
		newCdPanel.add(cbMus, "flowx,cell 1 6,growx,aligny center");

		lblCollaboratore = new JLabel("Gestione musicisti:");
		newCdPanel.add(lblCollaboratore, "cell 0 7,alignx right,aligny center");

		btnAggiungiCollaboratore = new JButton("Aggiungi/Rimuovi");
		newCdPanel.add(btnAggiungiCollaboratore, "cell 1 7");
		btnAggiungiCollaboratore.addActionListener(new btnShowCollaboratorListListener(this)); 	//apro nuovo frame

		lblListaMusicisti = new JLabel("Lista musicisti:");
		newCdPanel.add(lblListaMusicisti, "cell 0 8,alignx right,aligny center");

		//pannello visualizzazione musicisti partecipanti
		scrollPartecipantList = new JScrollPane();
		newCdPanel.add(scrollPartecipantList, "cell 1 8,grow");
		listMPartecipanti=new DefaultListModel<String>();	
		listPartecipantList = new JList(listMPartecipanti);
		scrollPartecipantList.setViewportView(listPartecipantList);

		lblAmo = new JLabel("Quantità:");
		newCdPanel.add(lblAmo, "cell 0 9,alignx right,aligny center");

		txtAmo = new JTextField();
		newCdPanel.add(txtAmo, "cell 1 9,alignx center,aligny center");

		btnAddNewCd = new JButton("Inserisci prodotto");
		btnAddNewCd.addActionListener(new btnAddNewCdListener(this));
		btnAddNewCd.addKeyListener(new btnAddNewCdListener(this));
		newCdPanel.add(btnAddNewCd, "flowx,cell 1 10,alignx left,growy");

		option1Panel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));
		option1Panel.add(newCdPanel, "cell 0 0,grow");

		btnBack_1 = new JButton("Annulla");
		btnBack_1.addActionListener(new btnBackListener(this));
		newCdPanel.add(btnBack_1, "cell 1 10,alignx right,growy");
		option1Panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtTitle, btnAddTrack, txtPrice, txtDesc, cbGen, cbMus, btnAggiungiCollaboratore, txtAmo, btnAddNewCd}));
	}

	private void createWarehousePanel()
	{
		option2Panel = new JPanel();
		panelContainer.add(option2Panel, "warehouse");
		option2Panel.setLayout(new MigLayout("", "[grow]", "[grow,fill]"));

		warehPanel = new JPanel();
		warehPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Tabella prodotti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		option2Panel.add(warehPanel, "cell 0 0,grow");
		warehPanel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill][]"));
		headerPanel = new JScrollPane();
		warehPanel.add(headerPanel, "cell 0 0,grow");
		tbCd = new JTable();
		tbCd.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		headerPanel.setViewportView(tbCd);
		tbCd.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnBack_2 = new JButton("Torna a lista opzioni");
		btnBack_2.addActionListener(new btnBackListener(this));
		warehPanel.add(btnBack_2, "cell 0 1,alignx center,aligny center");
		warehPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnBack_2}));
	}

	
	private void createLoginPanel()
	{
		loginPanel=new JPanel();
		loginPanel.setLayout(new MigLayout("", "[300px][grow][300px]", "[grow][][grow][][grow][]"));

		lblUser = new JLabel("Username");
		lblUser.setFont(new Font("Dialog", Font.BOLD, 15));
		loginPanel.add(lblUser, "cell 1 0,alignx center,aligny bottom");

		txtUser = new JTextField("zil");
		txtUser.setFont(new Font("Dialog", Font.PLAIN, 14));
		loginPanel.add(txtUser, "cell 1 1,growx,aligny center");
		txtUser.setColumns(10);

		lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Dialog", Font.BOLD, 15));
		loginPanel.add(lblPass, "cell 1 2,alignx center,aligny bottom");

		txtPass = new JPasswordField("nonlatrovi");
		txtPass.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtPass.setColumns(10);
		loginPanel.add(txtPass, "cell 1 3,grow");

		panelContainer.add(loginPanel, "login");
		
				btnLogin = new JButton("Login");
				btnLogin.setFont(new Font("Dialog", Font.BOLD, 18));
				loginPanel.add(btnLogin, "flowx,cell 1 5,growx,aligny top");
		loginPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUser, txtPass, btnLogin}));

		//Aggiungo gli eventi
		btnLogin.addActionListener(new btnLoginListener(this));
		btnLogin.addKeyListener(new btnLoginListener(this));
		txtPass.addActionListener(new btnLoginListener(this));

	}

	private void createOptionPanel()
	{
		optionPanel=new JPanel();
		buttonPanel = new JPanel();
		buttonPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Opzioni", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		buttonPanel.setToolTipText("");

		btnO1 = new JButton("Inserisci nuovo Cd/Dvd");
		btnO1.setFont(new Font("Dialog", Font.BOLD, 18));
		btnO2 = new JButton("Visualizza magazzino");
		btnO2.setFont(new Font("Dialog", Font.BOLD, 18));
		btnO3 = new JButton("Aggiungi band/musicista");
		btnO3.setFont(new Font("Dialog", Font.BOLD, 18));
		btnO4 = new JButton("Aggiungi genere");
		btnO4.setFont(new Font("Dialog", Font.BOLD, 18));

		btnO1.addActionListener(new o1Listener(this));
		btnO2.addActionListener(new o2Listener(this));
		optionPanel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));

		optionPanel.add(buttonPanel, "cell 0 0,alignx left,aligny top");
		buttonPanel.setLayout(new MigLayout("", "[300px][grow,fill][300px]", "[grow][grow][grow][grow]"));
		buttonPanel.add(btnO1, "cell 1 0,alignx center,aligny center");
		buttonPanel.add(btnO2, "cell 1 1,alignx center,aligny center");


		buttonPanel.add(btnO3, "cell 1 2,alignx center,aligny center");
		btnO3.addActionListener(new o3Listener(this));

		btnO4.addActionListener(new o4Listener(this));
		buttonPanel.add(btnO4, "cell 1 3,alignx center,aligny center");

		panelContainer.add(optionPanel, "options");
		optionPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnO1, btnO2, btnO3, btnO4}));

	}


	// *********************************************************************************************

	//								RECUPERO INFORMAZIONI DA FORM

	// *********************************************************************************************

	public String getGenFromMus()
	{
		return cbGeneri.getSelectedItem().toString();
	}

	public String getGenName()
	{
		return txtGen.getText();
	}

	public String getMusName()
	{	
		return txtArtName.getText();
	}

	public String getYearMus()
	{

		return txtYearMus.getText();
	}

	public String getUsername()
	{
		return txtUser.getText();
	}

	public String getPassword()
	{
		return String.valueOf(txtPass.getPassword());
	}

	public String getCdTitle()
	{
		return txtTitle.getText();
	}

	public DefaultListModel<String> getTrackList()	//lista dei brani
	{
		return listMTrack;
	}

	public DefaultListModel<String> getPartecipantList()	//lista dei musicisti partecipanti
	{
		return listMPartecipanti;
	}

	public DefaultListModel<String> getInstrumentList()		//lista degli strumenti
	{
		return listMStrumenti;
	}

	public String getCdPrice()
	{
		return txtPrice.getText();
	}

	public String getCdDesc()
	{
		return txtDesc.getText();
	}

	public Boolean getIsBand()
	{
		if(rdbtnBand.isSelected()){
			return true;
		}else{
			return false;
		}		
	}

	public int getMusicianId()
	{
		return kMus.get(cbMus.getSelectedItem());
	}

	public int getGenderId()
	{
		return kGen.get(cbGen.getSelectedItem());
	}

	public String getAmount()
	{
		return txtAmo.getText();
	}

	public int getSelectedCdId(int index)
	{
		return supListCd.get(index).getId();
	}

	// *********************************************************************************************

	//								SETTAGGIO DATI FORM

	// *********************************************************************************************


	//funzione chiamata da "aggiungiBranoWnd" (setta la tracklist)
	public void setTrackList(ArrayList<String> trackList)
	{
		listMTrack.clear();
		for(String track:trackList)
		{
			listMTrack.addElement(track);
		}
	}

	//funzione chiamata da "aggiungiPartecipanteWnd"
	public void setPartecipantList(ArrayList<String> partecipantList)	
	{
		listMPartecipanti.clear();
		for(String partecipant:partecipantList)
		{
			listMPartecipanti.addElement(partecipant);
		}
	}

	//funzione chiamata da "aggiungiStrumentiWnd"
	public void setInstrumentList(ArrayList<String> instrumentList)	
	{
		listMStrumenti.clear();
		for(String instrument:instrumentList)
		{
			listMStrumenti.addElement(instrument);
		}
	}



	// *********************************************************************************************

	//								FUNZIONI DI SUPPORTO

	// *********************************************************************************************

	public void clearComponents()
	{
		txtGen.setText("");
		txtTitle.setText("");
		txtPrice.setText("");
		txtDesc.setText("");
		txtAmo.setText("");
		listMTrack.clear();
		listMPartecipanti.clear();
		cbGen.setSelectedIndex(0);
		cbMus.setSelectedIndex(0);
	}

	public void clearAddNewMus(){
		txtArtName.setText("");
		txtYearMus.setText("");
		listMStrumenti.clear();
		cbGeneri.setSelectedIndex(0);
		rdbtnMusicista.setSelected(true);
	}


	// *********************************************************************************************

	//								INTERAZIONE CON MODEL

	// *********************************************************************************************

	//Aggiunge nuovo genere
	public void addNewGen()
	{
		GenereController cGenere = new GenereController(this);

		try {
			if(cGenere.insert()) {
				JOptionPane.showMessageDialog(this, "Genere inserito correttamente","Info!",JOptionPane.INFORMATION_MESSAGE);
				txtGen.setText("");	//resetto il JLabel del genere
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Errore!",JOptionPane.ERROR_MESSAGE);
		}
	}	

	//Aggiunge nuovo musicista
	public void addNewMus()
	{
		MusicistaController cMusicista = new MusicistaController(this);

		try {
			if(cMusicista.insert()) {
				JOptionPane.showMessageDialog(this, "Musicista inserito correttamente","Info",JOptionPane.INFORMATION_MESSAGE);
				this.clearAddNewMus();
			}else{
				JOptionPane.showMessageDialog(this, "Errore durante inserimento","Errore!",JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Errore!",JOptionPane.ERROR_MESSAGE);
		}
	}	


	public void addNewCd()
	{
		CdController cCd = new CdController(this);
		try {
			if(cCd.insert()) {
				JOptionPane.showMessageDialog(this, "Cd inserito correttamente","Info!",JOptionPane.INFORMATION_MESSAGE);
				this.clearComponents();
			}else{
				JOptionPane.showMessageDialog(this, "Errore durante inserimento (lista brani vuota)","Errore!",JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Errore!",JOptionPane.ERROR_MESSAGE);
		}
	}
}
