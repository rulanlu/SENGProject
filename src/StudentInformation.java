import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Page for students to view their own information
 * Students may see their username (name), GPA, and faculty. They may also make changes to this information.
 * @author Rulan Lu
 *
 */
public class StudentInformation extends JFrame {

	private JPanel contentPane;
	static String username;
	static String password;
	static String GPA;
	static String faculty;
	static String oldLine;
	static String newLine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInformation frame = new StudentInformation(username);
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
	public StudentInformation(String suser) {
		username = suser;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//get student information from the database
		try {
			Scanner in = new Scanner(new File("src/student.txt"));
			while (in.hasNextLine()) {
				String s = in.nextLine();
				String[] sArray = s.split(", ");
				password = sArray[1];
				if (sArray.length == 2) {
					GPA = "";
					faculty = "";
				} else {
					GPA = sArray[2];
					faculty = sArray[3];
				}
			}
			in.close();
		} catch (FileNotFoundException m) {
			JOptionPane.showMessageDialog(null,"Student Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
	    }
		
		//shows all student information
		JLabel nameLabel = new JLabel("Student Name: " + username);
		nameLabel.setBounds(9, 6, 216, 16);
		contentPane.add(nameLabel);
		
		JLabel gpaLabel = new JLabel("GPA: " + GPA);
		gpaLabel.setBounds(9, 52, 146, 16);
		contentPane.add(gpaLabel);
		
		JLabel facLabel = new JLabel("Faculty: " + faculty);
		facLabel.setBounds(9, 98, 227, 16);
		contentPane.add(facLabel);
		
		//if student wants to edit their information, go to editing page
		JButton editName = new JButton("Edit");
		editName.setBounds(9, 128, 91, 29);
		editName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditInformation edit = new EditInformation(username, password, GPA, faculty);
				edit.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(editName);
		
		//takes student back to the student menu
		JButton backButton = new JButton("Back");
		backButton.setBounds(0, 247, 67, 25);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentMenu student = new StudentMenu();
				student.setUsername(username);
				student.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(backButton);
		
		JButton confirmButton = new JButton("Finish");
		confirmButton.setBounds(362, 247, 82, 25);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentMenu student = new StudentMenu();
				student.setUsername(username);
				student.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(confirmButton);
		
		setLocationRelativeTo(null);
		setTitle("University of Saskatchewan");
		
	}
}
