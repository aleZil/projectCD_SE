package viewNegozio;

import utility.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.MaskFormatter;

import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import com.sun.xml.internal.ws.addressing.model.MissingAddressingHeaderException;

import areaRiservataListener.btnBackListener;
import areaRiservataListener.o1Listener;
import areaRiservataListener.o2Listener;
import areaRiservataListener.returnNegozioListener;
import jdk.nashorn.internal.scripts.JO;
import negozioListener.carrello_goMain;
import negozioListener.carrello_intro;
import negozioListener.main_wnd_btn_carrello;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.JFormattedTextField;
import javax.swing.AbstractAction;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import net.miginfocom.swing.MigLayout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class carrelloWnd extends JFrame {
	
	JFrame main_wnd;
	private static CardLayout carrello_layout=new CardLayout();
	private JPanel panel_container=new JPanel();
	
	private JPanel prova_panel=new JPanel();
	private JPanel carrello_panel=new JPanel();		


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
	private JButton btn_indietro;
	private JButton btn_save_updates;
	private JTextArea txt_desc;
	private JTextArea txt_tracklist;
	private JTable tb_product;
	
	//Utility
	Map<String,Integer> kGen;
	Map<String,Integer> kMus;
	Set<Integer> rowEdited;
	
	
	public carrelloWnd(JFrame caller) throws ParseException {
		setResizable(false);
		setTitle("Carrello");
		rowEdited=new HashSet<>();
		//Tengo il riferimento al main form
		main_wnd=caller;
		panel_container.setLayout(carrello_layout);
		this.addWindowListener(new returnNegozioListener(main_wnd,this));	//cambiare nome a questo file?
		//premendo la x rossa torno al pannello precedente
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(main_wnd.getLocation().x,main_wnd.getLocation().y, 1000, 700);
		
		//Creo pannello per visualizzare il carrello (andrea)
		createCarrelloPanel();
		createProvaPanel();
		
		//Aggiungo il container che contiene tutti i panel
		getContentPane().add(panel_container);
		carrello_layout.show(panel_container, "carrello");
		this.setVisible(true);

	}

	
//___________
	
	public void showMain()
	{
		
		main_wnd.setVisible(true);
		this.setVisible(false);
	}
	
	
	private void createProvaPanel()
	{
		panel_container.add(prova_panel, "prova");

		JPanel buttons_container = new JPanel();
		buttons_container.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Frocio!", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		buttons_container.setToolTipText("");

		JButton btn_insert_cd = new JButton("Eh...");
		JButton btn_view_warehouse = new JButton("Volevi");

		//btn_insert_cd.addActionListener(new area_riservata_option_insert_cd(this));
		//btn_view_warehouse.addActionListener(new carrello_intro(this));
		prova_panel.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));

		prova_panel.add(buttons_container, "cell 0 0,alignx left,aligny top");
		buttons_container.setLayout(new MigLayout("", "[grow,fill]", "[][]"));
		buttons_container.add(btn_insert_cd, "cell 0 0,growx,aligny top");
		buttons_container.add(btn_view_warehouse, "cell 0 1,growx,aligny top");

	}
	
	
	
	
	private void createCarrelloPanel()
	{
		panel_container.add(carrello_panel, "carrello");

		JButton btn_carrello_visualizza = new JButton("Visualizza Carrello");
		carrello_panel.add(btn_carrello_visualizza, "cell 0 6,alignx center,aligny center");
		
		JButton btn_carrello_modifica = new JButton("Modifica Carrello");
		carrello_panel.add(btn_carrello_modifica, "cell 2 2,alignx left, aligny left");
		
		JButton btn_carrello_goback = new JButton("Indietro");
		carrello_panel.add(btn_carrello_goback, "cell 2 0,alignx left, aligny left");
//voglio farlo tornare alla main_wnd con questo pulsante, non premendo la x rossa.
		
		JMenuBar jb = new JMenuBar();
		this.setJMenuBar(jb);
		
		JMenu jm = new JMenu("Edit");
		//JMenu jm = new JMenu("Bestemmie");
		jb.add(jm);
		
		//Aggiungo gli eventi
		btn_carrello_visualizza.addActionListener(new carrello_intro(this));
		btn_carrello_visualizza.addKeyListener(new carrello_intro(this));
		
		btn_carrello_modifica.addActionListener(new carrello_intro(this));

		btn_carrello_goback.addActionListener(new carrello_goMain(this));
		//btn_carrello_goback.addKeyListener(new carrello_goback(this));
			
	}
}

	
	
	
	
	
/*
//________________________DA CANCELLARE?___________________
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
		txt_tracklist.setText("");
		cb_gen.removeAllItems();
		cb_musician.removeAllItems();
	}
}

	*/