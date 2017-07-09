package aggiungiBranoListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import viewAreaRiservata.aggiungiBranoWnd;

public class btnAddTrackListener implements ActionListener{
	
	JFrame caller;
	public btnAddTrackListener(JFrame caller) {
		// TODO Auto-generated constructor stub
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((aggiungiBranoWnd) caller).addTrack();
	}
	

}
