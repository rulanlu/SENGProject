import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentMenu extends JFrame {

	private JPanel contentPane;

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
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel description = new JLabel("University of Saskatchewan scholarship system.");
		description.setBounds(6, 6, 438, 16);
		contentPane.add(description);
		
		JLabel description2 = new JLabel("Search for scholarships below using key words,");
		description2.setBounds(6, 23, 337, 16);
		contentPane.add(description2);
		
		JButton searchButton = new JButton("Search for Scholarships");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search search = new Search();
				search.setVisible(true);
				contentPane.setVisible(false);
				setVisible(false);
			}
		});
		searchButton.setBounds(6, 85, 182, 29);
		contentPane.add(searchButton);
		
		JLabel lblNewLabel = new JLabel("or view your application history.");
		lblNewLabel.setBounds(6, 41, 213, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Application History");
		btnNewButton.setBounds(6, 122, 160, 29);
		contentPane.add(btnNewButton);
	}
}
