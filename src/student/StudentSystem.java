package student;

import java.util.ArrayList;

import course.*;
import mark.*;
import scrame.FileIO;
import scrame.Utility;
import scrame.Validator;

public class StudentSystem {
	private ArrayList<Student> students = new ArrayList<Student>();
	
	public StudentSystem(ArrayList<String[]> data) {
		parseData(data);
	}
	
	private void parseData(ArrayList<String[]> data) {
		for (String[] studentData:data) {
			String studName 	= studentData[0].trim();
			String studIc 		= studentData[1].trim();
			String studMatric 	= studentData[2].trim();
			String studEmail 	= studentData[3].trim();
			Student student 	= new Student(studName, studIc, studMatric, studEmail);
			students.add(student);
		}
	}
	
	public ArrayList<Student> getStudents() {
		return this.students;
	}
	
	public Student getStudent() {
		int studentOption;
		this.printStudents();
		String question = String.format("Select a student from the list (1 ~ %d): ", this.students.size());
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
		
		String[] studData = {
				String.format("%-16s", newStudent.getName()),
				String.format("%-11s", newStudent.getIc()),
				String.format("%-11s", newStudent.getMatricNumber()),
				newStudent.getEmail()
		};
		
		FileIO.writeData("Students", studData);
		String successMsg = String.format("Added new student %s with assigned matriculation number: %s!", 
				newStudent.getName(), newStudent.getMatricNumber());
		Utility.printSuccessMessage(successMsg);
		this.printStudents();
	}
	
	public void studentMarkEntry() {
		Student selectedStudent = this.getStudent();
		MarkEntry.enterStudentMarks(selectedStudent);
	}
	
	public void printStudents() {
		if (students.isEmpty()) {			
			Utility.printNoticeMessage("There are currently no students enrolled");
		} else {
			System.out.println("=======================================================================");
			System.out.println("| No | Name                | Matric Number | Email                    |");
			System.out.println("=======================================================================");
			for (Student student: students) {
	    		System.out.printf("| %-3d| %-20s| %-14s| %-25s|\n", students.indexOf(student) + 1, 
	    				student.getName(), student.getMatricNumber(), student.getEmail());
	    	}
			System.out.println("=======================================================================");
			System.out.println();
		}
	}
	
	public void printStudentNames() {
		if (students.isEmpty()) {
			Utility.printNoticeMessage("There are currently no students enrolled.");
		} else {
			System.out.println("============================");
			System.out.println("| No | Name                |");
			System.out.println("============================");
			for (Student student: students) {
	    		System.out.printf("| %-3d| %-20s|\n", students.indexOf(student) + 1, student.getName());
	    	}
			System.out.println("============================");
		}
	}
	
	public void printStudentTranscript() {
		Student selectedStudent = this.getStudent();
		
		for (Mark result:selectedStudent.getResults()) {
			Course course = CourseSystem.getCourse(result.getCourseCode());
			ArrayList<Assessment> courseworkSubComponents = course.getAssessments().get(1).getSubComponents();
			
			if (courseworkSubComponents.size() > 0) {
				int courseWorkMarks = ComputeGrades.calculateWeightedMarks(
						courseworkSubComponents, result.getComponentMarkMapping());
				result.setComponentMarks("Coursework", courseWorkMarks);
			}
			
			ArrayList<Assessment> allAssessments = course.getAssessments();
			int overallMarks = ComputeGrades.calculateWeightedMarks(allAssessments, result.getComponentMarkMapping());
			result.setOverallMarks(overallMarks);
			
			String overallGrade = ComputeGrades.calculateFinalGrade(overallMarks);
			result.setOverallGrade(overallGrade);
		}
		
		selectedStudent.printTranscript();
	}
}
