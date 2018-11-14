package student;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

import mark.Mark;
import scrame.*;

public class Student {
	private String name;
	private String ic;
	private String matricNumber;
	private String email;
	// Array of course results, each item having its own component to mark mapping
	private ArrayList<Mark> results = new ArrayList<Mark>(); 
	
	private String generateMatricNumber() {
		Calendar dateNow = Calendar.getInstance();
		Random random = new Random();
		
		String frontNum = String.valueOf(dateNow.get(Calendar.YEAR)).substring(2);
		int endNum = random.nextInt(99999);
		char endAlphabet = (char)(random.nextInt(26) + 'A');
		
		return String.format("U%s%d%s", frontNum, endNum, endAlphabet);
	}
	
	// Constructor for new student
	public Student(String name, String ic, String email) {
		this.name = name;
		this.ic = ic;
		this.matricNumber = generateMatricNumber();
		this.email = email;
	}
	
	// Constructor for existing student
	public Student(String name, String ic, String matricNumber, String email) {
		this.name = name;
		this.ic = ic;
		this.matricNumber = matricNumber;
		this.email = email;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getMatricNumber() {
		return this.matricNumber;
	}
	
	public String getIc() {
		return this.ic;
	}
	
	public void addResult(Mark newCourseMark) {
		results.add(newCourseMark);
	}
	
	public ArrayList<Mark> getResults() {
		return this.results;
	}
	
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
