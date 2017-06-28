package viewAreaRiservata;

import utility.*;
import model.*;
import controller.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import java.sql.*;
import java.text.ParseException;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import areaRiservataListener.btnAddNewGenListener;
import areaRiservataListener.o4Listener;
import areaRiservataListener.o5Listener;
import areaRiservataListener.returnNegozioListener;
import areaRiservataListener.btnBackListener;
import areaRiservataListener.o3Listener;
import areaRiservataListener.btnLoginListener;
import areaRiservataListener.btnAddNewCdListener;
import areaRiservataListener.o1Listener;
import areaRiservataListener.btnSaveWarehListener;
import areaRiservataListener.btnShowCollaboratorListListener;
import areaRiservataListener.o2Listener;
import areaRiservataListener.Listener;
import areaRiservataListener.btnShowTrackListListener;
import areaRiservataListener.btnShowStrumentiListListener;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;


public class areaRiservataWnd extends JFrame {
	Listener ascoltatore = new Listener();

	JFrame negozio;
	private static CardLayout clPanel=new CardLayout();

	//Panel container
	private JPanel panelContainer=new JPanel();

	//Componenti di rilievo

	
	//Pannello inserimento cd
	private JTextField txtTitle;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JTextField txtPrice;
	private JTextField txtAmo;
	private JComboBox<String> cbGen;
	private JComboBox<String> cbMus;
	private JTextArea txtDesc;

	private JList listTrackList;					
	private DefaultListModel<String> listModel;		//lista dei brani

	private JList listPartecipantList;				
	private DefaultListModel<String> listModel2; 	//lista dei musicisti partecipanti

	private JList listInstrumentList;
	private DefaultListModel<String> listModel3;	//lista degli strumenti
	
	//Pannello magazzino
	private JTable tbCd;
	private ArrayList<Cd> supListCd;
	
	//Pannello nuovo musicista
	private JComboBox<String> cbGeneri;
	private JTextField txtArtName;

	//Pannello nuovo genere
	private JTextField txtGen;


	//Pannello strumenti per musicista
	private JComboBox cbMusIns;
	private JList listInst;
	private JComboBox cbIns;


	//Variabili usate per il fullscreen
	private int ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 70;
	private int ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width - 100;

	//Utility
	Map<String,Integer> kGen;
	Map<String,Integer> kMus;

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
		//Creo pannello aggiunta strumenti per musicista
		createOptionAddMusIns();

		//Aggiungo il container che contiene tutti i panel
		getContentPane().add(panelContainer);
		clPanel.show(panelContainer, "login");

	}



	
	
	// *********************************************************************************************
	
	//								SHOW
	
	// *********************************************************************************************

	
	
	public void showAddMusPanel()
	{
		this.setTitle("Aggiungi musicista");
		clPanel.show(panelContainer,"optionAddMus");
	}

	public void showOption()
	{
		this.setTitle("Pannello area riservata");
		clPanel.show(panelContainer, "options");
	}
	
	public void showWarehouse()
	{
		try
		{

			// recupero tutti i cd
			ArrayList<Cd> listaCd = new Cd().getAllBase();
			
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

			String[] colNames={"Titolo","Prezzo","Rimasti","Dettagli"};
			tableModel model=new tableModel();
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
			this.setTitle("Magazzino");
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
		this.setTitle("Inserisci un nuovo cd");

		//Recupero lista generi e lista musicisti per le combobox
		ArrayList<Genere> listaGeneri = new Genere().getAll();
		ArrayList<Musicista> listaMusicisti = new Musicista().getAll();


		//Se l'utente aveva scritto prima, pulisco
		clearComponents();

		kMus=new HashMap<String,Integer>();
		kGen=new HashMap<String,Integer>();

		//Rimuovo gli elementi che eventualmente ci sono
		cbMus.removeAll();
		cbGen.removeAll();

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

		clPanel.show(panelContainer, "insert");
	}

	public void showMain()
	{
		negozio.setVisible(true);
		this.setVisible(false);
	}

	public void showAddMusIns()
	{
		this.setTitle("Modifica strumenti per musicista");
		
		
		
		clPanel.show(panelContainer, "optionAddMusIns");
	}



	
	// *********************************************************************************************
	
	//								METODI PER CREAZIONE
	
	// *********************************************************************************************
	
	

	private void createOptionAddGenPanel()
	{
		JPanel option4Panel = new JPanel();
		panelContainer.add(option4Panel, "optionAddGen");
		option4Panel.setLayout(new MigLayout("", "[][60.00][100.00][grow][]", "[50.00][50.00][50.00][50.00]"));

		JLabel lblGen = new JLabel("Nome nuovo genere:");
		option4Panel.add(lblGen, "cell 1 0,alignx right,aligny center");

		txtGen = new JTextField();
		option4Panel.add(txtGen, "flowx,cell 2 0,alignx center,aligny center");
		txtGen.setColumns(10);

		JButton btnAddNewGen = new JButton("Aggiungi Genere");
		btnAddNewGen.addActionListener(new btnAddNewGenListener(this));
		option4Panel.add(btnAddNewGen, "cell 2 1,growx,aligny center");
		option4Panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtGen, btnAddNewGen}));

		JButton btnBack= new JButton("Annulla");
		btnBack.addActionListener(new btnBackListener(this));
		option4Panel.add(btnBack, "cell 2 2,growx,aligny center");
	}

	
	
	
	
	private void createOptionAddMusPanel()
	{
		JPanel option3Panel = new JPanel();
		panelContainer.add(option3Panel, "optionAddMus");
		option3Panel.setLayout(new MigLayout("", "[][60.00][300][300][grow]", "[40][40][40][40][100][40][40]"));

		JLabel lblArtName = new JLabel("Nome arte:");
		option3Panel.add(lblArtName, "cell 1 0,alignx right,aligny center");

		txtArtName = new JTextField();
		option3Panel.add(txtArtName, "cell 2 0,growx,aligny center");
		txtArtName.setColumns(10);
		
		JLabel lblAnnoDiNascita = new JLabel("Anno di nascita:");
		option3Panel.add(lblAnnoDiNascita, "cell 1 1,alignx trailing");
		
		textField = new JTextField();
		textField.setColumns(10);
		option3Panel.add(textField, "cell 2 1,growx,aligny center");

		JLabel lblGen = new JLabel("Genere:");
		option3Panel.add(lblGen, "cell 1 2,alignx right,aligny center");

		//ComboBox dei Generi
		cbGeneri = new JComboBox();
		option3Panel.add(cbGeneri, "cell 2 2,growx,aligny center");
		
		//da qui in poi per mostrare la lista di tutti i musicisti nella ComboBox
		ArrayList<Genere> listaGeneri = new Genere().getAll();					
		//Se l'utente aveva scritto prima,pulisco
		//clearComponents();	
		//TODO sistemare clearComponents
		kGen=new HashMap<String,Integer>();					
		//Rimuovo gli elementi che eventualmente ci sono
		cbGeneri.removeAll();					
		for (int i=0; i<listaGeneri.size(); i++) {						
			Genere genere = listaGeneri.get(i);
			kGen.put(genere.getNome(), genere.getId());
			cbGeneri.addItem(genere.getNome());
		}
		
		JLabel lblStrumenti = new JLabel("Strumenti:");
		option3Panel.add(lblStrumenti, "cell 1 3,alignx right,aligny center");
		
		JButton btnAggiungirimuovi = new JButton("Aggiungi/Rimuovi");
		option3Panel.add(btnAggiungirimuovi, "cell 2 3,growx,aligny center");
		btnAggiungirimuovi.addActionListener(new btnShowStrumentiListListener(this));
						
		JLabel lblListaStrumenti = new JLabel("Lista strumenti:");
		option3Panel.add(lblListaStrumenti, "cell 1 4");
		
		JScrollPane scrollInstrumentList = new JScrollPane();
		option3Panel.add(scrollInstrumentList, "cell 2 4,grow");
		listModel3=new DefaultListModel<String>();	
		listInstrumentList = new JList(listModel3);
		scrollInstrumentList.setViewportView(listInstrumentList);

		JButton btnAddNewMus = new JButton("Aggiungi musicista");
		option3Panel.add(btnAddNewMus, "cell 2 6,growx,aligny center");
		option3Panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtArtName, cbGeneri, btnAddNewMus}));

		JButton btnBack = new JButton("Annulla");
		option3Panel.add(btnBack, "cell 3 6,growx,aligny center");
		btnBack.addActionListener(new btnBackListener(this));

		this.setVisible(true);
	}

	
	
	
	
	private void createInsertPanel() throws ParseException
	{
		JPanel option1Panel = new JPanel();
		panelContainer.add(option1Panel, "insert");

		JPanel newCdPanel = new JPanel();
		newCdPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Dettagli nuovo prodotto", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		newCdPanel.setLayout(new MigLayout("", "[175][600px,grow,fill][]", "[][20px][][grow][20px][75][20px][20px][48.00,grow][grow][20px][50px]"));

		JLabel lblTitle = new JLabel("Titolo Cd:");
		newCdPanel.add(lblTitle, "cell 0 1,alignx right,aligny center");

		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		newCdPanel.add(txtTitle, "cell 1 1,alignx left,aligny center");

		JLabel lblAddTrackList = new JLabel("Gestione brani:");
		newCdPanel.add(lblAddTrackList, "cell 0 2,alignx right,aligny center");

		JButton btnAddTrack = new JButton("Aggiungi/Rimuovi");
		newCdPanel.add(btnAddTrack, "cell 1 2,grow");
		btnAddTrack.addActionListener(new btnShowTrackListListener(this));	//apro nuovo frame

		JLabel lblTrackList = new JLabel("Lista brani:");
		newCdPanel.add(lblTrackList, "cell 0 3,alignx right,aligny center");

		//Pannello di visualizzazione brani
		JScrollPane scrollTrackList = new JScrollPane();
		newCdPanel.add(scrollTrackList, "cell 1 3,grow");
		listModel=new DefaultListModel<String>();
		listTrackList = new JList(listModel);
		scrollTrackList.setViewportView(listTrackList);

		JLabel lblPrice = new JLabel("Prezzo:");
		newCdPanel.add(lblPrice, "cell 0 4,alignx right,aligny center");

		txtPrice = new JTextField();
		newCdPanel.add(txtPrice, "cell 1 4,alignx center,aligny center");
		txtPrice.setColumns(10);

		JLabel lblDesc = new JLabel("Descrizione:");
		newCdPanel.add(lblDesc, "cell 0 5,alignx right,aligny center");

		JScrollPane scrollDesc = new JScrollPane();
		newCdPanel.add(scrollDesc, "cell 1 5,grow");

		txtDesc = new JTextArea();
		scrollDesc.setViewportView(txtDesc);

		JLabel lblGen = new JLabel("Genere:");
		newCdPanel.add(lblGen, "cell 0 6,alignx right,aligny center");

		cbGen = new JComboBox();
		newCdPanel.add(cbGen, "cell 1 6,alignx center,aligny center");

		JLabel lblMus = new JLabel("Capo band:");
		newCdPanel.add(lblMus, "cell 0 7,alignx right,aligny center");

		cbMus = new JComboBox();
		newCdPanel.add(cbMus, "flowx,cell 1 7,growx,aligny center");

		JLabel lblCollaboratore = new JLabel("Gestione musicisti:");
		newCdPanel.add(lblCollaboratore, "cell 0 8,alignx right,aligny center");

		JButton btnAggiungiCollaboratore = new JButton("Aggiungi/Rimuovi");
		newCdPanel.add(btnAggiungiCollaboratore, "cell 1 8");
		btnAggiungiCollaboratore.addActionListener(new btnShowCollaboratorListListener(this)); 	//apro nuovo frame

		JLabel lblListaMusicisti = new JLabel("Lista musicisti:");
		newCdPanel.add(lblListaMusicisti, "cell 0 9,alignx right,aligny center");

		//pannello visualizzazione musicisti partecipanti
		JScrollPane scrollPartecipantList = new JScrollPane();
		newCdPanel.add(scrollPartecipantList, "cell 1 9,grow");
		listModel2=new DefaultListModel<String>();	
		listPartecipantList = new JList(listModel2);
		scrollPartecipantList.setViewportView(listPartecipantList);

		JLabel lblAmo = new JLabel("Quantità:");
		newCdPanel.add(lblAmo, "cell 0 10,alignx right,aligny center");

		txtAmo = new JTextField();
		newCdPanel.add(txtAmo, "cell 1 10,alignx center,aligny center");

		JButton btnAddNewCd = new JButton("Inserisci prodotto");
		btnAddNewCd.addActionListener(new btnAddNewCdListener(this));
		btnAddNewCd.addKeyListener(new btnAddNewCdListener(this));
		newCdPanel.add(btnAddNewCd, "flowx,cell 1 11,alignx left,growy");
		option1Panel.setLayout(new MigLayout("", "[1174px]", "[851px]"));
		option1Panel.add(newCdPanel, "cell 0 0,grow");

		JButton btnBack = new JButton("Annulla");
		btnBack.addActionListener(new btnBackListener(this));
		newCdPanel.add(btnBack, "cell 1 11,alignx right,growy");


	}

	private void createWarehousePanel()
	{
		JPanel option2Panel = new JPanel();
		panelContainer.add(option2Panel, "warehouse");
		option2Panel.setLayout(new MigLayout("", "[grow]", "[grow,fill]"));

		JPanel warehPanel = new JPanel();
		warehPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Tabella prodotti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		option2Panel.add(warehPanel, "cell 0 0,grow");
		warehPanel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill][]"));
		JScrollPane headerPanel = new JScrollPane();
		warehPanel.add(headerPanel, "cell 0 0,grow");
		tbCd = new JTable();
		tbCd.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		headerPanel.setViewportView(tbCd);
		tbCd.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));


		JButton btnSaveUpdate = new JButton("Salva Modifiche");
		btnSaveUpdate.addActionListener(new btnSaveWarehListener(this));
		warehPanel.add(btnSaveUpdate, "flowx,cell 0 1,grow");

		JButton btnBack = new JButton("Annulla");
		btnBack.addActionListener(new btnBackListener(this));
		warehPanel.add(btnBack, "cell 0 1,grow");
	}

	private void createLoginPanel()
	{
		JPanel loginPanel=new JPanel();
		loginPanel.setLayout(new MigLayout("", "[grow]", "[20px][20px][20px][20px][20px][20px][100px][50px,grow]"));

		JLabel lblUser = new JLabel("Username:");
		loginPanel.add(lblUser, "flowx,cell 0 3,alignx center,aligny center");

		txtUser = new JTextField("zil");
		loginPanel.add(txtUser, "cell 0 3,alignx center,aligny center");
		txtUser.setColumns(10);

		JLabel lblPass = new JLabel("Password:");
		loginPanel.add(lblPass, "flowx,cell 0 5,alignx center,aligny center");

		txtPass = new JPasswordField("nonlatrovi");
		txtPass.setColumns(10);
		loginPanel.add(txtPass, "cell 0 5,alignx center,growy");

		JButton btnLogin = new JButton("Login");
		loginPanel.add(btnLogin, "flowx,cell 0 6,alignx center,aligny center");

		JButton btnBack = new JButton("Indietro");
		loginPanel.add(btnBack, "flowx,cell 0 6,alignx center,aligny center");

		panelContainer.add(loginPanel, "login");

		//Aggiungo gli eventi
		btnLogin.addActionListener(new btnLoginListener(this));
		btnLogin.addKeyListener(new btnLoginListener(this));
		btnBack.addActionListener(new returnNegozioListener(negozio,this));
		txtPass.addActionListener(new btnLoginListener(this));

	}

	private void createOptionPanel()
	{
		JPanel optionPanel=new JPanel();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Opzioni", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		buttonPanel.setToolTipText("");

		JButton btnO1 = new JButton("Inserisci un nuovo Cd/Dvd");
		JButton btnO2 = new JButton("Visualizza magazzino");
		JButton btnO3 = new JButton("Aggiungi musicista");
		JButton btnO4 = new JButton("Aggiungi genere");

		btnO1.addActionListener(new o1Listener(this));
		btnO2.addActionListener(new o2Listener(this));
		optionPanel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));

		optionPanel.add(buttonPanel, "cell 0 0,alignx left,aligny top");
		buttonPanel.setLayout(new MigLayout("", "[grow][300.00,fill][grow]", "[pref!][][][][]"));
		buttonPanel.add(btnO1, "cell 1 0,alignx center,aligny center");
		buttonPanel.add(btnO2, "cell 1 1,alignx center,aligny center");


		buttonPanel.add(btnO3, "cell 1 2,alignx center,aligny center");
		btnO3.addActionListener(new o3Listener(this));


		btnO4.addActionListener(new o4Listener(this));
		buttonPanel.add(btnO4, "cell 1 3,alignx center,aligny center");

		JButton btnO5 = new JButton("Modifica strumenti per musicista");
		btnO5.addActionListener(new o5Listener(this));
		buttonPanel.add(btnO5, "cell 1 4,alignx center,aligny center");

		panelContainer.add(optionPanel, "options");

	}

	private void createOptionAddMusIns()
	{
		JPanel option5Panel = new JPanel();
		panelContainer.add(option5Panel, "optionAddMusIns");
		option5Panel.setLayout(new MigLayout("", "[grow]", "[][grow][][][][]"));

		JPanel musPanel = new JPanel();
		musPanel.setBorder(new TitledBorder(null, "Musicista", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		option5Panel.add(musPanel, "cell 0 0,growx,aligny center");
		musPanel.setLayout(new MigLayout("", "[grow]", "[]"));

		cbMusIns = new JComboBox();
		musPanel.add(cbMusIns, "cell 0 0,alignx center,aligny center");

		JPanel insMusPanel = new JPanel();
		insMusPanel.setBorder(new TitledBorder(null, "Strumenti suonati", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		option5Panel.add(insMusPanel, "cell 0 1,grow");
		insMusPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));

		JScrollPane scrollMusInsList = new JScrollPane();
		insMusPanel.add(scrollMusInsList, "flowx,cell 0 0,alignx center,growy");

		listInst = new JList();
		scrollMusInsList.setViewportView(listInst);

		JButton btnRemoveMusIns = new JButton("Rimuovi strumento");
		option5Panel.add(btnRemoveMusIns, "cell 0 2,alignx center");

		JPanel insPanel = new JPanel();
		insPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Strumenti", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		option5Panel.add(insPanel, "cell 0 3,growx,aligny center");
		insPanel.setLayout(new MigLayout("", "[grow]", "[]"));

		cbIns = new JComboBox();
		insPanel.add(cbIns, "flowx,cell 0 0,alignx center,aligny center");

		JButton btnAddMusIns = new JButton("Aggiungi strumento");
		option5Panel.add(btnAddMusIns, "flowx,cell 0 4,alignx center,aligny top");

		JButton btnBack = new JButton("Indietro");
		btnBack.addActionListener(new btnBackListener(this));
		option5Panel.add(btnBack, "cell 0 5,alignx center,aligny center");

	}

	// *********************************************************************************************
	
	//								RECUPERO INFORMAZIONI DA FORM
	
	// *********************************************************************************************

	public String getGenName()
	{
		return txtGen.getText();
	}

	public String getMusName()
	{
		return txtArtName.getText();
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
		return listModel;
	}

	public DefaultListModel<String> getPartecipantList()	//lista dei musicisti partecipanti
	{
		return listModel2;
	}
	
	public DefaultListModel<String> getInstrumentList()
	{
		return listModel3;
	}

	public String getCdPrice()
	{
		return txtPrice.getText();
	}

	public String getCdDesc()
	{
		return txtDesc.getText();
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
		listModel.clear();
		for(String track:trackList)
		{
			listModel.addElement(track);
		}
	}

	//funzione chiamata da "aggiungiPartecipanteWnd"
	public void setPartecipantList(ArrayList<String> partecipantList)	
	{
		listModel2.clear();
		for(String partecipant:partecipantList)
		{
			listModel2.addElement(partecipant);
		}
	}
	
	//funzione chiamata da "aggiungiStrumentiWnd"
	public void setInstrumentList(ArrayList<String> instrumentList)	
	{
		listModel3.clear();
		for(String instrument:instrumentList)
		{
			listModel3.addElement(instrument);
		}
	}

	
	
	// *********************************************************************************************
	
	//								FUNZIONI DI SUPPORTO
	
	// *********************************************************************************************
	

	public boolean validValues()
	{
		if(!dataValidator.checkString(getCdTitle()))
		{
			JOptionPane.showMessageDialog(this,"Inserire titolo Cd!","Attenzione",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(dataValidator.emptyTrackList(getTrackList()))
		{
			JOptionPane.showMessageDialog(this,"Inserire elenco brani!","Attenzione",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(!dataValidator.checkCdPrice(getCdPrice()))
		{
			JOptionPane.showMessageDialog(this, "Prezzo non valido!","Attenzione",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if(!dataValidator.checkInteger(getAmount()))
		{
			JOptionPane.showMessageDialog(this, "Quantità non valida!","Attenzione",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private JTextField textField;


	public void clearComponents()
	{
		txtGen.setText("");
		txtTitle.setText("");
		txtPrice.setText("");
		txtDesc.setText("");
		txtAmo.setText("");
		listModel.clear();
		listModel2.clear();
		listModel3.clear();
		cbGen.removeAllItems();
		cbMus.removeAllItems();
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
				JOptionPane.showMessageDialog(this, "Genere Inserito!","Info!",JOptionPane.INFORMATION_MESSAGE);
				this.clearComponents();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Errore!",JOptionPane.ERROR_MESSAGE);
		}
	}	
	

	public void AddNewCd()
	{

		CdController cCd = new CdController(this);
		try {
			if(cCd.insert()) {
				JOptionPane.showMessageDialog(this, "Cd Inserito!","Info!",JOptionPane.INFORMATION_MESSAGE);
				this.clearComponents();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Errore!",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	// probabilmente questo sarà un controller

}
