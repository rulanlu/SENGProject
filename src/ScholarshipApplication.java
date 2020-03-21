import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class for applying for scholarships
 * After a student clicks on a specific scholarship, they can view the details here
 * Then, they may choose to apply or cancel
 * @author Rulan Lu, Matt Tamkee
 */
public class ScholarshipApplication extends JFrame {

	private JPanel contentPane;
	static String name;
	static String date;
	static String ID;
	static String username;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScholarshipApplication frame = new ScholarshipApplication(name, date, ID, username);
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
	public ScholarshipApplication(String sname, String sdate, String sid, String suser) {
		//initialize variables
		name = sname;
		date = sdate;
		ID = sid;
		username = suser;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    JLabel scholarshipName = new JLabel("Name: " + name);
		scholarshipName.setBounds(6, 60, 438, 16);
		contentPane.add(scholarshipName);
		
	    JLabel lblNewLabel = new JLabel("Application Due Date: " + date);
		lblNewLabel.setBounds(6, 89, 438, 16);
		contentPane.add(lblNewLabel);
		
	    JLabel scholarshipID = new JLabel("ID: " + ID);
		scholarshipID.setBounds(6, 34, 438, 16);
		contentPane.add(scholarshipID);
		
		JLabel info = new JLabel("Information:");
		info.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		info.setBounds(6, 6, 101, 16);
		contentPane.add(info);
		
		JLabel scholarshipGPA = new JLabel("Minimum GPA: 3.7");
		scholarshipGPA.setBounds(6, 117, 438, 16);
		contentPane.add(scholarshipGPA);
		
		//if student chooses to apply
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//check to see if student has applied for that scholarship already
					String appendApplication = username + ", " + name;
					
					boolean exists = false;
					Scanner in = new Scanner(new File("src/Applications.txt"));
					while (in.hasNextLine()) {
						//ensures student can't apply for a scholarship they've already applied for
						if (in.nextLine().equals(appendApplication)) {
							setVisible(false);
							JOptionPane.showMessageDialog(null, "You've already applied for this scholarship", null, JOptionPane.PLAIN_MESSAGE);
							exists = true;
							break;
						} 
					}
					
					//if student has not applied for scholarship yet, add to database
					if (exists == false) {
						BufferedWriter new_writer = new BufferedWriter(new FileWriter("src/Applications.txt", true));
						
						new_writer.newLine();
						new_writer.write(appendApplication);
						new_writer.close();
						
						//close application window, gives success message
						setVisible(false);
						JOptionPane.showMessageDialog(null, "Application successful", null, JOptionPane.PLAIN_MESSAGE);
					}
				}
				catch (IOException m) {
					System.out.println("error" + m);
				}
				
			}
		});
		applyButton.setBounds(357, 243, 87, 29);
		contentPane.add(applyButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelButton.setBounds(6, 243, 106, 29);
		contentPane.add(cancelButton);
		setLocationRelativeTo(null);
	}
}
