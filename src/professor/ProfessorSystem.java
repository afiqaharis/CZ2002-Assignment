package professor;
import java.util.ArrayList;

import scrame.FileIO;
import scrame.Utility;
import scrame.Validator;

public class ProfessorSystem {
	private static ArrayList<Professor> professors = new ArrayList<Professor>();
	
	public ProfessorSystem(ArrayList<String[]> data) {
		parseData(data);
	}
	
	private void parseData(ArrayList<String[]> data) {
		for (String[] profData:data) {
			String profName 	= profData[0];
			String profId 		= profData[1];
			String profEmail 	= profData[2];
			Professor professor = new Professor(profName, profId, profEmail);
			professors.add(professor);
		}
	}
	
	public ArrayList<Professor> getProfessors() {
		return professors;
	}
	
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
