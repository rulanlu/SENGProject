import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(33, 71, 73, 16);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(112, 66, 274, 26);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(33, 135, 73, 16);
		contentPane.add(lblNewLabel_1);
		
		password = new JTextField();
		password.setBounds(112, 130, 274, 26);
		contentPane.add(password);
		password.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(160, 215, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome Coordinator!");
		lblNewLabel_2.setBounds(160, 23, 145, 16);
		contentPane.add(lblNewLabel_2);
	}
}
