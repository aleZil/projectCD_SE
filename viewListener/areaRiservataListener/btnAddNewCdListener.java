package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Cd;
import viewAreaRiservata.areaRiservataWnd;

public class btnAddNewCdListener implements ActionListener,KeyListener{

	areaRiservataWnd caller;
	private Cd modelCd = new Cd();

	public btnAddNewCdListener(areaRiservataWnd caller)
	{
		this.caller=caller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		caller.AddNewCd();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		caller.AddNewCd();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
