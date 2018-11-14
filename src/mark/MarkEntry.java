package mark;
import java.util.ArrayList;

import person.Student;
import scrame.Utility;

public class MarkEntry {
	public static void enterStudentMarks(Student student) {	
		int option1, option2;
		ArrayList<Mark> studentResults = student.getResults();
		if (studentResults.isEmpty()) {
			Utility.printErrorMessage("Student has not been registered to any course.");
		} else {
			System.out.println();
			System.out.println("===========================================================");
			System.out.printf("| Select which course to enter marks for %-17s|\n", student.getName());
			System.out.println("===========================================================");
			for (Mark result:studentResults) {
				int index = studentResults.indexOf(result);
				System.out.printf("| %-3d| %-6s| %-43s|\n", index + 1, result.getCourseCode(), result.getCourseName());
			}
			System.out.println("===========================================================");
			option1 = Utility.readIntOption("Select an option from the above list: ");
			Mark selectedResult = studentResults.get(option1 - 1);
			
			ArrayList<String> components = new ArrayList<String>(selectedResult.getComponentMarkMapping().keySet());
			
			do {
				System.out.println();
				System.out.println("===================================================");
				System.out.println("| Select which component to enter marks for       |");
				System.out.println("===================================================");
				
				for (String component:components) {
					int index = components.indexOf(component);
					System.out.printf("| %-3d| %-43s|\n", index + 1, component);
				}
				System.out.println("| 0  | Back                                       |");
				System.out.println("===================================================");
				option2 = Utility.readIntOption("Select an option from the above list: ");
				
				if (option2 > 0) {
					String selectedComponent = components.get(option2 - 1);
					
					if (selectedComponent.equals("Coursework") && selectedResult.getComponentMarkMapping().size() > 2) {
						Utility.printErrorMessage("Unable to directly allocate marks to Coursework since it has sub components");
					} else {
						String question = String.format("Enter the marks for the %s component (Out of 100): ", selectedComponent);
						int marks = Utility.readIntOption(question);
						
						if (marks > 100) {
							Utility.printErrorMessage("Marks entered should not exceed 100.");
						} else {
							selectedResult.setComponentMarks(selectedComponent, marks);
							String successMsg = String.format("Successfully updated marks for the %s component!", selectedComponent);							
							Utility.printSuccessMessage(successMsg);
						}
					}
				}
			} while (option2 > 0);
		}
	}
}
