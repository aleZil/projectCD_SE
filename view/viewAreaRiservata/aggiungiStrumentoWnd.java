package viewAreaRiservata;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import utility.dataValidator;

import javax.swing.JTextField;
import javax.swing.ListModel;
import areaRiservataListener.btnShowTrackListListener;
import areaRiservataListener.returnNegozioListener;
import model.Genere;
import model.Musicista;
import model.Strumento;
import areaRiservataListener.btnBackListener;
import areaRiservataListener.btnAddNewCdListener;
import areaRiservataListener.btnAddNewGenListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import aggiungiPartecipanteListener.btnAddPartecipantListener;
import aggiungiStrumentoListener.btnAddInstrumentListener;
import aggiungiStrumentoListener.btnRemoveInstrumentListener;
import aggiungiStrumentoListener.closerAddInstrumentListener;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Font;


public class aggiungiStrumentoWnd extends JFrame{

	private JFrame caller;
	private JList<String> list;
	private DefaultListModel<String> listModel3;

	Map<String,Integer> kStrumenti;
	private JComboBox<String> cbStrumenti;


	public aggiungiStrumentoWnd(JFrame caller) {
		//Tengo il riferimento al main form
		this.caller=caller;
		this.setTitle("Aggiungi gli strumenti suonati dal musicista");
		this.setAlwaysOnTop(true);
		this.addWindowListener(new closerAddInstrumentListener(this));
		loadModel();		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//chiude il frame
		setBounds(caller.getLocation().x,caller.getLocation().y, 700, 170);
		this.setLocation(caller.getLocation().x+200, caller.getLocation().y+200);
		getContentPane().setLayout(new MigLayout("", "[100.00][200.00,grow][230.00][100.00]", "[grow,top][grow,top][grow,top][grow,top]"));

		JLabel lblSelezionaStrumento = new JLabel("Seleziona strumento:");
		lblSelezionaStrumento.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(lblSelezionaStrumento, "cell 0 0,alignx trailing,aligny center");

		JScrollPane listPanel = new JScrollPane();
		getContentPane().add(listPanel, "cell 2 0 1 4,grow");
		list = new JList(listModel3);
		list.setFont(new Font("Dialog", Font.BOLD, 14));
		listPanel.setViewportView(list);

		JButton btnAggiungiStrumento = new JButton("Aggiungi strumento");
		btnAggiungiStrumento.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(btnAggiungiStrumento, "cell 1 2,alignx center,aligny center");
		btnAggiungiStrumento.addActionListener(new btnAddInstrumentListener(this));

		JButton btnRimuoviStrumento = new JButton("Rimuovi strumento");
		btnRimuoviStrumento.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(btnRimuoviStrumento, "cell 3 2,alignx center,aligny center");
		btnRimuoviStrumento.addActionListener(new btnRemoveInstrumentListener(this));

		//ComboBox
		cbStrumenti = new JComboBox();
		cbStrumenti.setFont(new Font("Dialog", Font.PLAIN, 14));

		//da qui in poi per mostrare la lista di tutti gli strumenti nella ComboBox
		ArrayList<Strumento> listaStrumenti = new Strumento().getAll();

		//TODO
		//Se l'utente aveva scritto prima,pulisco
		//clearComponents();			

		kStrumenti = new HashMap<String,Integer>();

		//Rimuovo gli elementi che eventualmente ci sono
		cbStrumenti.removeAll();	

		for (int i=0; i < listaStrumenti.size(); i++) {
			Strumento strumento = listaStrumenti.get(i);
			kStrumenti.put(strumento.getNome(), strumento.getId());
			cbStrumenti.addItem(strumento.getNome());
		}

		getContentPane().add(cbStrumenti, "cell 1 0,growx,aligny center");

		this.setVisible(true);
	}




	public void addInstrument()
	{
		//value prende il valore che è attualmente selezionato nella ComboBox
		String value = cbStrumenti.getSelectedItem().toString();

		if(dataValidator.checkString(value) && !listModel3.contains(value))
		{ 
			listModel3.addElement(value);
		}
	}

	public void removeInstrument()
	{
		if(list.getSelectedValue() != null)
		{
			listModel3.remove(list.getSelectedIndex());
		}
	}


	public void close()
	{
		caller.setEnabled(true);
		caller.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ArrayList<String> instrumentList=new ArrayList();

		for(int i=0; i<listModel3.size(); i++)
		{
			instrumentList.add(listModel3.getElementAt(i));
		}
		((areaRiservataWnd) caller).setInstrumentList(instrumentList);
	}

	private void loadModel()
	{		
		listModel3=((areaRiservataWnd)caller).getInstrumentList();
	}

}
