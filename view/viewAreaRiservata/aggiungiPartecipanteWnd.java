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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import aggiungiPartecipanteListener.btnAddPartecipantListener;
import aggiungiPartecipanteListener.btnRemovePartecipantListener;
import aggiungiPartecipanteListener.closerAddPartecipantListener;	

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Font;


public class aggiungiPartecipanteWnd extends JFrame{

	private JFrame caller;
	private JList<String> list;
	private DefaultListModel<String> listModel2;
	private JComboBox<String> cbMusicisti;


	public aggiungiPartecipanteWnd(JFrame caller) {
		//Tengo il riferimento al main form
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		this.caller=caller;
		this.setTitle("Aggiungi musicisti partecipanti");
		this.setAlwaysOnTop(true);
		this.addWindowListener(new closerAddPartecipantListener(this));
		loadModel();		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//chiude il frame
		setBounds(caller.getLocation().x,caller.getLocation().y, 650, 170);
		this.setLocation(caller.getLocation().x+200, caller.getLocation().y+200);
		getContentPane().setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow]"));

		JLabel lblNomePartecipante = new JLabel("Seleziona musicista:");
		lblNomePartecipante.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(lblNomePartecipante, "cell 0 0,alignx trailing,aligny center");

		JScrollPane listPanel = new JScrollPane();
		getContentPane().add(listPanel, "cell 2 0 1 3,grow");
		list = new JList(listModel2);
		list.setFont(new Font("Dialog", Font.BOLD, 14));
		listPanel.setViewportView(list);

		JButton btnAddColl = new JButton("Aggiungi musicista");
		btnAddColl.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(btnAddColl, "cell 1 1,alignx center,aligny center");
		btnAddColl.addActionListener(new btnAddPartecipantListener(this));

		//ComboBox
		cbMusicisti = new JComboBox();
		cbMusicisti.setFont(new Font("Dialog", Font.PLAIN, 14));
		//da qui in poi per mostrare la lista di tutti i musicisti nella ComboBox
		ArrayList<Musicista> listaMusicisti = new Musicista().getAllNotBand();

		for (int i=0; i<listaMusicisti.size(); i++) {
			Musicista musicista = listaMusicisti.get(i);
			cbMusicisti.addItem(musicista.getNomeArte());
		}

		getContentPane().add(cbMusicisti, "cell 1 0,growx,aligny center");

		JButton btnRemoveColl = new JButton("Rimuovi musicista");
		btnRemoveColl.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(btnRemoveColl, "cell 1 2,alignx center,aligny center");
		btnRemoveColl.addActionListener(new btnRemovePartecipantListener(this));	

		this.setVisible(true);
	}




	public void addPartecipant()
	{
		//value prende il valore che è attualmente selezionato nella ComboBox
		String value = cbMusicisti.getSelectedItem().toString();

		if(dataValidator.checkString(value) && !listModel2.contains(value))
		{ 
			listModel2.addElement(value);
		}
	}

	public void removePartecipant()
	{
		if(list.getSelectedValue() != null)
		{
			listModel2.remove(list.getSelectedIndex());
		}
	}


	public void close()
	{
		caller.setEnabled(true);
		caller.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ArrayList<String> partecipantList=new ArrayList();

		for(int i=0; i<listModel2.size(); i++)
		{
			partecipantList.add(listModel2.getElementAt(i));
		}

		if(caller instanceof areaRiservataWnd)
		{
			((areaRiservataWnd) caller).setPartecipantList(partecipantList);
		}
		else
		{
			((modificaCdWnd) caller).setPartecipantList(partecipantList);
		}

	}

	private void loadModel()
	{		
		if(caller instanceof areaRiservataWnd)
		{
			listModel2=((areaRiservataWnd)caller).getPartecipantList();
		}
		else
		{
			listModel2=((modificaCdWnd)caller).getPartecipantList();
		}
	}
}
