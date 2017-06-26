package viewAggiungiPartecipante;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import utility.dataValidator;
import viewAreaRiservata.areaRiservataWnd;

import javax.swing.JTextField;
import javax.swing.ListModel;

import areaRiservataListener.closerWndListener;
import areaRiservataListener.btnShowTrackListListener;
import areaRiservataListener.returnNegozioListener;
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
import aggiungiPartecipanteListener.btnRemovePartecipantListener;
import aggiungiPartecipanteListener.cbAddPartecipantListener;
import aggiungiPartecipanteListener.closerAddPartecipantListener;	

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;


public class aggiungiPartecipanteWnd extends JFrame{

	private JFrame caller;
	private JTextField txtCollName;
	private JList<String> list;
	private DefaultListModel<String> listModel2;
	
	Map<String,Integer> kMus;
	private JComboBox<String> cbMus;
	
	
	public aggiungiPartecipanteWnd(JFrame caller) {
		//Tengo il riferimento al main form
		this.caller=caller;
		this.setTitle("Aggiungi musicisti partecipanti");
		this.setAlwaysOnTop(true);
		this.addWindowListener(new closerAddPartecipantListener(this));
		loadModel();		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//chiude il frame
		setBounds(caller.getLocation().x,caller.getLocation().y, 650, 170);
		getContentPane().setLayout(new MigLayout("", "[100.00][200.00,grow][230.00][100.00]", "[grow,top][grow,top][grow,top][grow,top]"));
		
		JLabel lblNomePartecipante = new JLabel("Seleziona musicista:");
		getContentPane().add(lblNomePartecipante, "cell 0 0,alignx trailing,aligny center");
		
		//JComboBox cbMusicisti = new JComboBox();
		//getContentPane().add(cbMusicisti, "cell 1 0,growx,aligny center");
		
		//provo a sistemare la WOMBOCOMBOBOX
		cbMus = new JComboBox();
		getContentPane().add(cbMus, "cell 1 0,growx,aligny center");
		cbMus.addActionListener(new cbAddPartecipantListener(this));
		
		//questo try serve per mostrare la lista di tutti i musicisti nella ComboBox
		try
		{
			String queryMusicista="SELECT * FROM Musicista ORDER BY nome_arte";
			Connection con=DriverManager.getConnection("jdbc:postgresql://db-cdproject.czz77hrlmvcn.eu-west-1.rds.amazonaws.com/progetto_cd","hanzo","neversurrender");
			Statement stMus=con.createStatement();

			ResultSet musicisti=stMus.executeQuery(queryMusicista);

			//Devo tenere una mappa chiave primaria e stringa 
			kMus=new HashMap<String,Integer>();

			//Rimuovo gli elementi che eventualmente ci sono
			cbMus.removeAll();

			while(musicisti.next())
			{
				kMus.put(musicisti.getString("nome_arte"),musicisti.getInt("id"));
				cbMus.addItem(musicisti.getString("nome_arte"));
			}
			stMus.close();
			con.close();
		}
		catch(Exception exception)
		{
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}
		
		
		
		
		
		//___________________________
		JScrollPane listPanel = new JScrollPane();
		getContentPane().add(listPanel, "cell 2 0 1 4,grow");
		list = new JList(listModel2);
		listPanel.setViewportView(list);
		
		JButton btnRemoveColl = new JButton("Rimuovi musicista");
		btnRemoveColl.addActionListener(new btnRemovePartecipantListener(this));	
		
		JButton btnAddColl = new JButton("Aggiungi musicista");
		btnAddColl.addActionListener(new btnAddPartecipantListener(this));	
				
		txtCollName = new JTextField();
		getContentPane().add(txtCollName, "cell 0 2,alignx center,aligny center");
		txtCollName.setColumns(10);
		getContentPane().add(btnAddColl, "cell 1 2,alignx center,aligny center");
		getContentPane().add(btnRemoveColl, "cell 3 2,alignx center,aligny center");
		
		this.setVisible(true);
	}
	//_______________

	
	
	
	
	
	//_______________
	public void addPartecipant()
	{
		if(dataValidator.checkString(getTxtCollName()) && !listModel2.contains(getTxtCollName()))
		{ 
			listModel2.addElement(getTxtCollName());
		}
		txtCollName.setText("");
	}
	
	public void removePartecipant()
	{
		if(list.getSelectedValue() != null)
		{
			listModel2.remove(list.getSelectedIndex());
		}
	}
	
	private String getTxtCollName()
	{
		return txtCollName.getText();
	}
	
	public void close()
	{
		caller.setEnabled(true);
		caller.setAlwaysOnTop(true);
		
		ArrayList<String> partecipantList=new ArrayList();
		
		for(int i=0; i<listModel2.size(); i++)
		{
			partecipantList.add(listModel2.getElementAt(i));
		}

		((areaRiservataWnd) caller).setPartecipantList(partecipantList);
	}
	
	private void loadModel()
	{		
		listModel2=((areaRiservataWnd)caller).getPartecipantList();
	}

	
}














/*

cbMus.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	
		        System.out.println("ciaone"); 
		        this.aggiungiPartecipante();
		        this.rimuoviPartecipante();
		        this.getTxtPartecipante();
		        this.close();
		        this.loadModel();
		        System.out.println("ciaone2"); 

				//loadModel();		

		    }
		    
		    public void aggiungiPartecipante()
			{
				if(dataValidator.checkString(getTxtPartecipante()) && !listModel2.contains(getTxtPartecipante()))
				{ 
					listModel2.addElement(getTxtPartecipante());
				}
				txtCollName.setText("");
			}
			
			public void rimuoviPartecipante()
			{
				if(list.getSelectedValue() != null)
				{
					listModel2.remove(list.getSelectedIndex());
				}
			}
			
			private String getTxtPartecipante()
			{
				return txtCollName.getText();
			}
			
			public void close()
			{
				caller.setEnabled(true);
				caller.setAlwaysOnTop(true);
				
				ArrayList<String> partecipantList=new ArrayList();
				
				for(int i=0; i<listModel2.size(); i++)
				{
					partecipantList.add(listModel2.getElementAt(i));
				}

				((areaRiservataWnd) caller).setPartecipantList(partecipantList);
			}
			
			private void loadModel()
			{		
				listModel2=((areaRiservataWnd)caller).getPartecipantList();
			}
		    
			//_______________

		});

*/