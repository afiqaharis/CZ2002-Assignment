package course;
import java.util.ArrayList;
import java.util.Set;

import group.Group;
import group.Lab;
import group.Lecture;
import group.Tutorial;
import mark.Mark;
import professor.Professor;
import scrame.Utility;
import student.Student;

import java.util.HashSet;
import java.util.HashMap;

public class Course {
	private static final int DEFAULT_GROUP_SIZE = 10;
	private static final int DEFAULT_LECTURE_SIZE = 50;
	private String code;
	private String name;
	
	private int type;
	/* Course Types
	 * 1 : Lecture only
	 * 2 : Lecture and Tutorial
	 * 3 : Lecture, Tutorial and Lab
	 */
	private int numGroups;
	private Professor courseCoordinator;

	private ArrayList<Group> lectureGroups;
	private ArrayList<Group> tutorialGroups;
	private ArrayList<Group> labGroups;
	private ArrayList<Group> allGroups = new ArrayList<Group>();
	
	private ArrayList<Assessment> assessments = new ArrayList<Assessment>();
	
	public Course(String code, String name, int type, Professor courseCoordinator, int numGroups) {
		this.code = code;
		this.name = name;
		this.type = type;
		this.numGroups = numGroups;
		this.courseCoordinator = courseCoordinator;
		this.lectureGroups = new ArrayList<Group>();
		
		if (numGroups == 0) this.lectureGroups.add(new Lecture(DEFAULT_LECTURE_SIZE));
		else this.lectureGroups.add(new Lecture(numGroups * DEFAULT_GROUP_SIZE));
		
		if (this.type >= 2) {
			this.tutorialGroups = new ArrayList<Group>();
			for (int i = 0; i < numGroups; i++) this.tutorialGroups.add(new Tutorial(DEFAULT_GROUP_SIZE));
		}
		
		if (this.type >= 3) {
			this.labGroups = new ArrayList<Group>();
			for (int i = 0; i < numGroups; i++) this.labGroups.add(new Lab(DEFAULT_GROUP_SIZE));
		}
		
		 this.assessments.add(new Assessment("Exam", 60));
		 this.assessments.add(new Assessment("Coursework", 40));
		
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
	
	public int getNumGroups() {
		return this.numGroups;
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
		
		if (this.type == 1 ) {
			// Immediately register student to course's lecture
			Group lectureGroup = this.lectureGroups.get(0);
			if (lectureGroup.checkVacancy()) {
				if (!lectureGroup.checkStudentExists(student)) {
					lectureGroup.registerStudent(student);
				} else {
					Utility.printErrorMessage("Student already exists within the course.");
					return;
				}
			} else {
				Utility.printErrorMessage("Course does not have any slots left!");
				return;
			}
		} else {
			// Allow student to select which group to register in, excluding lecture
			allGroups.clear();
			if (this.type >= 2) allGroups.addAll(tutorialGroups);
			if (this.type >= 3) allGroups.addAll(labGroups);
			
			System.out.println("====================================================");
			System.out.printf("| Available groups under %-6s                    |\n", this.code);
			System.out.println("====================================================");
			System.out.println("|    | Type     | ID    | Slots Left               |");
			System.out.println("====================================================");
			
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
			
			String question = String.format("Select which group to register %s to (1 ~ %d): ", student.getName(), allGroups.size());
			int option = Utility.readIntOption(question);
			Group selectedGroup = allGroups.get(option - 1);
			
			// Check if student already registered to another group of the same type
			for (Group group:allGroups) {
				if (group.checkStudentExists(student)) {
					if ((group.getType().equals(selectedGroup.getType())) && 
						(group.getGroupId() != selectedGroup.getGroupId())) {
							String errorMsg = String.format("Student can only be registered to one %s group", selectedGroup.getType());
							Utility.printErrorMessage(errorMsg);
							return;
					}
				}
			}
			
			if (selectedGroup.checkVacancy()) {
				if (!selectedGroup.checkStudentExists(student)) {
					selectedGroup.registerStudent(student);
				} else {
					Utility.printErrorMessage("Student already exists within the group.");
					return;
				}
			} else {
				Utility.printErrorMessage("Group does not have any slots left!");
				return;
			}
		}
		
		Group lectureGroup = this.lectureGroups.get(0);
		if (!lectureGroup.checkStudentExists(student) && lectureGroup.checkVacancy()) {
			Utility.printNoticeMessage("Registering student to the course for the first time, hence adding student to lecture group as well");
			lectureGroup.registerStudent(student);
		}
		
		for (Mark result:student.getResults()) {
			if (this.code.equals(result.getCourseCode())) {
				found = 1;
				break;
			}
		}
		
		if (found == 0) {
			Mark newCourseMark = new Mark(this.getCode(), this.getName());
			ArrayList<Assessment> courseAssessments = this.getAssessments();
			
			for (Assessment assessment:courseAssessments) {
				newCourseMark.setComponentMarks(assessment.getType(), 0);
				if (assessment.getSubComponents().size() > 0) {
					for (Assessment subAssessment:assessment.getSubComponents()) {
						newCourseMark.setComponentMarks(subAssessment.getType(), 0);
					}
				}
			}
			student.addResult(newCourseMark);
		}
	}
	
	public void updateAssessmentWeightage() {
		ArrayList<Integer> allWeightages = new ArrayList<Integer>();
		int newWeightage, sumOfWeightages;
		boolean weightageValidated = false;
		this.printWeightages();
		
		do {
			allWeightages.clear();
			sumOfWeightages = 0;
			for (Assessment assessment:assessments) {
				String question = String.format("Enter the new weightage for the %s assessment: ", assessment.getType());
				newWeightage = Utility.readIntOption(question);
				allWeightages.add(newWeightage);
			}
			
			for (Integer weightage:allWeightages) {
				sumOfWeightages += weightage;
			}
			
			if (sumOfWeightages != 100) {
				Utility.printErrorMessage("Sum of all weightages has to add to 100");
			} else {
				for (Assessment assessment:assessments) {
					int index = assessments.indexOf(assessment);
					assessment.setWeightage(allWeightages.get(index));
				}
				String successMsg = String.format("Assessment Weightages for %s have been updated!", this.code);
				Utility.printSuccessMessage(successMsg);
				weightageValidated = true;
			}
		} while(!weightageValidated);
		
		return;
	}
	
	public void addSubComponent() {
		ArrayList<String> allNames = new ArrayList<String>();
		ArrayList<Integer> allWeightages = new ArrayList<Integer>();
		Assessment courseWork = this.assessments.get(1);
		int numOfSubComponents, newWeightage, sumOfWeightages = 0;
		String newName;
		
		System.out.println();
		String question1 = String.format("How many sub components would you like to add?: ");
		numOfSubComponents = Utility.readIntOption(question1);
		
		for (int i = 0; i < numOfSubComponents; i++) {
			String question = String.format("Enter name of Sub Component %d: ", i + 1);
			newName = Utility.readStringInput(question);
			allNames.add(newName);
			String question2 = String.format("Enter weightage of Sub Component %d: ", i + 1);
			newWeightage = Utility.readIntOption(question2);
			allWeightages.add(newWeightage);
		}
		
		for (Integer weightage:allWeightages) {
			sumOfWeightages += weightage;
		}
		
		if (sumOfWeightages != 100) {
			Utility.printErrorMessage("Sum of all weightages has to add to 100");
		} else {
			for (String name:allNames) {
				int index = allNames.indexOf(name);
				Assessment newComponent = new Assessment(name, allWeightages.get(index));
				courseWork.addSubComponent(newComponent);
				
			}
			String successMsg = String.format("New Components have been added to the Coursework of %s!", this.code);
			Utility.printSuccessMessage(successMsg);
		}
		return;
	}
	
	public void printStudents() {
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
		String question = String.format("Select which group to view the student list from (1 ~ %d): ", allGroups.size());
		int option = Utility.readIntOption(question);
		
		allGroups.get(option - 1).printStudents();
	}
	
	public void printAvailability() {
		System.out.println("====================================================");
		System.out.printf("| Groups under %-6s                              |\n", this.code);
		System.out.printf("| Coordinated By: %-10s                       |\n", this.courseCoordinator.getName());
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
	
	public void printStatistics() {
		allGroups.clear();
		if (this.type >= 1) allGroups.addAll(lectureGroups);
		if (this.type >= 2) allGroups.addAll(tutorialGroups);
		if (this.type >= 3) allGroups.addAll(labGroups);
		
		Set<Student> allStudents = new HashSet<Student>();
		
		for (Group group:allGroups) allStudents.addAll(group.getStudents());
		
		HashMap<String, Double> courseOverall = ComputeGrades.calculateCourseAverage(this, allStudents);
		
		System.out.println("=====================================================");
		System.out.printf("| Statistics For Course: %-27s|\n", this.code);
		System.out.println("=====================================================");
		System.out.printf("| Course Coordinator         : %-21s|\n", this.courseCoordinator.getName());
		System.out.printf("| Total Number of Students   : %-21d|\n", allStudents.size());
		System.out.printf("| Total Number Passes        : %-21d|\n", (int) Math.round(courseOverall.get("numPasses")));
		System.out.printf("| Total Number Failures      : %-21d|\n", (int) Math.round(courseOverall.get("numFailures")));
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
