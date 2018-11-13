import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	public static boolean validateName(String sentence) {
		if (!sentence.matches("[a-zA-Z]+")) {
			System.out.println();
			System.out.println("Error: Student name should only contain letters, please try again!");
			return false;
		}
		return true;
	}
	
	public static boolean validateNRIC(String nric) {
		if (nric.length() != 9) {
			System.out.println();
			System.out.println("Error: NRIC should contain 9 letters");
			return false;
		}
		
		if (!Character.isLetter(nric.charAt(0)) || !Character.isLetter(nric.charAt(nric.length() - 1))) {
			System.out.println();
			System.out.println("Error: NRIC should begin and and with an alphabet");
			return false;
		}
		return true;
	}
	
	public static boolean validateEmail(String email) {
		String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		Matcher matcher;
		matcher = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE).matcher(email);
		
		if (!matcher.matches()) {
			System.out.println();
			System.out.println("Error: Please enter a valid email address");
			return false;
		}
		
		return true;
	}
	
	public static boolean validateCourseCode(String code) {
		if (code.length() != 6) {
			System.out.println();
			System.out.println("Error: Course Code should contain 6 characters");
			return false;
		}
		
		if (!(Character.isLetter(code.charAt(0)) && (Character.isLetter(code.charAt(1))))) {
			System.out.println();
			System.out.println("Error: Course Code should begin with 2 alphabets");
			return false;
		}
		return true;
	}
}
