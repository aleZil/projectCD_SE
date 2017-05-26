package projectCD_SE;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class area_riservata_wnd extends JFrame {
	public area_riservata_wnd() {
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setBounds(100, 100, 450, 300);
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(149, 135, 117, 25);
		getContentPane().add(btnNewButton);
	}

}
