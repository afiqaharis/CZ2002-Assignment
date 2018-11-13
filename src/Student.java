import java.util.Calendar;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*UML:
unidirectional association from Student to Mark
Dependency from Student to:
1. Course (public void addResult(Course course) & public void printTranscript(School school))
2. Assessment (public void addResult(Course course) & public void printTranscript(School school))
3. School (public void printTranscript(School school))
4. ComputeGrades (public void printTranscript(School school))
*/
public class Student {
	Scanner sc = new Scanner(System.in);
	/*Attributes of Student class*/
	//Name of student
	private String name;
	//Matric Number of student
	private String matricNumber;
	//Email of student
	private String email;
	//Hold the results of student for a given course name and course code
	//Used to add results of student, get results of student and print transcript of student
	private ArrayList<Mark> results = new ArrayList<Mark>();

	/*Methods of Student class*/
	//To generate random Matric Number for student, if not given.
	private String generateMatricNumber() {
		Calendar dateNow = Calendar.getInstance();
		Random random = new Random();

		String frontNum = String.valueOf(dateNow.get(Calendar.YEAR)).substring(2);
		int endNum = random.nextInt(99999);
		char endAlphabet = (char)(random.nextInt(26) + 'A');

		return String.format("U%s%d%s", frontNum, endNum, endAlphabet);
	}

	// Constructor for new student
	public Student(String name, String email) {
		this.name = name;
		this.matricNumber = generateMatricNumber();
		this.email = email;
	}

	// Constructor for existing student
	public Student(String name, String matricNumber, String email) {
		this.name = name;
		this.matricNumber = matricNumber;
		this.email = email;
	}
	/*Getters*/
	//Begin
	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getMatricNumber() {
		return this.matricNumber;
	}
	//END

	//Student class uses addResult to add Result of Student for a given course
	//Dependency on Course (Passed in as method parameter), Mark and Assessment (Variable type within method)
	public void addResult(Course course) {
		Mark newCourseMark = new Mark(course.getCode(), course.getName());//newCourseMark is used to hold the result of code and name for the given course
		ArrayList<Assessment> courseAssessments = course.getAssessments();//Retrieve the assessment instances of the given course that are stored in an ArrayList
		//If type = "Coursework", there will be another arrayList for subComponents of assessment elements
		for (Assessment assessment:courseAssessments) {//Iterate through every assessment instance (type, weightage) of the given course
			newCourseMark.setComponentMarks(assessment.getType(), 0);//Initialise the mark for the assessment type to 0
			if (assessment.getSubComponents().size() > 0) {//Check if the assessment type has non-empty subComponent ArrayList!
				for (Assessment subAssessment:assessment.getSubComponents()) { //Iterate through the subComponent ArrayList
					newCourseMark.setComponentMarks(subAssessment.getType(), 0); //Initialise mark of each subComponent of the assessment type to 0
				}
			}
		}
		results.add(newCourseMark);//Update student's results record where for a given course instance, all of its assessment (and sub-components of "CourseWork") is initalised to 0.
	}

	public ArrayList<Mark> getResults() {
		return this.results;
	}

	//Student.printTranscript depends on School(method parameter type)
	//and then Course (local method variable), Assessment (local method variable [in a list]) and ComputeGrades(use it's static method)
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

			for (Mark result:results) {//Iterate through each result instance of the student
				curCourseCode = result.getCourseCode();
				curCourseName = result.getCourseName();
				curCourseAssessmentType = result.getAssessments();

				Course course = school.getCourse(curCourseCode);//Retrieve course instance from given school using the courseCode of the result instance
				ArrayList<Assessment> courseworkSubComponents = course.curCourseAssessmentType.get(1).getSubComponents();//Retrieve subComonents list from retrieved course instance
				//What is get(1) for?

				if (courseworkSubComponents.size() > 0) {//Course has subComponents
					int courseWorkMarks = ComputeGrades.calculateWeightedMarks(
							courseworkSubComponents, result.getComponentMarkMapping());//ComputeGrades will use calculateWeightedMarks for the total courseWorkmarks
					result.setComponentMarks("Coursework", courseWorkMarks);//Now student will have the courseWorkMarks
				}

				ArrayList<Assessment> allAssessments = curCourseAssessmentType;
				int overallMarks = ComputeGrades.calculateWeightedMarks(allAssessments, result.getComponentMarkMapping());
				String overallGrade = ComputeGrades.calculateFinalGrade(overallMarks);

				System.out.println("|---------------------------------------------------------|");
				System.out.printf("| Course Code  :  %-40s|\n",curCourseCode);
				System.out.printf("| Course Name  :  %-40s|\n", curCourseName);
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
