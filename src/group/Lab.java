package group;

/**
 * Subclass of a lesson group, holds the context of a Lab group
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public class Lab extends Group {
	/**
	 * Creates a new lab group with the specified class size
	 * @param size	The class size for this lab group
	 */
	public Lab(int size) {
		super(size);
		this.type = GroupType.LAB.toString();
	}
}
