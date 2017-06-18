package projectCD_SE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import listeners.area_riservata_btn_insert_cd;
import listeners.area_riservata_login;
import listeners.area_riservata_newcd_goback;
import listeners.area_riservata_newcd_insert;
import listeners.area_riservata_wnd_closer;
import javax.swing.JPasswordField;
import java.sql.*;
import java.text.Format;
import java.text.ParseException;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import com.sun.xml.internal.ws.addressing.model.MissingAddressingHeaderException;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import net.miginfocom.swing.MigLayout;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class area_riservata_wnd extends JFrame {
	
	JFrame main_wnd;
	private static CardLayout area_riservata_layout=new CardLayout();
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
	private JComboBox cb_gen;
	private JTextPane txt_desc;
	private JComboBox cb_musician;
	private JTextPane txt_track_list;
	private JCheckBox chb_leader;
	private JButton btn_insert_product;
	private JButton btn_goback;
	
	//Utility
	Map<String,Integer> kGen;
	Map<String,Integer> kMus;
	private JTable product_table;
	
	public area_riservata_wnd(JFrame caller) throws ParseException {
		setResizable(false);
		setTitle("Login");
		//Tengo il riferimento al main form
		main_wnd=caller;
		panel_container.setLayout(area_riservata_layout);
		this.addWindowListener(new area_riservata_wnd_closer(main_wnd));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(main_wnd.getLocation().x,main_wnd.getLocation().y, 488, 459);
		//Creo panel di login
		createLoginPanel();
		//Creo panel delle opzioni
		createOptionPanel();
		//Creo pannello di insert
		createInsertPanel();
		//Aggiungo il container che contiene tutti i panel
		getContentPane().add(panel_container);
		area_riservata_layout.show(panel_container, "login");
		
		JPanel warehouse_area_riservata_panel = new JPanel();
		panel_container.add(warehouse_area_riservata_panel, "name_13865907253381");
		warehouse_area_riservata_panel.setLayout(new MigLayout("", "[][][][][][][][][grow]", "[][][][][][][grow]"));
		
		JPanel product_grid = new JPanel();
		warehouse_area_riservata_panel.add(product_grid, "cell 0 0 9 7,grow");
		product_grid.setLayout(new MigLayout("", "[1px]", "[1px]"));
		
		product_table = new JTable();
		product_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Titoli Pezzi", "Titolo", "Codice"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		product_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		product_grid.add(product_table, "cell 0 0,alignx left,aligny top");
		
		
		this.setVisible(true);
	}
	
	public void seeOption(String user)
	{
		this.setTitle("Pannello area riservata");
		setBounds(100, 100, 500, 500);
		area_riservata_layout.show(panel_container, "options");

	}
	
	
	public void showInsertCd()
	{
		this.setTitle("Inserisci un nuovo cd");
		//Recupero lista generi e lista musicisti per le combobox
		String queryGenere="SELECT * FROM Genere";
		String queryMusicista="SELECT * FROM Musicista";

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
					.addComponent(product_detal_panel, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_insert_area_riservata_panel.setVerticalGroup(
			gl_insert_area_riservata_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_insert_area_riservata_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(product_detal_panel, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
					.addContainerGap())
		);
		//Mask per input date
		MaskFormatter dataMask=new MaskFormatter("##/##/##");
		
		JLabel lbl_price = new JLabel("Prezzo:");
		product_detal_panel.setLayout(new MigLayout("", "[100px][217px,grow][100px]", "[][19px][19px][19px][88px][19px][22.00,grow][22.00,grow][grow][][59.00]"));
		
		JLabel lbl_codice_cd = new JLabel("Codice Cd:");
		product_detal_panel.add(lbl_codice_cd, "cell 0 0,alignx left,aligny center");
		
		txt_cd_code = new JTextField();
		txt_cd_code.setColumns(10);
		product_detal_panel.add(txt_cd_code, "cell 1 0,growx,aligny top");
		
		btn_goback = new JButton("X");
		btn_goback.addActionListener(new area_riservata_newcd_goback(this));
		product_detal_panel.add(btn_goback, "cell 2 0,alignx center");
		
		JLabel lbl_title_cd = new JLabel("Titolo Cd:");
		product_detal_panel.add(lbl_title_cd, "cell 0 1,alignx left,aligny center");
		
		txt_cd_title = new JTextField();
		txt_cd_title.setColumns(10);
		product_detal_panel.add(txt_cd_title, "cell 1 1,growx,aligny top");
		
		JLabel lbl_track_list = new JLabel("Lista Brani:");
		product_detal_panel.add(lbl_track_list, "cell 0 2,growx,aligny top");
		
		txt_track_list = new JTextPane();
		insert_area_riservata_panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txt_cd_code, txt_cd_title, txt_track_list, txt_price, txt_desc, cb_gen, cb_musician, chb_leader, txt_amount, btn_insert_product}));
		product_detal_panel.add(txt_track_list, "cell 1 2 1 3,grow");
		product_detal_panel.add(lbl_price, "cell 0 5,alignx trailing,aligny center");
		
		txt_price = new JTextField();
		product_detal_panel.add(txt_price, "cell 1 5,growx");
		txt_price.setColumns(10);
		
		JLabel lbl_decr = new JLabel("Descrizione:");
		product_detal_panel.add(lbl_decr, "cell 0 6");
		
		txt_desc = new JTextPane();
		product_detal_panel.add(txt_desc, "cell 1 6,grow");
		
		JLabel lbl_type = new JLabel("Genere:");
		product_detal_panel.add(lbl_type, "cell 0 7,alignx trailing");
		
		cb_gen = new JComboBox();
		product_detal_panel.add(cb_gen, "cell 1 7,growx");
		
		JLabel lbl_musician = new JLabel("Musicista:");
		product_detal_panel.add(lbl_musician, "cell 0 8,alignx trailing");
		
		cb_musician = new JComboBox();
		product_detal_panel.add(cb_musician, "cell 1 8,growx");
		
		chb_leader = new JCheckBox("Capoband");
		product_detal_panel.add(chb_leader, "cell 2 8");
		
		JLabel lbl_quant = new JLabel("Quantità:");
		product_detal_panel.add(lbl_quant, "cell 0 9,alignx trailing");
		
		txt_amount = new JTextField();
		txt_amount.setColumns(10);
		product_detal_panel.add(txt_amount, "cell 1 9,growx");
		
		btn_insert_product = new JButton("Inserisci prodotto");
		btn_insert_product.addActionListener(new area_riservata_newcd_insert(this));
		product_detal_panel.add(btn_insert_product, "cell 1 10,grow");
		insert_area_riservata_panel.setLayout(gl_insert_area_riservata_panel);
	}
	
	private void createLoginPanel()
	{
		panel_container.add(login_area_riservata_panel, "login");
		login_area_riservata_panel.setLayout(new MigLayout("", "[114px][][][][grow]", "[15px][15px][][][][][]"));
		
		JLabel lbl_username = new JLabel("Username");
		login_area_riservata_panel.add(lbl_username, "cell 1 2,alignx trailing,aligny top");
		
		txt_usr = new JTextField();
		login_area_riservata_panel.add(txt_usr, "cell 3 2,alignx left");
		txt_usr.setColumns(10);
		
		JLabel lbl_password = new JLabel("Password");
		login_area_riservata_panel.add(lbl_password, "cell 1 4,alignx trailing,aligny top");
		
		txt_psswd = new JPasswordField();
		login_area_riservata_panel.add(txt_psswd, "cell 3 4,grow");
		
		JButton btn_login = new JButton("Login");
		
		login_area_riservata_panel.add(btn_login, "cell 3 6,growx");

		
		//Aggiungo gli eventi
		btn_login.addActionListener(new area_riservata_login(this));
		txt_psswd.addActionListener(new area_riservata_login(this));
		
	}
	
	private void createOptionPanel()
	{
		panel_container.add(option_area_riservata_panel, "options");
		
		JPanel buttons_container = new JPanel();
		buttons_container.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Opzioni", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		buttons_container.setToolTipText("");
		
		JButton btn_insert_cd = new JButton("Inserisci un nuovo cd");
		
		JButton btn_view_warehouse = new JButton("Visualizza magazzino");
		btn_insert_cd.addActionListener(new area_riservata_btn_insert_cd(this));
		option_area_riservata_panel.setLayout(new MigLayout("", "[462px]", "[410px,grow,fill]"));
		option_area_riservata_panel.add(buttons_container, "cell 0 0,grow");
		buttons_container.setLayout(new MigLayout("", "[211px,grow,fill][grow,fill]", "[25px][25px]"));
		buttons_container.add(btn_insert_cd, "cell 0 0 2 1,growx,aligny top");
		buttons_container.add(btn_view_warehouse, "cell 0 1 2 1,growx,aligny top");
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
		return txt_track_list.getText();
	}
	
	public BigDecimal getCdPrice()
	{
		return new BigDecimal(txt_price.getText());
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
	
	public int getAmount()
	{
		return Integer.parseInt(txt_amount.getText());
	}
	
	public boolean isLeader()
	{
		return chb_leader.isSelected();
	}
	
	public boolean validValues()
	{
		if(getCdCode().isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Inserire codice Cd!","Attenzione",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(getTrackList()=="")
		{
			JOptionPane.showMessageDialog(this,"Inserire elenco brani!","Attenzione",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		try
		{
			getCdPrice();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Prezzo non valido!","Attenzione",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if(getCdPrice().signum()==-1)
		{
			JOptionPane.showMessageDialog(this, "Prezzo negativo?","Attenzione",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if(getAmount()<=0)
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
		txt_track_list.setText("");
	}
}
