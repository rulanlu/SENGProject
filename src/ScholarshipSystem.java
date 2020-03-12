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
import java.util.Scanner;
import java.awt.BorderLayout;
import javax.swing.JButton;
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

public class ScholarshipSystem extends JFrame {
	private JPanel contentPane;
	private JTextField scholarshipname;
	private JTextField scholarshipdate;
	
	
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
	public ScholarshipSystem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); 
		setTitle("University of Saskatchewan");
		
		
		JLabel welcomeLabel = new JLabel("Scholarship updater!");
		welcomeLabel.setBounds(160, 23, 145, 16);
		contentPane.add(welcomeLabel);
		
		JLabel scholarshipnameLabel = new JLabel("Scholarship Name:");
		scholarshipnameLabel.setBounds(33, 71, 73, 16);
		contentPane.add(scholarshipnameLabel);
		
		scholarshipname = new JTextField();
		scholarshipname.setBounds(112, 66, 274, 26);
		contentPane.add(scholarshipname);
		scholarshipname.setColumns(10);
		
		JLabel duedateLabel = new JLabel("Due Date:");
		duedateLabel.setBounds(33, 135, 73, 16);
		contentPane.add(duedateLabel);
		
		scholarshipdate = new JTextField();
		scholarshipdate.setBounds(112, 130, 274, 26);
		contentPane.add(scholarshipdate);
		scholarshipdate.setColumns(10);
		
		
		
		JButton updateTable = new JButton("update");
		updateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(scholarshipname.getText().contentEquals("") || scholarshipdate.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all boxes.");
				}
				else {
					
					try {
						String appendScholarship = scholarshipname.getText() + "," + scholarshipdate.getText();
						BufferedWriter new_writer = new BufferedWriter(new FileWriter("src/Scholarships.txt", true));
						new_writer.newLine();
						new_writer.write(appendScholarship);
						new_writer.close();
						
						
						JFrame frame = new JFrame();
						JTable table = new JTable();
						File file = new File("src/Scholarships.txt");
						try { 
							BufferedReader new_reader = new BufferedReader(new FileReader(file));
							String headers = new_reader.readLine().trim();
							String[] columnNames = headers.split(",");
							DefaultTableModel new_model = (DefaultTableModel)table.getModel();
							new_model.setColumnIdentifiers(columnNames);
							
							Object[] cells = new_reader.lines().toArray();
							for (int counter = 0; counter < cells.length; counter++) {
								String row = cells[counter].toString().trim();
								String[] fillRows = row.split(",");
								new_model.addRow(fillRows);
								new_reader.close();
							}
						}
						catch(Exception ab) {
							System.out.println("there is an error" + ab);
							
						}
						JScrollPane scrollPane = new JScrollPane(table);
					    frame.add(scrollPane, BorderLayout.CENTER);
					    frame.setSize(300, 150);
					    frame.setVisible(true);
					}
					catch (IOException m) {
						System.out.println("error" + m);
					}
				}
			}
		});
		updateTable.setBounds(160, 215, 117, 29);
		contentPane.add(updateTable);
		
	    
	}
}
