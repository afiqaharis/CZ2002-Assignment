package course;

import java.util.ArrayList;

import professor.*;

public class CourseSystem {
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	public CourseSystem(ArrayList<String[]> data, ArrayList<Professor> allProfs) {
		parseData(data, allProfs);
	}
	
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
	
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
}
