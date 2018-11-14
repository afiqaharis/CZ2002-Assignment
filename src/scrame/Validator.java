package scrame;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the validation for user inputs. Validation
 * functions are specific to the context of the SCRAME App.
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public class Validator {
	/**
	 * Validate the specified name
	 * @param sentence	Name to be validated
	 * @return			True if name only contains characters
	 */
	public static boolean validateName(String sentence) {
		if (!sentence.matches("[ a-zA-Z]+")) {
			Utility.printErrorMessage("Student name should only contain characters, please try again!");
			return false;
		}
		return true;
	}
	
	/**
	 * Validate the specified NRIC
	 * @param nric	NRIC to be validated
	 * @return		True if the NRIC contains only 9 letters, and NRIC begins/ends with an alphabet
	 */
	public static boolean validateNRIC(String nric) {
		if (nric.length() != 9) {
			Utility.printErrorMessage("NRIC should contain 9 letters");
			return false;
		}
		
		if (!Character.isLetter(nric.charAt(0)) || !Character.isLetter(nric.charAt(nric.length() - 1))) {
			Utility.printErrorMessage("NRIC should begin and and with an alphabet");
			return false;
		}
		return true;
	}
	
	/**
	 * Validate the specified email
	 * @param email	Email to be validated
	 * @return		True if the email satisfies a general email regular expression(regex).
	 */
	public static boolean validateEmail(String email) {
		String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		Matcher matcher;
		matcher = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE).matcher(email);
		
		if (!matcher.matches()) {
			Utility.printErrorMessage("Please enter a valid email address");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Validate the specified course course
	 * @param code	Course code to be validated
	 * @return		True if the course code contains 6 letters, and Course code begins with 2 alphabets
	 */
	public static boolean validateCourseCode(String code) {
		if (code.length() != 6) {
			Utility.printErrorMessage("Course Code should contain 6 letters");
			return false;
		}
		
		if (!(Character.isLetter(code.charAt(0)) && (Character.isLetter(code.charAt(1))))) {
			Utility.printErrorMessage("Course Code should begin with 2 alphabets");
			return false;
		}
		return true;
	}
}
