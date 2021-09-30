import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Arrays;
class changeStudentInformationTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		try {
			changeStudentInformation student = new changeStudentInformation();
			
			String expectedArray[] = {"MattTest","4321","3.20","Science", "No"};
			String resultArray[] = null;
			String username = "MattTest";
			String usernameField = "MattTest";
			//reset all values of MattTest to
			String password = "4321";
			String GPA = "3.90";
			String Faculty = "Education";
			String Awarded = "No";
			student.changeInformation(username, usernameField, password, GPA, Faculty, Awarded);
			
			// changing GPA and faculty
			String newGPA = "3.20";
			String newFaculty = "Science";
			
			student.changeInformation(username, usernameField, password, newGPA, newFaculty, Awarded);
			//read file and check for changes 
			FileInputStream fstream = new FileInputStream("src/student.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			StringBuilder fileContent = new StringBuilder();

			while ((line = br.readLine()) != null) {
				String sArray[] = line.split(", ");
				if (sArray.length > 1) {
					// if line containing student is found, edit it
					if (sArray[0].equals(username)) {
						resultArray = sArray;
					} 
				}
			}
			//compare the arrays.
			Assert.assertArrayEquals(expectedArray, resultArray);
		}
		catch (Exception e){
			System.out.println("Problem reading file.");
		}
		
	}

}
