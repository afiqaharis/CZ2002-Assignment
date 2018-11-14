package group;

/**
 * Enumeration for Group Types
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public enum GroupType {
	LECTURE("Lecture"), TUTORIAL("Tutorial"), LAB("Lab");
	
	/**
	 * Name of the group type
	 */
	private String value;
	
	/**
	 * Creates a group type with the given name
	 * @param value	Name of the group type
	 */
	GroupType(final String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
        return value;
    }
}	
