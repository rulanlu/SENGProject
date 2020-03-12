import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
public class Search extends JFrame {
	
   private JTextField textField;
   private JLabel search;
   private TableModel table;
   private JTable scholarships;
   private TableRowSorter sortTable;
   private JScrollPane scrollPane;
   
   public static void main(String[] args) {
	      new Search();
   }
   
   public Search() {
	   
      setBounds(100, 100, 450, 300);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setResizable(false);
      setVisible(true);
      setTitle("University of Saskatchewan");
      
      String[] columns = new String[2];
      String[][] data = new String[100][2];
      int count = 0;
      int i = 0;
      try {
			Scanner in = new Scanner(new File("src/Scholarships.txt"));
			while (in.hasNextLine()) {
				String s = in.nextLine();
				String[] sArray = s.split(",");
				if(count == 0) {
					columns[0] = sArray[0];
					columns[1] = sArray[1];
				}
				else {
					data[i][0] = sArray[0];
					data[i][1] = sArray[1];
					i++;
				}
				count++;
			}
			in.close();
		} catch (FileNotFoundException m) {
			JOptionPane.showMessageDialog(null,"Scholarship Database Not Found", "Error", JOptionPane.ERROR_MESSAGE);
	  }
      
      table = new DefaultTableModel(data, columns);
      sortTable = new TableRowSorter<>(table);
      
      scholarships = new JTable(table);
      scholarships.setRowSorter(sortTable);
      
      scrollPane = new JScrollPane(scholarships);
      scrollPane.setBounds(0, 35, 450, 420);
      getContentPane().setLayout(null);
      search = new JLabel("Search:");
      search.setBounds(65, 10, 60, 15);
      getContentPane().add(search);
      textField = new JTextField(15);
      textField.setBounds(140, 5, 230, 25);
      getContentPane().add(textField);
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
         public void search(String str) {
            if (str.length() == 0) {
               sortTable.setRowFilter(null);
            } else {
               sortTable.setRowFilter(RowFilter.regexFilter(str));
            }
         }
      });
      
      getContentPane().add(scrollPane);
   }
}