package viewNegozio;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import aggiungiStrumentoListener.btnAddInstrumentListener;
import dettagliCdListener.btnAggiungiCarrelloListener;
import model.Cd;
import model.Musicista;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class dettagliCdWnd extends JFrame {

	//Componenti di rilievo
	Cd cd;
	JLabel titolo;
	JLabel genere;
	JLabel titolare;
	JLabel rimasti;
	JLabel prezzo;
	negozioWnd caller;
	JList<String> listPartecipanti;

	public dettagliCdWnd(Integer idCd,negozioWnd caller) {
		int offsetx=110;
		int offsety=100;
		
		this.caller=caller;
		this.setLocation(caller.getLocation().x+offsetx,caller.getLocation().y+offsety);
		setResizable(false);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		setTitle("Dettagli prodotto");
		setBounds(getLocation().x,getLocation().y, 450,450);
		createDettagliPanel();
		loadDettagli(idCd);
		this.setVisible(true);
	}

	private void createDettagliPanel()
	{
		JPanel dettagliPanel = new JPanel();
		dettagliPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		getContentPane().add(dettagliPanel, "cell 0 0,grow");
		dettagliPanel.setLayout(new MigLayout("", "[][grow][grow,fill]", "[grow,fill][grow,fill][grow,fill][grow,fill][grow][grow][grow]"));
		JLabel lblTitolo = new JLabel("Titolo:");
		lblTitolo.setFont(new Font("Dialog", Font.BOLD, 14));
		dettagliPanel.add(lblTitolo, "cell 0 0,alignx right,aligny center");

		titolo = new JLabel("");
		titolo.setFont(new Font("Dialog", Font.PLAIN, 14));
		dettagliPanel.add(titolo, "cell 1 0,alignx left,aligny center");

		JLabel lblGenere = new JLabel("Genere:");
		lblGenere.setFont(new Font("Dialog", Font.BOLD, 14));
		dettagliPanel.add(lblGenere, "cell 0 1,alignx right,aligny center");

		genere = new JLabel("");
		genere.setFont(new Font("Dialog", Font.PLAIN, 14));
		dettagliPanel.add(genere, "cell 1 1,alignx left,aligny center");

		JLabel lblTitolare = new JLabel("Titolare:");
		lblTitolare.setFont(new Font("Dialog", Font.BOLD, 14));
		dettagliPanel.add(lblTitolare, "cell 0 2,alignx right,aligny center");

		titolare = new JLabel("");
		titolare.setFont(new Font("Dialog", Font.PLAIN, 14));
		dettagliPanel.add(titolare, "cell 1 2,alignx left,aligny center");

		JLabel lblRimanenti = new JLabel("Rimasti:");
		lblRimanenti.setFont(new Font("Dialog", Font.BOLD, 14));
		dettagliPanel.add(lblRimanenti, "cell 0 3,alignx right,aligny center");

		rimasti = new JLabel("");
		rimasti.setFont(new Font("Dialog", Font.PLAIN, 14));
		dettagliPanel.add(rimasti, "cell 1 3,alignx left,aligny center");

		JLabel lblPartecipanti = new JLabel("Partecipanti:");
		lblPartecipanti.setFont(new Font("Dialog", Font.BOLD, 14));
		dettagliPanel.add(lblPartecipanti, "cell 0 4,alignx right,aligny center");

		JScrollPane scrollList = new JScrollPane();
		dettagliPanel.add(scrollList, "cell 1 4 2 1,grow");

		listPartecipanti = new JList();
		listPartecipanti.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollList.setViewportView(listPartecipanti);

		JLabel lblPrezzo = new JLabel("Prezzo:");
		lblPrezzo.setFont(new Font("Dialog", Font.BOLD, 14));
		dettagliPanel.add(lblPrezzo, "cell 0 5,alignx right,aligny center");

		prezzo = new JLabel("");
		prezzo.setFont(new Font("Dialog", Font.PLAIN, 14));
		dettagliPanel.add(prezzo, "cell 1 5,alignx left,aligny center");

		JButton btnAggiungiCarrello = new JButton("Aggiungi al carrello");
		btnAggiungiCarrello.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAggiungiCarrello.addActionListener(new btnAggiungiCarrelloListener(caller,this));
		dettagliPanel.add(btnAggiungiCarrello, "cell 0 6 3 1,grow");

	}


	private void loadDettagli(Integer idCd)
	{
		cd=new Cd();
		cd.getById(idCd);
		titolo.setText(cd.getTitolo());
		genere.setText(cd.getGenere().getNome());
		titolare.setText(cd.getTitolare().getNomeArte());
		rimasti.setText(cd.getPezziMagazzino().toString());
		prezzo.setText(cd.getPrezzo().toString()+"€");
		DefaultListModel lmPartecipanti=new DefaultListModel<String>();
		listPartecipanti.setModel(lmPartecipanti);

		for(Musicista m:cd.getPartecipanti())
		{
			lmPartecipanti.addElement(m.getNomeArte());
		}
	}
	
	public Cd getCd()
	{
		return cd;
	}
}
