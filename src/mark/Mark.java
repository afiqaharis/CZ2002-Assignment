package mark;
import java.util.HashMap;

/**
 * Represents the result of a course by collective marks of the 
 * course's assessment components. Each component's marks is 
 * stored in a hashmap that maps the assessment type to its marks
 * Marks belong to students, such that students will have as many marks
 * as the number of courses he or she is registered to.
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public class Mark {
	
	/**
	 * Course code of this mark
	 */
	private String courseCode;
	
	/**
	 * Course name of this mark
	 */
	private String courseName;
	
	/*
	 * Overall grade of the course result
	 */
	private String overallGrade;
	
	/**
	 * Overall marks of the course result
	 */
	private int overallMarks = 0;
	
	/**
	 * Map of each assessment component to its corresponding marks
	 */
	private HashMap<String, Integer> componentMarkMap = new HashMap<String, Integer>();
	
	/**
	 * Creates a new mark with the specified course code and course name
	 * @param courseCode	Course code of this mark
	 * @param courseName	Course name of this mark
	 */
	public Mark(String courseCode, String courseName) {
		this.courseCode = courseCode;
		this.courseName = courseName;
	}
	
	/**
	 * Get the course code of this mark
	 * @return		The course code of this mark
	 */
	public String getCourseCode() {
		return this.courseCode;
	}
	
	/**
	 * Get the course name of this mark
	 * @return		The course name of this mark
	 */
	public String getCourseName() {
		return this.courseName;
	}
	
	/**
	 * Get the overall grade of this course result
	 * @return		The overall grade of this course result
	 */
	public String getOverallGrade() {
		return this.overallGrade;
	}
	
	/**
	 * Get the overall marks of this course result
	 * @return		The overall marks of this course result
	 */
	public int getOverallMarks() {
		return this.overallMarks;
	}
	
	/**
	 * Set the overall marks of this course result
	 * @param marks		The overall marks of this course result
	 */
	public void setOverallMarks(int marks) {
		this.overallMarks = marks;
	}
	
	/**
	 * Set the overall grade of this course result
	 * @param grade		The overall grade of this course result
	 */
	public void setOverallGrade(String grade) {
		this.overallGrade = grade;
	}
	
	/**
	 * Get the map of the assessment components to their corresponding marks 
	 * @return		The map of the assessment components to their corresponding marks
	 */
	public HashMap<String, Integer> getComponentMarkMapping() {
		return this.componentMarkMap;
	}
	
	/**
	 * Set the map of the assessment components to their corresponding marks
	 * @param componentType		The type of assessment component
	 * @param marks				The marks of the assessment component which the student scored
	 */
	public void setComponentMarks(String componentType, int marks) {
		componentMarkMap.put(componentType, marks);
	}

}
