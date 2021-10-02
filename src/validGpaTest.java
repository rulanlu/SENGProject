

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class validGpaTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		validGpa isValid = new validGpa();
		String willPassGpa = "3.00";
		String willFailGpa = "5.00";
		Assert.assertTrue(!isValid.isValidGpa(willFailGpa) && isValid.isValidGpa(willPassGpa));
	}

}
