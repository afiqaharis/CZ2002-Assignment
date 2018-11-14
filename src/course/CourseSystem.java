package course;

import java.util.ArrayList;

import professor.*;
import scrame.FileIO;
import scrame.Utility;
import scrame.Validator;

/**
 * System to manage all courses within the school
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public class CourseSystem {
	
	/**
	 * List of all courses within the system
	 */
	private static ArrayList<Course> courses = new ArrayList<Course>();
	
	/**
	 * Creates a new course management system
	 * @param data			Any existing data which contains the information of all students
	 * @param allProfs		List of all professors within the system, to find the corresponding course coordinator for the course
	 */
	public CourseSystem(ArrayList<String[]> data, ArrayList<Professor> allProfs) {
		parseData(data, allProfs);
	}
	
	/**
	 * Parses a text data loads them into the student management system
	 * @param data		Text data to be loaded in
	 * @param allProfs	List of all professors within the system, to find the corresponding course coordinator for the course
	 */
	private void parseData(ArrayList<String[]> data, ArrayList<Professor> allProfs) {
		for (String[] courseData:data) {
			Professor selectedProfessor = null;
			String courseCode 			= courseData[0].trim();
			String courseName 			= courseData[1].trim();
			int courseType 				= Integer.parseInt(courseData[2].trim());
			String courseCoordinator 	= courseData[3].trim();
			int numTutLabGroups 		= Integer.parseInt(courseData[4].trim());
			
			for (Professor professor:allProfs) {
				if (courseCoordinator.equals(professor.getName())) {
					selectedProfessor = professor;
				}
			}
			
			Course student = new Course(courseCode, courseName, courseType, selectedProfessor, numTutLabGroups);
			courses.add(student);
		}
	}
	
	/**
	 * Get list of all courses within this system
	 * @return		The list of all courses within this system
	 */
	@SuppressWarnings("static-access")
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
	
	/**
	 * Retrieve a particular course from the list of courses within this system
	 * @return		The requested course
	 */
	@SuppressWarnings("static-access")
	public Course getCourse() {
		int courseOption;
		this.printCourses();
		String question = String.format("Select a course from the list (1 ~ %d): ", this.courses.size());
		courseOption = Utility.readIntOption(question);
		
		if (courseOption < 1 || courseOption > this.courses.size()) {
			Utility.printErrorMessage("Please choose from the options in the list");
		} else {
			return courses.get(courseOption - 1);
		}

		return null;
	}
	
	/**
	 * Retrieve a particular course via course code from the list of courses within this system
	 * @return		The requested course
	 */
	public static Course getCourse(String courseCode) {
		for (Course course:courses) {
			if (course.getCode().equals(courseCode)) {
				return course;
			}
		}
		return null;
	}
	
	/**
	 * Add a new course to the system
	 */
	@SuppressWarnings("static-access")
	public void addCourse() {
		boolean courseCodeValidated = false, courseNameValidated = false;
		String courseCode, courseName;
		System.out.println();
		
		do {
			boolean syntaxCheck = false, alreadyExistsCheck = true;
			courseCode = Utility.readStringInput("Enter the course code of the new course: ").toUpperCase();
			syntaxCheck = Validator.validateCourseCode(courseCode);
			for (Course course:courses) {
				if (course.getCode().equals(courseCode)) {
					Utility.printErrorMessage("There already exists a course with the code you entered!");
					alreadyExistsCheck = false;
				}
			}
			courseCodeValidated = syntaxCheck && alreadyExistsCheck;
		} while (!courseCodeValidated);
		
		System.out.println();
		
		do {
			boolean alreadyExistsCheck = true;
			courseName = Utility.readStringInput("Enter the name of the new course: ");
			for (Course course:courses) {
				if (course.getName().equals(courseName)) {
					Utility.printErrorMessage("There already exists a course with the name you entered!");
					alreadyExistsCheck = false;
				}
			}
			courseNameValidated = alreadyExistsCheck;
		} while (!courseNameValidated);
		
		System.out.println();
		String[] courseTypeMenu = { "Lecture Only", "Lecture and Tutorial Only", "Lecture, Tutorial and Lab" };
		int type = Utility.getUserOption("Select the type for the new course (1 ~ 3): ", courseTypeMenu, false);
		
		int numTutLabGroups = 0;
		if (type == 2) {
			numTutLabGroups = Utility.readIntOption("Enter the number of tutorial groups you\'d like for this course: ");
		} else if (type == 3) {
			numTutLabGroups = Utility.readIntOption("Enter the number of tutorial and lab groups you\'d like for this course: ");
		}
		
		System.out.println();
		Professor selectedProfessor = ProfessorSystem.getProfessor();
		
		Course newCourse = new Course(courseCode, courseName, type, selectedProfessor, numTutLabGroups);
		this.courses.add(newCourse);
		
		String[] courseData = {
			String.format("%-8s", newCourse.getCode()),
			String.format("%-43s", newCourse.getName()),
			String.format("%-3d", newCourse.getType()),
			String.format("%-19s", newCourse.getCourseCoordinator().getName()),
			String.format("%s", newCourse.getNumGroups())
		};
		
		FileIO.writeData("Courses", courseData);
		String successMsg = String.format("Added new course: %s: %s!", newCourse.getCode(), newCourse.getName());
		Utility.printSuccessMessage(successMsg);
		newCourse.printAvailability();
	}
	
	/**
	 * Adjust the course assessment weightage or add subcomponents to the course's course work assessment
	 */
	public void adjustCourseSettings() {
		Course selectedCourse = this.getCourse();
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
	
	/**
	 * Print the statistics of the selected course
	 */
	public void printCourseStatistics() {
		Course selectedCourse = this.getCourse();
		selectedCourse.printStatistics();
	}
	
	/**
	 * Print the assessment components and their corresponding weightages of a selected course
	 */
	public void printCourseWeightages() {
		Course selectedCourse = this.getCourse();
		selectedCourse.printWeightages();
	}
	
	/**
	 * Print the overall availability of a selected course
	 */
	public void printCourseAvailability() {
		Course selectedCourse = this.getCourse();
		selectedCourse.printAvailability();
	}
	
	/**
	 * Print the list of students of a selected course by its groups
	 */
	public void printStudentListByCourseGroup() {
		Course selectedCourse = this.getCourse();
		selectedCourse.printStudents();
	}
	
	/**
	 * Print the list of all courses in the system
	 */
	public void printCourses() {
		if (courses.isEmpty()) {			
			String noticeMsg = String.format("There are no courses available.");
			Utility.printNoticeMessage(noticeMsg);
		} else {
			System.out.println("====================================================================");
			System.out.println("| No | Course Code | Course Name                                   |");
			System.out.println("====================================================================");
			for (Course course: courses) {
	    		System.out.printf("| %-3d| %-12s| %-46s|\n", courses.indexOf(course) + 1,
	    			course.getCode(), course.getName());
	    	}
			System.out.println("====================================================================");
			System.out.println();
		}
	}
}
