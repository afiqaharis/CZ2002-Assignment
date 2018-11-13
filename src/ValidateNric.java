
public class ValidateNric implements Validate {
	static boolean checkValid(String nric) {

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

	public boolean checkValid() {
		//Default case
		return false;
	}
}