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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;


public class aggiungiStrumentoWnd extends JFrame{
	JFrame main_wnd;
	private static CardLayout aggiungiStrumentoLayout=new CardLayout();	//contenitore di pannelli-layout
	private JPanel panel_container=new JPanel();
	private JPanel aggiungiStrumento_panel=new JPanel();
	private final JButton btnAggiungi = new JButton("Aggiungi");
	
	
	
	public aggiungiStrumentoWnd(JFrame caller) throws ParseException {
		
		setResizable(true);
		
		//Tengo il riferimento al main form
		main_wnd=caller;
		panel_container.setLayout(aggiungiStrumentoLayout);	//card_layout (contenitore di tutti i panel, ogni panel è un Mig layout)
		
		this.addWindowListener(new closerWndListener(main_wnd));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//chiude il frame
		
		//Dimensioni finestra	
		//this.setSize(ScreenWidth, ScreenHeight);
		setBounds(main_wnd.getLocation().x,main_wnd.getLocation().y, 629, 323);

		//Toolkit tk = Toolkit.getDefaultToolkit();
		setLocation(0,0);
		//setSize(tk.getScreenSize());
		setUndecorated(false);
		
		createAggiungiStrumentoPanel();
		
		//Aggiungo il container che contiene tutti i panel
		getContentPane().add(panel_container);	//il ContentPane ha di default il BorderLayout
		aggiungiStrumentoLayout.show(panel_container, "intro");
		this.setVisible(true);
		
	}
	
	
	private void createAggiungiStrumentoPanel() {
		
		panel_container.add(aggiungiStrumento_panel, "intro");
		aggiungiStrumento_panel.setLayout(new MigLayout("", "[130,grow,left][130,grow,left]", "[][][]"));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Strumento 1");
		aggiungiStrumento_panel.add(chckbxNewCheckBox, "cell 0 0,aligny top");
		
		aggiungiStrumento_panel.add(btnAggiungi, "cell 0 2");
		
		
		
	}

	
}


