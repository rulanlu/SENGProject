import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

//menu for coordinators
//coordinators may choose to add, edit, or delete scholarships here
//they can also view students' applications and accept/deny applications
public class CoordinatorMenu extends JFrame {

	private JPanel contentPane;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoordinatorMenu frame = new CoordinatorMenu();
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
	public CoordinatorMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("University of Saskatchewan");
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel description = new JLabel("University of Saskatchewan scholarship system.");
		description.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		description.setBounds(189, 6, 438, 16);
		contentPane.add(description);
		
		JLabel description2 = new JLabel("Add a new scholarship, edit an existing scholarship, or view applications below.");
		description2.setBounds(136, 51, 547, 16);
		contentPane.add(description2);
		
		//button to add a new scholarship
		JButton update_scholarshipButton = new JButton("Add Scholarship");
		update_scholarshipButton.setBounds(286, 127, 166, 29);
		update_scholarshipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScholarshipSystem update = new ScholarshipSystem();
				update.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(update_scholarshipButton);
		
		JLabel lblNewLabel = new JLabel("Add a new scholarship, edit an existing scholarship, or view applications below.");
		lblNewLabel.setBounds(136, 51, 491, 16);
		contentPane.add(lblNewLabel);
		
		//button to edit an existing scholarship
		JButton editButton = new JButton("Edit Existing Scholarship");
		editButton.setBounds(274, 168, 194, 29);
		contentPane.add(editButton);
		
		//button to view student applications
		JButton btnNewButton = new JButton("View Applications");
		btnNewButton.setBounds(293, 209, 159, 29);
		contentPane.add(btnNewButton);
		
		
	}
}
