import java.util.Calendar;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

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
	
	public void addResult(Course course) {
		Mark newCourseMark = new Mark(course.getCode(), course.getName());
		ArrayList<Assessment> courseAssessments = course.getAssessments();
		for (Assessment assessment:courseAssessments) {
			newCourseMark.setComponentMarks(assessment.getType(), 0);
			if (assessment.getSubComponents().size() > 0) {
				for (Assessment subAssessment:assessment.getSubComponents()) {
					newCourseMark.setComponentMarks(subAssessment.getType(), 0);
				}
			}
		}
		results.add(newCourseMark);
	}
	
	public ArrayList<Mark> getResults() {
		return this.results;
	}
	
	public void printTranscript(School school) {
		if (results.isEmpty()) {
			System.out.println();
			System.out.println("Error: Student has not been registered to any course.");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("===========================================================");
			System.out.println("| Student Transcript                                      |");
			System.out.println("===========================================================");
			System.out.printf("| Student Name  : %-40s|\n", this.name);
			System.out.printf("| Matric Number : %-40s|\n", this.matricNumber);
			System.out.printf("| Email Address : %-40s|\n", this.email);
			
			for (Mark result:results) {
				
				Course course = school.getCourse(result.getCourseCode());
				ArrayList<Assessment> courseworkSubComponents = course.getAssessments().get(1).getSubComponents();
				
				if (courseworkSubComponents.size() > 0) {
					int courseWorkMarks = ComputeGrades.calculateWeightedMarks(
							courseworkSubComponents, result.getComponentMarkMapping());
					result.setComponentMarks("Coursework", courseWorkMarks);
				}
				
				ArrayList<Assessment> allAssessments = course.getAssessments();
				int overallMarks = ComputeGrades.calculateWeightedMarks(allAssessments, result.getComponentMarkMapping());
				String overallGrade = ComputeGrades.calculateFinalGrade(overallMarks);
				
				System.out.println("|---------------------------------------------------------|");
				System.out.printf("| Course Code  :  %-40s|\n", result.getCourseCode());
				System.out.printf("| Course Name  :  %-40s|\n", result.getCourseName());
				System.out.printf("| Overall Mark :  %-40s|\n", overallMarks + "/100 (" + overallGrade + ")");
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
