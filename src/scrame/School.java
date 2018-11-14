package scrame;
import java.util.ArrayList;

import course.Course;
import mark.MarkEntry;
import person.Professor;
import person.Student;

public class School {
	private String name;
	private ArrayList<Student> students;
	private ArrayList<Professor> professors;
	private ArrayList<Course> courses;
	
	public School (String name) {
		this.name = name;
		this.students = FileIO.retrieveExistingStudents();
		this.professors = FileIO.retrieveExistingProfessors();
		this.courses = FileIO.retrieveExistingCourses(this.professors);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Student> getStudents() {
		return this.students;
	}
	
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
	
	public ArrayList<Professor> getProfessors() {
		return this.professors; 
	}
	
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
	
	public Course getCourse(String courseCode) {
		for (Course course:courses) {
			if (course.getCode().equals(courseCode)) {
				return course;
			}
		}
		return null;
	}
	
	public Student getStudent() {
		int studentOption;
		this.printStudents();
		String question = String.format("Select a student from the list: (1 ~ %d)\n", this.students.size());
		studentOption = Utility.readIntOption(question);
		
		if (studentOption < 1 || studentOption > this.students.size()) {
			Utility.printErrorMessage("Please choose from the options in the list");
		} else {
			return students.get(studentOption - 1);
		}
		
		return null;
	}
	
	public void addStudent() {
		boolean nameValidated = false, nricValidated = false, emailValidated = false;
		String ic, name, email;
		System.out.println();
		
		do {
			boolean syntaxCheck = false, alreadyExistsCheck = true;
			ic = Utility.readStringInput("Enter the NRIC of the new student: ").toUpperCase();
			syntaxCheck = Validator.validateNRIC(ic);
			for (Student student:students) {
				if (student.getIc().equals(ic)) {
					Utility.printErrorMessage("There already exists a student with the NRIC you entered!");
					alreadyExistsCheck = false;
				}
			}
			nricValidated = syntaxCheck && alreadyExistsCheck;
		} while (!nricValidated);
		
		System.out.println();
		
		do {
			name = Utility.readStringInput("Enter the name of the new student: ");
			if (Validator.validateName(name)) nameValidated = true;
		} while (!nameValidated);
		
		System.out.println();
		
		do {
			boolean syntaxCheck = false, alreadyExistsCheck = true;
			email = Utility.readStringInput("Enter the email address of the new student: ");
			syntaxCheck = Validator.validateEmail(email);
			
			for (Student student:students) {
				if (student.getEmail().equals(email)) {
					Utility.printErrorMessage("There already exists a student with the email you entered!");
					alreadyExistsCheck = false;
				}
			}
			
			emailValidated = syntaxCheck && alreadyExistsCheck;
		} while (!emailValidated);	
		
		Student newStudent = new Student(name, ic, email);
		this.students.add(newStudent);
		FileIO.writeNewStudent(newStudent);
		String successMsg = String.format("Added new student %s with assigned matriculation number: %s!\n", 
				newStudent.getName(), newStudent.getMatricNumber());
		Utility.printSuccessMessage(successMsg);
		this.printStudents();
	}
	
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
		FileIO.writeNewProfessor(newProf);
		String successMsg = String.format("Added new professor: %s!\n", newProf.getName());
		Utility.printSuccessMessage(successMsg);
	}
	
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
				if (course.getName().equals(name)) {
					Utility.printErrorMessage("There already exists a course with the name you entered!");
					alreadyExistsCheck = false;
				}
			}
			courseNameValidated = alreadyExistsCheck;
		} while (!courseNameValidated);
		
		System.out.println();
		String[] courseTypeMenu = { "Lecture Only", "Lecture and Tutorial Only", "Lecture, Tutorial and Lab" };
		int type = Utility.getUserOption("Select the type for the new course: (1 ~ 3)", courseTypeMenu, false);
		
		int numTutLabGroups = 0;
		if (type == 2) {
			numTutLabGroups = Utility.readIntOption("Enter the number of tutorial groups you\'d like for this course");
		} else if (type == 3) {
			numTutLabGroups = Utility.readIntOption("Enter the number of tutorial and lab groups you\'d like for this course");
		}
		
		System.out.println();
		this.printProfessors();
		String question = String.format("Select a course coordinator from the list of professors: (1 ~ %d)\n", this.professors.size());
		int profIndex = Utility.readIntOption(question);
		Professor selectedProfessor = this.professors.get(profIndex - 1);
		
		Course newCourse = new Course(courseCode, courseName, type, selectedProfessor, numTutLabGroups);
		this.courses.add(newCourse);
		FileIO.writeNewCourse(newCourse);
		String successMsg = String.format("Added new course: %s: %s!\n", newCourse.getCode(), newCourse.getName());
		Utility.printSuccessMessage(successMsg);
		this.printCourses();
	}
	
	public void registerStudentToCourse() {
		this.printStudentNames();
		String question1 = String.format("Select which student you\'d like to register: (1 ~ %d)\n", this.students.size());
		int studentOption = Utility.readIntOption(question1);
		Student selectedStudent = students.get(studentOption - 1);
		
		this.printCourses();
		String question2 = String.format("Select which course you\'d like to register %s to: (1 ~ %d)\n", selectedStudent.getName(), this.courses.size());
		int courseOption = Utility.readIntOption(question2);
		Course selectedCourse = courses.get(courseOption - 1);
		
		selectedCourse.registerStudent(selectedStudent);
	}
	
	public void printStudents() {
		if (students.isEmpty()) {			
			String noticeMsg = String.format("There are currently no students enrolled into %s\n", this.name);
			Utility.printNoticeMessage(noticeMsg);
		} else {
			System.out.println();
			System.out.println("=======================================================================");
			System.out.println("| No | Name                | Matric Number | Email                    |");
			System.out.println("=======================================================================");
			for (Student student: students) {
	    		System.out.printf("| %-3d| %-20s| %-14s| %-25s|\n", students.indexOf(student) + 1, 
	    				student.getName(), student.getMatricNumber(), student.getEmail());
	    	}
			System.out.println("=======================================================================");
		}
	}
	
	public void printProfessors() {
		if (professors.isEmpty()) {	
			String noticeMsg = String.format("There are currently no professors employed with %s\n", this.name);
			Utility.printNoticeMessage(noticeMsg);
		} else {
			System.out.println();
			System.out.println("======================================================================");
			System.out.println("| No | Name                | Professor ID | Email                    |");
			System.out.println("======================================================================");
			for (Professor professor: professors) {
	    		System.out.printf("| %-3d| %-20s| %-13s| %-25s|\n", professors.indexOf(professor) + 1, 
	    				professor.getName(), professor.getId(), professor.getEmail());
	    	}
			System.out.println("======================================================================");
		}
	}
	
	public void printCourses() {
		if (courses.isEmpty()) {			
			String noticeMsg = String.format("There are no courses under %s\n", this.name);
			Utility.printNoticeMessage(noticeMsg);
		} else {
			System.out.println();
			System.out.println("====================================================================");
			System.out.println("| No | Course Code | Course Name                                   |");
			System.out.println("====================================================================");
			for (Course course: courses) {
	    		System.out.printf("| %-3d| %-12s| %-46s|\n", courses.indexOf(course) + 1,
	    			course.getCode(), course.getName());
	    	}
			System.out.println("====================================================================");
		}
	}
	
	public void printStudentNames() {
		if (students.isEmpty()) {
			String noticeMsg = String.format("There are currently no students enrolled into %s\n", this.name);
			Utility.printNoticeMessage(noticeMsg);
		} else {
			System.out.println();
			System.out.println("============================");
			System.out.println("| No | Name                |");
			System.out.println("============================");
			for (Student student: students) {
	    		System.out.printf("| %-3d| %-20s|\n", students.indexOf(student) + 1, student.getName());
	    	}
			System.out.println("============================");
		}
	}
	
	public void printStudentListByCourseGroup() {
		Course selectedCourse = this.getCourse();
		selectedCourse.printStudents();
	}
	
	public void printCourseAvailability() {
		Course selectedCourse = this.getCourse();
		selectedCourse.printAvailability();
	}
	
	public void printCourseWeightages() {
		Course selectedCourse = this.getCourse();
		selectedCourse.printWeightages();
	}
	
	public void printCourseStatistics() {
		Course selectedCourse = this.getCourse();
		selectedCourse.printStatistics();
	}
	
	public void studentMarkEntry() {
		Student selectedStudent = this.getStudent();
		MarkEntry.enterStudentMarks(selectedStudent);
	}
	
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
	
	public void printStudentTranscript() {
		Student selectedStudent = this.getStudent();
		selectedStudent.printTranscript(this);
	}
}
