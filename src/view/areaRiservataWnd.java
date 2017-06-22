package view;

import utility.*;
import model.*;
import negozioListener.carrello_goMain;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
//import listeners.carrello_intro;
//import listeners.main_wnd_btn_carrello;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.sql.*;
import java.text.ParseException;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.Color;
//<<<<<<< HEAD:src/projectCD_SE/area_riservata_wnd.java
import java.awt.FlowLayout;
import java.awt.Frame;
//=======
//>>>>>>> 03c59dba1181ae0b72d8248dfb48f38f6f0b13a0:src/frame/areaRiservataWnd.java
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JCheckBox;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import areaRiservataListener.area_riservata_goMain;
import areaRiservataListener.area_riservata_goback;
import areaRiservataListener.area_riservata_insert_musician;
import areaRiservataListener.area_riservata_login;
import areaRiservataListener.area_riservata_newcd_insert;
import areaRiservataListener.area_riservata_option_insert_cd;
import areaRiservataListener.area_riservata_save_updates;
import areaRiservataListener.area_riservata_see_warehouse;
import areaRiservataListener.area_riservata_wnd_closer;

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.JTextArea;


public class areaRiservataWnd extends JFrame {
	
	JFrame main_wnd;
	private static CardLayout area_riservata_layout=new CardLayout();	//contenitore di pannelli-layout
	private JPanel panel_container=new JPanel();
	private JPanel login_area_riservata_panel=new JPanel();
	private JPanel option_area_riservata_panel=new JPanel();
	//Componenti rilevanti del pannello insert
	private JTextField txt_cd_code;
	private JTextField txt_cd_title;
	private JTextField txt_usr;
	private JPasswordField txt_psswd;
	private JTextField txt_price;
	private JTextField txt_amount;
	private JComboBox<String> cb_gen;
	private JComboBox<String> cb_musician;
	private JCheckBox chb_leader;
	private JButton btn_insert_product;
	private JButton btn_goback_insert;
	private JButton btn_goback_warehouse;
	private JButton btn_save_updates;
	private JTextArea txt_desc;
	private JTextArea txt_tracklist;
	private JTable tb_product;
	private JButton btn_add_mus;
	private JButton btn_add_cd_mus;
	//Variabili usate per il fullscreen
		
	private int ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 70;
	private int ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width - 100;

	
	//Utility
	Map<String,Integer> kGen;
	Map<String,Integer> kMus;
	Set<Integer> rowEdited;
	
	// Model per il recupero dei dati
	private Cd modelCd = new Cd();
	
	
	public areaRiservataWnd(JFrame caller) throws ParseException {
		
		setResizable(true);
		
		rowEdited=new HashSet<>();
		//Tengo il riferimento al main form
		main_wnd=caller;
		panel_container.setLayout(area_riservata_layout);	//card_layout (contenitore di tutti i panel, ogni panel è un Mig layout)
		
		this.addWindowListener(new area_riservata_wnd_closer(main_wnd));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Dimensioni finestra	
		//this.setSize(ScreenWidth, ScreenHeight);
		
		setBounds(main_wnd.getLocation().x,main_wnd.getLocation().y, 1200, 900);
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);

		Toolkit tk = Toolkit.getDefaultToolkit();
		setLocation(0,0);
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
		//Aggiungo il container che contiene tutti i panel
		getContentPane().add(panel_container);
		area_riservata_layout.show(panel_container, "login");	
		


	}

	public void showOption(String user)
	{
		this.setTitle("Pannello area riservata");
		area_riservata_layout.show(panel_container, "options");

	}
	

	public void showAddMusPanel()
	{
		
		this.setTitle("Aggiungi musicista");
		area_riservata_layout.show(panel_container,"optionAddMus");
	}
	public void SaveUpdates()
	{
		
		if (rowEdited.size() == 0) {
			
			JOptionPane.showMessageDialog(this, "Premi invio dopo la modifica");
			
		} else {
		
			for(int row:rowEdited)
			{
				
				//Faccio l'update sulle righe modificate
				String codice=(String)tb_product.getValueAt(row, 0);
				String titolo=(String)tb_product.getValueAt(row, 1);
				String titoloBrani=(String)tb_product.getValueAt(row, 2);
				BigDecimal prezzo=(BigDecimal)tb_product.getValueAt(row, 3);
				Date dataInserimento=(Date)tb_product.getValueAt(row,4);
				String descrizione=(String)tb_product.getValueAt(row,5);
				
				try
				{
					// TODO dovrebbe chiamare il controller
					Boolean status = modelCd.updateByCodice(codice, titolo, titoloBrani, descrizione, prezzo, dataInserimento);
					
					if(status == true)
						JOptionPane.showMessageDialog(tb_product.getParent(), "Modifica eseguita con successo!","Info",JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(tb_product.getParent(), "Modifica non eseguita!","Errore",JOptionPane.ERROR_MESSAGE);
					
				}
				catch (Exception exception)
				{
					JOptionPane.showMessageDialog(tb_product.getParent(), exception.getMessage());
				}
			}
			
			rowEdited.clear();
			area_riservata_layout.show(panel_container, "warehouse");
		}
	}
	
	
	//Prende il riferimento alla riga modificata
	AbstractAction GetUpdate=new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {
			TableCellListener tcl=(TableCellListener)e.getSource();
			int row=tcl.getRow();
			
			//Se non viene modificato il valore,non si fa l'update
			if(tcl.getOldValue().equals(tcl.getNewValue()))
				return;
			//Se modificata,salvo l'indice con riga da modificare
			rowEdited.add(tcl.getRow());
		}
	};
	private JTextField textField;

	public void showWarehouse()
	{
		
		// String queryCd="SELECT * FROM Cd";
		try
		{
			// Connection con=DriverManager.getConnection("jdbc:postgresql://db-cdproject.czz77hrlmvcn.eu-west-1.rds.amazonaws.com/progetto_cd","hanzo","neversurrender");
			//Connection con = Db.getConnection();
			
			//Statement stm=con.createStatement();
			ResultSet res = modelCd.getAll();

			//Variabili supporto 
			String codeCd;
			String titleCd;
			String trackList;
			BigDecimal priceCd;
			Date insertDate;
			String descCd;
			int soldCd;
			int amountCd;
			int genId;
			int musId;
			//String[] colNames={"Codice","Titolo","Titolo Pezzi","Prezzo","Data I.","Descrizione","Venduti","Rimanenti","Genere Id","Musicista Id"};
			String[] colNames={"Codice","Titolo","Titolo Pezzi","Prezzo","Data I.","Descrizione","Venduti","Rimanenti","Genere Id"};
			
			//DefaultTableModel model=new DefaultTableModel(colNames, 0);
			tableModel model=new tableModel(0, 10);
			model.setColumnIdentifiers(colNames);
			while(res.next())
			{
				codeCd=res.getString("codice");
				titleCd=res.getString("titolo");
				trackList=res.getString("titoloBrani");
				priceCd=res.getBigDecimal("prezzo");
				insertDate=res.getDate("data_inserimento");
				descCd=res.getString("descrizione");
				soldCd=res.getInt("pezzi_venduti");
				amountCd=res.getInt("pezzi_magazzino");
				genId=res.getInt("genere_id");
				//musId=res.getInt("musicista_id");
				model.addRow(new Object[]{codeCd,titleCd,trackList,priceCd,insertDate,descCd,soldCd,amountCd,genId});
			}

			tb_product.setModel(model);
			
			TableCellListener tcl=new TableCellListener(tb_product,GetUpdate);
			
			this.setTitle("Magazzino");
			area_riservata_layout.show(panel_container, "warehouse");
			res.close();
		}
		catch(Exception exception)
		{
			JOptionPane.showMessageDialog(this, exception.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}

	}

	public void showInsertCd()
	{
		this.setTitle("Inserisci un nuovo cd");
		//Recupero lista generi e lista musicisti per le combobox
		String queryGenere="SELECT * FROM Genere";
		String queryMusicista="SELECT * FROM Musicista";
		//Se l'utente aveva scritto prima,pulisco
		clearComponents();

		//Il driver è già stato caricato durante il login

		try
		{
			Connection con=DriverManager.getConnection("jdbc:postgresql://db-cdproject.czz77hrlmvcn.eu-west-1.rds.amazonaws.com/progetto_cd","hanzo","neversurrender");
			Statement stGen=con.createStatement();
			Statement stMus=con.createStatement();

			ResultSet generi=stGen.executeQuery(queryGenere);
			ResultSet musicisti=stMus.executeQuery(queryMusicista);

			//Devo tenere una mappa chiave primaria e stringa 
			kMus=new HashMap<String,Integer>();
			kGen=new HashMap<String,Integer>();

			//Rimuovo gli elementi che eventualmente ci sono
			cb_musician.removeAll();
			cb_gen.removeAll();


			while(generi.next())
			{
				kGen.put(generi.getString("nome"),generi.getInt("id"));
				cb_gen.addItem(generi.getString("nome"));
			}

			while(musicisti.next())
			{
				kMus.put(musicisti.getString("nome_arte"),musicisti.getInt("id"));
				cb_musician.addItem(musicisti.getString("nome_arte"));
			}
			stGen.close();
			stMus.close();
			con.close();
		}
		catch(Exception exception)
		{
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}

		area_riservata_layout.show(panel_container, "insert");
	}

	private void createOptionAddMusPanel()
	{
		JPanel optionAddMus = new JPanel();
		panel_container.add(optionAddMus, "optionAddMus");
		optionAddMus.setLayout(new MigLayout("", "[][grow][]", "[][][][]"));
		
		JLabel lblNomeArte = new JLabel("Nome arte:");
		optionAddMus.add(lblNomeArte, "flowx,cell 1 1,alignx center,aligny center");
		
		textField = new JTextField();
		optionAddMus.add(textField, "cell 1 1,alignx center,aligny center");
		textField.setColumns(10);
		
		JLabel lblGen = new JLabel("Genere:");
		optionAddMus.add(lblGen, "flowx,cell 1 2,alignx center,aligny center");
		
		JComboBox comboBox = new JComboBox();
		optionAddMus.add(comboBox, "cell 1 2,alignx center,aligny center");
		
		JLabel lblInstru = new JLabel("Strumento:");
		optionAddMus.add(lblInstru, "flowx,cell 1 3,alignx center,aligny center");
		
		JComboBox cbInstru = new JComboBox();
		optionAddMus.add(cbInstru, "cell 1 3,alignx center,aligny center");
		this.setVisible(true);
	}
	
	private void createInsertPanel() throws ParseException
	{
		JPanel insert_area_riservata_panel = new JPanel();
		panel_container.add(insert_area_riservata_panel, "insert");

		JPanel product_detal_panel = new JPanel();
		product_detal_panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Dettagli nuovo prodotto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_insert_area_riservata_panel = new GroupLayout(insert_area_riservata_panel);
		gl_insert_area_riservata_panel.setHorizontalGroup(
				gl_insert_area_riservata_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_insert_area_riservata_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(product_detal_panel, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
						.addContainerGap())
				);
		gl_insert_area_riservata_panel.setVerticalGroup(
				gl_insert_area_riservata_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_insert_area_riservata_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(product_detal_panel, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
						.addContainerGap())
				);
		//Mask per input date
		MaskFormatter dataMask=new MaskFormatter("##/##/##");

		JLabel lbl_price = new JLabel("Prezzo:");
		product_detal_panel.setLayout(new MigLayout("", "[][600px,grow,fill][]", "[][20px][grow][20px][grow][20px][20px][20px][60px]"));

		JLabel lbl_codice_cd = new JLabel("Codice Cd:");
		product_detal_panel.add(lbl_codice_cd, "cell 0 0,alignx right,aligny center");

		txt_cd_code = new JTextField();
		txt_cd_code.setColumns(10);
		product_detal_panel.add(txt_cd_code, "cell 1 0,alignx center,aligny center");

		JLabel lbl_title_cd = new JLabel("Titolo Cd:");
		product_detal_panel.add(lbl_title_cd, "cell 0 1,alignx right,aligny center");

		txt_cd_title = new JTextField();
		txt_cd_title.setColumns(10);
		product_detal_panel.add(txt_cd_title, "cell 1 1,alignx left,aligny center");

		JLabel lbl_track_list = new JLabel("Lista Brani:");
		product_detal_panel.add(lbl_track_list, "cell 0 2,alignx right,aligny center");

		JScrollPane scroll_tracklist = new JScrollPane();
		product_detal_panel.add(scroll_tracklist, "cell 1 2,grow");

		txt_tracklist= new JTextArea();
		scroll_tracklist.setViewportView(txt_tracklist);
		product_detal_panel.add(lbl_price, "cell 0 3,alignx right,aligny center");

		txt_price = new JTextField();
		product_detal_panel.add(txt_price, "cell 1 3,alignx center,aligny center");
		txt_price.setColumns(10);

		JLabel lbl_decr = new JLabel("Descrizione:");
		product_detal_panel.add(lbl_decr, "cell 0 4");

		JScrollPane scroll_descr = new JScrollPane();
		product_detal_panel.add(scroll_descr, "cell 1 4,grow");

		txt_desc = new JTextArea();
		scroll_descr.setViewportView(txt_desc);

		JLabel lbl_type = new JLabel("Genere:");
		product_detal_panel.add(lbl_type, "cell 0 5,alignx trailing");

		cb_gen = new JComboBox();
		product_detal_panel.add(cb_gen, "cell 1 5,alignx center,aligny center");

		JLabel lbl_musician = new JLabel("Musicista:");
		product_detal_panel.add(lbl_musician, "cell 0 6,alignx trailing");

		cb_musician = new JComboBox();
		product_detal_panel.add(cb_musician, "flowx,cell 1 6,alignx center,aligny center");

		JLabel lbl_quant = new JLabel("Quantità:");
		product_detal_panel.add(lbl_quant, "cell 0 7,alignx right,aligny center");

		txt_amount = new JTextField();
		txt_amount.setColumns(10);
		product_detal_panel.add(txt_amount, "cell 1 7,alignx center,aligny center");

		btn_insert_product = new JButton("Inserisci prodotto");
		btn_insert_product.addActionListener(new area_riservata_newcd_insert(this));
		btn_insert_product.addKeyListener(new area_riservata_newcd_insert(this));
		product_detal_panel.add(btn_insert_product, "flowx,cell 1 8,alignx left,growy");

		chb_leader = new JCheckBox("Capoband");
		product_detal_panel.add(chb_leader, "cell 1 6,alignx left");
				
						btn_goback_insert = new JButton("Annulla");
						btn_goback_insert.addActionListener(new area_riservata_goback(this));
						product_detal_panel.add(btn_goback_insert, "cell 1 8,alignx right,growy");
		product_detal_panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txt_cd_code, txt_cd_title, txt_tracklist, txt_price, txt_desc, cb_gen, cb_musician, chb_leader, txt_amount, btn_insert_product}));
		insert_area_riservata_panel.setLayout(gl_insert_area_riservata_panel);
		insert_area_riservata_panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txt_cd_code, txt_cd_title, txt_tracklist, txt_price, txt_desc, cb_gen, cb_musician, chb_leader, txt_amount, btn_insert_product}));
	}

	private void createWarehousePanel()
	{
		JPanel warehouse_area_riservata_panel = new JPanel();
		panel_container.add(warehouse_area_riservata_panel, "warehouse");
		warehouse_area_riservata_panel.setLayout(new MigLayout("", "[grow]", "[][grow,fill][]"));
		
		btn_goback_warehouse=new JButton("X");
		btn_goback_warehouse.addActionListener(new area_riservata_goback(this));
		warehouse_area_riservata_panel.add(btn_goback_warehouse, "cell 0 0,alignx right");

		JPanel warehouse_tb_panel = new JPanel();
		warehouse_tb_panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Tabella prodotti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		warehouse_area_riservata_panel.add(warehouse_tb_panel, "cell 0 1 1 2,grow");
		warehouse_tb_panel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill][]"));
		JScrollPane header_panel = new JScrollPane();
		warehouse_tb_panel.add(header_panel, "cell 0 0,grow");
		tb_product = new JTable();
		tb_product.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		header_panel.setViewportView(tb_product);
		tb_product.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		btn_save_updates = new JButton("Salva Modifiche!");
		btn_save_updates.addActionListener(new area_riservata_save_updates(this));
		warehouse_tb_panel.add(btn_save_updates, "cell 0 1,alignx center,aligny center");
	}

	private void createLoginPanel()
	{
		panel_container.add(login_area_riservata_panel, "login");
		login_area_riservata_panel.setLayout(new MigLayout("", "[grow]", "[20px][20px][20px][20px][20px][20px][100px][50px,grow]"));

		JLabel lbl_username = new JLabel("Username:");
		login_area_riservata_panel.add(lbl_username, "flowx,cell 0 3,alignx center,aligny center");

		txt_usr = new JTextField("zil");
		login_area_riservata_panel.add(txt_usr, "cell 0 3,alignx center,aligny center");
		txt_usr.setColumns(10);

		JLabel lbl_passwd = new JLabel("Password:");
		login_area_riservata_panel.add(lbl_passwd, "flowx,cell 0 5,alignx center,aligny center");

		txt_psswd = new JPasswordField("nonlatrovi");
		txt_psswd.setColumns(10);
		login_area_riservata_panel.add(txt_psswd, "cell 0 5,alignx center,growy");

		JButton btn_login = new JButton("Login");
		login_area_riservata_panel.add(btn_login, "cell 0 6,alignx center,aligny center");
		
		JButton btn_indietro = new JButton("Indietro");
		login_area_riservata_panel.add(btn_indietro, "cell 0 6,alignx center,aligny center");

		//Aggiungo gli eventi
		btn_login.addActionListener(new area_riservata_login(this));
		btn_login.addKeyListener(new area_riservata_login(this));
		btn_indietro.addActionListener(new area_riservata_goMain(this));
		txt_psswd.addActionListener(new area_riservata_login(this));

	}

	public void showMain(String user)
	{
		main_wnd.setVisible(true);
		this.setVisible(false);
	}
	
	
	private void createOptionPanel()
	{
		panel_container.add(option_area_riservata_panel, "options");

		JPanel buttons_container = new JPanel();
		buttons_container.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Opzioni", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		buttons_container.setToolTipText("");

		JButton btn_insert_cd = new JButton("Inserisci un nuovo cd");
		JButton btn_view_warehouse = new JButton("Visualizza magazzino");

		btn_insert_cd.addActionListener(new area_riservata_option_insert_cd(this));
		btn_view_warehouse.addActionListener(new area_riservata_see_warehouse(this));
		option_area_riservata_panel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));

		option_area_riservata_panel.add(buttons_container, "cell 0 0,alignx left,aligny top");
		buttons_container.setLayout(new MigLayout("", "[grow,fill]", "[][][][]"));
		buttons_container.add(btn_insert_cd, "cell 0 0,growx,aligny top");
		buttons_container.add(btn_view_warehouse, "cell 0 1,growx,aligny top");
		
		btn_add_mus = new JButton("Aggiungi musicista");
		buttons_container.add(btn_add_mus, "cell 0 2");
		//btn_add_mus.addActionListener(new area_riservata_insert_musician(this));
		
		btn_add_cd_mus = new JButton("Aggiungi partecipazione");
		buttons_container.add(btn_add_cd_mus, "cell 0 3");
	}
	
	//Metodi pubblici
	public String getUsername()
	{
		return txt_usr.getText();
	}

	public String getPassword()
	{
		return String.valueOf(txt_psswd.getPassword());
	}

	public String getCdCode()
	{
		return txt_cd_code.getText();
	}

	public String getCdTitle()
	{
		return txt_cd_title.getText();
	}

	public String getTrackList()
	{
		return txt_tracklist.getText();
	}

	public String getCdPrice()
	{
		return txt_price.getText();
	}

	public String getCdDesc()
	{
		return txt_desc.getText();
	}

	public int getMusicianId()
	{
		return kMus.get(cb_musician.getSelectedItem());
	}

	public int getGenderId()
	{
		return kGen.get(cb_gen.getSelectedItem());
	}

	public String getAmount()
	{
		return txt_amount.getText();
	}

	public boolean isLeader()
	{
		return chb_leader.isSelected();
	}

	public boolean validValues()
	{
		if(!dataValidator.checkCdCode(getCdCode()))
		{
			JOptionPane.showMessageDialog(this,"Inserire codice Cd!","Attenzione",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(!dataValidator.checkTitle(getCdTitle()))
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

	public void clearComponents()
	{
		txt_cd_code.setText("");
		txt_cd_title.setText("");
		txt_price.setText("");
		txt_desc.setText("");
		txt_amount.setText("");
		txt_tracklist.setText("");
		cb_gen.removeAllItems();
		cb_musician.removeAllItems();
	}
}
