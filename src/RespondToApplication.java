import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

/**
 * Allows coordinators to give students a response regarding their applications.
 * Either accept or reject the application. 
 * Students will then be notified, and the application status will be updated in the database
 * 
 * @author Rulan Lu
 *
 */
public class RespondToApplication extends JFrame {

	private JPanel contentPane;
	static String username;
	static String scholarship;
	static boolean supplementary = false;
	static String suppText;
	static String GPA;
	static double doubleGPA;
	static boolean topContender;
	static int studentSpot;
	static String remove;

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
	 * Create the frame. Sets up labels, buttons, etc.
	 */
	public RespondToApplication(String user, String scholar) {
		// initialize variables
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

		JLabel suppLabel = new JLabel("Supplementary Information:");
		suppLabel.setBounds(6, 128, 195, 16);
		contentPane.add(suppLabel);

		JTextArea suppTextArea = new JTextArea();
		suppTextArea.setEditable(false);
		suppTextArea.setBounds(68, 156, 620, 240);
		contentPane.add(suppTextArea);

		JLabel studentNameLabel = new JLabel("Student: " + username);
		studentNameLabel.setBounds(6, 72, 237, 16);
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

		// find student's GPA and position in the list
		try {
			Scanner x = new Scanner(new File("src/Applications.txt"));
			int i = 0;
			while (x.hasNextLine()) {
				String y = x.nextLine();
				String[] sArray = y.split(", ");
				if (sArray.length > 1) {
					if (scholarship.equals(sArray[1]) && username.equals(sArray[0])) {
						GPA = sArray[2];
					}
					i++;
				}
			}
			x.close();
		} catch (FileNotFoundException ABC) {
			JOptionPane.showMessageDialog(null, "user not found", "Error", JOptionPane.ERROR_MESSAGE);
		}

		// check student's application against other applications
		try {
			Scanner x = new Scanner(new File("src/Applications.txt"));
			int i = 0;
			topContender = true;
			while (x.hasNextLine()) {
				String y = x.nextLine();
				String[] sArray = y.split(", ");
				if (sArray.length > 1) {
					// for every applicant of this scholarship
					if (scholarship.equals(sArray[1])) {
						// skip over student
						if (username.equals(sArray[0])) {
							continue;
						// if another GPA is higher, student is not the top contender
						} else if (Double.parseDouble(sArray[2]) > doubleGPA) {
							topContender = false;
							break;
						// if there is a student with the same GPA but applied earlier, student is not the top contender
						} else if ((Double.parseDouble(sArray[2]) == doubleGPA) && (i < studentSpot)) {
							topContender = false;
							break;
						}
					}
					i++;
				}
			}
			x.close();
		} catch (FileNotFoundException ABC) {
			JOptionPane.showMessageDialog(null, "user not found", "Error", JOptionPane.ERROR_MESSAGE);
		}

		JLabel topLabel = new JLabel();
		topLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		topLabel.setBounds(6, 6, 444, 16);
		contentPane.add(topLabel);

		// if the student is the best candidate, show this to the coordinator
		if (topContender) {
			topLabel.setText("This student is the best candidate for this scholarship");
			JButton awardButton = new JButton("Award Scholarship");
			awardButton.setBounds(578, 449, 166, 29);
			contentPane.add(awardButton);
			// if scholarship is awarded to student, cange database to reflect this
			awardButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// change student information to say that have been awarded a scholarship
					try {
						FileInputStream fstream2 = new FileInputStream("src/student.txt");
						BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream2));
						String line2;
						StringBuilder fileContent2 = new StringBuilder();

						while ((line2 = br2.readLine()) != null) {
							String sArray2[] = line2.split(", ");
							if (sArray2.length > 1) {
								// if line containing student is found, edit it
								if (sArray2[0].equals(username)) {
									String newLine2 = sArray2[0] + ", " + sArray2[1] + ", " + sArray2[2] + ", " + sArray2[3] +  ", Yes";
									fileContent2.append(newLine2);
									fileContent2.append("\n");
									// otherwise keep it as it is
								} else {
									fileContent2.append(line2);
									fileContent2.append("\n");
								}
							}
						}

						FileWriter fstreamWrite2 = new FileWriter("src/student.txt");
						BufferedWriter out2 = new BufferedWriter(fstreamWrite2);
						out2.write(fileContent2.toString());
						out2.close();

					} catch (Exception ex) {
						System.out.println("Problem reading file.");
					}
					// puts new awarded information into database
					try {
						String appendAward = username + ", " + scholarship;

						BufferedWriter new_writer = new BufferedWriter(new FileWriter("src/Awarded.txt", true));

						new_writer.newLine();
						new_writer.write(appendAward);
						new_writer.close();

					} catch (IOException m) {
						System.out.println("error" + m);
					}
					// remove all applications for this scholarship from database
					// also removes all other applications from this student
					try {
						File applicationsFile = new File("src/Applications.txt");
						File tempFile = new File("src/temp.txt");

						BufferedReader reader = new BufferedReader(new FileReader(applicationsFile));
						BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

						try {

							String currentLine;

							while ((currentLine = reader.readLine()) != null) {
								String trimmedLine = currentLine.trim();
								String[] sArray = trimmedLine.split(", ");
								if (sArray.length > 1) {
									// if application is for the same scholarship or is from the same student
									if (!scholarship.equals(sArray[1]) && !username.equals(sArray[0])) {
										writer.write(trimmedLine);
										writer.newLine();
									}
								}
							}
							writer.close();
							reader.close();
							boolean delete = applicationsFile.delete();
							boolean successful = tempFile.renameTo(applicationsFile);

						} catch (FileNotFoundException ABC) {
							JOptionPane.showMessageDialog(null, "user not found", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException m) {
						System.out.println("error" + m);
					}
					// remove the scholarship from the database
					try {
						File scholarshipFile = new File("src/Scholarships.txt");
						File tempFile = new File("src/temp.txt");

						BufferedReader reader = new BufferedReader(new FileReader(scholarshipFile));
						BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

						try {
							Scanner x = new Scanner(new File("src/Scholarships.txt"));
							while (x.hasNextLine()) {
								String y = x.nextLine();
								String[] sArray = y.split(", ");
								if (sArray.length > 1) {
									//remove line containing scholarship
									if (scholarship.equals(sArray[1])) {
										remove = y;
									}
								}
							}
							x.close();
						} catch (FileNotFoundException ABC) {
							JOptionPane.showMessageDialog(null, "scholarship not found", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

						//rewrite the txt file with scholarship deleted
						String currentLine;

						while ((currentLine = reader.readLine()) != null) {
							String trimmedLine = currentLine.trim();
							if (!trimmedLine.equals(remove)) {
								writer.write(trimmedLine);
								writer.newLine();
							}
						}
						writer.close();
						reader.close();
						boolean delete = scholarshipFile.delete();
						boolean successful = tempFile.renameTo(scholarshipFile);
					} catch (IOException m) {
						System.out.println("error" + m);
					}
					// go back to viewing applications
					ViewApplicationsCoordinator applications = new ViewApplicationsCoordinator();
					applications.setVisible(true);
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Scholarship successfully awarded", null,
							JOptionPane.PLAIN_MESSAGE);
				}
			});
		} else {
			topLabel.setText("This student is not the best candidate for this scholarship");
		}

		// determine if there is supplementary information to display
		try {
			// find scholarship in database
			Scanner in = new Scanner(new File("src/Scholarships.txt"));
			while (in.hasNextLine()) {
				String s = in.nextLine();
				String[] sArray = s.split(", ");
				if (sArray.length > 1) {
					if (scholarship.equals(sArray[1])) {
						// check to see if supplementary or not
						if (sArray[6].equals("Yes")) {
							supplementary = true;
						}
					}
				}
			}
			in.close();
		} catch (FileNotFoundException m) {
			JOptionPane.showMessageDialog(null, "Scholarship Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
		}

		// if scholarship needs supplementary information, display that information
		if (supplementary) {
			try {
				// find scholarship in database
				Scanner in = new Scanner(new File("src/" + scholarship + ".txt"));
				while (in.hasNextLine()) {
					String s = in.nextLine();
					String[] sArray = s.split(", ");
					if (sArray.length > 1) {
						if (username.equals(sArray[0])) {
							suppText = sArray[1];
							suppTextArea.setText(suppText);
							supplementary = false;
						}
					}
				}
				in.close();
			} catch (FileNotFoundException m) {
				JOptionPane.showMessageDialog(null, "Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
			}

		// otherwise do not display anything for supplimentary
		} else {
			suppTextArea.setText("N/A");
		}

		JLabel studentGpaLabel = new JLabel("GPA: " + GPA);
		studentGpaLabel.setBounds(6, 100, 61, 16);
		contentPane.add(studentGpaLabel);

	}
}
