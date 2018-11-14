package group;

/**
 * Subclass of a lesson group, holds the context of a Tutorial group
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public class Tutorial extends Group {
	/**
	 * Creates a new tutorial group with the specified class size
	 * @param size	The class size for this tutorial group
	 */
	public Tutorial(int size) {
		super(size);
		this.type = GroupType.TUTORIAL.toString();
	}
}
