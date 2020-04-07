import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
public class SupplementalApplication extends JFrame {
	
	
	private JPanel contentPane; 
	private JFrame frame;
	private JTextField scholarshipName;
	private JTextField supplemental;
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
		
		JLabel scholarshipnameLabel = new JLabel("Scholarship Name:");
		scholarshipnameLabel.setBounds(92, 71, 128, 16);
		contentPane.add(scholarshipnameLabel);
		
		scholarshipName = new JTextField();
		scholarshipName.setBounds(232, 66, 384, 26);
		contentPane.add(scholarshipName);
		scholarshipName.setColumns(10);
		
		
		JLabel supplementalApp = new JLabel("Application Form:");
		supplementalApp.setBounds(50, 135, 174, 16);
		contentPane.add(supplementalApp);
		
		supplemental = new JTextField();
		supplemental.setBounds(232, 130, 384, 300);
		contentPane.add(supplemental);
		supplemental.setColumns(10);
		
		JButton updateTable = new JButton("upload");
		updateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(scholarshipName.getText().contentEquals("") || supplemental.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all boxes.");
					
				}
				else {
					try {
						//System.out.println(scholarshipName.getText());
						StudentLogin astudent = new StudentLogin();
						Scanner in = new Scanner(new File("src/Scholarships.txt"));
						while (in.hasNextLine()) {
							String s = in.nextLine();
							String[] sArray = s.split(", ");
							//System.out.println(sArray[1]);
							if(scholarshipName.getText().equals(sArray[1])) {
								//System.out.println("found");
								String appendApplication = username + ", " + supplemental.getText();
								BufferedWriter new_writer = new BufferedWriter(new FileWriter("src/"+ scholarshipName.getText() + ".txt", true)); 
								new_writer.newLine();
								new_writer.write(appendApplication);
								new_writer.close();
							}
							
						}
						in.close();
					}
					catch (Exception ab) {
						System.out.println("error" + ab);
						
						
					}
				}
			}
		});
		updateTable.setBounds(633, 433, 117, 29);
		contentPane.add(updateTable);
		
	}
}
