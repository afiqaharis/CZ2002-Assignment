
public class ValidateCourseCode implements Validate {
	static boolean checkValid(String code){

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

	@Override
	public boolean checkValid() {
		// default case
		return false;
	}
		
}


