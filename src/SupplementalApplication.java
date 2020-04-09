import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

/**
 * Application form that allows students to enter in additional information about themselves
 * Such as a personal response, a resume, transcript, etc.
 * @author Matt Tamkee, Rulan Lu
 *
 */
public class SupplementalApplication extends JFrame {
	
	private JPanel contentPane; 
	private JFrame frame;
	private JTextField scholarshipName;
	protected Object usernameText;
	static String name;
	static String ID;
	static String username;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplementalApplication frame = new SupplementalApplication(name, ID, username);
					
					frame.setVisible(true);
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public SupplementalApplication(String sname, String sid, String suser) {
		
		name = sname;
		ID = sid;
		username = suser;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Supplemental Application Form");
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel scholarshipnameLabel = new JLabel("Scholarship Name:  " + name);
		scholarshipnameLabel.setBounds(46, 71, 497, 16);
		contentPane.add(scholarshipnameLabel);
		
		//text area for student information
		JTextArea supplemental = new JTextArea();
		supplemental.setBounds(178, 135, 505, 281);
		contentPane.add(supplemental);
		
		JLabel supplementalApp = new JLabel("Application Form: ");
		supplementalApp.setBounds(50, 135, 174, 16);
		contentPane.add(supplementalApp);
		
		JButton updateTable = new JButton("Upload");
		updateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(supplemental.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all boxes.");	
				}
				else {
					try {
						String s = "src/" + name + ".txt";
						//make a specific text file for applications for the specific scholarship
						File scholarshipTxt = new File(s);
						scholarshipTxt.createNewFile(); // if file already exists, do nothing 
						FileOutputStream oFile = new FileOutputStream(scholarshipTxt, false);
						
						//append application to scholarship text file
						String appendApplication = username + ", " + supplemental.getText();
						BufferedWriter writerOne = new BufferedWriter(new FileWriter(s, true)); 
						
						writerOne.newLine();
						writerOne.write(appendApplication);
						writerOne.close();
		
						//append application to applications
						appendApplication = username + ", " + name;
						BufferedWriter writerTwo = new BufferedWriter(new FileWriter("src/Applications.txt", true));
						
						writerTwo.newLine();
						writerTwo.write(appendApplication);
						writerTwo.close();
						//close application window, gives success message
						setVisible(false);
						JOptionPane.showMessageDialog(null, "Application successful", null, JOptionPane.PLAIN_MESSAGE);
						
					}
					catch (Exception ab) {
						System.out.println("error" + ab);									
					}
				}
			}
		});
		updateTable.setBounds(633, 449, 117, 29);
		contentPane.add(updateTable);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		backButton.setBounds(0, 449, 75, 29);
		contentPane.add(backButton);
		
	}
}
