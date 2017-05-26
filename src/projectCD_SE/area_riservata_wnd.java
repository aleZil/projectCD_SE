package projectCD_SE;

import javax.swing.JFrame;

import sun.awt.WindowClosingListener;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JPasswordField;
import java.sql.*;

public class area_riservata_wnd extends JFrame {
	
	JFrame main_wnd;
	private JTextField txt_usr;
	private JPasswordField txt_psswd;
	
	public area_riservata_wnd(JFrame caller) {
		setTitle("Login");
		
		
		main_wnd=caller;
		this.addWindowListener(new area_riservata_wnd_closer(main_wnd));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{194, 0, 60, 0};
		gridBagLayout.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		txt_usr = new JTextField();
		GridBagConstraints gbc_txt_usr = new GridBagConstraints();
		gbc_txt_usr.insets = new Insets(0, 0, 5, 5);
		gbc_txt_usr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_usr.gridx = 1;
		gbc_txt_usr.gridy = 2;
		getContentPane().add(txt_usr, gbc_txt_usr);
		txt_usr.setColumns(10);
		
		txt_psswd = new JPasswordField();
		GridBagConstraints gbc_txt_psswd = new GridBagConstraints();
		gbc_txt_psswd.insets = new Insets(0, 0, 5, 5);
		gbc_txt_psswd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_psswd.gridx = 1;
		gbc_txt_psswd.gridy = 5;
		getContentPane().add(txt_psswd, gbc_txt_psswd);
		JButton btn_login = new JButton("Login");
		btn_login.setBounds(149, 135, 117, 25);
		GridBagConstraints gbc_btn_login = new GridBagConstraints();
		gbc_btn_login.insets = new Insets(0, 0, 0, 5);
		gbc_btn_login.anchor = GridBagConstraints.NORTHWEST;
		gbc_btn_login.gridx = 1;
		gbc_btn_login.gridy = 8;
		getContentPane().add(btn_login, gbc_btn_login);
		this.setVisible(true);
	}
	
	
	
	
	

}
