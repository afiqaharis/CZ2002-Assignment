package scrame;

public class SCRAME {
	private School school;

	private void initialize() {
		school = new School("SCSE");
	}
	
	public void start() {
		initialize();
		while(true) printMainMenu();
	}
	
	private void printMainMenu() {
		String[] mainMenu = {
				"View Records", "Add Records", "Register Student to Course",
				"Course Assessment Settings", "Mark Entry For Student",
				"Print Student Transcript"
			};
		int option1 = Utility.getUserOption("Student Course Registration and Mark Entry System", mainMenu, false);
		switch(option1) {
			case 1:
				printViewRecordsMenu();
				break;
			case 2:
				printAddRecordsMenu();
				break;
			case 3:
				school.registerStudentToCourse();
				break;
			case 4:
				school.adjustCourseSettings();
				break;
			case 5:
				school.studentMarkEntry();
				break;
			case 6:
				school.printStudentTranscript();
				break;
			default:
				Utility.printErrorMessage("Error: Please choose from the options in the list.");
				break;
		}
		
	}
	
	private void printViewRecordsMenu() {
		int option2;
		String[] viewRecordMenu = {
			"View All Students", "View All Courses", "View all Professors",
			"View Student List By Course Group", "View Course Availability",
			"View Course Assessments", "View Course Statistics"
		};
		do {
			option2 = Utility.getUserOption("Select which records to view", viewRecordMenu, true);
			switch(option2) {
				case 1:
					school.printStudents();
					break;
				case 2:
					school.printCourses();
					break;
				case 3:
					school.printProfessors();
					break;
				case 4:
					school.printStudentListByCourseGroup();
					break;
				case 5:
					school.printCourseAvailability();
					break;
				case 6:
					school.printCourseWeightages();
					break;
				case 7:
					school.printCourseStatistics();
					break;
				case 0:
					break;
			}
		} while (option2 != 0);
	}
	
	private void printAddRecordsMenu() {
		String[] addRecordMenu = { "Add New Student", "Add New Course", "Add New Professor" };
		int option2;
		do {
			option2 = Utility.getUserOption("Select which records to add", addRecordMenu, true);
			switch(option2) {
				case 1:
					school.addStudent();
					break;
				case 2:
					school.addCourse();
					break;
				case 3:
					school.addProfessor();
					break;
				case 0:
					break;
			}
		} while (option2 != 0);
	}
}
