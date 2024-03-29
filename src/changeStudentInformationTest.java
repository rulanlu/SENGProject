import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
			fileReader getStudent = new fileReader();
			resultArray = getStudent.findStudent(username, "src/student.txt");
			//compare the arrays.
			Assert.assertArrayEquals(expectedArray, resultArray);
		}
		catch (Exception e){
			System.out.println("Problem reading file.");
		}
		
	}

}
