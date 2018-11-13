import java.util.HashMap;

public class Mark {
	//Serve as an unit of storage for Student's result(componentType and marks) in a given courseCode and courseName
	/*Attributes of Mark Class*/
	private String courseCode;
	private String courseName;
	//Integer is used to represent the marks for the key string that represents componentType (Coursework, Exam etc)
	private HashMap<String, Integer> componentMarkMap = new HashMap<String, Integer>();

	/*Methods of Mark Class*/
	public Mark(String courseCode, String courseName) {
		this.courseCode = courseCode;
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return this.courseCode;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public HashMap<String, Integer> getComponentMarkMapping() {
		return this.componentMarkMap;
	}

	//Used in Student.addResult and Student.printTranscript
	public void setComponentMarks(String componentType, int marks) {
		componentMarkMap.put(componentType, marks);
	}
}
