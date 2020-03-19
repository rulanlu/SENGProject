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

public class ScholarshipApplication extends JFrame {

	private JPanel contentPane;
	private String name;
	private String date;
	private String ID;
	private String username;
	
    public String getName() {
	    return name;
    }
	
    public void setName(String sname) {
	    this.name = sname;
    }
    
    public String getDate() {
	    return date;
    }
	
    public void setDate(String sdate) {
	    this.date = sdate;
    }

    public String getID() {
	    return ID;
    }
	
    public void setID(String sid) {
	    this.ID = sid;
    }
    
    public String getUsername() {
 	   return username;
    }
 	
    public void setUsername(String user) {
 	   this.username = user;
    }
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScholarshipApplication frame = new ScholarshipApplication();
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
	public ScholarshipApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel info = new JLabel("Information:");
		info.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		info.setBounds(6, 6, 101, 16);
		contentPane.add(info);
		
		JLabel scholarshipName = new JLabel("Name: ");
		scholarshipName.setBounds(6, 60, 438, 16);
		contentPane.add(scholarshipName);
		
		JLabel lblNewLabel = new JLabel("Application Due Date: ");
		lblNewLabel.setBounds(6, 89, 438, 16);
		contentPane.add(lblNewLabel);
		
		JLabel scholarshipID = new JLabel("ID: ");
		scholarshipID.setBounds(6, 34, 438, 16);
		contentPane.add(scholarshipID);
		
		JLabel scholarshipGPA = new JLabel("Minimum GPA: 3.7");
		scholarshipGPA.setBounds(6, 117, 438, 16);
		contentPane.add(scholarshipGPA);
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long lineCounter = Files.lines(Paths.get("src/Applications.txt")).count();
					int unique_ID = (int) lineCounter;
					int application_ID = unique_ID;
					
					String appendApplication = application_ID + ", " + username + ", " + name;
					BufferedWriter new_writer = new BufferedWriter(new FileWriter("src/Applications.txt", true));
					
					new_writer.newLine();
					new_writer.write(appendApplication);
					new_writer.close();
					
					
					JFrame frame = new JFrame();
					JTable table = new JTable();
					File file = new File("src/Applications.txt");
					try { 
						BufferedReader new_reader = new BufferedReader(new FileReader(file));
						String headers = new_reader.readLine().trim();
						String[] columnNames = headers.split(", ");
						DefaultTableModel new_model = (DefaultTableModel)table.getModel();
						new_model.setColumnIdentifiers(columnNames);
						
						Object[] cells = new_reader.lines().toArray();
						for (int counter = 0; counter < cells.length; counter++) {
							String row = cells[counter].toString().trim();
							String[] fillRows = row.split(",");
							new_model.addRow(fillRows);
							new_reader.close();
						}
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Application successful", null, JOptionPane.PLAIN_MESSAGE);
					}
					catch(Exception ab) {
						System.out.println("there is an error" + ab);
						
					}
				} catch (IOException m) {
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
