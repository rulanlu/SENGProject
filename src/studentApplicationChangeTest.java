import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class studentApplicationChangeTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		try {
			String expectedArray[] = {"MattTest","President's Scholarship","3.80"};
			String resultArray[] = null;
			String username = "MattTest";
			String usernameContent = "MattTest";
			
			//Set value back to original test value
			String GPA = "3.9";
			changeStudentEligibility sEligible = new changeStudentEligibility();
			sEligible.changeEligibility(username, usernameContent, GPA);
			
			//change GPA
			String newGPA = "3.80";
			sEligible.changeEligibility(username, usernameContent, newGPA);
			
			//read file and check for changes
//			FileInputStream fstream2 = new FileInputStream("src/Applications.txt");
//			BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream2));
//			String line2;
//			
//			while ((line2 = br2.readLine()) != null) {
//				String sArray2[] = line2.split(", ");
//				if (sArray2.length > 1) {
//					// if line containing student is found, edit it
//					if (sArray2[0].equals(username)) {
//						resultArray = sArray2;
//					}
//				}
//			}
			fileReader getStudent = new fileReader();
			resultArray = getStudent.findStudent(username, "src/Applications.txt");
			//compare the arrays 
			Assert.assertArrayEquals(expectedArray, resultArray);
		} catch (Exception e) {
			System.out.println("Problem reading file");
		}
		
	}

}
