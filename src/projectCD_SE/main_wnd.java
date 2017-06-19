package projectCD_SE;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import listeners.main_wnd_btn_area_riservata;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class main_wnd extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_wnd frame = new main_wnd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**b
	 * Create the frame.
	 */
	
	public main_wnd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btn_area_riservata = new JButton("Area Riservata");
		btn_area_riservata.setBounds(229, 12, 207, 25);
		btn_area_riservata.addActionListener(new main_wnd_btn_area_riservata(this));
		contentPane.add(btn_area_riservata);
		JButton btnZilButton = new JButton("Zil button");
		btnZilButton.setBounds(28, 101, 117, 25);
		contentPane.add(btnZilButton);
	}

}
