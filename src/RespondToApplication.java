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
		
		JLabel application = new JLabel("Application:");
		application.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		application.setBounds(6, 6, 101, 16);
		contentPane.add(application);
		
		JLabel suppLabel = new JLabel("Supplementary Information:");
		suppLabel.setBounds(6, 124, 195, 16);
		contentPane.add(suppLabel);
		
		JTextArea suppTextArea = new JTextArea();
		suppTextArea.setEditable(false);
		suppTextArea.setBounds(68, 156, 620, 240);
		contentPane.add(suppTextArea);
		
		JLabel studentNameLabel = new JLabel("Student: " + username);
		studentNameLabel.setBounds(6, 83, 237, 16);
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
		
		//determine if they are the best candidate
		//GPA is ranked first, then their position in the scholarship database
		try {
			Scanner x = new Scanner(new File("src/Applications.txt"));
			while (x.hasNextLine()) {
				String y = x.nextLine();
				String[] sArray = y.split(", ");
				//get GPA
				/**
				 * compares the scholarship name
				 * then should get all the GPA's 
				 * compare if username has the highest GPA**/
				if(scholarship.equals(sArray[1])) {
					
				}
				
			}
			x.close();
		} catch (FileNotFoundException ABC) {
			JOptionPane.showMessageDialog(null, "user not found", "Error", JOptionPane.ERROR_MESSAGE);
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
		
	}
}
