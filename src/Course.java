import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.HashMap;

public class Course {
	private String code;
	private String name;
	private int type;
	private Professor courseCoordinator;

	private ArrayList<Group> lectureGroups;
	private ArrayList<Group> tutorialGroups;
	private ArrayList<Group> labGroups;
	private ArrayList<Group> allGroups = new ArrayList<Group>();
	
	private ArrayList<Assessment> assessments = new ArrayList<Assessment>();

	Scanner sc = new Scanner(System.in);
	
	public Course(String code, String name, int type, Professor courseCoordinator) {
		this.code = code;
		this.name = name;
		this.type = type;
		this.courseCoordinator = courseCoordinator;
		
		if (this.type >= 1) {
			lectureGroups = new ArrayList<Group>();
			lectureGroups.add(new Group("Lecture", 50));
		}
		if (this.type >= 2) {
			tutorialGroups = new ArrayList<Group>();
			tutorialGroups.add(new Group("Tutorial", 10));
			tutorialGroups.add(new Group("Tutorial", 10));
		}
		if (this.type >= 3) {
			labGroups = new ArrayList<Group>();
			labGroups.add(new Group("Lab", 10));
			labGroups.add(new Group("Lab", 10));
		}
		
		 assessments.add(new Assessment("Exam", 50));
		 assessments.add(new Assessment("Coursework", 50));
		
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getType() {
		return this.type;
	}
	
	public Professor getCourseCoordinator() {
		return this.courseCoordinator;
	}
	
	public ArrayList<Group> getLectureGroups() {
		return this.lectureGroups;
	}
	
	public ArrayList<Group> getTutorialGroups() {
		return this.tutorialGroups;
	}
	
	public ArrayList<Group> getLabGroups() {
		return this.labGroups;
	}
	
	public ArrayList<Assessment> getAssessments() {
		return this.assessments;
	}
	
	public void registerStudent(Student student) {
		int found = 0;
		System.out.println();
		System.out.println("====================================================");
		System.out.printf("| Available groups under %-6s                    |\n", this.code);
		System.out.println("====================================================");
		System.out.println("|    | Type     | ID    | Slots Left               |");
		System.out.println("====================================================");
		
		allGroups.clear();
		if (this.type >= 1) allGroups.addAll(lectureGroups);
		if (this.type >= 2) allGroups.addAll(tutorialGroups);
		if (this.type >= 3) allGroups.addAll(labGroups);
		
		for (Group group:allGroups) {
			int groupSize = group.getSize();
			int numOfStudents = group.getStudents().size();
			String slotsLeft = "" + (groupSize - numOfStudents) + " / " + groupSize;
			int index = allGroups.indexOf(group);
			System.out.printf("| %-3d| %-9s| %-6d| %-25s|\n", index + 1, group.getType(),
					group.getGroupId(), slotsLeft);
		}
		System.out.println("====================================================");
		System.out.println();
		System.out.printf("Select which group to register %s to: (1 ~ %d)\n", student.getName(), allGroups.size());
		int option = sc.nextInt();
		sc.nextLine();
		
		Group selectedGroup = allGroups.get(option - 1);
		if (selectedGroup.checkVacancy() && selectedGroup.checkStudentExists(student)) {
			selectedGroup.registerStudent(student);
		}
		
		for (Mark result:student.getResults()) {
			if (this.code.equals(result.getCourseCode())) {
				found = 1;
				break;
			}
		}
		
		if (found == 0) {
			student.addResult(this);
		}
	}
	
	public void updateAssessmentWeightage() {
		ArrayList<Integer> allWeightages = new ArrayList<Integer>();
		int newWeightage, sumOfWeightages = 0;
		this.printWeightages();
		
		for (Assessment assessment:assessments) {
			System.out.printf("Enter the new weightage for the %s assessment:\n", assessment.getType());
			newWeightage = sc.nextInt();
			sc.nextLine();
			allWeightages.add(newWeightage);
		}
		
		for (Integer weightage:allWeightages) {
			sumOfWeightages += weightage;
		}
		
		if (sumOfWeightages != 100) {
			System.out.println();
			System.out.println("Error: Sum of all all weightages has to add to 100");
			System.out.println();
		} else {
			for (Assessment assessment:assessments) {
				int index = assessments.indexOf(assessment);
				assessment.setWeightage(allWeightages.get(index));
			}
			System.out.println();
			System.out.printf("Assessment Weightages for %s have been updated!\n", this.code);
			System.out.println();
		}
		return;
	}
	
	public void addSubComponent() {
		ArrayList<String> allNames = new ArrayList<String>();
		ArrayList<Integer> allWeightages = new ArrayList<Integer>();
		Assessment courseWork = this.assessments.get(1);
		int numOfSubComponents, newWeightage, sumOfWeightages = 0;
		String newName;
		
		System.out.println();
		System.out.println("How many sub components would you like to add?");
		numOfSubComponents = sc.nextInt();
		sc.nextLine();
		
		for (int i = 0; i < numOfSubComponents; i++) {
			System.out.printf("Enter name of Sub Component %d:\n", i + 1);
			newName = sc.nextLine();
			allNames.add(newName);
			System.out.printf("Enter weightage of Sub Component %d:\n", i + 1);
			newWeightage = sc.nextInt();
			sc.nextLine();
			allWeightages.add(newWeightage);
		}
		
		for (Integer weightage:allWeightages) {
			sumOfWeightages += weightage;
		}
		
		if (sumOfWeightages != 100) {
			System.out.println();
			System.out.println("Error: Sum of all all weightages has to add to 100");
			System.out.println();
		} else {
			for (String name:allNames) {
				int index = allNames.indexOf(name);
				Assessment newComponent = new Assessment(name, allWeightages.get(index));
				courseWork.addSubComponent(newComponent);
				
			}
			System.out.println();
			System.out.printf("New Components have been added to the Coursework of %s !\n", this.code);
			System.out.println();
		}
		return;
	}
	
	public void printStudents() {
		System.out.println();
		System.out.println("=============================================");
		System.out.printf("| Sessions from  %-6s                     |\n", this.code);
		System.out.println("=============================================");
		System.out.println("|    | Type     | ID                        |");
		System.out.println("=============================================");
		
		allGroups.clear();
		if (this.type >= 1) allGroups.addAll(lectureGroups);
		if (this.type >= 2) allGroups.addAll(tutorialGroups);
		if (this.type >= 3) allGroups.addAll(labGroups);
		
		for (Group group:allGroups) {
			int index = allGroups.indexOf(group);
			System.out.printf("| %-3d| %-9s| %-26d|\n", index + 1, group.getType(), group.getGroupId());
		}
		
		System.out.println("=============================================");
		System.out.println();
		System.out.printf("Select which group to view the student list from: (1 ~ %d)\n", allGroups.size());
		int option = sc.nextInt();
		sc.nextLine();
		
		allGroups.get(option - 1).printStudents();
	}
	
	public void printAvailability() {
		System.out.println();
		System.out.println("====================================================");
		System.out.printf("| Groups under %-6s                              |\n", this.code);
		System.out.println("====================================================");
		System.out.println("|    | Type     | ID    | Vacancy                  |");
		System.out.println("====================================================");
		allGroups.clear();
		if (this.type >= 1) allGroups.addAll(lectureGroups);
		if (this.type >= 2) allGroups.addAll(tutorialGroups);
		if (this.type >= 3) allGroups.addAll(labGroups);
		
		for (Group group:allGroups) {
			int groupSize = group.getSize();
			int numOfStudents = group.getStudents().size();
			String slotsLeft = "" + (groupSize - numOfStudents) + " / " + groupSize;
			int index = allGroups.indexOf(group);
			System.out.printf("| %-3d| %-9s| %-6d| %-25s|\n", index + 1, group.getType(),
					group.getGroupId(), slotsLeft);
		}
		System.out.println("====================================================");
		System.out.println();
	}
	
	public void printWeightages() {		
		System.out.println();
		System.out.println("=====================================================");
		System.out.printf("| Assessment Weightage for %-6s                   |\n", this.code);
		System.out.println("=====================================================");
		System.out.println("|    | Type                     | Current Weightage |");
		System.out.println("=====================================================");
		for (Assessment assessment:assessments) {
			int index = assessments.indexOf(assessment);
			System.out.printf("| %-3d| %-25s| %-18d|\n", index + 1, assessment.getType(), assessment.getWeightage());
			if (assessment.getSubComponents().size() > 0) {
				for (Assessment subAssessment:assessment.getSubComponents()) {
					System.out.printf("| %-3s| %-25s| %-18d|\n", "", " - " + subAssessment.getType(), subAssessment.getWeightage());
				}
			}
		}
		System.out.println("=====================================================");
		System.out.println();
	}
	
	public void printStatistics(School school) {
		allGroups.clear();
		if (this.type >= 1) allGroups.addAll(lectureGroups);
		if (this.type >= 2) allGroups.addAll(tutorialGroups);
		if (this.type >= 3) allGroups.addAll(labGroups);
		
		Set<Student> allStudents = new HashSet<Student>();
		
		for (Group group:allGroups) allStudents.addAll(group.getStudents());
		
		HashMap<String, Double> courseOverall = ComputeGrades.calculateCourseAverage(school, allStudents);
		
		System.out.println();
		System.out.println("=====================================================");
		System.out.printf("| Statistics For Course: %-27s|\n", this.code);
		System.out.println("=====================================================");
		System.out.printf("| Course Coordinator         : %-21s|\n", this.courseCoordinator.getName());
		System.out.printf("| Total Number of Students   : %-21d|\n", allStudents.size());
		System.out.println("|                                                   |");
		System.out.printf("| Average Overall Marks      : %-21.1f|\n", courseOverall.get("avgOverall"));
		System.out.printf("| Average Overall GPA        : %-21.1f|\n", courseOverall.get("avgGPAOverall"));
		System.out.println("|                                                   |");
		System.out.printf("| Average Exam Marks         : %-21.1f|\n", courseOverall.get("avgExam"));
		System.out.printf("| Average Exam GPA           : %-21.1f|\n", courseOverall.get("avgGPAExam"));
		System.out.println("|                                                   |");
		System.out.printf("| Average Coursework Marks   : %-21.1f|\n", courseOverall.get("avgCoursework"));
		System.out.printf("| Average Coursework GPA     : %-21.1f|\n", courseOverall.get("avgGPACoursework"));
		System.out.println("=====================================================");
		System.out.println();
	}
}
