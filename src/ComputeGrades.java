import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.lang.Math;

public class ComputeGrades {
	public static Integer calculateWeightedMarks(ArrayList<Assessment> assessments, HashMap<String, Integer> componentMarkMap) {
		int totalMarks = 0;
		for (Assessment assessment:assessments) {
			String assessmentType = assessment.getType();
			int assessmentWeightage = assessment.getWeightage();
			int assessmentMarks = componentMarkMap.get(assessmentType);
			double weightedMarks = (double)assessmentWeightage/100.0 * assessmentMarks;
			totalMarks += (int)Math.round(weightedMarks);
		}
		return totalMarks;
	}
	
	public static HashMap<String, Double> calculateCourseAverage(School school, Set<Student> allStudents) {
		HashMap<String, Double> allOverall = new HashMap<String, Double>();
		int numOfStudents = allStudents.size();
		int sumExam = 0, sumCoursework = 0, sumOverall = 0;
		double sumGPAExam = 0, sumGPACoursework = 0, sumGPAOverall = 0;
		
		for (Student student:allStudents) {
			for (Mark result:student.getResults()) {
				
				Course course = school.getCourse(result.getCourseCode());
				ArrayList<Assessment> courseworkSubComponents = course.getAssessments().get(1).getSubComponents();
				if (courseworkSubComponents.size() > 0) {
					int courseWorkMarks = ComputeGrades.calculateWeightedMarks(
							courseworkSubComponents, result.getComponentMarkMapping());
					result.setComponentMarks("Coursework", courseWorkMarks);
				}
				ArrayList<Assessment> allAssessments = course.getAssessments();
				int examMarks = result.getComponentMarkMapping().get("Exam");
				int courseworkMarks = result.getComponentMarkMapping().get("Coursework");
				int overallMarks = calculateWeightedMarks(allAssessments, result.getComponentMarkMapping());
				sumExam += examMarks;
				sumCoursework += courseworkMarks;
				sumOverall += overallMarks;
				
				sumGPAExam += calculateFinalGradePoint(examMarks);
				sumGPACoursework += calculateFinalGradePoint(courseworkMarks);
				sumGPAOverall += calculateFinalGradePoint(overallMarks);
			}
		}
		
		double avgExam = Math.round((double)sumExam / numOfStudents * 10.0)/10.0;
		double avgCoursework = Math.round((double)sumCoursework / numOfStudents * 10.0)/10.0;
		double avgOverall = Math.round((double)sumOverall / numOfStudents * 10.0)/10.0;
		
		double avgGPAExam = Math.round((double)sumGPAExam / numOfStudents * 10.0)/10.0;
		double avgGPACoursework = Math.round((double)sumGPACoursework / numOfStudents * 10.0)/10.0;
		double avgGPAOverall = Math.round((double)sumGPAOverall / numOfStudents * 10.0)/10.0;
		
		allOverall.put("avgExam", avgExam);
		allOverall.put("avgCoursework", avgCoursework);
		allOverall.put("avgOverall", avgOverall);
		
		allOverall.put("avgGPAExam", avgGPAExam);
		allOverall.put("avgGPACoursework", avgGPACoursework);
		allOverall.put("avgGPAOverall", avgGPAOverall);
		
		return allOverall;
	}
	
	public static String calculateFinalGrade(int overallMarks) {
		if (overallMarks >= 100) {
			return "A+";
		} else if (overallMarks >= 93) {
			return "A";
		} else if (overallMarks >= 90) {
			return "A-";
		} else if (overallMarks >= 87) {
			return "B+";
		} else if (overallMarks >= 83) {
			return "B";
		} else if (overallMarks >= 80) {
			return "B-";
		} else if (overallMarks >= 77) {
			return "C+";
		} else if (overallMarks >= 73) {
			return "C";
		} else if (overallMarks >= 67) {
			return "D+";
		} else if (overallMarks >= 63) {
			return "D";
		} else {
			return "F";
		}
	}
	
	public static double calculateFinalGradePoint(int overallMarks) {
		if (overallMarks >= 100) {
			return 5.0;
		} else if (overallMarks >= 93) {
			return 5.0;
		} else if (overallMarks >= 90) {
			return 4.5;
		} else if (overallMarks >= 87) {
			return 4.0;
		} else if (overallMarks >= 83) {
			return 3.5;
		} else if (overallMarks >= 80) {
			return 3.0;
		} else if (overallMarks >= 77) {
			return 2.5;
		} else if (overallMarks >= 73) {
			return 2.0;
		} else if (overallMarks >= 67) {
			return 1.5;
		} else if (overallMarks >= 63) {
			return 1.0;
		} else {
			return 0.0;
		}
	}
}
