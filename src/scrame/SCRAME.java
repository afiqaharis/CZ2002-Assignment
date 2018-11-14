package scrame;

import course.Course;
import course.CourseSystem;
import professor.ProfessorSystem;
import student.Student;
import student.StudentSystem;

public class SCRAME {
	private ProfessorSystem professorSystem;
	private StudentSystem studentSystem;
	private CourseSystem courseSystem;

	private void initialize() {
		professorSystem = new ProfessorSystem(FileIO.readData("Professors"));
		studentSystem = new StudentSystem(FileIO.readData("Students"));
		courseSystem = new CourseSystem(FileIO.readData("Courses"), professorSystem.getProfessors());
	}
	
	public void start() {
		initialize();
		while(true) printMainMenu();
	}
	
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
