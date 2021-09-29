import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class ScholarshipSystemTestRefector {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Test
	void test() {
		ScholarshipSystem sch = new ScholarshipSystem();
		try {
			Robot bot = new Robot();
			bot.mouseMove(635, 450);
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			try {
				Thread.sleep(250);
			}
			catch(InterruptedException e) {}
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
}
