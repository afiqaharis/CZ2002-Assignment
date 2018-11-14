package scrame;

/**
 * Contains high level generic functions for all other classes to use.
 * @author joshenlim
 * @version 1.0
 * @since 2018-11-14
 */
public class Utility {
	
	/**
	 * Prints out the question to receive a string input from the user
	 * @param prompt	Question to be displayed
	 * @return 			User's input as a string
	 */
	@SuppressWarnings("resource")
	public static String readStringInput(String prompt) {
		System.out.print(prompt);
		return new java.util.Scanner(System.in).nextLine();
	}
	
	/**
	 * Prints out the question to receive an integer input from the user
	 * @param prompt	Question to be displayed
	 * @return			User's input as an integer.
	 */
	public static int readIntOption(String prompt) {
		int input = 0;
		boolean valid = false;
		while (!valid) {
			try {
				input = Integer.parseInt(readStringInput(prompt));
				valid = true;
			} catch (NumberFormatException e) {
				Utility.printErrorMessage("Please enter an integer");
			}
		}
		System.out.println();
		return input;
	}
	
	/**
	 * Formats and prints out an error message
	 * @param msg		Error message to be displayed
	 */
	public static void printErrorMessage(String msg) {
		System.out.println();
		System.out.println("Error: " + msg);
		System.out.println();
	}
	
	/**
	 * Formats and prints out a success message
	 * @param msg		Success message to be displayed
	 */
	public static void printSuccessMessage(String msg) {
		System.out.println();
		System.out.println("Success: " + msg);
		System.out.println();
	}
	
	/**
	 * Formats and prints out an notification message
	 * @param msg		Notification message to be displayed
	 */
	public static void printNoticeMessage(String msg) {
		System.out.println();
		System.out.println("Notice: " + msg);
		System.out.println();
	}
	
	/**
	 * Prints out a line of a specified characters by a specified number of frequency
	 * @param frequency		Number of characters to print out in the line
	 * @param character 	Character to be printed out
	 */
	private static void printLine(int frequency, char character) {
		String line = "";
		for (int i = 0; i < frequency; i++) {
			line += character;
		}
		System.out.println(line);
	}
	
	/**
	 * Formats and prints out a menu with a given title and list of options
	 * @param title				Title of the menu to be displayed
	 * @param menu				Menu options to be provided for the user to select from
	 * @param addBackOption		To include a back option if required
	 */
	private static void printMenu(String title, String[] menu, boolean addBackOption) {
		printLine(60, '=');
		System.out.printf("| %-56s |\n", title);
		printLine(60, '=');
		for (int i = 0; i < menu.length; i++) {
			System.out.printf("| %d | %-52s |\n", (i + 1), menu[i]);
		}
		if (addBackOption) System.out.printf("| %d | %-52s |\n", 0, "Back");
		printLine(60, '=');
	}
	
	/**
	 * Prints out a specified menu and receives an integer input from the user as the choice from the list of options.
	 * @param title				Title of the menu to be displayed
	 * @param menu				Menu options to be provided for the user to select from
	 * @param addBackOption		To include a back option if required
	 * @return					The option that the user selected
	 */
	public static int getUserOption(String title, String[] menu, boolean addBackOption) {
		int choice, lastOption = 1;
		if (addBackOption) lastOption = 0;
		printMenu(title, menu, addBackOption);
		
		do {
			choice = readIntOption("Select an option from the above list: ");
			if (choice > menu.length || choice < lastOption) {
				printErrorMessage("Please choose an option from the list");
			}
		} while (choice > menu.length || choice < lastOption);
		
		return choice;
	}
}
