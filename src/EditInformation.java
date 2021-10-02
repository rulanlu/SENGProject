import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * Allows student to edit their own information. 
 * This information is updated in the database as well.
 * 
 * @author Rulan Lu, Matt Tamkee 
 *
 */
public class EditInformation extends JFrame {

	private JPanel contentPane;
	static String username;
	static String password;
	static String GPA;
	static String faculty;
	static String awarded;
	private JTextField usernameField;
	private JTextField gpaField;
	private JComboBox facField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditInformation frame = new EditInformation(username, password, GPA, faculty, awarded);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. Sets up labels, buttons, etc.
	 */
	public EditInformation(String suser, String spass, String sgpa, String sfac, String sawarded) {
		// initialize variables
		username = suser;
		password = spass;
		GPA = sgpa;
		faculty = sfac;
		awarded = sawarded;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setTitle("University of Saskatchewan");
		contentPane.setLayout(null);

		JLabel nameLabel = new JLabel("Student Name: ");
		nameLabel.setBounds(9, 17, 101, 16);
		contentPane.add(nameLabel);

		JLabel gpaLabel = new JLabel("GPA: ");
		gpaLabel.setBounds(72, 59, 38, 16);
		contentPane.add(gpaLabel);

		JLabel facLabel = new JLabel("Faculty:");
		facLabel.setBounds(52, 103, 61, 16);
		contentPane.add(facLabel);

		// shows text fields with current student information
		usernameField = new JTextField(username);
		usernameField.setBounds(109, 12, 211, 26);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		// ensures proper formatting for GPA
		NumberFormat percentFormat = NumberFormat.getNumberInstance();
		percentFormat.setMinimumFractionDigits(2);

		gpaField = new JFormattedTextField(percentFormat);
		gpaField.setText(GPA);
		gpaField.setBounds(109, 54, 211, 26);
		contentPane.add(gpaField);
		gpaField.setColumns(10);

		// available faculties for student to choose from
		String faculties[] = { "Architecture", "Arts", "Business", "Education", "Engineering", "Kinesiology", "Law",
				"Medicine", "Nursing", "Science" };

		facField = new JComboBox(faculties);
		facField.setSelectedItem(faculty);
		facField.setBounds(109, 99, 211, 27);
		contentPane.add(facField);

		// if student chooses to change information
		JButton change = new JButton("Change");
		change.addActionListener(new ActionListener() {
			// check to see that fields are entered in correctly
			public void actionPerformed(ActionEvent e) {
				validGpa gpaChecker = new validGpa();
				if (usernameField.getText().equals("") || gpaField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields");
				}
				else if (!gpaChecker.isValidGpa(gpaField.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter a valid number for GPA (Between 0.00 and 4.30");
				}
				else {
					// open the database and change the information for the student
//					try {
						changeStudentInformation student = new changeStudentInformation();
						changeStudentEligibility sEligible = new changeStudentEligibility();
						String usernameContent = usernameField.getText();
						String gpaContent = gpaField.getText();
						String faculty = facField.getSelectedItem().toString();
						student.changeInformation(username, usernameContent, password, gpaContent, faculty, sawarded);
						sEligible.changeEligibility(username, usernameContent, gpaContent);
						

					// change variables and go back to student information
					username = usernameField.getText();
					GPA = gpaField.getText();
					faculty = facField.getSelectedItem().toString();
					StudentLoginInformation information = new StudentInformation(username);
					information.setVisible(true);
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Change successful", null, JOptionPane.PLAIN_MESSAGE);

				}
			}
		});
		change.setBounds(349, 243, 95, 29);
		contentPane.add(change);

		// back button, goes back to student information
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentLoginInformation information = new StudentInformation(username);
				information.setVisible(true);
				setVisible(false);
			}
		});
		back.setBounds(9, 243, 69, 29);
		contentPane.add(back);

	}
}
