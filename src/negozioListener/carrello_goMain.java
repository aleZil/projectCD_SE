package negozioListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import viewAreaRiservata.areaRiservataWnd;
import viewNegozio.carrelloWnd;


public class carrello_goMain implements ActionListener {
	
	carrelloWnd prova_wnd;
	
	public carrello_goMain(carrelloWnd caller)
	{
		prova_wnd=caller;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//JOptionPane.showMessageDialog(prova_wnd, "prova");
		prova_wnd.showMain();
		
	}

}
