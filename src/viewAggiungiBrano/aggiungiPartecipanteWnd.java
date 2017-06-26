package viewAggiungiBrano;

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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

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
import aggiungiPartecipanteListener.closerAddPartecipantListener;	

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;


public class aggiungiPartecipanteWnd extends JFrame{

	private JFrame caller;
	private JTextField txtCollName;
	private JList<String> list;
	private DefaultListModel<String> listModel2;
	
	
	public aggiungiPartecipanteWnd(JFrame caller) {
		//Tengo il riferimento al main form
		this.caller=caller;
		this.setTitle("Aggiungi musicisti partecipanti");
		this.setAlwaysOnTop(true);
		this.addWindowListener(new closerAddPartecipantListener(this));
		loadModel();		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//chiude il frame
		setBounds(caller.getLocation().x,caller.getLocation().y, 550, 170);
		getContentPane().setLayout(new MigLayout("", "[175.00,grow][141.00,grow][grow,fill]", "[grow,top][grow,top][grow,top][grow,top]"));
		
		JLabel lblNomePartecipante = new JLabel("Seleziona musicista:");
		getContentPane().add(lblNomePartecipante, "cell 0 0,alignx trailing,aligny center");
		
		JButton btnAddColl = new JButton("Aggiungi musicista");
		btnAddColl.addActionListener(new btnAddPartecipantListener(this));	
		
		JComboBox cbMusicisti = new JComboBox();
		getContentPane().add(btnAddColl, "cell 0 2,alignx center,aligny center");
		
		JScrollPane listPanel = new JScrollPane();
		getContentPane().add(listPanel, "cell 2 0 1 4,grow");
		
		list = new JList(listModel2);
		listPanel.setViewportView(list);
		
		JButton btnRemoveColl = new JButton("Rimuovi musicista");
		btnRemoveColl.addActionListener(new btnRemovePartecipantListener(this));	
		
		txtCollName = new JTextField();
		getContentPane().add(txtCollName, "cell 1 2,alignx center,aligny center");
		txtCollName.setColumns(10);
		getContentPane().add(btnRemoveColl, "cell 0 3,alignx center,aligny center");
		
		this.setVisible(true);
	}

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
