package negozioListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//<<<<<<< HEAD:src/listeners/carrello_goback.java
//import projectCD_SE.area_riservata_wnd;
//import projectCD_SE.carrello_wnd;
//=======
import frame.areaRiservataWnd;
import frame.carrelloWnd;
//>>>>>>> 03c59dba1181ae0b72d8248dfb48f38f6f0b13a0:src/negozioListener/carrello_goback.java


public class carrello_goMain implements ActionListener {
	
	carrelloWnd prova_wnd;
	public carrello_goMain(carrelloWnd caller)
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
