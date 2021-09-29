
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class StudentLoginInformation extends JFrame {

	protected static String username;
	protected static String password;

	public StudentLoginInformation() throws HeadlessException {
		super();
	}

	public StudentLoginInformation(GraphicsConfiguration gc) {
		super(gc);
	}

	public StudentLoginInformation(String title) throws HeadlessException {
		super(title);
	}

	public StudentLoginInformation(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

}