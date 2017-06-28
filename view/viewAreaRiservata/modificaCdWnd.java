package viewAreaRiservata;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import areaRiservataListener.btnShowCollaboratorListListener;
import model.Brano;
import model.Cd;
import model.Genere;
import model.Musicista;
import modificaCdListener.*;
import net.miginfocom.swing.MigLayout;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

public class modificaCdWnd extends JFrame {

	private JTextField txtTitle;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JTextField txtPrice;
	private JTextField txtAmo;
	private JComboBox<String> cbGen;
	private JComboBox<String> cbMus;
	private DefaultListModel<String> listModel;
	private DefaultListModel<String> listModel2;
	private JList listTrackList;
	private JList listPartecipantList;
	private JTextArea txtDesc;
	private JFrame caller;

	public modificaCdWnd(JFrame caller,int index) throws ParseException{
		this.caller=caller;
		setResizable(false);
		this.addWindowListener(new closerModificaCdListener(this));
		this.setAlwaysOnTop(true);
		createInsertPanel();
		loadPanel(index);
		this.setVisible(true);
	}

	private void createInsertPanel() throws ParseException
	{
		this.setTitle("Modifica cd esistente");
		setBounds(0,0,800,550);
		JPanel insertPanel = new JPanel();
		JPanel newCdPanel = new JPanel();
		newCdPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Dettagli nuovo prodotto", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		newCdPanel.setLayout(new MigLayout("", "[grow][600px,grow,fill][]", "[][20px][][grow][20px][grow][20px][20px][48.00,grow][grow][20px][60px]"));

		JLabel lblTitle = new JLabel("Titolo Cd:");
		newCdPanel.add(lblTitle, "cell 0 1,alignx right,aligny center");

		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		newCdPanel.add(txtTitle, "cell 1 1,grow");

		JLabel lblAddTrackList = new JLabel("Gestione brani:");
		newCdPanel.add(lblAddTrackList, "cell 0 2,alignx right,aligny center");

		JButton btnAddTrack = new JButton("Aggiungi/Rimuovi");
		newCdPanel.add(btnAddTrack, "cell 1 2,grow");
		//btnAddTrack.addActionListener(new btnAddTrackListener(this));	//apro nuovo frame

		JLabel lblTrackList = new JLabel("Lista brani:");
		newCdPanel.add(lblTrackList, "cell 0 3,alignx right,aligny center");

		//Pannello di visualizzazione brani
		JScrollPane scrollTrackList = new JScrollPane();
		newCdPanel.add(scrollTrackList, "cell 1 3,grow");
		listModel=new DefaultListModel<String>();
		listTrackList = new JList(listModel);
		scrollTrackList.setViewportView(listTrackList);

		JLabel lblPrice = new JLabel("Prezzo:");
		newCdPanel.add(lblPrice, "cell 0 4,alignx right,aligny center");

		txtPrice = new JTextField();
		newCdPanel.add(txtPrice, "cell 1 4,alignx center,aligny center");
		txtPrice.setColumns(10);

		JLabel lblDesc = new JLabel("Descrizione:");
		newCdPanel.add(lblDesc, "cell 0 5,alignx right,aligny center");

		JScrollPane scrollDesc = new JScrollPane();
		newCdPanel.add(scrollDesc, "cell 1 5,grow");

		txtDesc = new JTextArea();
		scrollDesc.setViewportView(txtDesc);

		JLabel lblGen = new JLabel("Genere:");
		newCdPanel.add(lblGen, "cell 0 6,alignx right,aligny center");

		cbGen = new JComboBox();
		newCdPanel.add(cbGen, "cell 1 6,alignx center,aligny center");

		JLabel lblMus = new JLabel("Capo band:");
		newCdPanel.add(lblMus, "cell 0 7,alignx right,aligny center");

		cbMus = new JComboBox();
		newCdPanel.add(cbMus, "cell 1 7,alignx center,aligny center");

		JLabel lblCollaboratore = new JLabel("Gestione musicisti:");
		newCdPanel.add(lblCollaboratore, "cell 0 8,alignx right,aligny center");

		JButton btnAggiungiCollaboratore = new JButton("Aggiungi/Rimuovi");
		newCdPanel.add(btnAggiungiCollaboratore, "cell 1 8");
		btnAggiungiCollaboratore.addActionListener(new btnShowCollaboratorListListener(this)); 	//apro nuovo frame

		JLabel lblListaMusicisti = new JLabel("Lista musicisti:");
		newCdPanel.add(lblListaMusicisti, "cell 0 9,alignx right,aligny center");

		//pannello visualizzazione musicisti partecipanti
		JScrollPane scrollPartecipantList = new JScrollPane();
		newCdPanel.add(scrollPartecipantList, "cell 1 9,grow");

		listModel2=new DefaultListModel<String>();	
		listPartecipantList = new JList(listModel2);
		listPartecipantList.setModel(listModel2);
		scrollPartecipantList.setViewportView(listPartecipantList);

		JLabel lblAmo = new JLabel("Quantità:");
		newCdPanel.add(lblAmo, "cell 0 10,alignx right,aligny center");

		txtAmo = new JTextField();
		txtAmo.setColumns(10);
		newCdPanel.add(txtAmo, "cell 1 10,alignx center,aligny center");

		JButton btnUpdateCd = new JButton("Modifica prodotto");
		btnUpdateCd.addActionListener(new btnUpdateCdListener(this));
		newCdPanel.add(btnUpdateCd, "flowx,cell 1 11,alignx left,growy");
		insertPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		insertPanel.add(newCdPanel, "cell 0 0,grow");
		getContentPane().add(insertPanel);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtTitle, btnAddTrack, txtPrice, txtDesc, cbGen, cbMus, btnAggiungiCollaboratore, txtAmo, btnUpdateCd}));
	}

	private void loadPanel(int index)
	{
		Integer idCd=((areaRiservataWnd)caller).getSelectedCdId(index);
		Cd selectedCd=new Cd();
		selectedCd.getById(idCd);
		setCdTitle(selectedCd.getTitolo());
		setTrackList(selectedCd.getBrani());
		setDescription(selectedCd.getDescrizione());
		setMusicianList(selectedCd.getPartecipanti());
		setPrice(selectedCd.getPrezzo());
		setAmount(selectedCd.getPezziMagazzino());
		setGender(selectedCd.getGenere());
		setMusician(selectedCd.getTitolare());
	}

	public void saveUpdate()
	{
		//Fine update con chiusura della finestra
		caller.setEnabled(true);
		caller.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dispose();
	}

	public void close()
	{
		caller.setEnabled(true);
		caller.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	


	// *********************************************************************************************

	//								METODI SET

	// *********************************************************************************************

	private void setCdTitle(String title)
	{
		txtTitle.setText(title);
	}
	
	private void setTrackList(ArrayList<Brano> trackList)
	{
		for(Brano b:trackList)
		{
			listModel.addElement(b.getNome());
		}
	}
	
	private void setPrice(BigDecimal price)
	{
		txtPrice.setText(price.toString());
	}
	
	private void setDescription(String description)
	{
		txtDesc.setText(description);
	}
	
	private void setGender(Genere gen)
	{
		for(Genere g:gen.getAll())
		{
			cbGen.addItem(g.getNome());
		}
		cbGen.setSelectedItem(gen.getNome());
	}
	
	private void setMusician(Musicista mus)
	{
		System.out.println(mus.getId());
		for(Musicista m:mus.getAll())
		{
			cbMus.addItem(m.getNomeArte());
		}
		cbMus.setSelectedItem(mus.getNomeArte());
	}
	
	private void setMusicianList(ArrayList<Musicista> musList)
	{
		for(Musicista m:musList)
		{
			listModel2.addElement(m.getNomeArte());
		}
	}

	private void setAmount(Integer amount)
	{
		txtAmo.setText(amount.toString());
	}
}
