import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

/**
 * Class for student menu. 
 * Students may search/apply for scholarships, view application history and will be able to edit their information
 * Students can also see if they have been awarded a scholarship
 * 
 * @author Rulan Lu
 *
 */
public class StudentMenu extends JFrame {

	private JPanel contentPane;
	static String username;
	static String awardedScholarship;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMenu frame = new StudentMenu(username);
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
	public StudentMenu(String suser) {
		username = suser;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("University of Saskatchewan");

		JLabel description = new JLabel("University of Saskatchewan scholarship system.");
		description.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		description.setBounds(189, 6, 438, 16);
		contentPane.add(description);

		JLabel description2 = new JLabel(
				"Search for scholarships below, view your application history, view your account information.");
		description2.setBounds(92, 57, 582, 16);
		contentPane.add(description2);

		// check to see if student has been awarded a scholarship
		try {
			Scanner x = new Scanner(new File("src/student.txt"));
			while (x.hasNextLine()) {
				String y = x.nextLine();
				String[] sArray = y.split(", ");
				if (sArray.length > 1) {
					if (username.equals(sArray[0])) {
						if (sArray[3].equals("Yes")) {
							// check to see which scholarship student has been awarded
							try {
								// find student in database
								Scanner in = new Scanner(new File("src/Awarded.txt"));
								boolean found = false;
								while (in.hasNextLine()) {
									String s = in.nextLine();
									String[] sArray2 = s.split(", ");
									// find name of scholarship
									if (sArray2.length > 1) {
										if (username.equals(sArray2[0])) {
											awardedScholarship = sArray2[1];
										}
									}

								}
								in.close();
							} catch (FileNotFoundException m) {
								JOptionPane.showMessageDialog(null, "User Database Not Found", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

							// button that student can click to see notification
							JButton notificationButton = new JButton("You have an award!");
							notificationButton.setBounds(579, 443, 165, 29);
							contentPane.add(notificationButton);
							notificationButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									// display to student
									JOptionPane.showMessageDialog(null, "Congratulations! You have been awarded the "
											+ awardedScholarship + " scholarship!!!", null, JOptionPane.PLAIN_MESSAGE);
								}
							});
						}
					}
				}
			}
			x.close();
		} catch (FileNotFoundException ABC) {
			JOptionPane.showMessageDialog(null, "user not found", "Error", JOptionPane.ERROR_MESSAGE);
		}

		// if student wants to search, go to search page
		JButton searchButton = new JButton("Search for Scholarships");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search search = new Search();
				search.setUsername(username);
				search.setVisible(true);
				setVisible(false);
			}
		});
		searchButton.setBounds(281, 122, 182, 29);
		contentPane.add(searchButton);

		// button to go to application history
		// student may view their past applications
		JButton historyButton = new JButton("Application History");
		historyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationHistory history = new ApplicationHistory(username);
				history.setVisible(true);
				setVisible(false);
			}
		});
		historyButton.setBounds(295, 163, 160, 29);
		contentPane.add(historyButton);

		JButton studentInformation = new JButton("Account Information");
		studentInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentInformation information = new StudentInformation(username);
				information.setVisible(true);
				setVisible(false);
			}
		});
		studentInformation.setBounds(298, 204, 165, 29);
		contentPane.add(studentInformation);

		// university of saskatchewan logo
		JLabel logoLabel = new JLabel("");
		logoLabel.setBounds(286, 268, 180, 176);
		contentPane.add(logoLabel);

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image image = img.getScaledInstance(logoLabel.getWidth(), logoLabel.getHeight(), Image.SCALE_SMOOTH);
		logoLabel.setIcon(new ImageIcon(image));

	}
}
