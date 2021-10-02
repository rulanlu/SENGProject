public class validGpa {
	public boolean isValidGpa(String GPA) {
		Double gpa = Double.parseDouble(GPA);
		return gpa < 4.30 && gpa > 0.00;
	}
}