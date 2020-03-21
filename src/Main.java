import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Main class
 * Initial screen for both coordinators and students
 * Allows selection of user type
 * @author Rulan Lu
 *
 */
public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * Sets up labels, buttons, etc.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("University of Saskatchewan");
		frame.setLocationRelativeTo(null);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{65, 117, 189, 0};
		gridBagLayout.rowHeights = new int[]{66, 16, 49, 29, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		frame.getContentPane().setLayout(gridBagLayout);
		
		//button if you are student, takes you to student login
		JButton studentButton = new JButton("Student");
		studentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentLogin student = new StudentLogin();
				student.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JLabel signInAs = new JLabel("Sign in as:");
		
		GridBagConstraints gbc_signInAs = new GridBagConstraints();
		gbc_signInAs.anchor = GridBagConstraints.NORTHWEST;
		gbc_signInAs.insets = new Insets(0, 0, 5, 0);
		gbc_signInAs.gridx = 2;
		gbc_signInAs.gridy = 1;
		
		frame.getContentPane().add(signInAs, gbc_signInAs);
		
		
		//button if you are coordinator, takes you to coordinator login
		JButton coordButton = new JButton("Coordinator");
		coordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CoordinatorLogin coordinator = new CoordinatorLogin();
				coordinator.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		GridBagConstraints gbc_coordButton = new GridBagConstraints();
		gbc_coordButton.anchor = GridBagConstraints.NORTH;
		gbc_coordButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_coordButton.insets = new Insets(0, 0, 0, 5);
		gbc_coordButton.gridx = 1;
		gbc_coordButton.gridy = 3;
		
		frame.getContentPane().add(coordButton, gbc_coordButton);
		GridBagConstraints gbc_studentButton = new GridBagConstraints();
		
		gbc_studentButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_studentButton.gridx = 2;
		gbc_studentButton.gridy = 3;
		
		frame.getContentPane().add(studentButton, gbc_studentButton);
	}
}
