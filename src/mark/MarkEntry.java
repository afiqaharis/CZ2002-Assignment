package mark;
import java.util.ArrayList;
import scrame.Utility;
import student.Student;

public class MarkEntry {
	public static void enterStudentMarks(Student student) {	
		String question;
		int option1, option2;
		ArrayList<Mark> studentResults = student.getResults();
		if (studentResults.isEmpty()) {
			Utility.printErrorMessage("Student has not been registered to any course.");
			return;
		} else {
			String[] menu1 = new String[studentResults.size()];
			for (Mark result:studentResults) {
				menu1[studentResults.indexOf(result)] = String.format(" %-6s| %-43s", result.getCourseCode(), result.getCourseName());
			}
			question = String.format("Select which course to enter marks for %-17s", student.getName());
			option1 = Utility.getUserOption(question, menu1, false);
			Mark selectedResult = studentResults.get(option1 - 1);
			
			ArrayList<String> components = new ArrayList<String>(selectedResult.getComponentMarkMapping().keySet());
			
			do {
				String[] menu2 = new String[components.size()];
				for (String component:components) {
					menu2[components.indexOf(component)] = component;
				}
				option2 = Utility.getUserOption("Select which component to enter marks for", menu2, true);
				
				if (option2 > 0) {
					String selectedComponent = components.get(option2 - 1);
					
					if (selectedComponent.equals("Coursework") && selectedResult.getComponentMarkMapping().size() > 2) {
						Utility.printErrorMessage("Unable to directly allocate marks to Coursework since it has sub components");
					} else {
						question = String.format("Enter the marks for the %s component (Out of 100): ", selectedComponent);
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
