import java.util.ArrayList;
import java.util.Scanner;

public class MarkEntry {
	public static void enterStudentMarks(Scanner sc, Student student) {	
		int option1, option2;
		ArrayList<Mark> studentResults = student.getResults();
		if (studentResults.isEmpty()) {
			System.out.println();
			System.out.println("Error: Student has not been registered to any course.");
			System.out.println();
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
			option1 = sc.nextInt();
			sc.nextLine();
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
				option2 = sc.nextInt();
				sc.nextLine();
				
				if (option2 > 0) {
					String selectedComponent = components.get(option2 - 1);
					
					if (selectedComponent.equals("Coursework") && selectedResult.getComponentMarkMapping().size() > 2) {
						System.out.println("Error: Unable to directly allocate marks to Coursework since it has sub components");
					} else {
						System.out.printf("Enter the marks for the %s component: (Out of 100)\n", selectedComponent);
						int marks = sc.nextInt();
						sc.nextLine();
						
						if (marks > 100) {
							System.out.println();
							System.out.println("Error: Marks entered should not exceed 100.");
							System.out.println();
						} else {
							selectedResult.setComponentMarks(selectedComponent, marks);
							System.out.println();
							System.out.printf("Successfully updated marks for the %s component!\n", selectedComponent);
							System.out.println();
						}
					}
				}
			} while (option2 > 0);
		}
	}
}
