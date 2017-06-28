package viewNegozio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import areaRiservataListener.Listener;
import negozioListener.main_wnd_btn_area_riservata;
import negozioListener.main_wnd_btn_carrello;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class negozioWnd extends JFrame {
	
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					negozioWnd negozio = new negozioWnd();
					negozio.setVisible(true);
					//frame.setExtendedState(Frame.MAXIMIZED_BOTH);		//fullscreen
					/*
					//Altro modo per fare fullscreen:
					int ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
					int ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
					frame.setSize(ScreenWidth, ScreenHeight);
					*/
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the frame.
	 */
	
	public negozioWnd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(getLocation().x,getLocation().y, 1000, 700);	//misura uguale a tutte le Wnd principali
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	// null = Flaw layout
		
		JButton btnAreaRIservata = new JButton("Area Riservata");
		btnAreaRIservata.setBounds(700, 600, 200, 25);
		btnAreaRIservata.addActionListener(new main_wnd_btn_area_riservata(this));
		contentPane.add(btnAreaRIservata);
		
		JButton btnCarrello = new JButton("Carrello");
		btnCarrello.setBounds(700, 25, 200, 25);
		btnCarrello.addActionListener(new main_wnd_btn_carrello(this));
		contentPane.add(btnCarrello);
		
	}

}