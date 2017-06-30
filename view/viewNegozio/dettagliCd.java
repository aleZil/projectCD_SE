package viewNegozio;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

public class dettagliCd extends JFrame {
	public dettagliCd() {
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		this.setTitle("Dettagli prodotto");
		createDettagliPanel();
	
	}
	
	private void createDettagliPanel()
	{
		JPanel dettagliPanel = new JPanel();
		dettagliPanel.setBorder(new TitledBorder(null, "Dettagli", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		getContentPane().add(dettagliPanel, "cell 0 0,grow");
		dettagliPanel.setLayout(new MigLayout("", "[grow][grow,fill]", "[grow][grow][grow][grow][grow][grow][grow]"));
		
		JLabel lblTitolo = new JLabel("Titolo:");
		dettagliPanel.add(lblTitolo, "cell 0 0,alignx right,aligny center");
		
		JLabel titolo = new JLabel("");
		dettagliPanel.add(titolo, "cell 1 0,alignx left,aligny center");
		
		JLabel lblGenere = new JLabel("Genere:");
		dettagliPanel.add(lblGenere, "cell 0 1,alignx right,aligny center");
		
		JLabel genere = new JLabel("");
		dettagliPanel.add(genere, "cell 1 1,alignx left,aligny center");
		
		JLabel lblTitolare = new JLabel("Titolare:");
		dettagliPanel.add(lblTitolare, "cell 0 2,alignx right,aligny center");
		
		JLabel titolare = new JLabel("");
		dettagliPanel.add(titolare, "cell 1 2,alignx left,aligny center");
		
		JLabel lblRimanenti = new JLabel("Rimasti:");
		dettagliPanel.add(lblRimanenti, "cell 0 3,alignx right,aligny center");
		
		JLabel rimasti = new JLabel("");
		dettagliPanel.add(rimasti, "cell 1 3");
		
		JLabel lblPartecipanti = new JLabel("Partecipanti:");
		dettagliPanel.add(lblPartecipanti, "cell 0 4,alignx right,aligny center");
		
		JScrollPane scrollList = new JScrollPane();
		dettagliPanel.add(scrollList, "cell 1 4,grow");
		
		JList listPartecipanti = new JList();
		scrollList.setViewportView(listPartecipanti);
		
		JLabel lblPrezzo = new JLabel("Prezzo:");
		dettagliPanel.add(lblPrezzo, "cell 0 5,alignx right,aligny center");
		
		JLabel prezzo = new JLabel("");
		dettagliPanel.add(prezzo, "cell 1 5");
		
		JButton btnAggiungiCarrello = new JButton("Aggiungi al carrello");
		dettagliPanel.add(btnAggiungiCarrello, "cell 0 6 2 1,grow");
		
	}
}
