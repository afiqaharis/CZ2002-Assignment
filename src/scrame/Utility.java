package scrame;
public class Utility {
	
	@SuppressWarnings("resource")
	public static String readStringInput(String prompt) {
		System.out.print(prompt);
		return new java.util.Scanner(System.in).nextLine();
	}
	
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
	
	public static void printErrorMessage(String msg) {
		System.out.println();
		System.out.println("Error: " + msg);
		System.out.println();
	}
	
	public static void printSuccessMessage(String msg) {
		System.out.println();
		System.out.println("Success: " + msg);
		System.out.println();
	}
	
	public static void printNoticeMessage(String msg) {
		System.out.println();
		System.out.println("Notice: " + msg);
		System.out.println();
	}
	
	private static void printLine(int frequency, char character) {
		String line = "";
		for (int i = 0; i < frequency; i++) {
			line += character;
		}
		System.out.println(line);
	}
	
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
