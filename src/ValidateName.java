
public class ValidateName implements Validate{

	
	static boolean checkValid(String sentence) {
		if (!sentence.matches("[a-zA-Z]+")) {
			System.out.println();
			System.out.println("Error: Student name should only contain letters, please try again!");
			return false;
		}
		return true;
	}

	public boolean checkValid() {
		// Default case
		return false;
	}
}
