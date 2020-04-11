import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

/**
 * Allows coordinators to edit scholarships already in the database.
 * The changes made will then be saved to the database.
 * 
 * @author Rulan Lu
 *
 */
public class EditScholarship extends JFrame {

	private JPanel contentPane;
	static String name;
	static String date;
	static String ID;
	static String GPA;
	static String amount;
	static String faculty;
	private JTextField nameField;
	private JTextField dateField;
	private JTextField gpaField;
	private JTextField amountField;
	private JComboBox facultyCombo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditScholarship frame = new EditScholarship(name, date, ID, GPA, amount, faculty);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. Sets up labels, buttons, etc.
	 */
	public EditScholarship(String sname, String sdate, String sid, String sgpa, String samount, String sfac) {
		// initialize variables
		name = sname;
		date = sdate;
		ID = sid;
		GPA = sgpa;
		amount = samount;
		faculty = sfac;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// labels for each field
		JLabel scholarshipName = new JLabel("Name:");
		scholarshipName.setBounds(125, 75, 47, 15);
		contentPane.add(scholarshipName);

		JLabel lblNewLabel = new JLabel("Due Date (MM/DD/YYYY):");
		lblNewLabel.setBounds(6, 124, 166, 16);
		contentPane.add(lblNewLabel);

		JLabel scholarshipID = new JLabel("ID:      " + ID);
		scholarshipID.setBounds(147, 34, 179, 16);
		contentPane.add(scholarshipID);

		JLabel info = new JLabel("Edit information:");
		info.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		info.setBounds(6, 6, 148, 16);
		contentPane.add(info);

		JLabel scholarshipGPA = new JLabel("Minimum GPA:");
		scholarshipGPA.setBounds(77, 173, 101, 16);
		contentPane.add(scholarshipGPA);

		JLabel amountLabel = new JLabel("Amount:");
		amountLabel.setBounds(114, 223, 64, 16);
		contentPane.add(amountLabel);

		JLabel facLabel = new JLabel("Faculty:");
		facLabel.setBounds(125, 274, 57, 16);
		contentPane.add(facLabel);

		// textboxes for each field
		nameField = new JTextField();
		nameField.setText(name);
		nameField.setBounds(184, 69, 269, 26);
		contentPane.add(nameField);
		nameField.setColumns(10);

		// ensures date is in correct format
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateField = new JFormattedTextField(dateFormat);
		dateField.setText(date);
		dateField.setBounds(184, 119, 269, 26);
		contentPane.add(dateField);
		dateField.setColumns(10);

		// ensures GPA is in correct format
		NumberFormat percentFormat = NumberFormat.getNumberInstance();
		percentFormat.setMinimumFractionDigits(2);
		percentFormat.setMaximumFractionDigits(3);

		gpaField = new JFormattedTextField(percentFormat);
		gpaField.setText(GPA);
		gpaField.setBounds(184, 168, 269, 26);
		contentPane.add(gpaField);
		gpaField.setColumns(10);

		// ensures that scholarship amount is in the correct format
		NumberFormat amountFormat = NumberFormat.getIntegerInstance();
		amountFormat.setGroupingUsed(false);

		amountField = new JFormattedTextField(amountFormat);
		amountField.setText(amount);
		amountField.setBounds(184, 218, 269, 26);
		contentPane.add(amountField);
		amountField.setColumns(10);

		// available faculties to choose from
		String faculties[] = { "All", "Architecture", "Arts", "Business", "Education", "Engineering", "Kinesiology",
				"Law", "Medicine", "Nursing", "Science" };

		facultyCombo = new JComboBox(faculties);
		facultyCombo.setSelectedItem(faculty);
		facultyCombo.setBounds(184, 270, 269, 27);
		contentPane.add(facultyCombo);

		// if coordinator chooses to confirm edit
		JButton applyButton = new JButton("Edit");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check for correct input
				if (nameField.getText().equals("") || dateField.getText().equals("") || gpaField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in all fields");
				} else if ((Double.parseDouble(gpaField.getText()) > 4.30)
						|| (Double.parseDouble(gpaField.getText()) < 0.00)) {
					JOptionPane.showMessageDialog(null, "Please enter a valid number for GPA (Between 0.00 and 4.30");
				} else {
					// open the database and change the information for the scholarship
					try {
						FileInputStream fstream = new FileInputStream("src/Scholarships.txt");
						BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
						String line;
						StringBuilder fileContent = new StringBuilder();

						while ((line = br.readLine()) != null) {
							String sArray[] = line.split(", ");
							if (sArray.length > 0) {
								// if line containing scholarship is found, edit it
								if (sArray[0].equals(ID)) {
									String newLine = ID + ", " + nameField.getText() + ", " + dateField.getText() + ", "
											+ gpaField.getText() + ", $" + amountField.getText() + ", "
											+ facultyCombo.getSelectedItem().toString();
									fileContent.append(newLine);
									fileContent.append("\n");
									// otherwise keep it as it is
								} else {
									fileContent.append(line);
									fileContent.append("\n");
								}
							}
						}

						FileWriter fstreamWrite = new FileWriter("src/Scholarships.txt");
						BufferedWriter out = new BufferedWriter(fstreamWrite);
						out.write(fileContent.toString());
						out.close();

						// change scholarship information in application database as well
						try {
							FileInputStream fstream2 = new FileInputStream("src/Applications.txt");
							BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream2));
							String line2;
							StringBuilder fileContent2 = new StringBuilder();

							while ((line2 = br2.readLine()) != null) {
								String sArray2[] = line2.split(", ");
								if (sArray2.length > 0) {
									// if line containing scholarship is found, edit it
									if (sArray2[1].equals(name)) {
										String newLine2 = sArray2[0] + ", " + nameField.getText();
										fileContent2.append(newLine2);
										fileContent2.append("\n");
										// otherwise keep it as it is
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
					// go back to scholarship viewing
					ViewExistingScholarships view = new ViewExistingScholarships();
					view.setVisible(true);
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Edit successful", null, JOptionPane.PLAIN_MESSAGE);

				}
			}
		});
		applyButton.setBounds(413, 349, 87, 29);
		contentPane.add(applyButton);

		// go back to scholarship viewing
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewExistingScholarships view = new ViewExistingScholarships();
				view.setVisible(true);
				setVisible(false);
			}
		});
		cancelButton.setBounds(0, 349, 106, 29);
		contentPane.add(cancelButton);

	}
}
