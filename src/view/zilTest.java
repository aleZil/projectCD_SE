package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;

public class zilTest extends JFrame {

	public zilTest()
	{
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setBounds(100, 100, 450, 300);
		JTextPane textPane = new JTextPane();
		getContentPane().add(textPane);
		this.setVisible(true);
		
	}
}
