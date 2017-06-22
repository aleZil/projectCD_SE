package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import projectCD_SE.carrello_wnd;

public class main_wnd_btn_carrello implements ActionListener {

	JFrame main_wnd;
	public main_wnd_btn_carrello(JFrame caller_wnd)
	{
		main_wnd=caller_wnd;
	}


	public void actionPerformed(ActionEvent e)
	{	
		//Disabilito la main_wnd
		main_wnd.setVisible(false);
		//Visualizzo la finestra di configurazione
		try
		{
			carrello_wnd wnd=new carrello_wnd(main_wnd);
		}
		catch (Exception exception)
		{
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}

	}
}
