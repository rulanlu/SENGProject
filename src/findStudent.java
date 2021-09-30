import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class fileReader {
	public String[] findStudent (String username, String filePath) {
		String resultArray[] = null;
		try {
			
			FileInputStream readFile = new FileInputStream(filePath);
			BufferedReader fileContents = new BufferedReader(new InputStreamReader(readFile));
			String contentString;

			while ((contentString = fileContents.readLine()) != null) {
				String applicant[] = contentString.split(", ");
				if (applicant.length > 1) {
					// if line containing student is found, edit it
					if (applicant[0].equals(username)) {
						resultArray = applicant;
					}
				}
			}
			fileContents.close();
			
		} catch (Exception e) {
			System.out.println("Unable to read file.");
		}
		return resultArray;
		
	}
}