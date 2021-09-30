import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class changeStudentInformation {
	public void changeInformation(String username, String usernameField, String password, String gpaField, String facField, String awarded) {
		try {
			FileInputStream fstream = new FileInputStream("src/student.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			StringBuilder fileContent = new StringBuilder();

			while ((line = br.readLine()) != null) {
				String sArray[] = line.split(", ");
				if (sArray.length > 1) {
					// if line containing student is found, edit it
					if (sArray[0].equals(username)) {
						String newLine = usernameField + ", " + password + ", "
								+ gpaField + ", " + facField + ", "
								+ awarded;
						fileContent.append(newLine);
						fileContent.append("\n");
					// otherwise keep it as it is
					} else {
						fileContent.append(line);
						fileContent.append("\n");
					}
				}
			}

			FileWriter fstreamWrite = new FileWriter("src/student.txt");
			BufferedWriter out = new BufferedWriter(fstreamWrite);
			out.write(fileContent.toString());
			out.close();
		}
		catch (Exception e){
			System.out.println("Problem reading file.");
		}
	}
}