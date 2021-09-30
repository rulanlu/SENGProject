import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class changeStudentEligibility {
	public void changeEligibility (String username, String usernameField, String gpaField) {
		try {
			FileInputStream fstream2 = new FileInputStream("src/Applications.txt");
			BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream2));
			String line2;
			StringBuilder fileContent2 = new StringBuilder();

			while ((line2 = br2.readLine()) != null) {
				String sArray2[] = line2.split(", ");
				if (sArray2.length > 1) {
					// if line containing student is found, edit it
					if (sArray2[0].equals(username)) {
						String newLine2 = usernameField + ", " + sArray2[1] + ", "
								+ gpaField;
						fileContent2.append(newLine2);
						fileContent2.append("\n");
						// otherwise keep it as it is
					} else {
						fileContent2.append(line2);
						fileContent2.append("\n");
					}
				}
			}

			FileWriter fstreamWrite2 = new FileWriter("src/Applications.txt");
			BufferedWriter out2 = new BufferedWriter(fstreamWrite2);
			out2.write(fileContent2.toString());
			out2.close();

		} catch (Exception ex) {
			System.out.println("Problem reading file.");
		}
	}
}