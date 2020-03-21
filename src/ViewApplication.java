import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Class that allows student to view a specific previous application
 * Student may choose to withdraw the application
 * @author Rulan
 *
 */
public class ViewApplication extends JFrame {

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
					ViewApplication frame = new ViewApplication(username, name);
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
	public ViewApplication(String suser, String sname) {
		//initialize variables
		name = sname;
		username = suser;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel scholarshipName = new JLabel("Name: " + name);
		scholarshipName.setBounds(6, 60, 438, 16);
		contentPane.add(scholarshipName);
		
	    JLabel lblNewLabel = new JLabel("Application Due Date: " + date);
		lblNewLabel.setBounds(6, 89, 438, 16);
		contentPane.add(lblNewLabel);
		
		JLabel info = new JLabel("Information:");
		info.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		info.setBounds(6, 6, 101, 16);
		contentPane.add(info);
		
		JLabel scholarshipGPA = new JLabel("Minimum GPA: 3.7");
		scholarshipGPA.setBounds(6, 117, 438, 16);
		contentPane.add(scholarshipGPA);
		
		//if student chooses to withdraw application
		JButton applyButton = new JButton("Withdraw");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//remove student application from database
					File applicationsFile = new File("src/Applications.txt");
					File tempFile = new File("src/temp.txt");

					BufferedReader reader = new BufferedReader(new FileReader(applicationsFile));
					BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

					String lineToRemove = username + ", " + name;
					String currentLine;

					while((currentLine = reader.readLine()) != null) {
					    String trimmedLine = currentLine.trim();
					    if(!trimmedLine.equals(lineToRemove)) {
					    	writer.write(trimmedLine);
					    	writer.newLine();
					    }
					}
					writer.close(); 
					reader.close(); 
					boolean delete = applicationsFile.delete();
					boolean successful = tempFile.renameTo(applicationsFile);
					
					//close application window, gives success message
					ApplicationHistory history = new ApplicationHistory(username);
					history.setVisible(true);
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Withdraw successful", null, JOptionPane.PLAIN_MESSAGE);
				}
				catch (IOException m) {
					System.out.println("error" + m);
				}
				
			}
		});
		applyButton.setBounds(657, 449, 87, 29);
		contentPane.add(applyButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelButton.setBounds(0, 449, 106, 29);
		contentPane.add(cancelButton);
	}

}
