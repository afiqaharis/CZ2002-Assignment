package course;
import java.util.ArrayList;

/**
 * Represents an assessment for a course
 * One or many assessments can be part of a course
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public class Assessment {
	
	/**
	 * Weightage of this assessment in regards to the overall marks
	 */
	private int weightage;
	
	/**
	 * Type of this assessment
	 */
	private String type;
	
	/**
	 * List of subcomponents to this assessment if applicable
	 */
	private ArrayList<Assessment> subComponents = new ArrayList<Assessment>();
	
	/**
	 * Creates a new assessment of the specified type and weightage
	 * @param type			Type of assessment
	 * @param weightage		Weightage of the assessment in regards to the overall marks
	 */
	public Assessment(String type, int weightage) {
		this.type = type;
		this.weightage = weightage;
	}
	
	/**
	 * Get the type of this assessment
	 * @return	This assessment's type
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Get the weightage of this assessment
	 * @return	This assessment's weightage
	 */
	public int getWeightage() {
		return this.weightage;
	}
	
	/**
	 * Set the weightage of this assessment using the specified weightage
	 * @param weightage		Weightage of this assessment to be updated with
	 */
	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}
	
	/**
	 * Get the subcomponents of this assessment
	 * @return		The list of subcomponents of this assessment
	 */
	public ArrayList<Assessment> getSubComponents() {
		return this.subComponents;
	}
	
	/**
	 * Add a new subcomponent to this assessment
	 * @param newComponent		The subcomponent to be added to the assessment
	 */
	public void addSubComponent(Assessment newComponent) {
		if (!this.type.equals("Coursework")) {
			System.out.println("Internal Error: Only can add sub components to Coursework Assessment");
		} else {
			subComponents.add(newComponent);
		}
	}

}
