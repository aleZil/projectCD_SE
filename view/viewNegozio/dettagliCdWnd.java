package viewNegozio;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Cd;
import model.Musicista;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.Cursor;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class dettagliCdWnd extends JFrame {
	
	//Componenti di rilievo
	JLabel titolo;
	JLabel genere;
	JLabel titolare;
	JLabel rimasti;
	JLabel prezzo;
	JList<String> listPartecipanti;
	
	public dettagliCdWnd(Integer idCd) {
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
		dettagliPanel.setBorder(new TitledBorder(null, "Dettagli", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		getContentPane().add(dettagliPanel, "cell 0 0,grow");
		dettagliPanel.setLayout(new MigLayout("", "[grow][grow,fill]", "[grow][grow][grow][grow][grow][grow][grow]"));
		
		JLabel lblTitolo = new JLabel("Titolo:");
		dettagliPanel.add(lblTitolo, "cell 0 0,alignx right,aligny center");
		
		titolo = new JLabel("");
		dettagliPanel.add(titolo, "cell 1 0,alignx left,aligny center");
		
		JLabel lblGenere = new JLabel("Genere:");
		dettagliPanel.add(lblGenere, "cell 0 1,alignx right,aligny center");
		
		genere = new JLabel("");
		dettagliPanel.add(genere, "cell 1 1,alignx left,aligny center");
		
		JLabel lblTitolare = new JLabel("Titolare:");
		dettagliPanel.add(lblTitolare, "cell 0 2,alignx right,aligny center");
		
		titolare = new JLabel("");
		dettagliPanel.add(titolare, "cell 1 2,alignx left,aligny center");
		
		JLabel lblRimanenti = new JLabel("Rimasti:");
		dettagliPanel.add(lblRimanenti, "cell 0 3,alignx right,aligny center");
		
		rimasti = new JLabel("");
		dettagliPanel.add(rimasti, "cell 1 3");
		
		JLabel lblPartecipanti = new JLabel("Partecipanti:");
		dettagliPanel.add(lblPartecipanti, "cell 0 4,alignx right,aligny center");
		
		JScrollPane scrollList = new JScrollPane();
		dettagliPanel.add(scrollList, "cell 1 4,grow");
		
		listPartecipanti = new JList();
		scrollList.setViewportView(listPartecipanti);
		
		JLabel lblPrezzo = new JLabel("Prezzo:");
		dettagliPanel.add(lblPrezzo, "cell 0 5,alignx right,aligny center");
		
		prezzo = new JLabel("");
		dettagliPanel.add(prezzo, "cell 1 5");
		
		JButton btnAggiungiCarrello = new JButton("Aggiungi al carrello");
		dettagliPanel.add(btnAggiungiCarrello, "cell 0 6 2 1,grow");
		
	}

	private void loadDettagli(Integer idCd)
	{
		Cd cd=new Cd();
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
}
