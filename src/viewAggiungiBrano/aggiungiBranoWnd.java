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

import aggiungiBranoListener.btnAddTrackListener;
import aggiungiBranoListener.btnRemoveTrackListener;
import aggiungiBranoListener.closerAddTrackListener;

import javax.swing.JList;
import javax.swing.JScrollPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;


public class aggiungiBranoWnd extends JFrame{

	private JFrame caller;
	private JTextField txtTrackName;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	
	
	public aggiungiBranoWnd(JFrame caller) {
		setResizable(false);
		//Tengo il riferimento al main form
		this.caller=caller;
		this.setTitle("Aggiungi brani");
		this.setAlwaysOnTop(true);
		this.addWindowListener(new closerAddTrackListener(this));
		loadModel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//chiude il frame
		setBounds(caller.getLocation().x,caller.getLocation().y, 650, 170);
		getContentPane().setLayout(new MigLayout("", "[100.00][200.00][230.00][100.00]", "[grow,top][grow,top][grow,top][grow,top]"));
		
		JLabel lblNomeBrano = new JLabel("Nome brano:");
		getContentPane().add(lblNomeBrano, "cell 0 0,alignx center,aligny center");
		
		txtTrackName = new JTextField();
		getContentPane().add(txtTrackName, "cell 1 0,alignx center,aligny center");
		txtTrackName.setColumns(10);
		
		JScrollPane listPanel = new JScrollPane();
		getContentPane().add(listPanel, "cell 2 0 1 4,grow");
		
		list = new JList(listModel);
		listPanel.setViewportView(list);
		
		JButton btnRemoveTrack = new JButton("Rimuovi brano");
		btnRemoveTrack.addActionListener(new btnRemoveTrackListener(this));
		
		JButton btnAddTrack = new JButton("Aggiungi brano");
		btnAddTrack.addActionListener(new btnAddTrackListener(this));
		getContentPane().add(btnAddTrack, "cell 1 2,alignx center,aligny center");
		getContentPane().add(btnRemoveTrack, "cell 3 2,alignx center,aligny center");
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtTrackName, btnAddTrack}));
		
		this.setVisible(true);
	}

	public void addTrack()
	{
		if(dataValidator.checkString(getTxtTrackName()) && !listModel.contains(getTxtTrackName()))
		{ 
			listModel.addElement(getTxtTrackName());
		}
		txtTrackName.setText("");
	}
	
	public void removeTrack()
	{
		if(list.getSelectedValue() != null)
		{
			listModel.remove(list.getSelectedIndex());
		}
	}
	
	private String getTxtTrackName()
	{
		return txtTrackName.getText();
	}
	
	public void close()
	{
		caller.setEnabled(true);
		caller.setAlwaysOnTop(true);
		caller.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ArrayList<String> trackList=new ArrayList();
		
		for(int i=0; i<listModel.size(); i++)
		{
			trackList.add(listModel.getElementAt(i));
		}

		((areaRiservataWnd) caller).setTrackList(trackList);
	}
	
	private void loadModel()
	{		
		listModel=((areaRiservataWnd)caller).getTrackList();
	}
}

