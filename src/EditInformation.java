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

/**
 * Allows student to edit their own information
 * This information is updated in the database as well
 * @author Rulan Lu
 *
 */
public class EditInformation extends JFrame {

	private JPanel contentPane;
	static String username;
	static String password;
	static String GPA;
	static String faculty;
	private JTextField nameField;
	private JTextField gpaField;
	private JTextField facField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditInformation frame = new EditInformation(username, password, GPA, faculty);
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
	public EditInformation(String suser, String spass, String sgpa, String sfac) {
		username = suser;
		password = spass;
		GPA = sgpa;
		faculty = sfac;
		
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
		
		//shows text fields with current student information
		nameField = new JTextField(username);
		nameField.setBounds(109, 12, 211, 26);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		//ensures proper formatting for GPA
        NumberFormat percentFormat = NumberFormat.getNumberInstance();
        percentFormat.setMinimumFractionDigits(2);
        
		gpaField = new JFormattedTextField(percentFormat);
		gpaField.setText(GPA);
		gpaField.setBounds(109, 54, 211, 26);
		contentPane.add(gpaField);
		gpaField.setColumns(10);
		
		facField = new JTextField(faculty);
		facField.setBounds(109, 98, 211, 26);
		contentPane.add(facField);
		facField.setColumns(10);
		
		//if student chooses to change information
		JButton change = new JButton("Change");
		change.addActionListener(new ActionListener() {
			//check to see that fields are entered in correctly
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().equals("") || gpaField.getText().equals("") || facField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields");
				} else if ((Double.parseDouble(gpaField.getText()) > 4.30) || (Double.parseDouble(gpaField.getText()) < 0.00)) {
					JOptionPane.showMessageDialog(null, "Please enter a valid number for GPA (Between 0.00 and 4.30");
				} else {
					//open the database and change the information for the student
					try {
			            FileInputStream fstream = new FileInputStream("src/student.txt");
			            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			            String line;
			            StringBuilder fileContent = new StringBuilder();
						
						while ((line = br.readLine()) != null) {
			                String sArray[] = line.split(", ");
			                if (sArray.length > 0) {
			                	//if line containing student is found, edit it
			                    if (sArray[0].equals(username)) {
			                        String newLine = nameField.getText() + ", " + password + ", " + gpaField.getText() + ", " + facField.getText();
			                        fileContent.append(newLine);
			                        fileContent.append("\n");
			                    //otherwise keep it as it is
			                    } else {
			                        fileContent.append(line);
			                        fileContent.append("\n");
			                    }
			                }
			            }
	
				        FileWriter fstreamWrite = new FileWriter("src/student.txt");
			            BufferedWriter out = new BufferedWriter(fstreamWrite);
			            out.write(fileContent.toString());
			            out.close();
			            
			            //change student information in application database as well
			            try {
				            FileInputStream fstream2 = new FileInputStream("src/Applications.txt");
				            BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream2));
				            String line2;
				            StringBuilder fileContent2 = new StringBuilder();
							
							while ((line2 = br2.readLine()) != null) {
				                String sArray2[] = line2.split(", ");
				                if (sArray2.length > 0) {
				                	//if line containing student is found, edit it
				                    if (sArray2[0].equals(username)) {
				                        String newLine2 = nameField.getText() + ", " + sArray2[1];
				                        fileContent2.append(newLine2);
				                        fileContent2.append("\n");
				                    //otherwise keep it as it is
				                    } else {
				                        fileContent2.append(line2);
				                        fileContent2.append("\n");
				                    }
				                }
				            }
	
					        FileWriter fstreamWrite2 = new FileWriter("src/Applications.txt");
				            BufferedWriter out2 = new BufferedWriter(fstreamWrite2);
				            out2.write(fileContent2.toString());
				            out2.close();
	
					    } catch (Exception ex) {
					        System.out.println("Problem reading file.");
					    }
	
				    } catch (Exception ex) {
				        System.out.println("Problem reading file.");
				    }
					//change variables and go back to student information
	                username = nameField.getText();
	                GPA = gpaField.getText();
	                faculty = facField.getText();
					StudentInformation information = new StudentInformation(username);
					information.setVisible(true);
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Change successful", null, JOptionPane.PLAIN_MESSAGE);
					
					}
			}
		});
		change.setBounds(349, 243, 95, 29);
		contentPane.add(change);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentInformation information = new StudentInformation(username);
				information.setVisible(true);
				setVisible(false);
			}
		});
		back.setBounds(9, 243, 69, 29);
		contentPane.add(back);
		
	}
}
