package student;

import java.util.ArrayList;

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
}
