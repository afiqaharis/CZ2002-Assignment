package student;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

import mark.Mark;
import scrame.*;

/**
 * Represents a student enrolled in the school.
 * Can be registered to many courses
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public class Student {
	
	/**
	 * Name of this student
	 */
	private String name;
	
	/**
	 * NRIC of this student
	 */
	private String ic;
	
	/**
	 * Matriculation number of this student
	 */
	private String matricNumber;
	
	/**
	 * Email of this student
	 */
	private String email;
	
	/**
	 * List of course results for this student, with each item in the list
	 * comprising of HashMaps which map each component to its own mark
	 */
	private ArrayList<Mark> results = new ArrayList<Mark>(); 
	
	/**
	 * Generates a matriculation number for newly added students
	 * @return	A randomly generated matriculation number for this student
	 */
	private String generateMatricNumber() {
		Calendar dateNow = Calendar.getInstance();
		Random random = new Random();
		
		String frontNum = String.valueOf(dateNow.get(Calendar.YEAR)).substring(2);
		int endNum = random.nextInt(99999);
		char endAlphabet = (char)(random.nextInt(26) + 'A');
		
		return String.format("U%s%d%s", frontNum, endNum, endAlphabet);
	}
	
	/**
	 * Creates a new student with the specified name, NRIC and email 
	 * @param name		Name of this student
	 * @param ic		NRIC of this student
	 * @param email		Email of this student
	 */
	public Student(String name, String ic, String email) {
		this.name = name;
		this.ic = ic;
		this.matricNumber = generateMatricNumber();
		this.email = email;
	}
	
	/**
	 * Creates an existing student with the specified name, NRIC, matriculation number and email
	 * @param name				Name of this student
	 * @param ic				NRIC of this student
	 * @param matricNumber		Matriculation number of this student
	 * @param email				Email of this student
	 */
	public Student(String name, String ic, String matricNumber, String email) {
		this.name = name;
		this.ic = ic;
		this.matricNumber = matricNumber;
		this.email = email;
	}
	
	/**
	 * Get the name of this student
	 * @return		The name of this student
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get the email of this student
	 * @return		The email of this student
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Get the matriculation number of this student
	 * @return		The matriculation number of this student
	 */
	public String getMatricNumber() {
		return this.matricNumber;
	}
	
	/**
	 * Get the NRIC of this student
	 * @return		The NRIC of this student
	 */
	public String getIc() {
		return this.ic;
	}
	
	/**
	 * Adds a course result to this student's list of results
	 * @param newCourseMark		The new course result to add
	 */
	public void addResult(Mark newCourseMark) {
		results.add(newCourseMark);
	}
	
	/**
	 * Get the course results of this student
	 * @return		The course results of this student
	 */
	public ArrayList<Mark> getResults() {
		return this.results;
	}
	
	/**
	 * Prints the transcript of this student, showing each 
	 * courses' performance as well and the courses' mark breakdown
	 */
	public void printTranscript() {
		if (results.isEmpty()) {
			Utility.printErrorMessage("Student has not been registered to any course.");
		} else {
			System.out.println();
			System.out.println("===========================================================");
			System.out.println("| Student Transcript                                      |");
			System.out.println("===========================================================");
			System.out.printf("| Student Name  : %-40s|\n", this.name);
			System.out.printf("| Matric Number : %-40s|\n", this.matricNumber);
			System.out.printf("| Email Address : %-40s|\n", this.email);
			
			for (Mark result:results) {				
				System.out.println("|---------------------------------------------------------|");
				System.out.printf("| Course Code  :  %-40s|\n", result.getCourseCode());
				System.out.printf("| Course Name  :  %-40s|\n", result.getCourseName());
				System.out.printf("| Overall Mark :  %-40s|\n", result.getOverallMarks() + "/100 (" + result.getOverallGrade() + ")");
				System.out.println("|                                                         |");
				System.out.println("| Marks Breakdown                                         |");
				HashMap<String, Integer> componentMarkMap = result.getComponentMarkMapping();
				componentMarkMap.forEach((k, v) -> {
					System.out.printf("| %-20s:  %-33s|\n", k, v + "/100");
				});
			}
			
			System.out.println("===========================================================");
			System.out.println();
		}	
	}
}
