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
				printCourseSettingsMenu();
				break;
			case 5:
				Student selectedStudent = school.getStudent();
				MarkEntry.enterStudentMarks(selectedStudent);
				break;
			case 6:
				selectedStudent = school.getStudent();
				selectedStudent.printTranscript(school);
				break;
			default:
				Utility.printErrorMessage("Error: Please choose from the options in the list.");
				break;
		}
		
	}
	
	private void printViewRecordsMenu() {
		int option2;
		Course selectedCourse;
		String[] viewRecordMenu = {
			"View All Students", "View All Courses", "View all Professors",
			"View Student List By Course", "View Course Availability",
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
					selectedCourse = school.getCourse();
					selectedCourse.printStudents();
					break;
				case 5:
					selectedCourse = school.getCourse();
					selectedCourse.printAvailability();
					break;
				case 6:
					selectedCourse = school.getCourse();
					selectedCourse.printWeightages();
					break;
				case 7:
					selectedCourse = school.getCourse();
					selectedCourse.printStatistics();
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
	
	private void printCourseSettingsMenu() {
		Course selectedCourse = school.getCourse();
		String[] courseSettingsMenu = { "Change Assessment Weightages", "Add Subcomponent to Coursework Assessment" };
		int option2;
		do {
			option2 = Utility.getUserOption(String.format("Select which setting you\'d like to change under %-6s", selectedCourse.getCode()), courseSettingsMenu, true);
			switch(option2) {
				case 1:
					selectedCourse.updateAssessmentWeightage();
					break;
				case 2:
					selectedCourse.addSubComponent();
					break;
				case 0:
					break;
			}
		} while (option2 != 0);
	}
}
