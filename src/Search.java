import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Allows students to search for scholarships within the system
 * Search results will filter based on key words that student enters
 * @author Rulan Lu
 *
 */
public class Search extends JFrame {
	
   private JTextField textField;
   private JLabel search;
   private TableModel table;
   private JTable scholarships;
   private TableRowSorter sortTable;
   private JScrollPane scrollPane;
   private String username;
	
   //getters and setters
   public String getUsername() {
	   return username;
   }
	
   public void setUsername(String user) {
	   this.username = user;
   }
   
	/**
	 * Launch the application.
	 */
   public static void main(String[] args) {
	      new Search();
   }
   
	/**
	 * Create the frame.
	 * Sets up labels, buttons, etc.
	 */
   public Search() {
	   
      setBounds(200, 200, 750, 500);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setResizable(false);
      setVisible(true);
      setTitle("University of Saskatchewan");
      
      //set up the table by reading the scholarship database
      String[] columns = new String[6];
      String[][] data = new String[100][6];
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
				}
				//actual data
				else {
					data[i][0] = sArray[0];
					data[i][1] = sArray[1];
					data[i][2] = sArray[2];
					data[i][3] = sArray[3];
					data[i][4] = sArray[4];
					data[i][5] = sArray[5];
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
      
      //if student clicks on a scholarship, bring up the information/application form for that scholarship
      scholarships.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		int i = scholarships.getSelectedRow();
      		String text = (String)table.getValueAt(i, 1);
      		ScholarshipApplication application = new ScholarshipApplication((String)table.getValueAt(i, 1), 
      				(String)table.getValueAt(i, 2), (String)table.getValueAt(i, 0), username, (String)table.getValueAt(i, 3), 
      				(String)table.getValueAt(i, 4), (String)table.getValueAt(i, 5));
      		application.setVisible(true);
      		application.setTitle(text);
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
      //update table as student searches
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
      
      //goes back to student menu
      JButton backButton = new JButton("Back");
      backButton.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
			StudentMenu menu = new StudentMenu();
			menu.setUsername(username);
			menu.setVisible(true);
			setVisible(false);
      	}
      });
      backButton.setBounds(5, 4, 75, 29);
      getContentPane().add(backButton);
   }
}