import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Font;

/**
 * System for coordinator to add new scholarships to the system
 * Coordinator will specify information and restraints for each scholarship they add
 * @author Matt Tamkee, Rulan Lu
 *
 */
public class ScholarshipSystem extends JFrame {
	private JPanel contentPane;
	private JTextField scholarshipName;
	private JTextField scholarshipDate;
	private JTextField scholarshipID;
	private JTextField scholarshipGPA;
	private JTextField scholarshipAmount;
	private JTextField scholarshipFaculty;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScholarshipSystem frame = new ScholarshipSystem();
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
	public ScholarshipSystem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); 
		setTitle("University of Saskatchewan");
		setLocationRelativeTo(null);
		
		JLabel welcomeLabel = new JLabel("Add Scholarship");
		welcomeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		welcomeLabel.setBounds(315, 23, 166, 16);
		contentPane.add(welcomeLabel);
		
		JLabel scholarshipnameLabel = new JLabel("Scholarship Name:");
		scholarshipnameLabel.setBounds(92, 71, 128, 16);
		contentPane.add(scholarshipnameLabel);
		
		scholarshipName = new JTextField();
		scholarshipName.setBounds(232, 66, 384, 26);
		contentPane.add(scholarshipName);
		scholarshipName.setColumns(10);
		
		JLabel duedateLabel = new JLabel("Due Date (MM/DD/YYYY):");
		duedateLabel.setBounds(50, 135, 174, 16);
		contentPane.add(duedateLabel);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		scholarshipDate = new JFormattedTextField(dateFormat);
		scholarshipDate.setBounds(232, 130, 384, 26);
		contentPane.add(scholarshipDate);
		scholarshipDate.setColumns(10);
		
		//ensures proper formatting for GPA
        NumberFormat percentFormat = NumberFormat.getNumberInstance();
        percentFormat.setMinimumFractionDigits(2);
        percentFormat.setMaximumFractionDigits(3);
		
		scholarshipGPA = new JFormattedTextField(percentFormat);
		scholarshipGPA.setBounds(232, 194, 384, 26);
		contentPane.add(scholarshipGPA);
		scholarshipGPA.setColumns(10);
		
		JLabel gpaLabel = new JLabel("Minimum GPA:");
		gpaLabel.setBounds(118, 199, 105, 16);
		contentPane.add(gpaLabel);
		
		//ensures proper formatting for scholarship amount
        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        amountFormat.setMinimumFractionDigits(0);
		
		scholarshipAmount = new JFormattedTextField(amountFormat);
		scholarshipAmount.setBounds(232, 261, 384, 26);
		contentPane.add(scholarshipAmount);
		scholarshipAmount.setColumns(10);
		
		JLabel amountLabel = new JLabel("Amount:");
		amountLabel.setBounds(159, 266, 61, 16);
		contentPane.add(amountLabel);
		
		scholarshipFaculty = new JTextField();
		scholarshipFaculty.setBounds(232, 328, 384, 26);
		contentPane.add(scholarshipFaculty);
		scholarshipFaculty.setColumns(10);
		
		JLabel facLabel = new JLabel("Faculty:");
		facLabel.setBounds(162, 333, 61, 16);
		contentPane.add(facLabel);
		
		//if coordinator hits add button
		JButton updateTable = new JButton("Add");
		updateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(scholarshipName.getText().contentEquals("") || scholarshipDate.getText().contentEquals("") 
						|| scholarshipGPA.getText().contentEquals("") || scholarshipAmount.getText().contentEquals("") || scholarshipFaculty.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all boxes.");
				} else if ((Double.parseDouble(scholarshipGPA.getText()) > 4.30) || (Double.parseDouble(scholarshipGPA.getText()) < 0.00)) {
					JOptionPane.showMessageDialog(null, "Please enter a valid number for GPA (Between 0.00 and 4.30");
				} else if (Integer.parseInt(scholarshipAmount.getText()) < 50) {
					JOptionPane.showMessageDialog(null, "Please enter a valid amount for the scholarship (Must be greater than $50)");
				}
				else {
					//puts new scholarship in database
					try {
						long lineCounter = Files.lines(Paths.get("src/Scholarships.txt")).count();
						int unique_ID = (int) lineCounter;
						int scholarship_ID = unique_ID;
						
						String appendScholarship = scholarship_ID + ", " + scholarshipName.getText() + ", " + scholarshipDate.getText() 
							+ ", " + scholarshipGPA.getText() + ", $" + scholarshipAmount.getText() + ", " + scholarshipFaculty.getText();
						BufferedWriter new_writer = new BufferedWriter(new FileWriter("src/Scholarships.txt", true));
						
						new_writer.newLine();
						new_writer.write(appendScholarship);
						new_writer.close();
						
						
						JFrame frame = new JFrame();
						JTable table = new JTable();
						File file = new File("src/Scholarships.txt");
						//displays table of all scholarships for coordinator
						try { 
							BufferedReader new_reader = new BufferedReader(new FileReader(file));
							String headers = new_reader.readLine().trim();
							String[] columnNames = headers.split(", ");
							DefaultTableModel new_model = (DefaultTableModel)table.getModel();
							new_model.setColumnIdentifiers(columnNames);
							
							Object[] cells = new_reader.lines().toArray();
							for (int counter = 0; counter < cells.length; counter++) {
								String row = cells[counter].toString().trim();
								String[] fillRows = row.split(", ");
								new_model.addRow(fillRows);
								new_reader.close();
							}
						}
						//catches exceptions
						catch(Exception ab) {
							System.out.println("there is an error" + ab);
							
						}
						JScrollPane scrollPane = new JScrollPane(table);
					    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
					    frame.setSize(650, 350);
					    frame.setLocationRelativeTo(null);
					    frame.setVisible(true);
					}
					catch (IOException m) {
						System.out.println("error" + m);
					}

				}
			}
		});
		updateTable.setBounds(633, 443, 117, 29);
		contentPane.add(updateTable);
		
		//go back to coordinator menu
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CoordinatorMenu coordinator = new CoordinatorMenu();
				coordinator.setVisible(true);
				setVisible(false);
			}
		});
		backButton.setBounds(6, 443, 85, 29);
		contentPane.add(backButton);
		 
	}
}

