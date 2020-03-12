import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Coordinator");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JLabel scholarship_label = new JLabel("Update Scholarships");
		scholarship_label.setBounds(33, 71, 73, 16);
		contentPane.add(scholarship_label);
		
		JButton update_scholarshipButton = new JButton("Update Scholarships");
		update_scholarshipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScholarshipSystem update = new ScholarshipSystem();
				update.setVisible(true);
				frame.setVisible(false);
			}
		});
		update_scholarshipButton.setBounds(160,215,117, 29);
		contentPane.add(update_scholarshipButton);
		
	}

}
