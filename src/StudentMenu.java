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
import java.awt.Font;

/**
 * Class for student menu
 * Students may search/apply for scholarships, view apllication history
 * and later on will be able to edit their information
 * @author Rulan Lu
 *
 */
public class StudentMenu extends JFrame {

	private JPanel contentPane;
	private String username;
	private String password;
	
	//getters and setters
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}

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
	 * Sets up labels, buttons, etc.
	 */
	public StudentMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("University of Saskatchewan");
		
		JLabel description = new JLabel("University of Saskatchewan scholarship system.");
		description.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		description.setBounds(189, 6, 438, 16);
		contentPane.add(description);
		
		JLabel description2 = new JLabel("Search for scholarships below or view your application history.");
		description2.setBounds(177, 57, 515, 16);
		contentPane.add(description2);
		
		//if student wants to search, go to search page
		JButton searchButton = new JButton("Search for Scholarships");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search search = new Search();
				search.setUsername(username);
				search.setVisible(true);
				setVisible(false);
			}
		});
		searchButton.setBounds(281, 122, 182, 29);
		contentPane.add(searchButton);
		
		//button to go to application history
		//student may view their past applications
		JButton historyButton = new JButton("Application History");
		historyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationHistory history = new ApplicationHistory(username);
				history.setVisible(true);
				setVisible(false);
			}
		});
		historyButton.setBounds(292, 181, 160, 29);
		contentPane.add(historyButton);
	}
}
