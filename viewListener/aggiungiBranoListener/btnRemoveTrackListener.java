package aggiungiBranoListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import viewAreaRiservata.aggiungiBranoWnd;

public class btnRemoveTrackListener implements ActionListener{
	
	JFrame caller;
	public btnRemoveTrackListener(JFrame caller) {
		// TODO Auto-generated constructor stub
		this.caller=caller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((aggiungiBranoWnd) caller).removeTrack();
	}
}
