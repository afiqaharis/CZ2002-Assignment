package group;

/**
 * Subclass of a lesson group, holds the context of a Lecture group
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public class Lecture extends Group {
	/**
	 * Creates a new lecture group with the specified class size
	 * @param size	The class size for this lecture group
	 */
	public Lecture(int size) {
		super(size);
		this.type = GroupType.LECTURE.toString();
	}
}
