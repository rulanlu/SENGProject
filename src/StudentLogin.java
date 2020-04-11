import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class for student login screen
 * Student will login to scholarship system with username and password
 * @author Rulan Lu, Matt Tamkee
 *
 */
public class StudentLogin extends JFrame {

	private JPanel contentPane;
	protected Object student;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentLogin frame = new StudentLogin();
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
	public StudentLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setTitle("University of Saskatchewan");
		setLocationRelativeTo(null);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(33, 71, 73, 16);
		contentPane.add(usernameLabel);
		
		JTextField username;
		JTextField password;
		
		username = new JTextField();
		username.setBounds(112, 66, 274, 26);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(33, 135, 73, 16);
		contentPane.add(passwordLabel);
		
		password = new JPasswordField();
		password.setBounds(112, 130, 274, 26);
		contentPane.add(password);
		password.setColumns(10);
		
		//if student his login button
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//find student in database
					Scanner in = new Scanner(new File("src/student.txt"));
					boolean found = false;
					while (in.hasNextLine()) {
						String s = in.nextLine();
						String[] sArray = s.split(", ");
						//if username and password are correct, login successful
						//go to student menu
						if(username.getText().equals(sArray[0]) && password.getText().equals(sArray[1])) {
							found = true;
							StudentMenu student = new StudentMenu(username.getText());
							student.setVisible(true);
							setVisible(false);
							JOptionPane.showMessageDialog(null, "Login successful", null, JOptionPane.PLAIN_MESSAGE);
						}
						//otherwise login failed, must try again
						else if((password.getText().equals("") || username.getText().equals(""))) {
							JOptionPane.showMessageDialog(null, "Please enter your username and password");
						} 
					}
					if (found == false) {
						JOptionPane.showMessageDialog(null, "Incorrect username and/or password");
					}
					in.close();
				} catch (FileNotFoundException m) {
				
					JOptionPane.showMessageDialog(null,"User Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		loginButton.setBounds(160, 215, 117, 29);
		contentPane.add(loginButton);
		
		JLabel welcomeLabel = new JLabel("Welcome Student!");
		welcomeLabel.setBounds(160, 24, 117, 16);
		contentPane.add(welcomeLabel);
	}
}

