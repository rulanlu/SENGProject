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
import java.io.FileReader;
import java.io.IOException;

//login screen for coordinators
//coordinator will login to scholarship system with username and password
public class CoordinatorLogin extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoordinatorLogin frame = new CoordinatorLogin();
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
	public CoordinatorLogin() {
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
		
		
		//enter in username
		username = new JTextField();
		username.setBounds(112, 66, 274, 26);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(33, 135, 73, 16);
		contentPane.add(passwordLabel);
		
		//enter in password
		password = new JPasswordField();
		password.setBounds(112, 130, 274, 26);
		contentPane.add(password);
		password.setColumns(10);
		
		//if coordinator clicks login
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//check to see if coordinator is in database
				try {
					Scanner in = new Scanner(new File("src/coordinator.txt"));
					while (in.hasNextLine()) {
						String s = in.nextLine();
						String[] sArray = s.split(", ");
						//if username and password match, successful login
						if(username.getText().equals(sArray[0]) && password.getText().equals(sArray[1])) {
							CoordinatorMenu coordinator = new CoordinatorMenu();
							coordinator.setVisible(true);
							contentPane.setVisible(false);
							setVisible(false);
						    JOptionPane.showMessageDialog(null, "Login successful", null, JOptionPane.PLAIN_MESSAGE);
						}
						//otherwise, failed login, must try again
						else if((password.getText().equals("") || username.getText().equals(""))) {
							JOptionPane.showMessageDialog(null, "Please enter your username and password");
						}
					}
					in.close();
				} catch (FileNotFoundException m) {
				
					JOptionPane.showMessageDialog(null,"User Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		loginButton.setBounds(160, 215, 117, 29);
		contentPane.add(loginButton);
		
		JLabel welcomeLabel = new JLabel("Welcome Coordinator!");
		welcomeLabel.setBounds(160, 23, 145, 16);
		contentPane.add(welcomeLabel);
	}
}
