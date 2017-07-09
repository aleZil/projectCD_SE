package aggiungiPartecipanteListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import viewAreaRiservata.aggiungiBranoWnd;
import viewAreaRiservata.aggiungiPartecipanteWnd;


public class btnAddPartecipantListener implements ActionListener,KeyListener{
	
	JFrame caller;
	public btnAddPartecipantListener(JFrame caller) {
		// TODO Auto-generated constructor stub
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((aggiungiPartecipanteWnd) caller).addPartecipant();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		((aggiungiPartecipanteWnd) caller).addPartecipant();
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
