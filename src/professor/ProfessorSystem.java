package professor;
import java.util.ArrayList;

import scrame.FileIO;
import scrame.Utility;
import scrame.Validator;

/**
 * System to manage all professors employed in the school
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public class ProfessorSystem {
	/**
	 * List of all the professors within this system
	 */
	private static ArrayList<Professor> professors = new ArrayList<Professor>();
	
	/**
	 * Creates a new professor management system
	 * @param data		Any existing data which contains the information of all professors
	 */
	public ProfessorSystem(ArrayList<String[]> data) {
		parseData(data);
	}
	
	/**
	 * Parses a text data loads them into the professor management system
	 * @param data		Text data to be loaded in
	 */
	private void parseData(ArrayList<String[]> data) {
		for (String[] profData:data) {
			String profName 	= profData[0];
			String profId 		= profData[1];
			String profEmail 	= profData[2];
			Professor professor = new Professor(profName, profId, profEmail);
			professors.add(professor);
		}
	}
	
	/**
	 * Get list of all professors within this system
	 * @return		The list of all professors within this system
	 */
	public ArrayList<Professor> getProfessors() {
		return professors;
	}
	
	/**
	 * Retrieve a particular student from the list of students within this system
	 * @return		The requests professor
	 */
	public static Professor getProfessor() {
		int profOption;
		printProfessors();
		String question = String.format("Select a professor from the list (1 ~ %d): ", professors.size());
		profOption = Utility.readIntOption(question);
		
		if (profOption < 1 || profOption > professors.size()) {
			Utility.printErrorMessage("Please choose from the options in the list");
		} else {
			return professors.get(profOption - 1);
		}

		return null;
	}
	
	/**
	 * Add a new professor to this system
	 */
	@SuppressWarnings("static-access")
	public void addProfessor() {
		boolean nameValidated = false, emailValidated = false;
		String name, email;
		
		do {
			name = Utility.readStringInput("Enter the name of the new professor: ");
			if (Validator.validateName(name)) nameValidated = true;
		} while (!nameValidated);
		
		System.out.println();
		
		do {
			boolean syntaxCheck = false, alreadyExistsCheck = true;
			email = Utility.readStringInput("Enter the email address of the new professor: ");
			syntaxCheck = Validator.validateEmail(email);
			
			for (Professor professor:professors) {
				if (professor.getEmail().equals(email)) {
					Utility.printErrorMessage("There already exists a professor with the email you entered!");
					alreadyExistsCheck = false;
				}
			}
			
			emailValidated = syntaxCheck && alreadyExistsCheck;
		} while (!emailValidated);
		
		
		Professor newProf = new Professor(name, email);
		this.professors.add(newProf);
		String[] profData = {newProf.getName(), newProf.getId(), newProf.getEmail()};
		FileIO.writeData("Professors", profData);
		String successMsg = String.format("Added new professor: %s!", name);
		Utility.printSuccessMessage(successMsg);
		printProfessors();
	}
	
	/**
	 * Print the list of all professors within this system
	 */
	public static void printProfessors() {
		if (professors.isEmpty()) {	
			String noticeMsg = String.format("There are currently no professors employed.");
			Utility.printNoticeMessage(noticeMsg);
		} else {
			System.out.println("======================================================================");
			System.out.println("| No | Name                | Professor ID | Email                    |");
			System.out.println("======================================================================");
			for (Professor professor: professors) {
	    		System.out.printf("| %-3d| %-20s| %-13s| %-25s|\n", professors.indexOf(professor) + 1, 
	    				professor.getName(), professor.getId(), professor.getEmail());
	    	}
			System.out.println("======================================================================");
			System.out.println();
		}
	}
}
