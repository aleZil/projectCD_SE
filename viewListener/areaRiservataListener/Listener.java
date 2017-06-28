//file di prova fatto da Andrea e Zil

package areaRiservataListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Listener implements ActionListener {
	public final static String ANNULLA = "annulla";

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String com = e.getActionCommand();
		
		switch(com){
			case ANNULLA: annulla(); break;
			default:break; 
		}
	}

	private void annulla() {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null, "provaaaaa");
		System.exit(0);
	}

}
