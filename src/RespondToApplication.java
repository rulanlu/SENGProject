import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Allows coordinators to give students a response regarding their applications
 * Either accept or reject the application
 * Students will then be notified, and the application status will be updated in the database
 * @author Rulan Lu
 *
 */
public class RespondToApplication extends JFrame {

	private JPanel contentPane;
	static String username;
	static String scholarship;
	static boolean supplementary = false;
	static String suppText;
	static String GPA;
	static double doubleGPA;
	static boolean topContender;
	static int studentSpot;

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
		
		JLabel scholarshipName = new JLabel("Scholarship: " + scholarship);
		scholarshipName.setBounds(6, 44, 438, 16);
		contentPane.add(scholarshipName);
		
		JLabel suppLabel = new JLabel("Supplementary Information:");
		suppLabel.setBounds(6, 128, 195, 16);
		contentPane.add(suppLabel);
		
		JTextArea suppTextArea = new JTextArea();
		suppTextArea.setEditable(false);
		suppTextArea.setBounds(68, 156, 620, 240);
		contentPane.add(suppTextArea);
		
		JLabel studentNameLabel = new JLabel("Student: " + username);
		studentNameLabel.setBounds(6, 72, 237, 16);
		contentPane.add(studentNameLabel);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewApplicationsCoordinator applications = new ViewApplicationsCoordinator();
				applications.setVisible(true);
				setVisible(false);
			}
		});
		backButton.setBounds(6, 449, 75, 29);
		contentPane.add(backButton);
		
		//find student's GPA and position in the list
		try {
			Scanner x = new Scanner(new File("src/Applications.txt"));
			int i = 0;
			while (x.hasNextLine()) {
				String y = x.nextLine();
				String[] sArray = y.split(", ");
				if(scholarship.equals(sArray[1])) {
					if (username.equals(sArray[0])) {
						GPA = sArray[2];
						doubleGPA = Double.parseDouble(sArray[2]);
						studentSpot = i;
					} 
				}
				i++;				
			}
			x.close();
		} catch (FileNotFoundException ABC) {
			JOptionPane.showMessageDialog(null, "user not found", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		//check student's application against other applications
		try {
			Scanner x = new Scanner(new File("src/Applications.txt"));
			int i = 0;
			topContender = true;
			while (x.hasNextLine()) {
				String y = x.nextLine();
				String[] sArray = y.split(", ");
				//for every applicant of this scholarship
				if(scholarship.equals(sArray[1])) {
					//skip over student
					if (username.equals(sArray[0])) {
						continue;
					//if another GPA is higher, student is not the top contender
					} else if (Double.parseDouble(sArray[2]) > doubleGPA) {
						topContender = false;
						break;
					//if there is a student with the same GPA but applied earlier, student is not the top contender
					} else if ((Double.parseDouble(sArray[2]) == doubleGPA) && (i < studentSpot)) {
						topContender = false;
						break;
					}
				}
				i++;				
			}
			x.close();
		} catch (FileNotFoundException ABC) {
			JOptionPane.showMessageDialog(null, "user not found", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		JLabel topLabel = new JLabel();
		topLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		topLabel.setBounds(6, 6, 444, 16);
		contentPane.add(topLabel);
		
		//if the student is the best candidate, show this to the coordinator
		if (topContender) {
			topLabel.setText("This student is the best candidate for this scholarship");
			JButton awardButton = new JButton("Award Scholarship");
			awardButton.setBounds(578, 449, 166, 29);
			contentPane.add(awardButton);
		} else {
			topLabel.setText("This student is not the best candidate for this scholarship");
		}
		
		//determine what to display in suppTextArea
		try {
			//find scholarship in database
			Scanner in = new Scanner(new File("src/Scholarships.txt"));
			while (in.hasNextLine()) {
				String s = in.nextLine();
				String[] sArray = s.split(", ");
				if(scholarship.equals(sArray[1])) {
					//check to see if supplementary or not
					if (sArray[6].equals("Yes")) {
						supplementary = true;
					}
				}
			}
			in.close();
		} catch (FileNotFoundException m) {
			JOptionPane.showMessageDialog(null,"Scholarship Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		//if scholarship needs supplementary information, display that information
		if (supplementary) {
			try {
				//find scholarship in database
				Scanner in = new Scanner(new File("src/" + scholarship + ".txt"));
				while (in.hasNextLine()) {
					String s = in.nextLine();
					String[] sArray = s.split(", ");
					if(username.equals(sArray[0])) {
						suppText = sArray[1];
						suppTextArea.setText(suppText);
						supplementary = false;
					}
				}
				in.close();
			} catch (FileNotFoundException m) {
				JOptionPane.showMessageDialog(null,"Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		//otherwise do not display anything for supplimentary
		} else {
			suppTextArea.setText("N/A");
		}
		
		JLabel studentGpaLabel = new JLabel("GPA: " + GPA);
		studentGpaLabel.setBounds(6, 100, 61, 16);
		contentPane.add(studentGpaLabel);
		
	}
}
