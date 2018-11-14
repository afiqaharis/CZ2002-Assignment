package scrame;
import course.Course;
import course.CourseSystem;
import professor.ProfessorSystem;
import student.Student;
import student.StudentSystem;

/**
 * Main program of the Student Course Registration And Mark Entry System.
 * Initializes and connects each system (Student, Professor and Course) together
 * and serves as the menu for users to interact with. Each system is initialized
 * with data that is loaded from text files.
 * @author joshenlim
 * @version 1.0
 * @since 2018-11-14
 */

public class SCRAME {
	/**
	 * Professor System Controller: Stores the context of all professors in the system.
	 */
	private ProfessorSystem professorSystem;
	/**
	 * Student System Controller: Stores the context of all students in the system.
	 */
	private StudentSystem studentSystem;
	/**
	 * Course System Controller: Stores the context of all courses in the system.
	 */
	private CourseSystem courseSystem;
	
	/**
	 * Entry function called from SCRAMEApp. 
	 */
	public void start() {
		initialize();
		while(true) printMainMenu();
	}

	/**
	 * Initializes the system with existing data and updates the corresponding objects.
	 */
	private void initialize() {
		professorSystem = new ProfessorSystem(FileIO.readData("Professors"));
		studentSystem = new StudentSystem(FileIO.readData("Students"));
		courseSystem = new CourseSystem(FileIO.readData("Courses"), professorSystem.getProfessors());
	}
	
	/**
	 * Prints out the main menu for users to interact with. Allows users to view and add records,
	 * register students to courses, adjust course assessment settings, enter marks for students
	 * and print student transcript.
	 */
	private void printMainMenu() {
		Student selectedStudent;
		Course selectedCourse;
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
				System.out.println("Select which student you\'d like to register: ");
				selectedStudent = studentSystem.getStudent();

				System.out.println(String.format("Select which course you\'d like to register %s to: ", selectedStudent.getName()));
				selectedCourse = courseSystem.getCourse();
				
				selectedCourse.registerStudent(selectedStudent);
				break;
			case 4:
				courseSystem.adjustCourseSettings();
				break;
			case 5:
				studentSystem.studentMarkEntry();
				break;
			case 6:
				studentSystem.printStudentTranscript();
				break;
			default:
				Utility.printErrorMessage("Error: Please choose from the options in the list.");
				break;
		}
		
	}
	
	/**
	 * Second level menu when user selects view records in the main menu. Allows users to view all students,
	 * view all courses, view all professors, view student list by course group, view each course's availability,
	 * view course assessment criteria and view course statistics.
	 */
	@SuppressWarnings("static-access")
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
					studentSystem.printStudents();
					break;
				case 2:
					courseSystem.printCourses();
					break;
				case 3:
					professorSystem.printProfessors();
					break;
				case 4:
					courseSystem.printStudentListByCourseGroup();
					break;
				case 5:
					courseSystem.printCourseAvailability();
					break;
				case 6:
					courseSystem.printCourseWeightages();
					break;
				case 7:
					courseSystem.printCourseStatistics();
					break;
				case 0:
					break;
			}
		} while (option2 != 0);
	}
	
	/**
	 * Second level menu when user selects add records in the main menu. Allow users to add new student, 
	 * add new course and add new professor.
	 */
	private void printAddRecordsMenu() {
		String[] addRecordMenu = { "Add New Student", "Add New Course", "Add New Professor" };
		int option2;
		do {
			option2 = Utility.getUserOption("Select which records to add", addRecordMenu, true);
			switch(option2) {
				case 1:
					studentSystem.addStudent();
					break;
				case 2:
					courseSystem.addCourse();
					break;
				case 3:
					professorSystem.addProfessor();
					break;
				case 0:
					break;
			}
		} while (option2 != 0);
	}
}
