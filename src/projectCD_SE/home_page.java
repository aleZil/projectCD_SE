package projectCD_SE;

import utility.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import listeners.area_riservata_option_insert_cd;
import listeners.area_riservata_save_updates;
import listeners.area_riservata_get_change_table;
import listeners.area_riservata_goback;
import listeners.home_page_visualizza_carrello;
import listeners.area_riservata_goback;
import listeners.area_riservata_newcd_insert;
import listeners.area_riservata_see_warehouse;
import listeners.area_riservata_wnd_closer;
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

import jdk.nashorn.internal.scripts.JO;

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

public class home_page extends JFrame {

	JFrame main_wnd;
	private static CardLayout home_page_layout=new CardLayout();
	private JPanel panel_container=new JPanel();
	private JPanel carrello_home_page_panel=new JPanel();
	//private JPanel option_area_riservata_panel=new JPanel();

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
	
	//Utility
	Map<String,Integer> kGen;
	Map<String,Integer> kMus;
	Set<Integer> rowEdited;
	
	
	public home_page(JFrame caller) throws ParseException {
		setResizable(false);
		setTitle("Login");
		rowEdited=new HashSet<>();
		//Tengo il riferimento al main form
		main_wnd=caller;
		panel_container.setLayout(home_page_layout);
		this.addWindowListener(new area_riservata_wnd_closer(main_wnd));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(main_wnd.getLocation().x,main_wnd.getLocation().y, 770, 600);
		
		
		//Aggiungo il container che contiene tutti i panel
		getContentPane().add(panel_container);
		home_page_layout.show(panel_container, "login");
		this.setVisible(true);

	}

	public void showOption(String user)
	{
		this.setTitle("Pannello area riservata");
		home_page_layout.show(panel_container, "options");

	}
	
	
	private void createLoginPanel()
	{
		panel_container.add(carrello_home_page_panel, "carrello");
		carrello_home_page_panel.setLayout(new MigLayout("", "[grow]", "[20px][20px][20px][20px][20px][20px][100px][50px,grow]"));

		JLabel lbl_username = new JLabel("Username (da canc):");
		carrello_home_page_panel.add(lbl_username, "flowx,cell 0 3,alignx center,aligny center");

		txt_usr = new JTextField();
		carrello_home_page_panel.add(txt_usr, "cell 0 3,alignx center,aligny center");
		txt_usr.setColumns(10);

		JLabel lbl_passwd = new JLabel("Password (da canc):");
		carrello_home_page_panel.add(lbl_passwd, "flowx,cell 0 5,alignx center,aligny center");

		txt_psswd = new JPasswordField();
		txt_psswd.setColumns(10);
		carrello_home_page_panel.add(txt_psswd, "cell 0 5,alignx center,growy");

		JButton btn_visualizza_carrello = new JButton("Visualizza Carrello");

		carrello_home_page_panel.add(btn_visualizza_carrello, "cell 0 6,alignx center,aligny center");


		//Aggiungo gli eventi
		btn_visualizza_carrello.addActionListener(new home_page_visualizza_carrello(this));
		btn_visualizza_carrello.addKeyListener(new home_page_visualizza_carrello(this));
		txt_psswd.addActionListener(new home_page_visualizza_carrello(this));

	}
	
	
	
	
	
//--------------------------------------------------------------------------

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
