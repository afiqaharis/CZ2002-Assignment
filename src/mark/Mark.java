package mark;
import java.util.HashMap;

public class Mark {
	private String courseCode;
	private String courseName;
	private String overallGrade;
	private int overallMarks = 0;
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
	
	public String getOverallGrade() {
		return this.overallGrade;
	}
	
	public int getOverallMarks() {
		return this.overallMarks;
	}
	
	public void setOverallMarks(int marks) {
		this.overallMarks = marks;
	}
	
	public void setOverallGrade(String grade) {
		this.overallGrade = grade;
	}
	
	public HashMap<String, Integer> getComponentMarkMapping() {
		return this.componentMarkMap;
	}
	
	public void setComponentMarks(String componentType, int marks) {
		componentMarkMap.put(componentType, marks);
	}

}
