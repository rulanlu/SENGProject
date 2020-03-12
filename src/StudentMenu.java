import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class StudentMenu extends JFrame {

	private JPanel contentPane;
	private JTextField searchBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMenu frame = new StudentMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel description = new JLabel("University of Saskatchewan scholarship system.");
		description.setBounds(6, 6, 438, 23);
		contentPane.add(description);
		
		JLabel lblNewLabel = new JLabel("Search for scholarships below.");
		lblNewLabel.setBounds(6, 28, 298, 16);
		contentPane.add(lblNewLabel);
		
		searchBar = new JTextField();
		searchBar.setBounds(6, 70, 246, 26);
		contentPane.add(searchBar);
		searchBar.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(258, 70, 85, 29);
		contentPane.add(searchButton);
	}
}
