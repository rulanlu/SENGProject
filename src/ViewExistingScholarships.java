import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Allows corrdinator to view and search for already existing scholarships
 * Coordinator may then click on a scholarship to the edit it
 * @author Rulan Lu
 *
 */
public class ViewExistingScholarships extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel search;
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
					ViewExistingScholarships frame = new ViewExistingScholarships();
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
	public ViewExistingScholarships() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("University of Saskatchewan");
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		//set up the table by reading the scholarship database
	      String[] columns = new String[7];
	      String[][] data = new String[100][7];
	      int count = 0;
	      int i = 0;
	      try {
				Scanner in = new Scanner(new File("src/Scholarships.txt"));
				while (in.hasNextLine()) {
					String s = in.nextLine();
					String[] sArray = s.split(", ");
					//header information
					if(count == 0) {
						columns[0] = sArray[0];
						columns[1] = sArray[1];
						columns[2] = sArray[2];
						columns[3] = sArray[3];
						columns[4] = sArray[4];
						columns[5] = sArray[5];
						columns[6] = sArray[6];
					}
					//actual data
					else {
						data[i][0] = sArray[0];
						data[i][1] = sArray[1];
						data[i][2] = sArray[2];
						data[i][3] = sArray[3];
						data[i][4] = sArray[4];
						data[i][5] = sArray[5];
						data[i][6] = sArray[6];
						i++;
					}
					count++;
				}
				in.close();
			} catch (FileNotFoundException m) {
				JOptionPane.showMessageDialog(null,"Scholarship Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
		  }
	      
	      //users are not allowed to edit table
	      table = new DefaultTableModel(data, columns) {
	    	    @Override
	    	    public boolean isCellEditable(int row, int column) {
	    	        return false;
	    	    }
	      };
	      
	      sortTable = new TableRowSorter<>(table);
	      scholarships = new JTable(table);
	      
	      scholarships.getColumnModel().getColumn(0).setPreferredWidth(25);
	      scholarships.getColumnModel().getColumn(1).setPreferredWidth(200);
	      scholarships.getColumnModel().getColumn(2).setPreferredWidth(150);
	      
	      //if coordinator clicks on a scholarship, bring up the editing page for that scholarship
	      scholarships.addMouseListener(new MouseAdapter() {
	      	@Override
	      	public void mouseClicked(MouseEvent e) {
	      		int i = scholarships.getSelectedRow();
	      		if (scholarships.getValueAt(scholarships.getSelectedRow(), 0) != null) {
		      		String text = table.getValueAt(i, 1).toString();
		      		EditScholarship edit = new EditScholarship(table.getValueAt(i, 1).toString(), 
		      				table.getValueAt(i, 2).toString(), table.getValueAt(i, 0).toString(), table.getValueAt(i, 3).toString(), 
		      				table.getValueAt(i, 4).toString().substring(1), table.getValueAt(i, 5).toString());
		      		edit.setVisible(true);
		      		edit.setTitle(text);
		      		setVisible(false);
	      		}
	      	}
	      });
	      scholarships.setRowSorter(sortTable);
	      
	      scrollPane = new JScrollPane(scholarships);
	      scrollPane.setBounds(5, 35, 745, 495);
	      getContentPane().setLayout(null);
	      search = new JLabel("Search:");
	      search.setBounds(178, 10, 60, 15);
	      getContentPane().add(search);
	      textField = new JTextField(15);
	      textField.setBounds(235, 4, 287, 25);
	      getContentPane().add(textField);
	      //update table as coordinator searches
	      textField.getDocument().addDocumentListener(new DocumentListener() {
	         @Override
	         public void insertUpdate(DocumentEvent e) {
	            search(textField.getText());
	         }
	         @Override
	         public void removeUpdate(DocumentEvent e) {
	            search(textField.getText());
	         }
	         @Override
	         public void changedUpdate(DocumentEvent e) {
	            search(textField.getText());
	         }
	         //filters table based on search
	         public void search(String str) {
	            if (str.length() == 0) {
	               sortTable.setRowFilter(null);
	            } else {
	               sortTable.setRowFilter(RowFilter.regexFilter(str));
	            }
	         }
	      });
	      
	      getContentPane().add(scrollPane);
	      
	      //goes back to coordinator menu
	      JButton backButton = new JButton("Back");
	      backButton.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
				CoordinatorMenu menu = new CoordinatorMenu();
				menu.setVisible(true);
				setVisible(false);
	      	}
	      });
	      backButton.setBounds(5, 4, 75, 29);
	      getContentPane().add(backButton);
		
	}

}
