
package viewAreaRiservata;

import areaRiservataListener.*;
import controller.CdController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import controller.CdController;
import model.Brano;
import model.Cd;
import model.Genere;
import model.Musicista;
import modificaCdListener.*;
import net.miginfocom.swing.MigLayout;
import utility.dataValidator;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;

public class modificaCdWnd extends JFrame {

	private JTextField txtTitle;
	private JTextField txtPrice;
	private JTextField txtAmo;
	private JComboBox<String> cbGen;
	private JComboBox<String> cbMus;
	private DefaultListModel<String> listModel;
	private DefaultListModel<String> listModel2;
	private JList<String> listTrackList;
	private JList<String> listPartecipantList;
	private JTextArea txtDesc;
	private JFrame caller;

	//id del cd da modificare
	private Integer idCd;

	public modificaCdWnd(JFrame caller,int index) throws ParseException{
		this.caller=caller;
		this.setTitle("Aggiungi brani");
		this.setAlwaysOnTop(true);
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
		setBounds(caller.getLocation().x, caller.getLocation().y, 1000, 700);
		JPanel insertPanel = new JPanel();
		JPanel newCdPanel = new JPanel();
		newCdPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Dettagli nuovo prodotto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//		newCdPanel.setLayout(new MigLayout("", "[175][600px,grow,fill][]", "[grow][grow][grow][grow][60][grow][grow][grow][grow][grow][grow]"));

		newCdPanel.setLayout(new MigLayout("", "[175][600px,grow,fill][]", "[grow][grow][grow][grow][65][grow][grow][grow][grow][grow][grow]"));

		JLabel lblTitle = new JLabel("Titolo Cd");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(lblTitle, "cell 0 0,alignx right,aligny center");

		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTitle.setColumns(10);
		newCdPanel.add(txtTitle, "cell 1 0,grow");

		JLabel lblAddTrackList = new JLabel("Gestione brani");
		lblAddTrackList.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(lblAddTrackList, "cell 0 1,alignx right,aligny center");

		JButton btnAddTrack = new JButton("Aggiungi/Rimuovi");
		btnAddTrack.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(btnAddTrack, "cell 1 1,grow");
		btnAddTrack.addActionListener(new btnShowTrackListListener(this));	//apro nuovo frame

		JLabel lblTrackList = new JLabel("Lista brani");
		lblTrackList.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(lblTrackList, "cell 0 2,alignx right,aligny center");

		//Pannello di visualizzazione brani
		JScrollPane scrollTrackList = new JScrollPane();
		newCdPanel.add(scrollTrackList, "cell 1 2,grow");
		listModel=new DefaultListModel<String>();
		listTrackList = new JList(listModel);
		listTrackList.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollTrackList.setViewportView(listTrackList);

		JLabel lblPrice = new JLabel("Prezzo");
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(lblPrice, "cell 0 3,alignx right,aligny center");

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Dialog", Font.PLAIN, 14));
		newCdPanel.add(txtPrice, "cell 1 3,alignx center,aligny center");
		txtPrice.setColumns(10);

		JLabel lblDesc = new JLabel("Descrizione");
		lblDesc.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(lblDesc, "cell 0 4,alignx right,aligny center");

		JScrollPane scrollDesc = new JScrollPane();
		newCdPanel.add(scrollDesc, "cell 1 4,grow");

		txtDesc = new JTextArea();
		txtDesc.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtDesc.addKeyListener(new KeyListener() {


			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_TAB)
				{
					e.consume();
					KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
				}

			}
		});
		scrollDesc.setViewportView(txtDesc);

		JLabel lblGen = new JLabel("Genere");
		lblGen.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(lblGen, "cell 0 5,alignx right,aligny center");

		cbGen = new JComboBox();
		cbGen.setFont(new Font("Dialog", Font.PLAIN, 14));
		newCdPanel.add(cbGen, "cell 1 5,alignx center,aligny center");

		JLabel lblMus = new JLabel("Musicista/band titolare");
		lblMus.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(lblMus, "cell 0 6,alignx right,aligny center");

		cbMus = new JComboBox();
		cbMus.setFont(new Font("Dialog", Font.PLAIN, 14));
		newCdPanel.add(cbMus, "cell 1 6,alignx center,aligny center");

		JLabel lblCollaboratore = new JLabel("Gestione musicisti");
		lblCollaboratore.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(lblCollaboratore, "cell 0 7,alignx right,aligny center");

		JButton btnAggiungiCollaboratore = new JButton("Aggiungi/Rimuovi");
		btnAggiungiCollaboratore.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(btnAggiungiCollaboratore, "cell 1 7");
		btnAggiungiCollaboratore.addActionListener(new btnShowCollaboratorListListener(this)); 	//apro nuovo frame

		JLabel lblListaMusicisti = new JLabel("Lista musicisti");
		lblListaMusicisti.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(lblListaMusicisti, "cell 0 8,alignx right,aligny center");

		//pannello visualizzazione musicisti partecipanti
		JScrollPane scrollPartecipantList = new JScrollPane();
		newCdPanel.add(scrollPartecipantList, "cell 1 8,grow");

		listModel2=new DefaultListModel<String>();	
		listPartecipantList = new JList(listModel2);
		listPartecipantList.setFont(new Font("Dialog", Font.PLAIN, 14));
		listPartecipantList.setModel(listModel2);
		scrollPartecipantList.setViewportView(listPartecipantList);

		JLabel lblAmo = new JLabel("Quantità");
		lblAmo.setFont(new Font("Dialog", Font.BOLD, 14));
		newCdPanel.add(lblAmo, "cell 0 9,alignx right,aligny center");

		txtAmo = new JTextField();
		txtAmo.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtAmo.setColumns(10);
		newCdPanel.add(txtAmo, "cell 1 9,alignx center,aligny center");

		JButton btnUpdateCd = new JButton("Modifica prodotto");
		btnUpdateCd.setFont(new Font("Dialog", Font.BOLD, 18));
		btnUpdateCd.addActionListener(new btnUpdateCdListener(this));
		newCdPanel.add(btnUpdateCd, "flowx,cell 1 10,alignx left,growy");
		insertPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		insertPanel.add(newCdPanel, "cell 0 0,grow");
		getContentPane().add(insertPanel);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtTitle, btnAddTrack, txtPrice, txtDesc, cbGen, cbMus, btnAggiungiCollaboratore, txtAmo, btnUpdateCd}));
	}

	private void loadPanel(int index)
	{
		idCd=((areaRiservataWnd)caller).getSelectedCdId(index);
		Cd selectedCd=new Cd();
		selectedCd.getById(idCd);
		setCdTitle(selectedCd.getTitolo());
		loadTrackList(selectedCd.getBrani());
		setDescription(selectedCd.getDescrizione());
		loadMusicianList(selectedCd.getPartecipanti());
		setPrice(selectedCd.getPrezzo());
		setAmount(selectedCd.getPezziMagazzino());
		setGender(selectedCd.getGenere());
		setMusician(selectedCd.getTitolare());
	}

	public void saveUpdate()
	{
		CdController cCd = new CdController(this);
		try {
			if(cCd.update()) {
				JOptionPane.showMessageDialog(this, "Cd modificato correttamente","Info!",JOptionPane.INFORMATION_MESSAGE);
				//Fine update con chiusura della finestra
				((areaRiservataWnd)caller).showWarehouse();
				close();
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Cd non modificato!","Info!",JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Errore!",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void close()
	{
		caller.setEnabled(true);
		caller.setAlwaysOnTop(true);
		caller.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// *********************************************************************************************

	//								FUNZIONI DI SUPPORTO

	// *********************************************************************************************




	// *********************************************************************************************

	//								METODI SET

	// *********************************************************************************************

	private void setCdTitle(String title)
	{
		txtTitle.setText(title);
	}

	private void loadTrackList(ArrayList<Brano> trackList)
	{
		listModel.clear();
		for(Brano b:trackList)
		{
			listModel.addElement(b.getNome());
		}
	}

	public void setTrackList(ArrayList<String> trackList)
	{
		listModel.clear();
		for(String b:trackList)
		{
			listModel.addElement(b);
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
		for(Musicista m:mus.getAll())
		{
			cbMus.addItem(m.getNomeArte());
		}
		cbMus.setSelectedItem(mus.getNomeArte());
	}

	private void loadMusicianList(ArrayList<Musicista> musList)
	{
		listModel2.clear();
		for(Musicista m:musList)
		{
			listModel2.addElement(m.getNomeArte());
		}
	}

	private void setAmount(Integer amount)
	{
		txtAmo.setText(amount.toString());
	}

	public void setPartecipantList(ArrayList<String> partecipantList)	
	{
		listModel2.clear();
		for(String partecipant:partecipantList)
		{
			listModel2.addElement(partecipant);
		}
	}


	// *********************************************************************************************

	//								RECUPERO INFORMAZIONI DA FORM

	// *********************************************************************************************

	public int getCdId()
	{
		return idCd;
	}

	public String getCdTitle()
	{
		return txtTitle.getText();
	}

	public DefaultListModel<String> getTrackList()	//lista dei brani
	{
		return listModel;
	}

	public DefaultListModel<String> getPartecipantList()	//lista dei musicisti partecipanti
	{
		return listModel2;
	}

	public String getCdPrice()
	{
		return txtPrice.getText();
	}

	public String getCdDesc()
	{
		return txtDesc.getText();
	}

	public String getMusician()
	{
		return cbMus.getSelectedItem().toString();
	}

	public String getGender()
	{
		return cbGen.getSelectedItem().toString();
	}

	public String getAmount()
	{
		return txtAmo.getText();
	}
}
