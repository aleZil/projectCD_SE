package viewAreaRiservata;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import utility.dataValidator;

import javax.swing.JTextField;

import areaRiservataListener.closerWndListener;
import areaRiservataListener.btnAddTrackListener;
import areaRiservataListener.returnNegozioListener;
import areaRiservataListener.btnBackListener;
import areaRiservataListener.btnAddNewCdListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.HashSet;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class aggiungiBranoWnd extends JFrame{
	JFrame main_wnd;
	private static CardLayout aggiungiBranoLayout=new CardLayout();	//contenitore di pannelli-layout
	private JPanel panel_container=new JPanel();
	private JPanel aggiungiBrano_panel=new JPanel();
	
	private JTextField txtNumeroBrano;
	private JTextField txtTitoloBrano;
	
	public aggiungiBranoWnd(JFrame caller) throws ParseException {
		
		setResizable(true);
		
		//Tengo il riferimento al main form
		main_wnd=caller;
		panel_container.setLayout(aggiungiBranoLayout);	//card_layout (contenitore di tutti i panel, ogni panel è un Mig layout)
		
		this.addWindowListener(new closerWndListener(main_wnd));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//chiude il frame
		
		//Dimensioni finestra	
		//this.setSize(ScreenWidth, ScreenHeight);
		setBounds(main_wnd.getLocation().x,main_wnd.getLocation().y, 629, 323);

		//Toolkit tk = Toolkit.getDefaultToolkit();
		setLocation(0,0);
		//setSize(tk.getScreenSize());
		setUndecorated(false);
		
		createAggiungiBranoPanel();
		
		//Aggiungo il container che contiene tutti i panel
		getContentPane().add(panel_container);	//il ContentPane ha di default il BorderLayout
		aggiungiBranoLayout.show(panel_container, "intro");
		this.setVisible(true);
		
	}
	
	
	private void createAggiungiBranoPanel() {
		
		panel_container.add(aggiungiBrano_panel, "intro");
		aggiungiBrano_panel.setLayout(new MigLayout("", "[130,grow,left][130,grow,left]", "[][][]"));
		
		JLabel lblNumeroBrano = new JLabel("Numero brano:");
		aggiungiBrano_panel.add(lblNumeroBrano, "cell 0 0,alignx left");
		
		txtNumeroBrano = new JTextField();
		aggiungiBrano_panel.add(txtNumeroBrano, "cell 1 0,alignx left");
		txtNumeroBrano.setColumns(10);
		
		JLabel lblTitoloBrano = new JLabel("Titolo brano:");
		aggiungiBrano_panel.add(lblTitoloBrano, "cell 0 1,alignx left");
		
		txtTitoloBrano = new JTextField();
		aggiungiBrano_panel.add(txtTitoloBrano, "cell 1 1,alignx left");
		txtTitoloBrano.setColumns(10);
		
		JButton btnAggiungi = new JButton("Aggiungi");
		aggiungiBrano_panel.add(btnAggiungi, "cell 0 2");
		
		
	}
/*
	public void showMain()
	{
		main_wnd.setVisible(true);
		this.setVisible(false);
	}
	*/
	
	
	
	
}


