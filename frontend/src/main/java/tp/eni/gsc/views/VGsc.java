package main.java.tp.eni.gsc.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class VGsc {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VGsc window = new VGsc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VGsc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTableauDeBord = new JLabel("TABLEAU DE BORD");
		lblTableauDeBord.setFont(new Font("FLOR PERSONAL USE", Font.BOLD, 29));
		lblTableauDeBord.setBounds(270, 0, 276, 55);
		frame.getContentPane().add(lblTableauDeBord);
	}
}
