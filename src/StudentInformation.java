import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentInformation extends JFrame {

	private JPanel contentPane;
	static String username;
	static String password;
	static String GPA;
	static String faculty;

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
	 */
	public StudentInformation(String suser) {
		username = suser;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JLabel nameLabel = new JLabel("Student Name: " + username);
		nameLabel.setBounds(79, 6, 216, 16);
		contentPane.add(nameLabel);
		
		JLabel gpaLabel = new JLabel("GPA: " + GPA);
		gpaLabel.setBounds(79, 52, 146, 16);
		contentPane.add(gpaLabel);
		
		JLabel facLabel = new JLabel("Faculty: " + faculty);
		facLabel.setBounds(79, 98, 227, 16);
		contentPane.add(facLabel);
		
		JButton editName = new JButton("Edit");
		editName.setBounds(0, 1, 67, 29);
		editName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		contentPane.add(editName);
		
		JButton editGPA = new JButton("Edit");
		editGPA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		editGPA.setBounds(0, 47, 67, 29);
		contentPane.add(editGPA);
		
		JButton editFaculty = new JButton("Edit");
		editFaculty.setBounds(0, 93, 67, 29);
		editFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		contentPane.add(editFaculty);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(6, 443, 75, 29);
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
		confirmButton.setBounds(662, 443, 82, 29);
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
