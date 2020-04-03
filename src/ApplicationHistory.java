import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;

/**
 * Class for viewing past applications
 * Students may view their application history here, and choose a specific application to view more details
 * @author Rulan Lu
 *
 */
public class ApplicationHistory extends JFrame {

	private JPanel contentPane;
	private TableModel table;
	private JTable scholarships;
	private TableRowSorter sortTable;
	private JScrollPane scrollPane;
	
	static String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationHistory frame = new ApplicationHistory(username);
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
	public ApplicationHistory(String user) {
		//initialize user
		username = user;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("University of Saskatchewan");
		
		JLabel studentLabel = new JLabel(username + "'s Past Applications:");
		studentLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		studentLabel.setBounds(6, 6, 429, 16);
		contentPane.add(studentLabel);
		
		//set up the table by reading the application database
	    String[] columns = new String[2];
	    String[][] data = new String[100][2];
	    int count = 0;
	    int i = 0;
	    try {
				Scanner in = new Scanner(new File("src/Applications.txt"));
				while (in.hasNextLine()) {
					String s = in.nextLine();
					String[] sArray = s.split(", ");
					//header information
					if(count == 0) {
						columns[0] = sArray[0];
						columns[1] = sArray[1];
					}
					//actual data, display all applications for specific student
					else {
						if (sArray[0].equals(username)) {
							data[i][0] = sArray[0];
							data[i][1] = sArray[1];
							i++;
						}
					}
					count++;
				}
				in.close();
	    } catch (FileNotFoundException m) {
			JOptionPane.showMessageDialog(null,"Application Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
		}
	      
	    //users are not allowed to edit table
	    table = new DefaultTableModel(data, columns) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	      
	    sortTable = new TableRowSorter<>(table);
	      
	    //if student clicks on a scholarship, bring up the information/application form for that scholarship
	    scholarships = new JTable(table);
	    scholarships.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		int i = scholarships.getSelectedRow();
	        		if (scholarships.getValueAt(scholarships.getSelectedRow(), 0) != null) {
		        		String text = (String)table.getValueAt(i, 0) + ": " + (String)table.getValueAt(i, 1);
		        		ViewApplication application = new ViewApplication((String)table.getValueAt(i, 0), (String)table.getValueAt(i, 1));
		        		application.setVisible(true);
		        		application.setTitle(text);
		        		setVisible(false);
	        		}
	        	}
	    });
	    
	    scholarships.setRowSorter(sortTable);
	      
	    scrollPane = new JScrollPane(scholarships);
	    scrollPane.setBounds(5, 50, 745, 380);

	    getContentPane().add(scrollPane);
		
		//if student presses back, return to student menu
		JButton backButton = new JButton("Back");
		backButton.setBounds(0, 443, 86, 29);
		contentPane.add(backButton);
	    backButton.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	 			StudentMenu menu = new StudentMenu();
	 			menu.setUsername(username);
	  			menu.setVisible(true);
	  			setVisible(false);
	        }
	    });
		
	}
}
