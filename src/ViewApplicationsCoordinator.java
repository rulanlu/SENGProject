import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Allows coordinators to view student applications. 
 * They may then click on an application to view more information on the application 
 * As well as accept/deny the application
 * 
 * @author Rulan Lu
 *
 */
public class ViewApplicationsCoordinator extends JFrame {

	private JPanel contentPane;
	private TableModel table;
	private JTable scholarships;
	private TableRowSorter sortTable;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewApplicationsCoordinator frame = new ViewApplicationsCoordinator();
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
	public ViewApplicationsCoordinator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("University of Saskatchewan");

		JLabel studentLabel = new JLabel("All Applications:");
		studentLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		studentLabel.setBounds(6, 6, 429, 16);
		contentPane.add(studentLabel);

		// set up the table by reading the application database
		String[] columns = new String[2];
		String[][] data = new String[100][2];
		int count = 0;
		int i = 0;
		try {
			Scanner in = new Scanner(new File("src/Applications.txt"));
			while (in.hasNextLine()) {
				String s = in.nextLine();
				String[] sArray = s.split(", ");
				if (sArray.length > 1) {
					// header information
					if (count == 0) {
						columns[0] = sArray[0];
						columns[1] = sArray[1];
					}
					// actual data for each application
					else {
						data[i][0] = sArray[0];
						data[i][1] = sArray[1];
						i++;
					}
					count++;
				}
			}
			in.close();
		} catch (FileNotFoundException m) {
			JOptionPane.showMessageDialog(null, "Application Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
		}

		// users are not allowed to edit table
		table = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		sortTable = new TableRowSorter<>(table);

		// if coordinator clicks on a scholarship, bring up the application form for that scholarship
		scholarships = new JTable(table);
		scholarships.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = scholarships.getSelectedRow();
				if (scholarships.getValueAt(scholarships.getSelectedRow(), 0) != null) {
					String text = (String) table.getValueAt(i, 0) + "'s application for "
							+ (String) table.getValueAt(i, 1);
					RespondToApplication response = new RespondToApplication((String) table.getValueAt(i, 0),
							(String) table.getValueAt(i, 1));
					response.setVisible(true);
					response.setTitle(text);
					setVisible(false);
				}
			}
		});

		scholarships.setRowSorter(sortTable);

		scrollPane = new JScrollPane(scholarships);
		scrollPane.setBounds(5, 50, 745, 380);

		getContentPane().add(scrollPane);

		// if student presses back, return to student menu
		JButton backButton = new JButton("Back");
		backButton.setBounds(0, 443, 86, 29);
		contentPane.add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CoordinatorMenu menu = new CoordinatorMenu();
				menu.setVisible(true);
				setVisible(false);
			}
		});

	}
}
