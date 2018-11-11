import java.util.HashMap;

public class Mark {
	private String courseCode;
	private String courseName;
	private HashMap<String, Integer> componentMarkMap = new HashMap<String, Integer>();
	
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
	
	public void setComponentMarks(String componentType, int marks) {
		componentMarkMap.put(componentType, marks);
	}
}
