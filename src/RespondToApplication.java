import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Allows coordinators to give students a response regarding their applications
 * Either accept or reject the application
 * Students will then be notified, and the application status will be updated in the database
 * @author Rulan
 *
 */
public class RespondToApplication extends JFrame {

	private JPanel contentPane;
	static String username;
	static String scholarship;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RespondToApplication frame = new RespondToApplication(username, scholarship);
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
	public RespondToApplication(String user, String scholar) {
		username = user;
		scholarship = scholar;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
	}

}
