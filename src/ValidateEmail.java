import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmail implements Validate {
	static boolean checkValid(String email) {

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


	public boolean checkValid() {
		//Default case
		return false;
	}
	}


