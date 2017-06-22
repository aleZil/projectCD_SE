package negozioListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.areaRiservataWnd;
import view.carrelloWnd;


public class carrello_goback implements ActionListener {
	
	carrelloWnd prova_wnd;
	public carrello_goback(carrelloWnd caller)
	{
		prova_wnd=caller;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//JOptionPane.showMessageDialog(prova_wnd, "SOS");
		//System.out.println("siamo qui");
		//JOptionPane.showMessageDialog(prova_wnd, "Volevi tornare indietro, stronzo...invece no!");
		prova_wnd.showMain("");
		
	}

}
